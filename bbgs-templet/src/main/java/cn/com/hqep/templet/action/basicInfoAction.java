package cn.com.hqep.templet.action;

import cn.com.hqep.bidder.common.exportExcel;
import cn.com.hqep.bidder.model.tblBbgsTendererModel;
import cn.com.hqep.bidder.service.bidderTendererService;
import cn.com.hqep.templet.common.ZipUtils;
import cn.com.hqep.templet.common.cmdOrderUtil;
import cn.com.hqep.templet.common.jsonMsg;
import cn.com.hqep.templet.model.tblBbgsBidInformationModel;
import cn.com.hqep.templet.service.basicInfoService;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * 基础信息导入  （第一次信息导入）
 * * @author hq4
 *
 * @date 2017年10月10日 下午02:57:15
 */
@Controller
@RequestMapping("/basicInfo")
public class basicInfoAction {
    @Autowired
    private basicInfoService basicInfoService;
    @Autowired
    private bidderTendererService tendererService;
    /**
     * 查询是否可以导出专家组阅标记录
     */
    @ResponseBody
    @RequestMapping("/viewState")
    public Map viewState(){
        tblBbgsBidInformationModel model = new tblBbgsBidInformationModel();
        model.setFlag("N");
        List l1 = basicInfoService.queryAllBidRecord(model);
        int size1 = l1.size();
        model.setFlag("Y");
        List l2 = basicInfoService.queryAllBidRecord(model);
        int size2 = l2.size();
        if(size1>0&&size2==0){
            return jsonMsg.booleanToMap(true);
        }
        return jsonMsg.booleanToMap(false);
    }
    /**
     * 查询是否可以导入
     */
    @ResponseBody
    @RequestMapping("/viewIsImport")
    public Map viewIsImport() {
        return jsonMsg.booleanToMap(basicInfoService.viewIsImport());
    }

    /**
     * 删除所有专家
     *
     * @return
     */
    @ResponseBody
    @RequestMapping("/delAllExpert")
    public Map delAllExpert() {
        return basicInfoService.delAllExpert();
    }

    @ResponseBody
    @RequestMapping("/expertExcel")
    public Map expertExcel(@RequestParam("expertFile") MultipartFile file) {
//        CommonsMultipartFile cf = (CommonsMultipartFile) file;
//        DiskFileItem fi = (DiskFileItem) cf.getFileItem();
//        File f = fi.getStoreLocation();
        Map map = readExcel(file);
        if (map.get("state").equals("error")) {
            return map;
        }
        Map<String, List<String>> m = (Map<String, List<String>>) map.get("msg");
        return basicInfoService.saveExpert(m);
    }

