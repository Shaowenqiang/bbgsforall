package cn.com.hqep.bidder.service.Impl;

import cn.com.hqep.bidder.common.*;
import cn.com.hqep.bidder.dao.bidderDao;
import cn.com.hqep.bidder.dao.conductDao;
import cn.com.hqep.bidder.model.tblBbgsBidInformationModel;
import cn.com.hqep.bidder.model.tblBbgsFlowStateModel;
import cn.com.hqep.bidder.model.tblBbgsPackageSupplierModel;
import cn.com.hqep.bidder.service.bidderService;
import org.apache.ibatis.annotations.Param;
import org.apache.poi.xssf.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.*;
import java.util.regex.Pattern;

/**
 * Created by sssJL on 2017-10-12.
 * bidderService 实现类
 */
@Repository
public class bidderServiceImpl implements bidderService {
    @Autowired
    private bidderDao dao;

    @Autowired
    conductDao conductDao; //加载conductDao 使用查询方法

    public Map<String, String> saveBid(MultipartFile file) {
        //查询流程进行到的位置
        tblBbgsFlowStateModel model1 = dao.queryMinIdByState();
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
                        List<tblBbgsPackageSupplierModel> UserList = new ArrayList<tblBbgsPackageSupplierModel>();
                        //正确的sheet页数据
                        List<Map> rowList = (List<Map>) sheetMap.get("data");
                        tblBbgsPackageSupplierModel model = null;
                        int index = 2;
                        for (int i = 0; i < rowList.size(); i++) {
                            Map row = rowList.get(i);
                            if (!"供应商名称".equals(row.get("供应商名称"))) {
                                ++index;
                                model = new tblBbgsPackageSupplierModel();
                                String bidAbbreviaion = row.get("标段简称").toString();
                                String supplier = row.get("供应商名称").toString();
                                String offer = row.get("投标报价（万元）").toString();
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
                                if ("无".equals(offer)) {
                                    dao.deleteByModel(model);
                                    dao.deleteByModel_2(model);
                                    continue;
                                }
                                //验证报价是否是double类型
                                String regex = "^[-//+]?[0-9]*([\\\\.][0-9]+)?$";
                                Pattern pattern = Pattern.compile(regex);
                                boolean flag = pattern.matcher(offer).matches();
                                if (!flag) {
                                    Map errorMap = new HashMap();
                                    errorMap.put("state", "error");
                                    errorMap.put("msg", "第" + index + "行投标报价有误！");
                                    return errorMap;
                                }
                                String strOffer = String.format("%.6f", Double.parseDouble(offer));
                                model.setOffer(strOffer);
                                model.setFlagBit("1");
                                model.setFlagInvalid("1");
                                model.setFlagBidInvalid("1");
                                UserList.add(model);

                            }
                        }
                        for (tblBbgsPackageSupplierModel saveModel : UserList) {
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

    public boolean updateById(tblBbgsPackageSupplierModel model) {
        boolean flag = true;
        try {
            dao.updateById(model);
        } catch (Exception exp) {
            exp.printStackTrace();
            flag = false;
        }
        return flag;
    }

    public List<tblBbgsPackageSupplierModel> queryDataList(tblBbgsPackageSupplierModel model) {
        try {
            List<tblBbgsPackageSupplierModel> list = dao.queryList(model);
            return list;
        } catch (Exception exp) {
            exp.printStackTrace();
            return null;
        }

    }

    public List<tblBbgsPackageSupplierModel> queryListByName(tblBbgsPackageSupplierModel model) {
        List<tblBbgsPackageSupplierModel> list = null;
        try {
            list = dao.queryListByName(model);
        } catch (Exception exp) {
            exp.printStackTrace();
            list = null;
        }
        return list;
    }

    public List<tblBbgsPackageSupplierModel> queryDistinctBidList() {
        List<tblBbgsPackageSupplierModel> list = null;
        try {
            list = dao.queryDistinctBidList();
        } catch (Exception exp) {
            exp.printStackTrace();
        }
        return list;
    }


    public List<tblBbgsPackageSupplierModel> queryDistinctPackageList(tblBbgsPackageSupplierModel model) {
        List<tblBbgsPackageSupplierModel> list = null;
        try {
            list = dao.queryDistinctPackageList(model);
        } catch (Exception exp) {
            exp.printStackTrace();
        }
        return list;
    }


    public boolean updateInvalidActionOne(tblBbgsPackageSupplierModel model) {
        boolean flag = true;
        try {
            dao.updateByModel(model);
            tblBbgsBidInformationModel tblDo = new tblBbgsBidInformationModel();
            List<tblBbgsPackageSupplierModel> list = dao.queryListByName(model);
            //准备
            String sign = model.getFlagInvalid();
            String name;
            for (int i = 0; i < list.size(); i++) {
                name = list.get(i).getBidAbbreviaion();
                tblDo.setBidAbbreviaion(name);
                tblDo.setInvalid(sign);
                //标段信息表 中将标段置（0/1）（是否流标）
                //执行
                conductDao.updateBidInfo(tblDo);
            }
        } catch (Exception exp) {
            exp.printStackTrace();
            flag = false;
        }
        return flag;
    }

    public boolean updateByModel(tblBbgsPackageSupplierModel model) {
        boolean flag = true;
        try {
            dao.updateByModel(model);
        } catch (Exception exp) {
            exp.printStackTrace();
            flag = false;
        }
        return flag;
    }

    public List<tblBbgsPackageSupplierModel> queryDistinctBidAbbreviaionList() {
        List<tblBbgsPackageSupplierModel> list = null;
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
            tblBbgsFlowStateModel model1 = dao.queryMinIdByState();
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
            List<tblBbgsPackageSupplierModel> list = dao.queryListForMinutes();
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
            List<tblBbgsPackageSupplierModel> outList1 = new ArrayList<tblBbgsPackageSupplierModel>();
            List<tblBbgsPackageSupplierModel> outList2 = new ArrayList<tblBbgsPackageSupplierModel>();

            Map<String, List<tblBbgsPackageSupplierModel>> dealMap = new LinkedHashMap<String,
                    List<tblBbgsPackageSupplierModel>>();
//            将处理的list 按照标段简称分组
            for (tblBbgsPackageSupplierModel model : list) {
                if (dealMap.keySet().contains(model.getBidAbbreviaion())) {
                    dealMap.get(model.getBidAbbreviaion()).add(model);
                    continue;
                } else {
                    dealMap.put(model.getBidAbbreviaion(), new ArrayList<tblBbgsPackageSupplierModel>());
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

    public Map exportMinutesExcel(List<tblBbgsPackageSupplierModel> isWinList, List<tblBbgsPackageSupplierModel>
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
                    cell.setCellValue("投标报价（万元）");
                    cell = row.createCell(9);
                    cell.setCellStyle(titleStyle);
                    cell.setCellValue("评标基准价");
                    cell = row.createCell(10);
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
                    sheet.setColumnWidth(10, 256 * 20 + 184);
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
                cell.setCellValue(isWinList.get(i).getOffer());
                cell = row.createCell(9);
                cell.setCellStyle(infoStyle);
                cell.setCellValue(isWinList.get(i).getBasePrice());
                cell = row.createCell(10);
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
                    sheet.setColumnWidth(10, 256 * 20 + 184);

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
                    cell.setCellValue("投标报价（万元）");
                    cell = row.createCell(9);
                    cell.setCellStyle(titleStyle);
                    cell.setCellValue("评标基准价");
                    cell = row.createCell(10);
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
                cell.setCellValue(threeList.get(i).getOffer());
                cell = row.createCell(9);
                cell.setCellStyle(infoStyle);
                cell.setCellValue(threeList.get(i).getBasePrice());
                cell = row.createCell(10);
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
    public tblBbgsFlowStateModel queryMinIdByState(){
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

    /**
     * 新增方法 查询条件改为in的方式
     * 根据标段 查询包并去除重复
     * @param model 带查询条件的实体
     * @return 查询结果（实体集合）
     */
    @Override
    public List<tblBbgsPackageSupplierModel> queryDistinctPackageListIn(tblBbgsPackageSupplierModel model) {
        List<tblBbgsPackageSupplierModel> list = null;
        try {
            list = dao.queryDistinctPackageListIn(model);
        } catch (Exception exp) {
            exp.printStackTrace();
        }
        return list;
    }

    /**
     * 新增方法 查询条件改为in的方式
     * 通过标段简称 查询数据
     * @param model 查询条件实体
     * @return 实体集合
     */
    public List<tblBbgsPackageSupplierModel> queryListByNameIn(tblBbgsPackageSupplierModel model) {
        List<tblBbgsPackageSupplierModel> list = null;
        try {
            list = dao.queryListByNameIn(model);
            String former,latter,current;
            for(int i = 0; i < list.size(); i++) {
                if (list.size() > 1 && i < list.size() - 1) {
                    current = list.get(i).getSupplier();
                    latter = list.get(i+1).getSupplier();
                    if (current.equals(latter)) {
                        list.get(i).setShowColor("1");
                        list.get(i + 1).setShowColor("1");
                    }
                }
            }
        } catch (Exception exp) {
            exp.printStackTrace();
            list = null;
        }
        return list;
    }



    /**
     * 新增方法：更新废标复标供应商 in（）
     * 通过ID 更新数据
     * @param model 带有更新数据的实体
     * @return 更新结果
     */
    public boolean updateByInParams(tblBbgsPackageSupplierModel model) {
        boolean flag = true;
        try {
            dao.updateByInParams(model);
        } catch (Exception exp) {
            exp.printStackTrace();
            flag = false;
        }
        return flag;
    }
}
