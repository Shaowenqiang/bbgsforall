package cn.com.hqep.bidder.service.Impl;

import cn.com.hqep.bidder.common.*;
import cn.com.hqep.bidder.dao.RaceBidderDao;
import cn.com.hqep.bidder.dao.RaceConductDao;
import cn.com.hqep.bidder.dao.technologyAndBusinessDao;
import cn.com.hqep.bidder.model.*;
import cn.com.hqep.bidder.service.RaceBidderService;
import org.apache.ibatis.annotations.Param;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.*;
import java.util.regex.Pattern;

/**
 * Created by sssJL on 2017-10-12.
 * bidderService 实现类
 */
@Repository
public class RaceBidderServiceImpl implements RaceBidderService {
    @Autowired
    private RaceBidderDao dao;

    @Autowired
    RaceConductDao RaceConductDao; //加载RaceConductDao 使用查询方法

    @Autowired
    cn.com.hqep.bidder.dao.technologyAndBusinessDao technologyAndBusinessDao;

    public Map<String, String> saveBid(MultipartFile file) {
        //查询流程进行到的位置
        tblBbgsFlowStateRaceModel model1 = dao.queryMinIdByState();
        if (model1.getId() != null && Integer.valueOf(model1.getId()) <= 501) {
            return jsonMsg.toJosn("error", model1.getAlertMessage());
        }
        String fileName = file.getOriginalFilename();
        try {
            List<Integer> l = new ArrayList<Integer>();
            l.add(1);
            l.add(2);
            l.add(3);
//            l.add(4);
            Map<String, Object> m = ExcelUtil.readExcel(file, l, fileName);
            //判断文件是否正确 正确执行插入数据操作 错误 返回错误信息
            if (m.get("fileflag").equals(false)) {
                Map errorMap = new HashMap();
                errorMap.put("state", "error");
                errorMap.put("msg", m.get("filemsg").toString());
                return errorMap;
            }
            if (m.get("fileflag").equals(true)) {
                Map map = (Map) m.get("filedata");
                Iterator<Map.Entry<String, Object>> it = map.entrySet().iterator();
                while (it.hasNext()) {
                    Map.Entry<String, Object> entry = it.next();
                    //所有sheet页 数据
                    Map sheetMap = (Map) entry.getValue();
                    //sheet页 题头 例如 国网黑龙江省电力有限公司2017年第二批紧急物资集中采购项目投标人统计
                    //判断sheet页数据是否有问题
                    if (sheetMap.get("flag").equals(false)) {
                        Map errorMap = new HashMap();
                        errorMap.put("state", "error");
                        errorMap.put("msg", sheetMap.get("msg").toString());
                        return errorMap;
                    }
                    if (sheetMap.get("flag").equals(true)) {
                        //储存所有 投标人信息
                        List<tblBbgsPackageSupplierRaceModel> UserList = new ArrayList<tblBbgsPackageSupplierRaceModel>();
                        //正确的sheet页数据
                        List<Map> rowList = (List<Map>) sheetMap.get("data");
                        tblBbgsPackageSupplierRaceModel model = null;
                        int index = 2;
                        for (int i = 0; i < rowList.size(); i++) {
                            Map row = rowList.get(i);
                            if (!"供应商名称".equals(row.get("供应商名称"))) {
                                ++index;
                                model = new tblBbgsPackageSupplierRaceModel();
                                String bidAbbreviaion   = row.get("标段简称").toString();
                                String supplier         = row.get("供应商名称").toString();
                                String modOffer         = row.get("最终金额报价（万元）").toString();
                                String finalRate        = row.get("最终折扣比例").toString();
                                for (int j = i + 1; j < rowList.size(); j++) {
                                    Map testRow = rowList.get(j);
                                    if (!testRow.get("供应商名称").equals("供应商名称")) {
                                        if (bidAbbreviaion.equals(testRow.get("标段简称").toString())
                                                && supplier.equals(testRow.get("供应商名称").toString())) {
                                            Map errorMap = new HashMap();
                                            errorMap.put("state", "error");
                                            errorMap.put("msg", "第" + index + "行，该供应商在一个包重复出现！");
                                            return errorMap;
                                        }
                                    }
                                }
                                model.setBidAbbreviaion(bidAbbreviaion);
                                model.setSupplier(supplier);
//                                if ("无".equals(modOffer)) {
//                                    dao.deleteByModel(model);
//                                    continue;
//                                }
//                                if ("无".equals(finalRate)) {
//                                    dao.deleteByModel(model);
//                                    continue;
//                                }
                                //验证报价是否是double类型
                                String regex = "^[-//+]?[0-9]*([\\\\.][0-9]+)?$";
                                Pattern pattern = Pattern.compile(regex);
                                boolean flag = pattern.matcher(modOffer).matches();
                                if (!flag && !"无".equals(modOffer)) {
                                    Map errorMap = new HashMap();
                                    errorMap.put("state", "error");
                                    errorMap.put("msg", "第" + index + "行最终金额报价有误！");
                                    return errorMap;
                                }
                                String strOffer = "";
                                if("无".equals(modOffer)){
                                    strOffer = "";
                                }else{
                                    strOffer = String.format("%.6f", Double.parseDouble(modOffer));
                                }

                                //验证比例是否是百分数
                                String regexRate = "^\\d+\\.?\\d*\\%?$";
                                Pattern patternRate = Pattern.compile(regexRate);
                                boolean flagRate = patternRate.matcher(finalRate).matches();
                                if (!flagRate && !"无".equals(finalRate)) {
                                    Map errorMap = new HashMap();
                                    errorMap.put("state", "error");
                                    errorMap.put("msg", "第" + index + "行最终折扣比例有误！");
                                    return errorMap;
                                }

                                String strRate = "";
                                if("无".equals(finalRate)){
                                    strRate = "";
                                }else{
                                    strRate = finalRate.replace("%","");
                                }

                                model.setModOffer(strOffer);
                                model.setFinalRate(strRate);
                                model.setFlagBit("1");
                                model.setFlagInvalid("1");
                                model.setFlagBidInvalid("1");
                                UserList.add(model);

                            }
                        }
                        for (tblBbgsPackageSupplierRaceModel saveModel : UserList) {
                            dao.updateByBidAbbreviaionAndSupplier(saveModel);
                        }
                    }
                }
            }
            dao.updateStateById("601","1");
            return jsonMsg.toJosn("success","");
        } catch (Exception exp) {
            exp.printStackTrace();
        }
        Map errorMap = new HashMap();
        errorMap.put("state", "error");
        errorMap.put("msg", "请检查上传文件内容！");
        return errorMap;
    }

