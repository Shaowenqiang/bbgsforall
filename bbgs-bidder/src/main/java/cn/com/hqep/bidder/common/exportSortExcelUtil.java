package cn.com.hqep.bidder.common;

import cn.com.hqep.bidder.model.tblBbgsBidInformationModel;
import cn.com.hqep.bidder.model.tblBbgsPackageSupplierModel;
import cn.com.hqep.bidder.model.tblBbgsPackageSupplierRaceModel;
import cn.com.hqep.bidder.model.tblBbgsTechnologyBusinessModel;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.context.ContextLoader;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.List;

/**
 * Created by sssJL on 2017-10-23.
 * 导出综合排序工具类
 */
public class exportSortExcelUtil<T> {

    /**
     * 导出综合排序
     * @param FileName 文件名
     * @param result 导出数据
     * @param response HttpServletResponse
     */
    public void exportSortExcel(String FileName,String title,
                                  List<T> result, HttpServletResponse response) {
        try{
            String webPath = ContextLoader.getCurrentWebApplicationContext().getServletContext().getRealPath("/");
            String tempPath = webPath + "export/sort/综合排序模板.xlsx";
            System.out.println(tempPath);
            OutputStream outputStream;
            tblBbgsPackageSupplierModel model = new tblBbgsPackageSupplierModel();
            //读取模板
            XSSFWorkbook workbook = new XSSFWorkbook(new FileInputStream(tempPath));
            Sheet sheet = workbook.getSheetAt(0);
            Row row = sheet.getRow(0);
            Cell cell = row.getCell(1);
            cell.setCellValue(title);
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
            for (int i=0;i<result.size();i++){
                model = (tblBbgsPackageSupplierModel)result.get(i);
                if (model != null){
                    row = sheet.createRow(i+3);
                    row.setHeightInPoints((short)34);
                    cell = row.createCell(0);
                    cell.setCellStyle(infostyle);

//                    cell.setCellType(Cell.CELL_TYPE_FORMULA);
//                    cell.setCellValue("=AVERAGE(D124:J124)");

                    cell.setCellValue(model.getBidAbbreviaion());

                    cell = row.createCell(1);
                    cell.setCellStyle(infostyle);
                    cell.setCellValue(model.getSupplier());

                    cell = row.createCell(2);
                    cell.setCellStyle(infostyle);
                    cell.setCellValue(model.getTechnologyScore());

                    cell = row.createCell(3);
                    cell.setCellStyle(infostyle);
                    cell.setCellValue(model.getPriceScore());

                    cell = row.createCell(4);
                    cell.setCellStyle(infostyle);
                    cell.setCellValue(model.getBusinessScore());

                    cell = row.createCell(5);
                    cell.setCellStyle(infostyle);
                    cell.setCellValue(model.getTotalScore());

                    cell = row.createCell(6);
                    cell.setCellStyle(infostyle);
                    cell.setCellValue(model.getOffer());

                    cell = row.createCell(7);
                    cell.setCellStyle(infostyle);
                    cell.setCellValue(model.getSort());
                }
            }
            response.setContentType("application/vnd.ms-excel;charset=UTF-8");
            response.setHeader("content-disposition",
                    "attachment;filename=" + new String(FileName.getBytes("gb2312"), "ISO8859-1")+".xlsx");
            OutputStream os = response.getOutputStream();// 取得输出流
            workbook.write(os);
            os.close();
        }catch (Exception exp){
            exp.printStackTrace();
        }

    }


    /**
     * 导出综合排序
     * 修改
     * @param fileName 文件名
     * @param result 导出数据
     */
    public void exportSortExcel(String zipDir,String fileName,String title,
                                List<T> result) {
        try{
            String webPath = ContextLoader.getCurrentWebApplicationContext().getServletContext().getRealPath("/");
            String tempPath = webPath + "export/sort/综合排序模板.xlsx";
            System.out.println(tempPath);
            OutputStream outputStream;
            tblBbgsPackageSupplierModel model = new tblBbgsPackageSupplierModel();
            //读取模板
            XSSFWorkbook workbook = new XSSFWorkbook(new FileInputStream(tempPath));
            Sheet sheet = workbook.getSheetAt(0);
            Row row = sheet.getRow(0);
            Cell cell = row.getCell(1);
            cell.setCellValue(title);
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
            String addMergedRegionSign = "";
            int startRowNum = 3;
            for (int i=0;i<result.size();i++){
                model = (tblBbgsPackageSupplierModel)result.get(i);
                if (model != null){
                    row = sheet.createRow(i+3);
                    row.setHeightInPoints((short)34);
                    cell = row.createCell(0);
                    cell.setCellStyle(infostyle);

                    cell.setCellValue(model.getBidAbbreviaion());

                    cell = row.createCell(1);
                    cell.setCellStyle(infostyle);
                    cell.setCellValue(model.getSupplier());

                    cell = row.createCell(2);
                    cell.setCellStyle(infostyle);
                    cell.setCellValue(model.getTechnologyScore());

                    cell = row.createCell(3);
                    cell.setCellStyle(infostyle);
                    cell.setCellValue(model.getPriceScore());

                    cell = row.createCell(4);
                    cell.setCellStyle(infostyle);
                    cell.setCellValue(model.getBusinessScore());

                    cell = row.createCell(5);
                    cell.setCellStyle(infostyle);
                    cell.setCellValue(model.getTotalScore());

                    cell = row.createCell(6);
                    cell.setCellStyle(infostyle);
                    cell.setCellValue(model.getOffer());

                    cell = row.createCell(7);
                    cell.setCellStyle(infostyle);
                    cell.setCellValue(model.getBasePrice());

                    cell = row.createCell(8);
                    cell.setCellStyle(infostyle);
                    cell.setCellValue(model.getSort());
                    if (i==0){
                        addMergedRegionSign = model.getBidAbbreviaion();
                        startRowNum = 3;
                    }else if(i==result.size()-1){
                        if (addMergedRegionSign.equals(model.getBidAbbreviaion())){
                            sheet.addMergedRegion(new CellRangeAddress(startRowNum, i+3, 0, 0));
                        } else{
                            sheet.addMergedRegion(new CellRangeAddress(startRowNum, i+2, 0, 0));
                        }
                    } else{
                        if (! addMergedRegionSign.equals(model.getBidAbbreviaion())){
                            sheet.addMergedRegion(new CellRangeAddress(startRowNum, i+2, 0, 0));
                            addMergedRegionSign = model.getBidAbbreviaion();
                            startRowNum = i+3;
                        }
                    }
                }
            }
            File dirFile = new File(zipDir);
            if (!dirFile.exists()) {
                dirFile.mkdirs();
            }
            fileName = fileName + "评分汇总表.xlsx";
            File excelFile = new File(dirFile, fileName);
            outputStream = new FileOutputStream(excelFile);
            workbook.write(outputStream);
            outputStream.close();
        }catch (Exception exp){
            exp.printStackTrace();
        }

    }