    private Map readExcel(MultipartFile file) {
        InputStream is = null;
        Map<String, List<String>> map = new LinkedHashMap<>();
        try {
            is = file.getInputStream();
            Workbook workbook = WorkbookFactory.create(is);
            Sheet sheet = null;
            Row row = null;
            Cell cell = null;
            //sheet页数
            int sheetNum = workbook.getNumberOfSheets();

            for (int i = 0; i < sheetNum; i++) {
                sheet = workbook.getSheetAt(i);
                int rows = sheet.getPhysicalNumberOfRows();
                int cols = sheet.getRow(0).getPhysicalNumberOfCells();
                if (rows == 0 || cols == 0) {
                    return jsonMsg.toJosn("error", "上传文件中 " + sheet.getSheetName() + "sheet页为空");
                }
                for (int j = 0; j < rows; j++) {
                    row = sheet.getRow(j);
                    if (row.getCell(0).getCellType() == Cell.CELL_TYPE_BLANK) {
                        return jsonMsg.toJosn("error", "第" + (j + 1) + "行 不存在专家组名称");
                    }
                    String key = row.getCell(0).getStringCellValue().replace(" ", "");
                    for (String s : map.keySet()) {
                        if (s.equals(key)) {
                            return jsonMsg.toJosn("error", "专家文件中存在两个" + key);
                        }
                    }
                    List<String> list = new ArrayList<>();

                    for (int k = 1; k < row.getLastCellNum(); k++) {
                        cell = row.getCell(k);
                        if (row.getCell(k) == null || row.getCell(k).getCellType() == Cell.CELL_TYPE_BLANK) {
                            return jsonMsg.toJosn("error", "第" + (j + 1) + "行 第" + k + "列不存在专家姓名");
                        }
                        String cellVal = cell.getStringCellValue().replace(" ", "");
                        list.add(cellVal);
                        for (String s : map.keySet()) {
                            for (String s2 : map.get(s)) {
                                if (s2.equals(cellVal)) {
                                    return jsonMsg.toJosn("error", s + "中已经包含" + s2 + "专家，请为" + key + "的" + s2 + "专家加上身份证号");
                                }
                            }
                        }
                    }
                    map.put(key, list);
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (InvalidFormatException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return jsonMsg.toJosn("success", map);
    }

    /**
     * 接收Excel文件 解析数据 并向数据库中插入
     */
    @ResponseBody
    @RequestMapping("/saveData")
    public Map saveData(@RequestParam("file") MultipartFile file, @RequestParam("category") String category) {

        return basicInfoService.saveData(file, category);
    }

    /**
     * 店商类基础数据导入
     * 接收Excel文件夹 解析数据 并向数据库中插入
     */
    @ResponseBody
    @RequestMapping("/saveDataForE")
    public Map saveDataForE(@RequestParam("fileList") MultipartFile[] files, @RequestParam("category") String category) {
        return basicInfoService.saveDataForE(files, category);
    }

    /**
     * 接收Excel文件 解析数据 并向数据库中插入
     */
    @ResponseBody
    @RequestMapping("/saveBaseOffer")
    public Map saveBaseOffer(@RequestParam("file") MultipartFile file) {
        return basicInfoService.saveBaseOffer(file);
    }


    /**
     * 查询当前批次名称
     *
     * @return
     */
    @ResponseBody
    @RequestMapping("/queryBatchName")
    public Map queryBatchName() {
        Map<String, Object> map = basicInfoService.queryName();
        return map;
    }

    /**
     * 清空 当前批次所有数据
     *
     * @return
     */
    @ResponseBody
    @RequestMapping("/delAllData")
    public Map delAllData(HttpServletRequest request) {
        return jsonMsg.booleanToMap(basicInfoService.delAllData());
    }

    /**
     * 执行 使用 cmd 运行 D:\backup.bat
     * 达到备份mysql数据库的作用
     *
     * @return
     */
    @ResponseBody
    @RequestMapping("/backup")
    public void backup(HttpServletRequest request) {
        Runtime cmd = Runtime.getRuntime();
        try {
            String batPath = request.getSession().getServletContext().getRealPath("")+"cmdFile/backup.bat";
            Process p = cmd.exec("cmd /c start /b  "+batPath);
            p.waitFor();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 判断是否存在当前批次数据
     *
     * @return
     */
    @ResponseBody
    @RequestMapping("/existData")
    public Map existData() {
        return jsonMsg.booleanToMap(basicInfoService.existData());
    }

    /**
     * 查询 标段-包信息
     *
     * @return
     */
//    PageHelper.startPage(3, 10);
//    List<tblBbgsBidInformationModel> list = basicInfoService.queryAllBid();
//    Map<String, Object> resultMap = jsonMsg.msgByListForEasyuiTable(list);
    @ResponseBody
    @RequestMapping("/queryAllBid")
    public List queryAllBid() {
        return basicInfoService.queryAllBid();
    }

    /**
     * 通过标号查询其下的包
     *
     * @return
     */
    @ResponseBody
    @RequestMapping("/queryPackageByBid")
    public List queryPackageByBid(String bid) {
        return basicInfoService.queryPackageByBid(bid);
    }

    @ResponseBody
    @RequestMapping("/queryPackageByBid_new")
    public List queryPackageByBid_new(String bids) {
        return basicInfoService.queryPackageByBid_new(bids);
    }
    /**
     * 查询 标段-包信息 已经添加了模板信息的
     *
     * @return
     */
    @ResponseBody
    @RequestMapping("/queryAllBidRecord")
    public List<tblBbgsBidInformationModel> queryAllBidRecord(tblBbgsBidInformationModel model) {
        if (model.getTechnologyExpertGroup() != null && model.getTechnologyExpertGroup() == "") {
            model.setTechnologyExpertGroup(null);
        }
        if (model.getBusinessExpertGroup() != null && model.getBusinessExpertGroup() == "") {
            model.setBusinessExpertGroup(null);
        }
        if (model.getBidAbbreviaion() != null && model.getBidAbbreviaion() == "") {
            model.setBidAbbreviaion(null);
        }
        return basicInfoService.queryAllBidRecord(model);
    }

    /**
     * 更新标段包信息 标志位
     *
     * @return
     */
    @ResponseBody
    @RequestMapping("/updateBidInfoFlag")
    public Map updateBidInfoFlag(String id) {
        return jsonMsg.booleanToMap(basicInfoService.updateBidInfoFlag(id));
    }

    /**
     * 保存 一个 或 多个 标段模板信息
     *
     * @param
     * @return
     */
    @ResponseBody
    @RequestMapping("/saveBidInfos")
    public Map saveBidInfos(String ids, tblBbgsBidInformationModel data) {
        return basicInfoService.saveBidInfos(ids, data);
    }

    /**
     * 导出唱标报价模板（空）、投标人清单
     *
     * @return
     */
    @ResponseBody
    @RequestMapping("/exportFirstExcel")
    public Map exportAll(HttpServletRequest request) {
        String sourcePath = request.getSession().getServletContext().getRealPath("");
        try {
            //查询流程 导入基础数据 节点是否完成
            String flowMessage = basicInfoService.queryFlowState("501");
            if(flowMessage!=null){
                return jsonMsg.toJosn("error",flowMessage);
            }
            //唱标报价模板
            List<tblBbgsTendererModel> list = tendererService.queryList();
            String[][] head = {{"bidAbbreviaion", "标段简称"}, {"supplier", "供应商名称"}, {"id", "投标报价（万元）"}, {"id", "签字确认"}};
            if (list == null || list.size() == 0) {
                return jsonMsg.toJosn("error","无数据可供导出");
            }
//            new exportExcel().commonExportExcel("唱标标价", list.get(0).getBatchTitle() + "唱标表", head, list, sourcePath);
            String vbsPath = request.getSession().getServletContext().getRealPath("")+"cmdFile/print.vbs";
//            cmdOrderUtil.fixHeaerAndCss(sourcePath + "唱标报价模板.xls", "$1:$2",vbsPath);
            //          投标人清单记录
            Map map = basicInfoService.exportBidderList(sourcePath);

            if (map.get("state").equals("error")) {
                return map;
            } else if (map.get("state").equals("success")) {
//                cmdOrderUtil.fixHeaerAndCss(map.get("msg").toString(), "$1:$5",vbsPath);
            }
            return jsonMsg.booleanToMap(true);
        } catch (Exception exp) {
            exp.printStackTrace();
        }
        return jsonMsg.booleanToMap(false);
    }

    /**
     * 导出唱标报价模板（空）、投标人清单
     *
     * @return
     */
    @ResponseBody
    @RequestMapping("/exportRaceTalk")
    public Map exportRaceTalk(HttpServletRequest request) {
        String sourcePath = request.getSession().getServletContext().getRealPath("") + "WEB-INF/page/bbgs/excel/唱标报价模板/";
        try {
            //查询流程 导入基础数据 节点是否完成
            String flowMessage = basicInfoService.queryFlowState("501");
            if(flowMessage!=null){
                return jsonMsg.toJosn("error",flowMessage);
            }
            //唱标报价模板
            List<tblBbgsTendererModel> list = tendererService.queryList();

            String[][] head = {{"bidAbbreviaion", "标段简称"}, {"supplier", "供应商名称"}, {"id", "最终金额报价（万元）"}, {"id", "最终折扣比例"}, {"id", "签字确认"}};
            if (list == null || list.size() == 0) {
                return jsonMsg.toJosn("error","无数据可供导出");
            }
            new exportExcel().utilExportExcel("唱标标价", list.get(0).getBatchTitle() + "唱标表", head, list, sourcePath);
            String vbsPath = request.getSession().getServletContext().getRealPath("")+"cmdFile/print.vbs";
            cmdOrderUtil.fixHeaerAndCss(sourcePath + "唱标报价模板.xls", "$1:$2",vbsPath);

            ZipUtils.zip(sourcePath, sourcePath.replace("唱标报价模板/", "") + "唱标报价模板.zip", true);
            return jsonMsg.booleanToMap(true);
        } catch (Exception exp) {
            exp.printStackTrace();
        }
        return jsonMsg.booleanToMap(false);
    }



    /**
     * 导出专家组阅标记录
     *
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping("/exportSencondExcle")
    public Map exportSencondExcle(HttpServletRequest request) {
        String sourcePath = request.getSession().getServletContext().getRealPath("") + "WEB-INF/page/bbgs/excel/专家组阅标记录/";
        String vbsPath = request.getSession().getServletContext().getRealPath("")+"cmdFile/print.vbs";
        Map map = basicInfoService.exportBidRecordes(sourcePath,vbsPath);
        try {
            ZipUtils.zip(sourcePath, sourcePath.replace("专家组阅标记录/", "") + "专家组阅标记录.zip", true);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return map;
    }

    @RequestMapping("/getBidNameList")
    @ResponseBody
    public List getBidNameList(){
        return basicInfoService.getBidNameList();
    }
}