    public boolean updateById(tblBbgsPackageSupplierRaceModel model) {
        boolean flag = true;
        try {
            dao.updateById(model);
        } catch (Exception exp) {
            exp.printStackTrace();
            flag = false;
        }
        return flag;
    }

    public List<tblBbgsPackageSupplierRaceModel> queryDataList(tblBbgsPackageSupplierRaceModel model) {
        try {
            List<tblBbgsPackageSupplierRaceModel> list = dao.queryList(model);
            return list;
        } catch (Exception exp) {
            exp.printStackTrace();
            return null;
        }

    }

    public List<tblBbgsPackageSupplierRaceModel> queryListByName(tblBbgsPackageSupplierRaceModel model) {
        List<tblBbgsPackageSupplierRaceModel> list = null;
        try {
            list = dao.queryListByName(model);
        } catch (Exception exp) {
            exp.printStackTrace();
            list = null;
        }
        return list;
    }

    public List<tblBbgsPackageSupplierRaceModel> queryDistinctBidList() {
        List<tblBbgsPackageSupplierRaceModel> list = null;
        try {
            list = dao.queryDistinctBidList();
        } catch (Exception exp) {
            exp.printStackTrace();
        }
        return list;
    }


    public List<tblBbgsPackageSupplierRaceModel> queryDistinctPackageList(tblBbgsPackageSupplierRaceModel model) {
        List<tblBbgsPackageSupplierRaceModel> list = null;
        try {
            list = dao.queryDistinctPackageList(model);
        } catch (Exception exp) {
            exp.printStackTrace();
        }
        return list;
    }


    public boolean updateInvalidActionOne(tblBbgsPackageSupplierRaceModel model) {
        boolean flag = true;
        try {
            dao.updateByModel(model);
            tblBbgsBidInformationRaceModel tblDo = new tblBbgsBidInformationRaceModel();
            List<tblBbgsPackageSupplierRaceModel> list = dao.queryListByName(model);
            //准备
            String sign = model.getFlagInvalid();
            String name;
            for (int i = 0; i < list.size(); i++) {
                name = list.get(i).getBidAbbreviaion();
                tblDo.setBidAbbreviaion(name);
                tblDo.setInvalid(sign);
                //标段信息表 中将标段置（0/1）（是否流标）
                //执行
                RaceConductDao.updateBidInfo(tblDo);
            }
        } catch (Exception exp) {
            exp.printStackTrace();
            flag = false;
        }
        return flag;
    }

    public boolean updateByModel(tblBbgsPackageSupplierRaceModel model) {
        boolean flag = true;
        try {
            dao.updateByModel(model);
        } catch (Exception exp) {
            exp.printStackTrace();
            flag = false;
        }
        return flag;
    }

    public List<tblBbgsPackageSupplierRaceModel> queryDistinctBidAbbreviaionList() {
        List<tblBbgsPackageSupplierRaceModel> list = null;
        try {
            list = dao.queryDistinctBidAbbreviaionList();
        } catch (Exception exp) {
            exp.printStackTrace();
        }
        return list;

    }