    /**
     * 导出综合排序
     * 修改
     * @param fileName 文件名
     * @param result 导出数据
     */
    public void RaceExportSortExcel(String zipDir,String fileName,String title,
                                List<T> result) {
        try{
            String webPath = ContextLoader.getCurrentWebApplicationContext().getServletContext().getRealPath("/");
            String tempPath = webPath + "export/sort/综合排序模板（竞谈）.xlsx";
            System.out.println(tempPath);
            OutputStream outputStream;
            tblBbgsPackageSupplierRaceModel model = new tblBbgsPackageSupplierRaceModel();
            //读取模板
            XSSFWorkbook workbook = new XSSFWorkbook(new FileInputStream(tempPath));
            Sheet sheet = workbook.getSheetAt(0);
            Row row = sheet.getRow(0);
            Cell cell = row.getCell(1);
            cell.setCellValue(title);
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
            String addMergedRegionSign = "";
            int startRowNum = 3;
            for (int i=0;i<result.size();i++){
                model = (tblBbgsPackageSupplierRaceModel)result.get(i);
                if (model != null){
                    row = sheet.createRow(i+3);
                    row.setHeightInPoints((short)34);
                    cell = row.createCell(0);
                    cell.setCellStyle(infostyle);

                    cell.setCellValue(model.getBidAbbreviaion());

                    cell = row.createCell(1);
                    cell.setCellStyle(infostyle);
                    cell.setCellValue(model.getSupplier());

                    cell = row.createCell(2);
                    cell.setCellStyle(infostyle);
                    cell.setCellValue(model.getTechnologyScore());

                    cell = row.createCell(3);
                    cell.setCellStyle(infostyle);
                    cell.setCellValue(model.getPriceScore());

                    cell = row.createCell(4);
                    cell.setCellStyle(infostyle);
                    cell.setCellValue(model.getBusinessScore());

                    cell = row.createCell(5);
                    cell.setCellStyle(infostyle);
                    cell.setCellValue(model.getTotalScore());

                    cell = row.createCell(6);
                    cell.setCellStyle(infostyle);
                    cell.setCellValue(model.getModOffer());

                    cell = row.createCell(7);
                    cell.setCellStyle(infostyle);
                    if(model.getFinalRate().equals("") || model.getFinalRate() == null){
                        cell.setCellValue(model.getFinalRate());
                    }else{
                        cell.setCellValue(model.getFinalRate()+"%");
                    }

                    cell = row.createCell(8);
                    cell.setCellStyle(infostyle);
                    cell.setCellValue(model.getBasePrice());

                    cell = row.createCell(9);
                    cell.setCellStyle(infostyle);
                    cell.setCellValue(model.getSort());
                    if (i==0){
                        addMergedRegionSign = model.getBidAbbreviaion();
                        startRowNum = 3;
                    }else if(i==result.size()-1){
                        if (addMergedRegionSign.equals(model.getBidAbbreviaion())){
                            sheet.addMergedRegion(new CellRangeAddress(startRowNum, i+3, 0, 0));
                        } else{
                            sheet.addMergedRegion(new CellRangeAddress(startRowNum, i+2, 0, 0));
                        }
                    } else{
                        if (! addMergedRegionSign.equals(model.getBidAbbreviaion())){
                            sheet.addMergedRegion(new CellRangeAddress(startRowNum, i+2, 0, 0));
                            addMergedRegionSign = model.getBidAbbreviaion();
                            startRowNum = i+3;
                        }
                    }
                }
            }
            File dirFile = new File(zipDir);
            if (!dirFile.exists()) {
                dirFile.mkdirs();
            }
            fileName = fileName + "评分汇总表.xlsx";
            File excelFile = new File(dirFile, fileName);
            outputStream = new FileOutputStream(excelFile);
            workbook.write(outputStream);
            outputStream.close();
        }catch (Exception exp){
            exp.printStackTrace();
        }

    }

}
