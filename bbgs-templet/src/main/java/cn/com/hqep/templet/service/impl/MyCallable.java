package cn.com.hqep.templet.service.impl;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.InputStream;
import java.util.*;
import java.util.concurrent.Callable;

/**
 * ${todo}(这里用一句话描述这个类的作用)
 *
 * @author swq
 * @date 2018-11-30 10:48
 */
class MyCallable implements Callable<Object> {
    private Map result;
    private MultipartFile file;

    MyCallable(MultipartFile file) {
        this.file = file;
    }

    public List call() throws Exception {
        result = this.readExcel(file, 2, this.getExcelArray());
        List list = new ArrayList();
        list.add(file);
        list.add(result);
        return list;
    }


    private List<ArrayList> getExcelArray() {
        List<ArrayList> result = new ArrayList();
        ArrayList list1 = new ArrayList();
        ArrayList list2 = new ArrayList();
        result.add(list1);
        result.add(list2);
        list1.add("序号");
        list2.add("row_type");
        list1.add("单价\n" +
                "（含税 元）");
        list2.add("row_price");
        list1.add("单价加权系数(该系数仅作为价格评审使用)");
        list2.add("row_price_weight");
        return result;
    }

    private Map readExcel(MultipartFile file, int titleRowNum, List<ArrayList> rowMapKeys) {
        Workbook wb;
        Map result = new LinkedHashMap();
        try {
            InputStream is = new BufferedInputStream(file.getInputStream(), 1024);
            if (file.getOriginalFilename().endsWith(".xls")) {
                wb = new HSSFWorkbook(is);
            } else if (file.getOriginalFilename().endsWith(".xlsx")) {
                wb = new XSSFWorkbook(is);
            } else {
                wb = new XSSFWorkbook();
            }
//            String filename = file.getOriginalFilename();
            CommonsMultipartFile f = (CommonsMultipartFile) file;
            String filename = f.getFileItem().getName();

            int sheetNum = wb.getNumberOfSheets();
            for (int i = 0; i < sheetNum; i++) {
                Sheet sheet = wb.getSheetAt(i);
                String bidAbbr = sheet.getRow(0).getCell(2).getStringCellValue().trim();
                System.out.println(filename);
                String s1 = bidAbbr.substring(0,3);
                String s2 = bidAbbr.substring(3,bidAbbr.length());
                bidAbbr = s1 +"-"+ s2;
                String supplier = sheet.getRow(1).getCell(2).getStringCellValue().trim();
                String totalPrice = formatExcelCellTypeToString(sheet.getRow(sheet.getLastRowNum()).getCell(11));
                totalPrice = totalPrice.trim();
                totalPrice = totalPrice.replace("万元","");
                totalPrice = totalPrice.replace("元","");
                if (totalPrice.trim()==""){
                    totalPrice = "0";
                }
                totalPrice = this.decimalFormatNum(Double.valueOf(totalPrice) / 10000, 7);
                System.out.println(totalPrice);
                Map sheetMap = new LinkedHashMap();
                result.put(wb.getSheetName(i), sheetMap);
                int rowNum = sheet.getPhysicalNumberOfRows();
                for (int j = titleRowNum + 1; j < rowNum - 1; j++) {
                    Map rowMap = new LinkedHashMap();
                    sheetMap.put(String.valueOf(j), rowMap);
                    Row row = sheet.getRow(j);
                    int colNum = row.getPhysicalNumberOfCells();
                    for (int k = 0; k < colNum; k++) {
                        Cell cell = row.getCell(k);
//                        System.out.println(i+"sheet页"+j+"行"+k+"列");
                        String colContent = this.formatExcelCellTypeToString(cell);
                        String title = sheet.getRow(titleRowNum).getCell(k).getStringCellValue().trim();
                        int keyIndex = rowMapKeys.get(0).indexOf(title);
                        if (keyIndex >= 0) {
                            rowMap.put("filename", filename + "文件中" + wb.getSheetName(i) + "sheet页,第" + j + "行，第" + k + "列");
                            rowMap.put("bid_abbreviaion", bidAbbr);
                            rowMap.put("supplier", supplier);
                            rowMap.put("total_price", totalPrice.toString());
                            rowMap.put(rowMapKeys.get(1).get(keyIndex), colContent);
                        }
                    }
                }
            }
        } catch (Exception e) {

            e.printStackTrace();
        }
        return result;
    }


//    CellType 类型 值
//    CELL_TYPE_NUMERIC 数值型 0
//    CELL_TYPE_STRING 字符串型 1
//    CELL_TYPE_FORMULA 公式型 2
//    CELL_TYPE_BLANK 空值 3
//    CELL_TYPE_BOOLEAN 布尔型 4
//    CELL_TYPE_ERROR 错误 5

    /**
     * cell内容转为String
     *
     * @param cell
     * @return
     */
    private String formatExcelCellTypeToString(Cell cell) {
        String result = "";
        if(cell==null){
            result = "0";
        } else if (cell.getCellType() == 0) {
            result = this.decimalFormatNum(cell.getNumericCellValue(), 7);
        } else if (cell.getCellType() == 1) {
            result = cell.getStringCellValue().trim();
        } else if (cell.getCellType() == 2) {
            try {
                result = String.valueOf(cell.getNumericCellValue());
            } catch (IllegalStateException e) {
                result = String.valueOf(cell.getRichStringCellValue());
            }
        } else if (cell.getCellType() == 3) {
            result = "";
        } else if (cell.getCellType() == 4) {
            boolean b = cell.getBooleanCellValue();
            if (b) {
                result = "true";
            } else {
                result = "false";
            }
        } else if (cell.getCellType() == 5) {
            result = "";
        }
        return result;
    }

    private String getUUid() {
        return UUID.randomUUID().toString().replace("-", "");
    }

    // 格式化小数点位数
    public String decimalFormatNum(double num, int ws) {
        return String.format("%." + ws + "f", num);
    }
}