    /***
     * 导出定标纪要
     * @return 结果信息
     */
    public Map exportForMinutes() {
        Map map = new HashMap();
        map.put("result", "success");
        map.put("msg", "操作成功！");
        try {
            //查询流程进行到的位置
            tblBbgsFlowStateRaceModel model1 = dao.queryMinIdByState();
            if (model1.getId() != null && Integer.valueOf(model1.getId()) <= 801) {
                map.put("result", "fail");
                map.put("msg",model1.getAlertMessage());
                return  map;
            }
            String webPath = ContextLoader.getCurrentWebApplicationContext().getServletContext().getRealPath("/");
            String zipName = "定标纪要";//String.valueOf(System.currentTimeMillis());
            String zipDir = webPath + "WEB-INF/page/bbgs/excel/" + zipName;//"D:\\temp"
            File tatFile = new File(zipDir);
            if (tatFile.exists()) {
                new deleteFileUtil().deleteDir(tatFile);
            }
            File delFZ = new File(zipDir + ".zip");
            if (delFZ.exists()) {
                new deleteFileUtil().deleteDir(delFZ);
            }
            List<tblBbgsPackageSupplierRaceModel> list = dao.queryListForMinutes();
            if (list.size() < 1) {
                map.put("result", "fail");
                map.put("msg", "无有效数据生成导出文件");
                return map;
            }
            if (list.get(0).getTotalScore() == null) {
                map.put("result", "fail");
                map.put("msg", "请先计算综合排名！");
                return map;
            }
            List<tblBbgsPackageSupplierRaceModel> outList1 = new ArrayList<tblBbgsPackageSupplierRaceModel>();
            List<tblBbgsPackageSupplierRaceModel> outList2 = new ArrayList<tblBbgsPackageSupplierRaceModel>();

            Map<String, List<tblBbgsPackageSupplierRaceModel>> dealMap = new LinkedHashMap<String,
                    List<tblBbgsPackageSupplierRaceModel>>();
//            将处理的list 按照标段简称分组
            for (tblBbgsPackageSupplierRaceModel model : list) {
                if (dealMap.keySet().contains(model.getBidAbbreviaion())) {
                    dealMap.get(model.getBidAbbreviaion()).add(model);
                    continue;
                } else {
                    dealMap.put(model.getBidAbbreviaion(), new ArrayList<tblBbgsPackageSupplierRaceModel>());
                    dealMap.get(model.getBidAbbreviaion()).add(model);
                    continue;
                }
            }
            String sign = "test";
            for (String bidName : dealMap.keySet()) {
                list = dealMap.get(bidName);
                if ("test".equals(sign)) {
                    for (int i = 0; i < list.size(); i++) {
                        if ("1".equals(list.get(i).getIsWin())) {
                            sign = "isWin";
                            break;
                        }
                    }
                    if ("test".equals(sign)) {
                        sign = "noWin";
                    }
                }
                if ("isWin".equals(sign)) {
                    for (int i = 0; i < list.size(); i++) {
                        if ("1".equals(list.get(i).getIsWin())) {
                            outList1.add(list.get(i));
                            outList2.add(list.get(i));
                            if (i == 1) {
                                if (list.get(i).getSort().equals(list.get(i - 1).getSort())) {
                                    outList2.add(list.get(i - 1));
                                    if (list.size() > i + 1) {
                                        outList2.add(list.get(i + 1));
                                    }
                                    break;
                                }
                            } else if (i > 1) {
                                if (list.get(i).getSort().equals(list.get(i - 1).getSort())) {
                                    if (list.get(i).getSort().equals(list.get(i - 2).getSort())) {
                                        outList2.add(list.get(i - 2));
                                        outList2.add(list.get(i - 1));
                                        break;
                                    } else {
                                        outList2.add(list.get(i - 1));
                                        if (list.size() > i + 1) {
                                            outList2.add(list.get(i + 1));
                                        }
                                        break;
                                    }
                                }
                            }
                            if (list.size() > i + 1) {
                                outList2.add(list.get(i + 1));
                            }
                            if (list.size() > i + 2) {
                                outList2.add(list.get(i + 2));
                            }
                            break;
                        }
                    }
                }
                if ("noWin".equals(sign)) {
                    for (int i = 0; i < list.size(); i++) {
                        if (i == 0) {
                            outList1.add(list.get(i));
                            outList2.add(list.get(i));
                        }
                        if (i == 1 || i == 2) {
                            outList2.add(list.get(i));
                        }
                    }
                }
            }
            if (outList1.size() > 0 && outList2.size() > 0) {
                map = exportMinutesExcel(outList1, outList2, zipDir, zipName);
            } else {
                map.put("result", "fail");
                map.put("msg", "无有效数据生成导出文件");
            }
        } catch (Exception exp) {
            exp.printStackTrace();
            map.put("result", "fail");
            map.put("msg", "操作失败！");
        }
        return map;
    }

