package cn.com.hqep.bidder.action;

import cn.com.hqep.bidder.common.ExcelUtil;
import cn.com.hqep.bidder.common.exportExcel;
import cn.com.hqep.bidder.model.tblBbgsPriceInformationModel;
import cn.com.hqep.bidder.service.priceInfoService;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.ContextLoader;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * Created by sssJL on 2017-10-13.
 * 处理价格打分信息 Controller 类
 */
@Controller
@RequestMapping("/priceInfo")
public class priceInfoAction {

    @Autowired
    priceInfoService service;


    /***
     * 查询：
     * 根据参数 标段简称 得到所有对应供应商 返回前台
     * @param model 带参数的实体
     * @return 实体集合
     */
    @ResponseBody
    @RequestMapping("/queryListByName")
    public List<tblBbgsPriceInformationModel> queryListForName(tblBbgsPriceInformationModel model){
        List<tblBbgsPriceInformationModel> list = null;
        try {
            list = service.queryListByName(model);
        }catch (Exception exp){
            exp.printStackTrace();
            list = null;
        }
        return list;
    }

    /***
     * 导出excel：
     * 导出Excel评标价格计算得分表
     * @param request HttpServletRequest
     * @param response HttpServletResponse
     * @param model 带有查询条件的实体
     */
    @Deprecated
    @ResponseBody
    @RequestMapping("commonExportExcelPrice")
    public void commonExportExcelPrice(HttpServletRequest request, HttpServletResponse response, tblBbgsPriceInformationModel model){
        String BidAbbreviaion = model.getBidAbbreviaion();
        String sourcePath = request.getRealPath("") + "WEB-INF\\page\\bbgs\\excelHtml";
        List<tblBbgsPriceInformationModel> list = service.queryListByName(model);
        String[][] head = {{"supplier", "供应商名称"}, {"price", "投标价格"},
                {"price", "算数修正后的投标价格"},
                {"a1", "a1"},
                {"n", "n"},
                {"a2", "a2"},
                {"p", "p"},
                {"basePrice", "评标基准价"},
                {"priceScore", "评标价格得分"},
                {"sort", "排序"}};
        new exportExcel().commonExportExcelPrice(sourcePath,"价格",
                "评标价格计算得分表", head, list,BidAbbreviaion, response);
    }


    /***
     * 根据标段简称的得到数据，
     * 生成HTM
     * @param request HttpServletRequest
     * @param response HttpServletResponse
     * @param model 带有查询条件的实体
     * @return 返回结果信息
     */
    @ResponseBody
    @RequestMapping("/CreateExcelHtm")
    public Map CreateExcelHtm(HttpServletRequest request,HttpServletResponse response,tblBbgsPriceInformationModel model) {
        Map info = new HashMap();
        try{
            String sourcePath = request.getRealPath("") + "WEB-INF\\page\\bbgs\\excelHtml\\";
            String BidAbbreviaion =  model.getBidAbbreviaion();
            List<tblBbgsPriceInformationModel> list = service.queryListByName(model);
            String[][] head = {{"supplier", "投标人"}, {"price", "投标价格"},
                    {"price", "算数修正后的投标价格"},
                    {"a1", "a1"},
                    {"n", "n"},
                    {"a2", "a2"},
                    {"p", "p"},
                    {"basePrice", "评标基准价"},
                    {"priceScore", "评标价格得分"},
                    {"sort", "排序"}};
            String fileName = new exportExcel().commonExportExcelPrice(sourcePath,"价格",
                    "评标价格计算得分表", head, list,BidAbbreviaion, response);
            if(fileName != null){
                ExcelUtil.ExcelToHtm(sourcePath, fileName + ".xls");
                info.put("flag","success");
                info.put("srcpath","\\page\\bbgs\\excelHtml\\"+fileName+ ".htm");
            }else {
                info.put("flag","fail");
                info.put("srcpath","");
            }
        }catch (Exception exp){
            exp.printStackTrace();
            info.put("flag","fail");
            info.put("srcpath","");
        }
        return info;
    }


    /**
     * 导出评标价格计算得分表
     * 浏览器有反应的
     * @param response HttpServletResponse
     * @param model 带有查询条件的实体
     */
    @RequestMapping("/priceExcel")
    public void priceExcel(HttpServletResponse response,tblBbgsPriceInformationModel model) {
        try{
            String BidAbbreviaion =  model.getBidAbbreviaion();
            List<tblBbgsPriceInformationModel> list = service.queryListByName(model);
            String[][] head = {{"supplier", "供应商名称"}, {"price", "投标价格"},
                    {"price", "算数修正后的投标价格"},
                    {"a1", "a1"},
                    {"n", "n"},
                    {"a2", "a2"},
                    {"p", "p"},
                    {"basePrice", "评标基准价"},
                    {"priceScore", "评标价格得分"},
                    {"sort", "排序"}};
            new exportExcel().priceExcel("价格",
                    "评标价格计算得分表", head, list,BidAbbreviaion, response);
        }catch (Exception exp){
            exp.printStackTrace();
        }
    }


