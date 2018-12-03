package cn.com.hqep.templet.common;

import org.apache.poi.hssf.usermodel.HSSFDataValidationHelper;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.ss.util.CellRangeAddressList;
import org.apache.poi.xssf.usermodel.XSSFDataValidationConstraint;
import org.apache.poi.xssf.usermodel.XSSFDataValidationHelper;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.util.*;

/**
 * Created by swq on 2017/10/21.
 */
public class ExcelUtil_poi {
    /**
     * 导出技术或商务打分模板
     * @param sourcePathexcelName 保存路径+名字
     * @param bidAtt              标段简称
     * @param list                厂商列表
     * @param xlsTemplate         模板路径
     * @param type                技术或者是商务
     */

    public static void exportScoreTemplate(String sourcePathexcelName, String bidAtt, List<String> list, String xlsTemplate, String type,String weight) {
        try {
            File pathFile = new File(xlsTemplate);
            InputStream is = new FileInputStream(pathFile);
            Workbook workbook = null;
            if (xlsTemplate.endsWith(".xls")) {
                //2003
                workbook = new HSSFWorkbook(is);
            } else if (xlsTemplate.endsWith(".xlsx")) {
                //2007
                workbook = new XSSFWorkbook(is);
            }

            Sheet sheet = workbook.getSheetAt(0);
            CellStyle firstRowCellStyle = sheet.getRow(0).getCell(3).getCellStyle();
            sheet.protectSheet("edit");
            int rows = sheet.getLastRowNum();
            CellStyle unlockRow = workbook.createCellStyle();
            unlockRow.setLocked(false);
            //厂商列样式
            CellStyle unlockStyle = workbook.createCellStyle();
            unlockStyle.setAlignment(CellStyle.ALIGN_CENTER);//水平居中
            unlockStyle.setVerticalAlignment(CellStyle.VERTICAL_CENTER);//垂直居中
            unlockStyle.setBorderBottom(CellStyle.BORDER_THIN);//边框
            unlockStyle.setBorderLeft(CellStyle.BORDER_THIN);
            unlockStyle.setBorderRight(CellStyle.BORDER_THIN);
            unlockStyle.setBorderTop(CellStyle.BORDER_THIN);
            unlockStyle.setAlignment(CellStyle.ALIGN_CENTER);
            Font unlockStyleFont = workbook.createFont();
            unlockStyleFont.setFontHeightInPoints((short) 8);
            unlockStyleFont.setFontName("宋体");
            unlockStyle.setFont(unlockStyleFont);
            unlockStyle.setWrapText(true);
            unlockStyle.setLocked(false);
            //厂商列样式
            CellStyle warpText = workbook.createCellStyle();
            warpText.setAlignment(CellStyle.ALIGN_CENTER);//水平居中
            warpText.setVerticalAlignment(CellStyle.VERTICAL_CENTER);//垂直居中
            warpText.setBorderBottom(CellStyle.BORDER_THIN);//边框
            warpText.setBorderLeft(CellStyle.BORDER_THIN);
            warpText.setBorderRight(CellStyle.BORDER_THIN);
            warpText.setBorderTop(CellStyle.BORDER_THIN);
            warpText.setAlignment(CellStyle.ALIGN_CENTER);
            Font warpTextFont = (Font) workbook.createFont();
            warpTextFont.setFontHeightInPoints((short) 8);
            warpTextFont.setFontName("宋体");
            warpText.setFont(warpTextFont);
            warpText.setWrapText(true);
            //求和样式
            CellStyle sumCss = workbook.createCellStyle();
            sumCss.setAlignment(CellStyle.ALIGN_CENTER);//水平居中
            sumCss.setVerticalAlignment(CellStyle.VERTICAL_CENTER);//垂直居中
            sumCss.setBorderBottom(CellStyle.BORDER_THIN);//边框
            sumCss.setBorderLeft(CellStyle.BORDER_THIN);
            sumCss.setBorderRight(CellStyle.BORDER_THIN);
            sumCss.setBorderTop(CellStyle.BORDER_THIN);
            sumCss.setAlignment(CellStyle.ALIGN_CENTER);
            Font sumCssFont = workbook.createFont();
            sumCssFont.setFontHeightInPoints((short) 9);
            sumCssFont.setFontName("Arial");
            sumCssFont.setBoldweight(Font.BOLDWEIGHT_BOLD);
            sumCss.setFont(sumCssFont);
            sumCss.setWrapText(true);
            int rowNum_real = rows - 2;
            if (type.equals("技术")) {
                rowNum_real = rows - 2;
            }
            for (int i = 1; i < rowNum_real; i++) {
                if (i == 1) {
                    int colNum = 4;
                    for (String supplier : list) {
                        sheet.setColumnWidth(colNum, sheet.getColumnWidth(4));
                        Cell cell = sheet.getRow(i).createCell(colNum);
                        cell.setCellValue(supplier);
                        cell.setCellStyle(warpText);
                        colNum++;
                    }
                } else {
                    for (int j = 0; j < list.size(); j++) {
//                        Cell cell = sheet.getRow(i).createCell(j + 4);
////                        cell.setCellType(Cell.CELL_TYPE_NUMERIC);
////                        cell.setCellValue("");
////                        cell.setCellStyle(unlockStyle);
                        //获取此行满分项分数
                        int num = (int) sheet.getRow(i).getCell(2).getNumericCellValue();
                        //根据满分项制作分数列表
                        String[] scoreSelect = new String[num+1];
                        for (int n=0;n<num+1;n++){
                            //倒叙排列分数数组
                            scoreSelect[n]=String.valueOf(num-n);
                        }
                        //获取操作的单元格
                        Cell cell = sheet.getRow(i).createCell(j+4);
                        //选中下拉列表生效的单元格范围 现在使用的范围是只有一个单元格
                        CellRangeAddressList addressList = new CellRangeAddressList(i,i,j+4,j+4);
                        //创建 03 版数据验证对象
                        DataValidationHelper helper = new HSSFDataValidationHelper((HSSFSheet) sheet);
                        //在数据验证对象中建立新的分数选项
                        DataValidationConstraint dvConstraint = helper.createExplicitListConstraint(scoreSelect);
                        //创建新的数据验证对象
                        DataValidation dataValidation = helper.createValidation(dvConstraint, addressList);
                        //插入数据验证
                        sheet.addValidationData(dataValidation);
                        //解锁单元格
                        cell.setCellStyle(unlockStyle);
                    }
                }
            }
            for (int j = 0; j < list.size(); j++) {
                if (type.equals("商务")) {
                    Cell cell = sheet.getRow(rows - 2).createCell(j + 4);
                    cell.setCellType(Cell.CELL_TYPE_FORMULA);
                    cell.setCellStyle(sumCss);
                    cell.setCellFormula("SUM(" + excelColIndexToStr(j + 5) + "3:" + excelColIndexToStr(j + 5) + (rows - 2) + ")");
                    cell = sheet.getRow(rows - 1).createCell(j + 4);
                    cell.setCellType(Cell.CELL_TYPE_FORMULA);
                    cell.setCellStyle(sumCss);
                    cell.setCellFormula("C" + (rows) + "*SUM(" + excelColIndexToStr(j + 5) + "3:" + excelColIndexToStr(j + 5) + (rows - 2) + ")");
                    if (weight != null){
                        cell = sheet.getRow(rows - 1).getCell(2);
                        cell.setCellValue(String.valueOf(Double.parseDouble(weight) *100)+"%");
                    }
                }
                if (type.equals("技术")) {
                    Cell cell = sheet.getRow(rows - 2).createCell(j + 4);
                    cell.setCellType(Cell.CELL_TYPE_FORMULA);
                    cell.setCellStyle(sumCss);
                    cell.setCellFormula("SUM(" + excelColIndexToStr(j + 5) + "3:" + excelColIndexToStr(j + 5) + (rows - 2) + ")");
                    cell = sheet.getRow(rows - 1).createCell(j + 4);
                    cell.setCellType(Cell.CELL_TYPE_FORMULA);
                    cell.setCellStyle(sumCss);
                    cell.setCellFormula("C" + (rows) + "*SUM(" + excelColIndexToStr(j + 5) + "3:" + excelColIndexToStr(j + 5) + (rows - 2) + ")");
                    if (weight != null){
                        cell = sheet.getRow(rows - 1).getCell(2);
                        cell.setCellValue(String.valueOf(Double.parseDouble(weight) *100)+"%");
                    }
                }
            }
            //动态设置列宽
            int width_1 = sheet.getColumnWidth(0)+sheet.getColumnWidth(1)+
                    sheet.getColumnWidth(2)+sheet.getColumnWidth(3);
            int col_total = sheet.getRow(1).getLastCellNum()-4;
            int row_size = 10;
            int ys = col_total%row_size;
            if(ys>0){
                int last_width = (39200-width_1)/ys;
                for (int i=0;i<ys;i++){
                    sheet.setColumnWidth(sheet.getRow(1).getLastCellNum()-1-i,last_width);
                }
            }
            for (int i=0;i<sheet.getRow(1).getLastCellNum()-3;i++){
                sheet.getRow(0).createCell(i+3);
                sheet.getRow(0).getCell(i+3).setCellStyle(unlockStyle);
            }


            sheet.getRow(0).getCell(3).setCellValue(bidAtt);
            sheet.getRow(0).getCell(3).setCellStyle(firstRowCellStyle);
            //合并第一行
            for (int i=0;i<col_total/row_size;i++){
                sheet.addMergedRegion(new CellRangeAddress(0,0,3+10*i,3+10*i+10));
            }
//            if((sheet.getRow(0).getLastCellNum()-1)>(4+(col_total/row_size)*10)){
                sheet.addMergedRegion(new CellRangeAddress(0,0,3+(col_total/row_size)*10,sheet.getRow(0).getLastCellNum()-1));
//            }
            sheet.getRow(0).getCell(3+(col_total/row_size)*10).setCellValue(bidAtt);
            //设置打印区域
            workbook.setPrintArea(0,0,sheet.getRow(1).getLastCellNum()-1,0,sheet.getLastRowNum());
            sheet.setForceFormulaRecalculation(true);
            OutputStream out = new FileOutputStream(sourcePathexcelName);
            workbook.write(out);
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 根据数字算出excel ABCDE...
     *
     * @param columnIndex
     * @return
     */
    public static String excelColIndexToStr(int columnIndex) {
        if (columnIndex <= 0) {
            return null;
        }
        String columnStr = "";
        columnIndex--;
        do {
            if (columnStr.length() > 0) {
                columnIndex--;
            }
            columnStr = ((char) (columnIndex % 26 + (int) 'A')) + columnStr;
            columnIndex = (int) ((columnIndex - columnIndex % 26) / 26);
        } while (columnIndex > 0);
        return columnStr;
    }

    /***
     * 导出时候判断有无同名文件，如果有创建（index+1）名字文件
     * @param title 当前文件名
     * @param path 文件路径
     * @return 创建文件名
     */
    public static String getFileName(String title, String path) {
        File file = new File(path);
        String[] fileName = file.list();

        int index = 1;
        boolean flag = false;
        for (int i = 0; i < fileName.length; i++) {
            String[] name = fileName[i].split("\\.");
            if (fileName[i].split("\\.")[1].equals("xls") && fileName[i].split("\\.")[1].equals("xlsx")) {
                continue;
            }
            name = name[0].split("\\(");
            if (title.equals(name[0])) {
                flag = true;
                if (name.length == 1) {
                    continue;
                }
                name = name[1].split("\\)");
                if (Integer.parseInt(name[0]) > index) {
                    index = Integer.parseInt(name[0]);
                }
                if (Integer.parseInt(name[0]) == index) {
                    index++;
                }
            }
        }
        if (flag) {
            title = title + "(" + index + ")";
        }
        return title;
    }

    /**
     * 保存文件
     *
     * @param inputStream
     * @param fileName
     */
    public static void savePic(InputStream inputStream, String fileName, String path) {

        OutputStream os = null;
        try {
//            String path = "D:\\testFile\\";
            // 2、保存到临时文件
            // 1K的数据缓冲
            byte[] bs = new byte[1024];
            // 读取到的数据长度
            int len;
            // 输出的文件流保存到本地文件

            File tempFile = new File(path);
            if (!tempFile.exists()) {
                tempFile.mkdirs();
            }
            os = new FileOutputStream(tempFile.getPath() + File.separator + fileName);
            // 开始读取
            while ((len = inputStream.read(bs)) != -1) {
                os.write(bs, 0, len);
            }

        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 完毕，关闭所有链接
            try {
                os.close();
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * poi获取Excel内容
     * @param file
     * @param key
     * @return
     */
    public static Map getExcelList(File file, String key) {
        Map<String, Object> mapAll = new LinkedHashMap<>();
        mapAll.put("state","success");
        mapAll.put("文件夹名",key);
        Map<String,String> map = new LinkedHashMap<>();
        InputStream is = null;
        try {
            is = new FileInputStream(file);
            Workbook workbook = WorkbookFactory.create(is);
            Sheet sheet = null;
            Row row = null;
            Cell cell = null;
            //直接获取第一个sheet页
            sheet = workbook.getSheetAt(0);
//            获取总行数
            int rows = sheet.getLastRowNum();
//            int rows = sheet.getPhysicalNumberOfRows();
            if (rows==0){
                return jsonMsg.toJosn("error",key+"第1个sheet页为空");
            }
//            if (rows < 11) {
//                return jsonMsg.toJosn("error", "Excel文件：" + key + "的第一个sheet页不符合上传标准 请检查行列");
//            }
            boolean flag = true;
            for (int i = 0; i < rows; i++) {
                row = sheet.getRow(i);
                //获取每一行的列数
                int cols = sheet.getRow(i).getPhysicalNumberOfCells();
                if (i == 1 && cols < 5) {
                    return jsonMsg.toJosn("error", "Excel文件：" + key + "的第一个sheet页不符合上传标准 请检查行列");
                }
                if (i > 1) {
                    cols = sheet.getRow(1).getPhysicalNumberOfCells();
                }
                for (int j = 0; j < cols; j++) {
                    cell = row.getCell(j);
                    if (i>0&&i<(rows-2)&&j>3){
                        if (cell.getCellType()==Cell.CELL_TYPE_NUMERIC){
                            if(cell==null||String.valueOf(cell.getNumericCellValue()).equals("")){
                                return jsonMsg.toJosn("error",key+"第1个sheet页 第"+(i+1)+"行 第"+(j+1)+"列 值为空");
                            }
                        }else if (cell==null||cell.getCellType()==Cell.CELL_TYPE_STRING){
                            if(cell.getStringCellValue().equals("")){
                                return jsonMsg.toJosn("error",key+"第1个sheet页 第"+(i+1)+"行 第"+(j+1)+"列 值为空");
                            }
                        }else if(cell.getCellType() == Cell.CELL_TYPE_BLANK){
                            return jsonMsg.toJosn("error",key+"第1个sheet页 第"+(i+1)+"行 第"+(j+1)+"列 值为空");
                        }

                    }
                    if(j==1){
                       if(cell.getStringCellValue().equals("总分")){
                           flag = false;
                       }
                    }
                    if (i == 0 && j == 3) {
                        //获取标段简称 为空时返回错误信息
                        int bidType = cell.getCellType();
                        if (bidType != Cell.CELL_TYPE_STRING) {
                            return jsonMsg.toJosn("error", key + "第1个sheet页 D1 单元格格式应为字符串格式");
                        }
                        String bidName = cell.getStringCellValue();
                        if (bidName.equals("")) {
                            return jsonMsg.toJosn("error", key + "第1个sheet页 D1 单元格 内容不能为空");
                        }
                        mapAll.put("标段简称", bidName);
                    }
                    if (row.getCell(1).getStringCellValue().equals("总分")){
                        flag = false;
                        for (int k=4;k<cols;k++){
                            if(!sheet.getRow(1).getCell(k).getStringCellValue().equals("")){
                                if (row.getCell(k).getCellType() != Cell.CELL_TYPE_FORMULA){
                                    return jsonMsg.toJosn("error",key+"第1个sheet页"+(i+1)+"行 第"+(k+1)+"列 的总分单元格格式应该是公式类型");
                                }
                                String score = String.valueOf(row.getCell(k).getNumericCellValue());
                                if (row.getCell(k).getNumericCellValue()>100){
                                    return jsonMsg.toJosn("error",key+"第1个sheet页"+(i+1)+"行 第"+(k+1)+"列 总分信息 超过了100分");
                                }
                                if(!score.matches("^[0-9,.]*$")){
                                    return jsonMsg.toJosn("error",key+"第1个sheet页"+(i+1)+"行 第"+(k+1)+"列 总分信息只能是数字");
                                }

                                if (score.length()>4){
                                    return jsonMsg.toJosn("error",key+"第1个sheet页"+(i+1)+"行 第"+(k+1)+"列 总分信息 长度过长");
                                }
                                map.put(sheet.getRow(1).getCell(k).getStringCellValue(),score);
                            }
                        }
                        mapAll.put("总分",map);
                    }
                }
            }
            if (flag){
                return jsonMsg.toJosn("error",key+" 第1个sheet页 总分信息 缺失");
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (InvalidFormatException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return mapAll;
    }

    /**
     * @param sourcePathexcelName 保存到哪个目录下++文件名
     * @param xlsTemplate         模板文件目录
     * @param supplier            创建多个sheet页 （名字）
     */
    public static Map exportBidRecord(String sourcePathexcelName, String xlsTemplate, Map<String, Set<String>> supplier, String title, String type) {
        try {
            File pathFile = new File(xlsTemplate);
            InputStream is = new FileInputStream(pathFile);
            Workbook workbook = null;
            if (xlsTemplate.endsWith(".xls")) {
                //2003
                workbook = new HSSFWorkbook(is);
            } else if (xlsTemplate.endsWith(".xlsx")) {
                //2007
                workbook = new XSSFWorkbook(is);
            }
            int i = 0;
            Sheet sheet = null;
            Row row = null;
            Cell cell = null;
//            单元格样式
            //厂商列样式
            CellStyle cellStyle = workbook.createCellStyle();
            cellStyle.setAlignment(CellStyle.ALIGN_CENTER);//水平居中
            cellStyle.setVerticalAlignment(CellStyle.VERTICAL_CENTER);//垂直居中
            cellStyle.setBorderBottom(CellStyle.BORDER_THIN);//边框
            cellStyle.setBorderLeft(CellStyle.BORDER_THIN);
            cellStyle.setBorderRight(CellStyle.BORDER_THIN);
            cellStyle.setBorderTop(CellStyle.BORDER_THIN);
            cellStyle.setAlignment(CellStyle.ALIGN_CENTER);
            Font warpTextFont = (Font) workbook.createFont();
            warpTextFont.setFontHeightInPoints((short) 11);
            warpTextFont.setFontName("宋体");
            cellStyle.setFont(warpTextFont);
            cellStyle.setWrapText(true);
            //只解锁
            CellStyle unlockStyle2=workbook.createCellStyle();
            unlockStyle2.setLocked(false);
            //解锁单元格
            CellStyle unlockStyle=workbook.createCellStyle();
            unlockStyle.setLocked(false);
            unlockStyle.setAlignment(CellStyle.ALIGN_CENTER);
            unlockStyle.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
            unlockStyle.setBorderBottom(CellStyle.BORDER_THIN);//边框
            unlockStyle.setBorderLeft(CellStyle.BORDER_THIN);
            unlockStyle.setBorderRight(CellStyle.BORDER_THIN);
            unlockStyle.setBorderTop(CellStyle.BORDER_THIN);
            unlockStyle.setFont(warpTextFont);
            unlockStyle.setWrapText(true);
            for (String s : supplier.keySet()) {
                workbook.cloneSheet(0);
                workbook.setSheetName(i + 1, s);
                sheet = workbook.getSheet(s);
                sheet.protectSheet("edit");
                sheet.getRow(0).getCell(0).setCellValue(title);
                int j = 3;
                for (String s1 : supplier.get(s)) {
                    row = sheet.createRow(j);
                    int total = 10;
                    if (type == "商务") {
                        total = 16;
                    }
                    for (int col = 0; col < total; col++) {
                        row.createCell(col);
                        row.setHeightInPoints(30);
                        row.getCell(col).setCellStyle(unlockStyle);
                    }
                    cell = sheet.getRow(j).getCell(0);
                    cell.setCellValue(j - 2);
                    cell.setCellStyle(cellStyle);
                    cell = sheet.getRow(j).getCell(1);
                    cell.setCellValue(s);
                    cell.setCellStyle(cellStyle);
                    cell = sheet.getRow(j).getCell(2);
                    cell.setCellValue(s1);
                    cell.setCellStyle(cellStyle);
                    j++;
                }
                Row lastRow = sheet.createRow(supplier.get(s).size() + 3);
                lastRow.setRowStyle(unlockStyle2);
                lastRow.setHeightInPoints(20);
                lastRow.createCell(2).setCellValue("主审人：");
                lastRow.createCell(4).setCellValue("复查人：");
                lastRow.createCell(7).setCellValue("组长：");
                i = i + 1;
            }
            workbook.removeSheetAt(0);
            OutputStream out = new FileOutputStream(sourcePathexcelName);
            workbook.write(out);
            out.close();
            return jsonMsg.toJosn("success",sourcePathexcelName);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return jsonMsg.booleanToMap(false);
    }

    /**
     * 投标清单导出
     *
     * @param sourcePathexcelName 保存路径+文件名
     * @param title               题头
     * @param supplierMap         供应商列表
     * @param xlsTemplate         模板保存路径
     */
    public static String exportBiderList(String sourcePathexcelName, String title, Map<String, Map<String, String>> supplierMap, String xlsTemplate) {
        try {
            File pathFile = new File(xlsTemplate);
            InputStream is = new FileInputStream(pathFile);
            Workbook workbook = null;
            String suffix = ".xls";
            if (xlsTemplate.endsWith(".xls")) {
                //2003
                suffix = ".xls";
                workbook = new HSSFWorkbook(is);

            } else if (xlsTemplate.endsWith(".xlsx")) {
                //2007
                workbook = new XSSFWorkbook(is);
                suffix = ".xlsx";
            }
            int i = 0;
            Sheet sheet = null;
            Row row = null;
            Cell cell = null;
//            单元格样式

            //厂商列样式
            CellStyle cellStyle = workbook.createCellStyle();
            cellStyle.setAlignment(CellStyle.ALIGN_CENTER);//水平居中
            cellStyle.setVerticalAlignment(CellStyle.VERTICAL_CENTER);//垂直居中
            cellStyle.setBorderBottom(CellStyle.BORDER_THIN);//边框
            cellStyle.setBorderLeft(CellStyle.BORDER_THIN);
            cellStyle.setBorderRight(CellStyle.BORDER_THIN);
            cellStyle.setBorderTop(CellStyle.BORDER_THIN);
            cellStyle.setLocked(true);
            Font warpTextFont = workbook.createFont();
            warpTextFont.setFontHeightInPoints((short) 11);
            warpTextFont.setFontName("宋体");
            cellStyle.setFont(warpTextFont);
            cellStyle.setWrapText(true);
            //解锁单元格
            CellStyle unlockStyle=workbook.createCellStyle();
            unlockStyle.setLocked(false);
            unlockStyle.setAlignment(CellStyle.ALIGN_CENTER);
            unlockStyle.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
            unlockStyle.setBorderBottom(CellStyle.BORDER_THIN);//边框
            unlockStyle.setBorderLeft(CellStyle.BORDER_THIN);
            unlockStyle.setBorderRight(CellStyle.BORDER_THIN);
            unlockStyle.setBorderTop(CellStyle.BORDER_THIN);
            unlockStyle.setFont(warpTextFont);
            unlockStyle.setWrapText(true);
            for (String s : supplierMap.keySet()) {
                workbook.cloneSheet(0);
                workbook.setSheetName(i + 1, s.substring(0, s.indexOf("-")));

                sheet = workbook.getSheet(s.substring(0, s.indexOf("-")));

//                sheet.protectSheet("edit");
//                sheet.getRow(0).getCell(0).setCellValue(title);
                sheet.getRow(1).getCell(0).setCellValue("项目名称："+title+"             " +"分标编号："+ s.substring(0, s.indexOf("-")) +"        分标名称：" + s);

                //从第五行开始
                int j = 5;
                for (String s1 : supplierMap.get(s).keySet()) {
                    row = sheet.createRow(j);
                    int total = sheet.getRow(4).getLastCellNum();
                    for (int col = 0; col < total; col++) {
                        row.createCell(col);
                        row.setHeightInPoints(30);
                        row.getCell(col).setCellStyle(unlockStyle);
                    }
                    cell = sheet.getRow(j).getCell(0);
                    cell.setCellValue(j - 4);
                    cell.setCellStyle(cellStyle);
                    cell = sheet.getRow(j).getCell(1);
                    cell.setCellValue(s1);
                    cell.setCellStyle(cellStyle);
                    cell = sheet.getRow(j).getCell(2);
                    cell.setCellValue(supplierMap.get(s).get(s1));
                    cell.setCellStyle(cellStyle);
                    j++;
                }
                //设置打印区域
                workbook.setPrintArea(i+1,0,sheet.getRow(4).getLastCellNum()-1,0,sheet.getLastRowNum());
                workbook.setRepeatingRowsAndColumns(i + 1,-1,-1,0,4);
                i = i + 1;
            }
//            workbook.removeSheetAt(0);
            workbook.setSheetHidden(0,true);
            workbook.setActiveSheet(1);
            OutputStream out = new FileOutputStream(sourcePathexcelName + suffix);
            workbook.write(out);
            out.close();
            return sourcePathexcelName + suffix;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