    public Map exportMinutesExcel(List<tblBbgsPackageSupplierRaceModel> isWinList, List<tblBbgsPackageSupplierRaceModel>
            threeList
            , String parentPath, String fileName) {
        Map map = new HashMap();
        map.put("result", "success");
        map.put("msg", "操作成功！");
        OutputStream outputStream;
        try {
            XSSFWorkbook workbook = new XSSFWorkbook();
            XSSFSheet sheet = workbook.createSheet();
            XSSFRow row;
            XSSFCell cell;
            POICellStyle poiCellStyle = new POICellStyle();
            List<XSSFCellStyle> styles = poiCellStyle.getXssfCellStyle(workbook);
            XSSFCellStyle titleStyle = styles.get(0);
            XSSFCellStyle infoStyle = styles.get(1);
            //第一个sheet页
            for (int i = 0; i < isWinList.size(); i++) {
                if (i == 0) {
                    row = sheet.createRow(0);
                    row.setHeightInPoints(26);
                    cell = row.createCell(0);
                    cell.setCellStyle(titleStyle);
                    cell.setCellValue("序号");
                    cell = row.createCell(1);
                    cell.setCellStyle(titleStyle);
                    cell.setCellValue("分标名称");
                    cell = row.createCell(2);
                    cell.setCellStyle(titleStyle);
                    cell.setCellValue("推荐中标候选人名称");
                    cell = row.createCell(3);
                    cell.setCellStyle(titleStyle);
                    cell.setCellValue("技术得分");
                    cell = row.createCell(4);
                    cell.setCellStyle(titleStyle);
                    cell.setCellValue("报价得分");
                    cell = row.createCell(5);
                    cell.setCellStyle(titleStyle);
                    cell.setCellValue("商务得分");
                    cell = row.createCell(6);
                    cell.setCellStyle(titleStyle);
                    cell.setCellValue("总分");
                    cell = row.createCell(7);
                    cell.setCellStyle(titleStyle);
                    cell.setCellValue("排名");
                    cell = row.createCell(8);
                    cell.setCellStyle(titleStyle);
                    cell.setCellValue("最终金额报价（万元）");
                    cell = row.createCell(9);
                    cell.setCellStyle(titleStyle);
                    cell.setCellValue("最终折扣比例");
                    cell = row.createCell(10);
                    cell.setCellStyle(titleStyle);
                    cell.setCellValue("评标基准价");
                    cell = row.createCell(11);
                    cell.setCellStyle(titleStyle);
                    cell.setCellValue("项目单位");
                    // 设置表格列宽
                    sheet.setColumnWidth(0, 256 * 10 + 184);
                    sheet.setColumnWidth(1, 256 * 24 + 184);
                    sheet.setColumnWidth(2, 256 * 50 + 184);
                    sheet.setColumnWidth(3, 256 * 14 + 184);
                    sheet.setColumnWidth(4, 256 * 14 + 184);
                    sheet.setColumnWidth(5, 256 * 14 + 184);
                    sheet.setColumnWidth(6, 256 * 14 + 184);
                    sheet.setColumnWidth(7, 256 * 10 + 184);
                    sheet.setColumnWidth(8, 256 * 24 + 184);
                    sheet.setColumnWidth(9, 256 * 24 + 184);
                    sheet.setColumnWidth(10, 256 * 24 + 184);
                    sheet.setColumnWidth(11, 256 * 20 + 184);
                }
                row = sheet.createRow(i + 1);
                row.setHeightInPoints(24);

                cell = row.createCell(0);
                cell.setCellStyle(infoStyle);
                cell.setCellValue(i + 1);
                cell = row.createCell(1);
                cell.setCellStyle(infoStyle);
                cell.setCellValue(isWinList.get(i).getBidAbbreviaion());
                cell = row.createCell(2);
                cell.setCellStyle(infoStyle);
                cell.setCellValue(isWinList.get(i).getSupplier());
                cell = row.createCell(3);
                cell.setCellStyle(infoStyle);
                cell.setCellValue(isWinList.get(i).getTechnologyScore());
                cell = row.createCell(4);
                cell.setCellStyle(infoStyle);
                cell.setCellValue(isWinList.get(i).getPriceScore());
                cell = row.createCell(5);
                cell.setCellStyle(infoStyle);
                cell.setCellValue(isWinList.get(i).getBusinessScore());
                cell = row.createCell(6);
                cell.setCellStyle(infoStyle);
                cell.setCellValue(isWinList.get(i).getTotalScore());
                cell = row.createCell(7);
                cell.setCellStyle(infoStyle);
                cell.setCellValue(isWinList.get(i).getSort());
                cell = row.createCell(8);
                cell.setCellStyle(infoStyle);
                cell.setCellValue(isWinList.get(i).getModOffer());
                cell = row.createCell(9);
                cell.setCellStyle(infoStyle);
                if(isWinList.get(i).getFinalRate().equals("") || isWinList.get(i).getFinalRate() == null){
                    cell.setCellValue(isWinList.get(i).getFinalRate());
                }else{
                    cell.setCellValue(isWinList.get(i).getFinalRate()+"%");
                }

                cell = row.createCell(10);
                cell.setCellStyle(infoStyle);
                cell.setCellValue(isWinList.get(i).getBasePrice());
                cell = row.createCell(11);
                cell.setCellStyle(infoStyle);
                cell.setCellValue("");
            }
            sheet = workbook.createSheet();  //创建新的sheet页
            for (int i = 0; i < threeList.size(); i++) {
                if (i == 0) {
                    // 设置表格列宽
                    // 设置表格列宽
                    sheet.setColumnWidth(0, 256 * 10 + 184);
                    sheet.setColumnWidth(1, 256 * 24 + 184);
                    sheet.setColumnWidth(2, 256 * 50 + 184);
                    sheet.setColumnWidth(3, 256 * 14 + 184);
                    sheet.setColumnWidth(4, 256 * 14 + 184);
                    sheet.setColumnWidth(5, 256 * 14 + 184);
                    sheet.setColumnWidth(6, 256 * 14 + 184);
                    sheet.setColumnWidth(7, 256 * 10 + 184);
                    sheet.setColumnWidth(8, 256 * 24 + 184);
                    sheet.setColumnWidth(9, 256 * 24 + 184);
                    sheet.setColumnWidth(10, 256 * 24 + 184);
                    sheet.setColumnWidth(11, 256 * 20 + 184);

                    row = sheet.createRow(0);
                    row.setHeightInPoints(26);
                    cell = row.createCell(0);
                    cell.setCellStyle(titleStyle);
                    cell.setCellValue("序号");
                    cell = row.createCell(1);
                    cell.setCellStyle(titleStyle);
                    cell.setCellValue("分标名称");
                    cell = row.createCell(2);
                    cell.setCellStyle(titleStyle);
                    cell.setCellValue("推荐中标候选人名称");
                    cell = row.createCell(3);
                    cell.setCellStyle(titleStyle);
                    cell.setCellValue("技术得分");
                    cell = row.createCell(4);
                    cell.setCellStyle(titleStyle);
                    cell.setCellValue("报价得分");
                    cell = row.createCell(5);
                    cell.setCellStyle(titleStyle);
                    cell.setCellValue("商务得分");
                    cell = row.createCell(6);
                    cell.setCellStyle(titleStyle);
                    cell.setCellValue("总分");
                    cell = row.createCell(7);
                    cell.setCellStyle(titleStyle);
                    cell.setCellValue("排名");
                    cell = row.createCell(8);
                    cell.setCellStyle(titleStyle);
                    cell.setCellValue("最终金额报价（万元）");
                    cell = row.createCell(9);
                    cell.setCellStyle(titleStyle);
                    cell.setCellValue("最终折扣比例");
                    cell = row.createCell(10);
                    cell.setCellStyle(titleStyle);
                    cell.setCellValue("评标基准价");
                    cell = row.createCell(11);
                    cell.setCellStyle(titleStyle);
                    cell.setCellValue("项目单位");
                }
                row = sheet.createRow(i + 1);
                row.setHeightInPoints(24);

                cell = row.createCell(0);
                cell.setCellStyle(infoStyle);
                cell.setCellValue(i + 1);
                cell = row.createCell(1);
                cell.setCellStyle(infoStyle);
                cell.setCellValue(threeList.get(i).getBidAbbreviaion());
                cell = row.createCell(2);
                cell.setCellStyle(infoStyle);
                cell.setCellValue(threeList.get(i).getSupplier());
                cell = row.createCell(3);
                cell.setCellStyle(infoStyle);
                cell.setCellValue(threeList.get(i).getTechnologyScore());
                cell = row.createCell(4);
                cell.setCellStyle(infoStyle);
                cell.setCellValue(threeList.get(i).getPriceScore());
                cell = row.createCell(5);
                cell.setCellStyle(infoStyle);
                cell.setCellValue(threeList.get(i).getBusinessScore());
                cell = row.createCell(6);
                cell.setCellStyle(infoStyle);
                cell.setCellValue(threeList.get(i).getTotalScore());
                cell = row.createCell(7);
                cell.setCellStyle(infoStyle);
                cell.setCellValue(threeList.get(i).getSort());
                cell = row.createCell(8);
                cell.setCellStyle(infoStyle);
                cell.setCellValue(threeList.get(i).getModOffer());
                cell = row.createCell(9);
                cell.setCellStyle(infoStyle);

                if(threeList.get(i).getFinalRate().equals("") || threeList.get(i).getFinalRate() == null){
                    cell.setCellValue(threeList.get(i).getFinalRate());
                }else{
                    cell.setCellValue(threeList.get(i).getFinalRate()+"%");
                }

                cell = row.createCell(10);
                cell.setCellStyle(infoStyle);
                cell.setCellValue(threeList.get(i).getBasePrice());
                cell = row.createCell(11);
                cell.setCellStyle(infoStyle);
                cell.setCellValue("");
            }
            File tatFile = new File(parentPath);
            if (!tatFile.exists()) {
                tatFile.mkdirs();
            }
            File excelFile = new File(tatFile, fileName + ".xlsx");
            outputStream = new FileOutputStream(excelFile);
            workbook.write(outputStream);
            outputStream.close();
            ZipUtils.zip(parentPath, parentPath + ".zip", true);

        } catch (Exception exp) {
            exp.printStackTrace();
            map.put("result", "fail");
            map.put("msg", "操作失败！");
        }
        return map;
    }