    /***
     * 根据标段简称的得到数据，
     * 生成HTM  修改版
     * @param model 带有查询条件的实体
     * @return 返回结果信息
     */
    @ResponseBody
    @RequestMapping("/CreateExcelHtmHHH")
    public Map CreateExcelHtmHHH(tblBbgsPriceInformationModel model,boolean flag_delete) {
        try{
            String bidAbbreviaion =  model.getBidAbbreviaion();
            List<tblBbgsPriceInformationModel> list = service.queryListByName(model);
           return this.exportExcelForHtmHHHH(list,"招标价格得分表",bidAbbreviaion,flag_delete);

        }catch (Exception exp){
            exp.printStackTrace();
        }
        return new HashMap();

    }

    /**
     * 生成HTM  修改版 调用
     * @param list htm数据集合
     * @param fileName 文件名称
     * @param bidAbbreviaion 标段简称
     * @param flag_delete 是否删除缓存
     * @return 反馈生成结果map
     */
    public Map exportExcelForHtmHHHH(List<tblBbgsPriceInformationModel> list ,String fileName,String bidAbbreviaion,boolean flag_delete){
        Map info = new HashMap();
        try{
            String webPath = ContextLoader.getCurrentWebApplicationContext().getServletContext().getRealPath("/");
            String tempPath = webPath + "export/priceScore/招标价格得分表模板.xlsx";
            System.out.println(tempPath);
            OutputStream outputStream;
            tblBbgsPriceInformationModel  model = new tblBbgsPriceInformationModel();
            //读取模板
            XSSFWorkbook workbook = new XSSFWorkbook(new FileInputStream(tempPath));
            Sheet sheet = workbook.getSheetAt(0);
            Row row = sheet.getRow(0);
            Cell cell = row.getCell(0);
            cell.setCellValue("评标价格计算得分表");
            cell = row.getCell(2);
            cell.setCellValue("招标编号："+bidAbbreviaion);
            for (int i=0;i<list.size();i++){
                model = (tblBbgsPriceInformationModel)list.get(i);
                sheet.getRow(1).getCell(i+2).setCellValue(model.getSupplier().toString()); //投标人
                sheet.getRow(2).getCell(i+2).setCellValue(model.getPrice()); //投标价格
                sheet.getRow(3).getCell(i+2).setCellValue(0); //算术修正值
                sheet.getRow(4).getCell(i+2).setCellValue(model.getPrice()); //算数修正后的投标价格
                sheet.getRow(5).getCell(i+2).setCellValue(0); //投标声明（如有无条件升、降价和折扣）
                sheet.getRow(6).getCell(i+2).setCellValue(model.getPrice()); //投标总价


                sheet.getRow(7).getCell(i+2).setCellValue("");
                sheet.getRow(8).getCell(i+2).setCellValue("");
                sheet.getRow(9).getCell(i+2).setCellValue("");
                sheet.getRow(10).getCell(i+2).setCellValue(0);

                sheet.getRow(11).getCell(i+2).setCellValue(0);
                sheet.getRow(12).getCell(i+2).setCellValue(model.getPrice());
                sheet.getRow(13).getCell(i+2).setCellValue(model.getA1());
                sheet.getRow(14).getCell(i+2).setCellValue(model.getN());
                sheet.getRow(15).getCell(i+2).setCellValue(model.getA2());
                sheet.getRow(16).getCell(i+2).setCellValue(model.getP());

                sheet.getRow(17).getCell(i+2).setCellValue(model.getBasePrice());
                sheet.getRow(18).getCell(i+2).setCellValue(model.getPriceScore());
                sheet.getRow(19).getCell(i+2).setCellValue(model.getPriceWeight());
                sheet.getRow(19).getCell(i+2).setCellValue(model.getPriceWeight());

            }
            fileName += UUID.randomUUID().toString().replace("-","");
            String sourcePath = webPath + "WEB-INF\\page\\bbgs\\excelHtml\\HTM\\";
            File dirFile = new File(sourcePath);
            if(dirFile.exists() && flag_delete){
                deleteDir(dirFile);
            }
            if(!dirFile.exists()){
                dirFile.mkdir();
            }
            File file = new File(dirFile,fileName+".xlsx");
            outputStream = new FileOutputStream(file);
            workbook.write(outputStream);
            outputStream.close();
            if(fileName != null){
                ExcelUtil.ExcelToHtm(sourcePath, fileName + ".xlsx");
                info.put("flag","success");
                info.put("srcpath","\\page\\bbgs\\excelHtml\\HTM\\"+fileName+ ".htm");
            }else {
                info.put("flag","fail");
                info.put("srcpath","");
            }
        }catch (Exception exp){
            exp.printStackTrace();
            info.put("flag","fail");
            info.put("srcpath","");
        }
        return info;

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