    private String getUUID() {
        return UUID.randomUUID().toString().replace("-", "");
    }

    /**
     * 查询进行到了哪一个节点及提示信息
     * @return
     */
    public tblBbgsFlowStateRaceModel queryMinIdByState(){
        return dao.queryMinIdByState();
    }

    /**
     * 通过id 更新流程状态
     * id 为 null 时 更新所有记录
     * @param id
     * @param state
     */
    public void updateStateById(@Param("id") String id, @Param("state") String state){
        dao.updateStateById(id,state);
    }

    /***
     * 根据 标段简称 得到所有对应供应商
     * @param model 带查询条件的实体
     * @return 实体集合
     */
    public List<tblBbgsPriceInformationRaceModel> queryPriceListByName(tblBbgsPriceInformationRaceModel model) {
        List<tblBbgsPriceInformationRaceModel> list = null;
        try {
            list = dao.queryPriceListByName(model);
        }catch (Exception exp){
            exp.printStackTrace();
            list = null;
        }
        return list;
    }


    /***
     * 修改 ==》导出 平均分汇总，综合排名
     * @return  文件名称，格式：文件.zip
     */
    public Map queryScoreGroupList() {
        Map result = new HashMap();
        try {
            //查询流程进行到的位置
            tblBbgsFlowStateRaceModel model1 = dao.queryMinIdByState();
            if (model1.getId() != null && Integer.valueOf(model1.getId()) <= 801) {
                result.put("flag","fail");
                result.put("msg",model1.getAlertMessage());
                return  result;
            }
            String webPath = ContextLoader.getCurrentWebApplicationContext().getServletContext().getRealPath("/");
            String zipName = "平均分汇总、综合排序表";//String.valueOf(System.currentTimeMillis());
            String zipDir = webPath + "WEB-INF/page/bbgs/excel/" + zipName;
            File delF = new File(zipDir);
            if (delF.exists()) {
                deleteDir(delF);
            }
            File delFZ = new File(zipDir+".zip");
            if (delFZ.exists()) {
                deleteDir(delFZ);
            }
            List listNum = dao.queryDistinctBidAbbreviaionList();
            if (listNum.size()<1){
                result.put("flag","fail");
                result.put("msg","无有效数据生成导出文件");
                return result;
            }

            tblBbgsPackageSupplierRaceModel modelParam = new tblBbgsPackageSupplierRaceModel();
            modelParam.setFlagInvalid("0");
            modelParam.setFlagBidInvalid("0");
            modelParam.setFlagBit("0");
            modelParam.setModOffer("无");
            modelParam.setFinalRate("无");
            List<tblBbgsPackageSupplierRaceModel> sortList = dao.querySortList(modelParam);
            if (sortList.size()>0){
                if (sortList.get(0).getTotalScore() != null){
                    new exportSortExcelUtil().RaceExportSortExcel(zipDir,"综合排序","评分汇总表",sortList);
                }else{
                    result.put("flag","fail");
                    result.put("msg","请先计算综合排名！");
                    return result;
                }
            }else {
                result.put("flag","fail");
                result.put("msg","无有效数据生成导出文件");
                return result;
            }

            //创建组 -对应组数据 变量map
            Map<String, List<tblBbgsTechnologyBusinessModel>> map =
                    new LinkedHashMap<String, List<tblBbgsTechnologyBusinessModel>>();
            //查询所有打分信息 得到List
            List<tblBbgsTechnologyBusinessModel> list = technologyAndBusinessDao.queryScoreGroupList();
            if (list.size() < 1){
                result.put("flag","fail");
                result.put("msg","请先导入专家打分！");
                return result;
            }
            //变量打分信息 分组 存入 map<String,List>
            for (tblBbgsTechnologyBusinessModel model : list) {
                if (map.keySet().contains(model.getExpertGroupName())) {
                    map.get(model.getExpertGroupName()).add(model);
                } else {
                    List<tblBbgsTechnologyBusinessModel> l = new ArrayList<tblBbgsTechnologyBusinessModel>();
                    l.add(model);
                    map.put(model.getExpertGroupName(), l);
                }
            }
            String flag = "";
            //遍历组
            for (String groupName:map.keySet()){
                //得到组内数据
                List<tblBbgsTechnologyBusinessModel> groupList  = map.get(groupName);
                tblBbgsTechnologyBusinessModel indexModel = new tblBbgsTechnologyBusinessModel();
                if (groupName.startsWith("技术")){
                    indexModel.setExpertGroupName(groupName);
                    indexModel.setType("技术");
                    List<tblBbgsTechnologyBusinessModel> rowList = technologyAndBusinessDao.queryByGroupName(indexModel);
                    //导出 当前组 excel
                    flag = exportPriceExcel(groupList,rowList,groupName,zipDir,"技术");
                }
                if (groupName.startsWith("商务")){
                    indexModel.setExpertGroupName(groupName);
                    indexModel.setType("商务");
                    List<tblBbgsTechnologyBusinessModel> rowList = technologyAndBusinessDao.queryByGroupName(indexModel);
                    //导出 当前组 excel
                    flag = exportPriceExcel(groupList,rowList,groupName,zipDir,"商务");
                }
                if (!"success".equals(flag)){
                    result.put("flag","fail");
                    result.put("msg","导出失败！");
                    return result;
                }
            }

            if (list.size() > 0 && list.get(0).getPrice() != null) {
                ZipUtils.zip(zipDir, zipDir + ".zip", true);
                result.put("flag","success");
                result.put("msg","导出成功！");
                result.put("fileName",zipName + ".zip");
                return result;
            }
        } catch (Exception exp) {
            exp.printStackTrace();
            result.put("flag","fail");
            result.put("msg","导出失败！");
            return result;
        }
        return result;
    }


    /**
     * 导出：
     * 导出方法===》导出平均分 调用该方法
     * @param list 打分的分数List
     * @param rowList 供应商 标段简称 row
     * @param groupName 组名 作为文件名称
     * @param parentOPath 文件路径
     * @param type 类型
     * @return 返回结果
     */
    public String exportPriceExcel(List<tblBbgsTechnologyBusinessModel> list,List<tblBbgsTechnologyBusinessModel> rowList,String groupName,String parentOPath,String type){
        String result ="";
        String[] addNum= {"A","B","C","D","E","F","G","H","I","G","K","L","M","N","O","P","Q","R","S","T","U","V","W","S","Y","Z"};
        try {
            String webPath = ContextLoader.getCurrentWebApplicationContext().getServletContext().getRealPath("/");
            String tempPath = webPath + "export/sort/平均分汇总模板.xlsx";
            OutputStream outputStream;
            double technologyWeight = 0;                                //技术权重
            double businessWeight = 0;                                  //商务权重
            double temp = 0;
            //读取模板
            XSSFWorkbook workbook = new XSSFWorkbook(new FileInputStream(tempPath));
            Sheet sheet = workbook.getSheetAt(0);
            sheet.setForceFormulaRecalculation(true);
            Row row = sheet.getRow(0);
            Cell cell = row.getCell(2);

            // 生成一个样式
            XSSFCellStyle infostyle = workbook.createCellStyle();
            // 设置这些样式
            infostyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);//水平居中
            infostyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);//垂直居中
            infostyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);//边框
            infostyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);
            infostyle.setBorderRight(HSSFCellStyle.BORDER_THIN);
            infostyle.setBorderTop(HSSFCellStyle.BORDER_THIN);
            infostyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
            infostyle.setWrapText(true);
            // 生成一个字体
            XSSFFont font = workbook.createFont();
            font.setFontHeightInPoints((short) 12);
            font.setFontName("宋体");
            // 把字体应用到当前的样式
            infostyle.setFont(font);

            // 生成一个样式
            XSSFCellStyle styleTitle = workbook.createCellStyle();
            // 设置这些样式
            styleTitle.setAlignment(HSSFCellStyle.ALIGN_CENTER);//水平居中
            styleTitle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);//垂直居中
            styleTitle.setBorderBottom(HSSFCellStyle.BORDER_THIN);//边框
            styleTitle.setBorderLeft(HSSFCellStyle.BORDER_THIN);
            styleTitle.setBorderRight(HSSFCellStyle.BORDER_THIN);
            styleTitle.setBorderTop(HSSFCellStyle.BORDER_THIN);
            styleTitle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
            styleTitle.setWrapText(true);
            // 生成一个字体
            XSSFFont fontTitle = workbook.createFont();
            fontTitle.setFontHeightInPoints((short) 12);
            fontTitle.setFontName("宋体");
            fontTitle.setBoldweight(XSSFFont.BOLDWEIGHT_BOLD);
            // 把字体应用到当前的样式
            styleTitle.setFont(fontTitle);

            // 生成一个double样式
            XSSFCellStyle styleDouble = workbook.createCellStyle();
            // 设置这些样式
            styleDouble.setAlignment(HSSFCellStyle.ALIGN_CENTER);//水平居中
            styleDouble.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);//垂直居中
            styleDouble.setBorderBottom(HSSFCellStyle.BORDER_THIN);//边框
            styleDouble.setBorderLeft(HSSFCellStyle.BORDER_THIN);
            styleDouble.setBorderRight(HSSFCellStyle.BORDER_THIN);
            styleDouble.setBorderTop(HSSFCellStyle.BORDER_THIN);
            styleDouble.setAlignment(HSSFCellStyle.ALIGN_CENTER);
            styleDouble.setWrapText(true);
            // 生成一个字体
            XSSFFont fontDouble = workbook.createFont();
            fontDouble.setFontHeightInPoints((short) 12);
            fontDouble.setFontName("宋体");
            fontDouble.setBoldweight(XSSFFont.BOLDWEIGHT_BOLD);
            // 把字体应用到当前的样式
            styleDouble.setFont(fontDouble);
            //生成单元格格式
            XSSFDataFormat df = workbook.createDataFormat();
            //设置单元格格式
            styleDouble.setDataFormat(df.getFormat("#,##0.00"));

            List<tblBbgsTechnologyBusinessModel> exportList = new ArrayList<tblBbgsTechnologyBusinessModel>();
            //创建专家组List （为后续导出excel使用）
            List<String> expertNames = new ArrayList<String>();
            //创建专家组set（去除重复）
            Set<String> names = new HashSet<String>();
            List<tblBbgsTechnologyBusinessModel> indexModelList = new ArrayList<tblBbgsTechnologyBusinessModel>();
            tblBbgsTechnologyBusinessModel indexModel = new tblBbgsTechnologyBusinessModel();
            //将专家存入set，去除重复，得到组内专家
            for (tblBbgsTechnologyBusinessModel model :list){
                if (type.equals(model.getType())){
                    names.add(model.getExpertName());
                    exportList.add(model);
                }
            }
            //遍历专家 将存入专家List （为后续导出excel使用）
            Iterator<String> it = names.iterator();
            while(it.hasNext()){
                expertNames.add(it.next());
            }
            //将数据写入excel
            if (exportList != null && exportList.size()>0 && exportList.get(0).getPrice() != null){
                String addMergedRegionSign = "";
                int startRowNum = 3;
                //遍历导出数据
                for (int i=0;i<rowList.size();i++){
                    if (i==0){
                        addMergedRegionSign = rowList.get(0).getBidAbbreviaion();
                        startRowNum = 3;
                    }else if(i==rowList.size()-1){
                        if (addMergedRegionSign.equals(rowList.get(i).getBidAbbreviaion())){
                            sheet.addMergedRegion(new CellRangeAddress(startRowNum, i+3, 0, 0));
                        }else{
                            sheet.addMergedRegion(new CellRangeAddress(startRowNum, i+2, 0, 0));
                        }
                    }else{
                        if (! addMergedRegionSign.equals(rowList.get(i).getBidAbbreviaion())){
                            sheet.addMergedRegion(new CellRangeAddress(startRowNum, i+2, 0, 0));
                            addMergedRegionSign = rowList.get(i).getBidAbbreviaion();
                            startRowNum = i+3;
                        }
                    }
                    row = sheet.createRow(i+3);
                    row.setHeight((short)(16 * 38));
                    cell = row.createCell(0);
                    cell.setCellStyle(infostyle);
                    cell.setCellValue(rowList.get(i).getBidAbbreviaion().toString());
                    cell = row.createCell(1);
                    cell.setCellStyle(infostyle);
                    cell.setCellValue(rowList.get(i).getSupplier().toString());
                    //遍历专家
                    for (int j=0;j<expertNames.size();j++){
                        Row rowexpert = sheet.getRow(2);
                        Cell cellExpert = rowexpert.createCell(j+2);
                        cellExpert.setCellStyle(styleTitle);
                        cellExpert.setCellValue(expertNames.get(j).toString());
                        rowexpert = sheet.getRow(1);
                        cellExpert = rowexpert.createCell(j+2);
                        cellExpert.setCellStyle(styleTitle);
                        //查找数据写入
                        for (int k=0;k<exportList.size();k++){
                            if (exportList.get(k).getExpertName().equals(expertNames.get(j))
                                    && exportList.get(k).getSupplier().equals(rowList.get(i).getSupplier())
                                    && exportList.get(k).getBidAbbreviaion().equals(rowList.get(i).getBidAbbreviaion())
                                    && exportList.get(k).getType().equals("技术")){
                                if (exportList.get(k).getTechnologyWeight() != null){
                                    technologyWeight = Double.parseDouble(exportList.get(k).getTechnologyWeight());     //技术权重
                                    temp = technologyWeight * Double.parseDouble(exportList.get(k).getPrice());
//                                        BigDecimal b = new BigDecimal(temp);
//                                        double price = b.setScale(1,BigDecimal.ROUND_HALF_UP).doubleValue();
//                                        String  f2 = new java.text.DecimalFormat("#.00").format(temp);
                                    String f2 = String.format("%.2f", temp);
                                    double price = Double.parseDouble(f2);
                                    cell =  row.createCell(j+2);
                                    cell.setCellStyle(styleDouble);
                                    cell.setCellType(Cell.CELL_TYPE_NUMERIC);
                                    cell.setCellValue(price);
                                }
                            }
                            if (exportList.get(k).getExpertName().equals(expertNames.get(j))
                                    && exportList.get(k).getSupplier().equals(rowList.get(i).getSupplier())
                                    && exportList.get(k).getBidAbbreviaion().equals(rowList.get(i).getBidAbbreviaion())
                                    && exportList.get(k).getType().equals("商务")){
                                if (exportList.get(k).getBusinessWeight() != null){
                                    businessWeight = Double.parseDouble(exportList.get(k).getBusinessWeight());     //商务权重
                                    temp = businessWeight * Double.parseDouble(exportList.get(k).getPrice());
//                                        BigDecimal b = new BigDecimal(temp);
//                                        double price = b.setScale(1,BigDecimal.ROUND_HALF_UP).doubleValue();
//                                        String  f2 = new java.text.DecimalFormat("#.00").format(temp);
                                    String f2 = String.format("%.2f", temp);
                                    double price = Double.parseDouble(f2);
                                    cell =  row.createCell(j+2);
                                    cell.setCellStyle(styleDouble);
                                    cell.setCellType(Cell.CELL_TYPE_NUMERIC);
                                    cell.setCellValue(price);
                                }
                            }
                        }
                    }
                    cell = row.createCell(expertNames.size()+2);
                    cell.setCellType(Cell.CELL_TYPE_FORMULA);
                    String str = "AVERAGE(C"+(i+4)+":"+addNum[expertNames.size()+1]+(i+4)+")";
                    cell.setCellStyle(styleDouble);
                    cell.setCellFormula(str);
                }

                //合并评委打分
                sheet.addMergedRegion(new CellRangeAddress(1, 1, 2, expertNames.size()+1));
                cell = sheet.getRow(1).getCell(2);
                cell.setCellStyle(styleTitle);
                cell.setCellValue("评委打分");
                //合并平均分列
                sheet.addMergedRegion(new CellRangeAddress(1, 2, expertNames.size()+2, expertNames.size()+2));
                cell = sheet.getRow(1).createCell(expertNames.size()+2);
                cell.setCellStyle(styleTitle);
                cell.setCellValue("平均得分");
                cell = sheet.getRow(2).createCell(expertNames.size()+2);
                cell.setCellStyle(styleTitle);
                cell.setCellValue("");
                //写入标题
                sheet.getRow(0).getCell(1).setCellValue("评分汇总表("+list.get(0).getType()+")");


                File groupFile = new File(parentOPath);
                if (!groupFile.exists()) {
                    groupFile.mkdirs();
                }
                File dirFile = new File(parentOPath+"/"+ type);
                if (!dirFile.exists()) {
                    dirFile.mkdirs();
                }
                String fileName = null;
                if (groupName == null){
                    fileName = type + "平均分汇总.xlsx";
                }else {
                    fileName = groupName + "平均分汇总.xlsx";
                }
                File excelFile = new File(dirFile, fileName);
                outputStream = new FileOutputStream(excelFile);
                workbook.write(outputStream);
                outputStream.close();
            }else {
                result = "listPriceNull";
                return result;
            }
            result = "success";
        } catch (Exception exp) {
            exp.printStackTrace();
            result = "Fail";
        }
        return result;
    }


    /***
     * 删除文件夹 及文件夹下的所有文件
     * @param dir 文件
     * @return 删除结果 true or false
     */
    private static boolean deleteDir(File dir) {
        if (dir.isDirectory()) {
            String[] children = dir.list();
            if (children != null){
                for (int i=0; i<children.length; i++) {
                    boolean success = deleteDir(new File(dir, children[i]));
                    if (!success) {
                        return false;
                    }
                }
            }
        }
        // 目录此时为空，可以删除
        return dir.delete();
    }
}
