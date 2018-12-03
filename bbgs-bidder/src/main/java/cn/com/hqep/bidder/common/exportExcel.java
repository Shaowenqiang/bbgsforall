package cn.com.hqep.bidder.common;

import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 导出工具类
 * Created by sssJL on 2017-10-16.
 */
public class exportExcel<T> {
    /**
     * 导出唱标报价模板
     * @param sheettitle sheet页名字
     * @param title 文件名
     * @param headers 导出数据字段名和数据库对应名 数组
     * @param result 导出数据
     */
    public void commonExportExcel(String sheettitle,String title, String[][] headers,
                             List<T> result,String parentPath) {
        try{
            // 声明一个工作薄
            HSSFWorkbook workbook = new HSSFWorkbook();
            // 生成一个表格
            HSSFSheet sheet = workbook.createSheet(sheettitle);
            // 设置表格默认列宽度为15个字节
            sheet.setDefaultColumnWidth((short) 40);
            // 生成一个样式
            HSSFCellStyle infostyle = workbook.createCellStyle();
            // 设置这些样式
            infostyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);//水平居中
            infostyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);//垂直居中
            infostyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);//边框
            infostyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);
            infostyle.setBorderRight(HSSFCellStyle.BORDER_THIN);
            infostyle.setBorderTop(HSSFCellStyle.BORDER_THIN);
            infostyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
            // 生成一个字体
            HSSFFont font = workbook.createFont();
            font.setFontHeightInPoints((short) 12);
            font.setFontName("宋体");
            // 把字体应用到当前的样式
            infostyle.setFont(font);

            // 生成一个样式
            HSSFCellStyle titlestyle = workbook.createCellStyle();
            titlestyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);//水平居中
            titlestyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);//垂直居中
            titlestyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);//边框
            titlestyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);
            titlestyle.setBorderRight(HSSFCellStyle.BORDER_THIN);
            titlestyle.setBorderTop(HSSFCellStyle.BORDER_THIN);
            titlestyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
            // 生成一个字体
            HSSFFont titlefont = workbook.createFont();
            titlefont.setFontHeightInPoints((short) 16);
            titlefont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
            titlefont.setFontName("宋体");
            // 把字体应用到当前的样式
            titlestyle.setFont(titlefont);
            HSSFRow row = null;
            HSSFRow  firstrow  = null;
            firstrow  = sheet.createRow(1);
            sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 3));
            for(int index =0;index<result.size();index++){
                T n =(T)result.get(index);
                row  = sheet.createRow(index+2);//第一行标题，第二行表格标题行
                row.setHeight((short)(16 * 32));
                Field[] fields = n.getClass().getDeclaredFields();
                for(int i=0;i<headers.length;i++)
                {
                    for(int j = 0;j<fields.length;j++)
                    {
                        Field field = fields[j];
                        String fieldName = field.getName();
                        if(Arrays.binarySearch(headers[i],fieldName)>=0)
                        {
                            if(index==1)
                            {
                                sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 3));
                                HSSFRow titlerow = sheet.createRow(0);
                                titlerow.setHeight((short)(16 * 82));
                                HSSFCell titleCell = titlerow.createCell(0);
                                titleCell.setCellStyle(titlestyle);
                                titleCell.setCellValue(title);

                            }
                            if(index==2) //产生表格标题行
                            {
                                HSSFCell firstcell = firstrow.createCell((short)i);
                                firstcell.setCellStyle(infostyle);//应用字体样式
                                firstrow.setHeight((short)(16 * 38));
                                firstcell.setCellValue(headers[i][1]);
                            }

                            HSSFCell cell = row.createCell((short) i);
                            cell.setCellStyle(infostyle);
                            String getMethodName = "get"+ fieldName.substring(0,1).toUpperCase()+fieldName.substring(1);
                            Class tCls = n.getClass();
                            Method mothod = tCls.getMethod(getMethodName,new Class[] {});
                            Object value = mothod.invoke(n, new Object[] {});

                            String textValue = null;
                            if (value instanceof Boolean) {
                                boolean bValue = (Boolean) value;
                                textValue = "true";
                                if (!bValue) {
                                    textValue = "false";
                                }
                            } else if (value instanceof Date) {
                                Date date = (Date) value;
                                SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
                                textValue = sdf.format(date);

                            } else if (value instanceof byte[]) {
                                row.setHeightInPoints(60);
                                sheet.setColumnWidth((short) i, (short) (35.6 * 80));
                                @SuppressWarnings("unused")
                                byte[] bsValue = (byte[]) value;
                                HSSFClientAnchor anchor = new HSSFClientAnchor(0,
                                        0, 1023, 255, (short) 6, index, (short) 6,
                                        index);
                                anchor.setAnchorType(2);
                            } else {
                                if (value != null) {
                                    textValue = value.toString();
                                }
                            }
                            if (textValue != null) {
                                //正则表达式验证是否是数字组成
                                Pattern p = Pattern.compile("^\\d+(\\.\\d+)?$");
                                Matcher matcher = p.matcher(textValue);
                                if (matcher.matches()) {
                                    cell.setCellValue(textValue);
                                } else {
                                    HSSFRichTextString richString = new HSSFRichTextString(
                                            textValue);
                                    cell.setCellValue(richString);
                                }
                            }else{
                                cell.setCellValue("-");
                            }
                        }
                    }
                    HSSFCell autographCell = row.createCell((short) headers.length-1);
                    autographCell.setCellStyle(infostyle);
                    autographCell.setCellValue("");
                    HSSFCell offerCell = row.createCell((short) headers.length-2);
                    offerCell.setCellStyle(infostyle);
                    offerCell.setCellValue("");
                }
            }
            String downPath = parentPath;
            File dirFile = new File(parentPath);
            if (!dirFile.exists()){
                dirFile.mkdir();
                dirFile = new File(downPath);
                if (!dirFile.exists()) {
                    dirFile.mkdirs();
                }
            }else {
                dirFile = new File(downPath);
                if (!dirFile.exists()) {
                    dirFile.mkdirs();
                }
            }
            String fileName = "唱标报价模板.xls";
            File excelFile = new File(dirFile, fileName);
            OutputStream outputStream = new FileOutputStream(excelFile);
            workbook.write(outputStream);
            outputStream.close();
        }catch (Exception exp){
            exp.printStackTrace();
        }

    }

    /**
     * 导出唱标报价模板(竞谈)
     * @param sheettitle sheet页名字
     * @param title 文件名
     * @param headers 导出数据字段名和数据库对应名 数组
     * @param result 导出数据
     */
    public void utilExportExcel(String sheettitle,String title, String[][] headers,
                                  List<T> result,String parentPath) {
        try{
            // 声明一个工作薄
            HSSFWorkbook workbook = new HSSFWorkbook();
            // 生成一个表格
            HSSFSheet sheet = workbook.createSheet(sheettitle);
            // 设置表格默认列宽度为15个字节
            sheet.setDefaultColumnWidth((short) 40);
            // 生成一个样式
            HSSFCellStyle infostyle = workbook.createCellStyle();
            // 设置这些样式
            infostyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);//水平居中
            infostyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);//垂直居中
            infostyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);//边框
            infostyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);
            infostyle.setBorderRight(HSSFCellStyle.BORDER_THIN);
            infostyle.setBorderTop(HSSFCellStyle.BORDER_THIN);
            infostyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
            // 生成一个字体
            HSSFFont font = workbook.createFont();
            font.setFontHeightInPoints((short) 12);
            font.setFontName("宋体");
            // 把字体应用到当前的样式
            infostyle.setFont(font);

            // 生成一个样式
            HSSFCellStyle titlestyle = workbook.createCellStyle();
            titlestyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);//水平居中
            titlestyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);//垂直居中
            titlestyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);//边框
            titlestyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);
            titlestyle.setBorderRight(HSSFCellStyle.BORDER_THIN);
            titlestyle.setBorderTop(HSSFCellStyle.BORDER_THIN);
            titlestyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
            // 生成一个字体
            HSSFFont titlefont = workbook.createFont();
            titlefont.setFontHeightInPoints((short) 16);
            titlefont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
            titlefont.setFontName("宋体");
            // 把字体应用到当前的样式
            titlestyle.setFont(titlefont);
            HSSFRow row = null;
            HSSFRow  firstrow  = null;
            firstrow  = sheet.createRow(1);
            sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 4));
            for(int index =0;index<result.size();index++){
                T n =(T)result.get(index);
                row  = sheet.createRow(index+2);//第一行标题，第二行表格标题行
                row.setHeight((short)(16 * 32));
                Field[] fields = n.getClass().getDeclaredFields();
                for(int i=0;i<headers.length;i++)
                {
                    for(int j = 0;j<fields.length;j++)
                    {
                        Field field = fields[j];
                        String fieldName = field.getName();
                        if(Arrays.binarySearch(headers[i],fieldName)>=0)
                        {
                            if(index==1)
                            {
                                sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 4));
                                HSSFRow titlerow = sheet.createRow(0);
                                titlerow.setHeight((short)(16 * 82));
                                HSSFCell titleCell = titlerow.createCell(0);
                                titleCell.setCellStyle(titlestyle);
                                titleCell.setCellValue(title);

                            }
                            if(index==2) //产生表格标题行
                            {
                                HSSFCell firstcell = firstrow.createCell((short)i);
                                firstcell.setCellStyle(infostyle);//应用字体样式
                                firstrow.setHeight((short)(16 * 38));
                                firstcell.setCellValue(headers[i][1]);
                            }

                            HSSFCell cell = row.createCell((short) i);
                            cell.setCellStyle(infostyle);
                            String getMethodName = "get"+ fieldName.substring(0,1).toUpperCase()+fieldName.substring(1);
                            Class tCls = n.getClass();
                            Method mothod = tCls.getMethod(getMethodName,new Class[] {});
                            Object value = mothod.invoke(n, new Object[] {});

                            String textValue = null;
                            if (value instanceof Boolean) {
                                boolean bValue = (Boolean) value;
                                textValue = "true";
                                if (!bValue) {
                                    textValue = "false";
                                }
                            } else if (value instanceof Date) {
                                Date date = (Date) value;
                                SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
                                textValue = sdf.format(date);

                            } else if (value instanceof byte[]) {
                                row.setHeightInPoints(60);
                                sheet.setColumnWidth((short) i, (short) (35.6 * 80));
                                @SuppressWarnings("unused")
                                byte[] bsValue = (byte[]) value;
                                HSSFClientAnchor anchor = new HSSFClientAnchor(0,
                                        0, 1023, 255, (short) 6, index, (short) 6,
                                        index);
                                anchor.setAnchorType(2);
                            } else {
                                if (value != null) {
                                    textValue = value.toString();
                                }
                            }
                            if (textValue != null) {
                                //正则表达式验证是否是数字组成
                                Pattern p = Pattern.compile("^\\d+(\\.\\d+)?$");
                                Matcher matcher = p.matcher(textValue);
                                if (matcher.matches()) {
                                    cell.setCellValue(textValue);
                                } else {
                                    HSSFRichTextString richString = new HSSFRichTextString(
                                            textValue);
                                    cell.setCellValue(richString);
                                }
                            }else{
                                cell.setCellValue("-");
                            }
                        }
                    }
                    HSSFCell autographCell = row.createCell((short) headers.length-1);
                    autographCell.setCellStyle(infostyle);
                    autographCell.setCellValue("");
                    HSSFCell rateCell = row.createCell((short) headers.length-2);
                    rateCell.setCellStyle(infostyle);
                    rateCell.setCellValue("");
                    HSSFCell offerCell = row.createCell((short) headers.length-3);
                    offerCell.setCellStyle(infostyle);
                    offerCell.setCellValue("");
                }
            }
            String downPath = parentPath;
            File dirFile = new File(parentPath);
            if (!dirFile.exists()){
                dirFile.mkdir();
                dirFile = new File(downPath);
                if (!dirFile.exists()) {
                    dirFile.mkdirs();
                }
            }else {
                dirFile = new File(downPath);
                if (!dirFile.exists()) {
                    dirFile.mkdirs();
                }
            }
            String fileName = "唱标报价模板.xls";
            File excelFile = new File(dirFile, fileName);
            OutputStream outputStream = new FileOutputStream(excelFile);
            workbook.write(outputStream);
            outputStream.close();
        }catch (Exception exp){
            exp.printStackTrace();
        }

    }

    /***
     * 导出Excel评标价格计算得分表util
     * @param sourcePath 文件存储地址
     * @param sheetTitle sheet页名
     * @param fileName 文件名
     * @param headers 字段-属性对应数组
     * @param result 导出数据
     * @param BidAbbreviaion 标段名称
     * @param response HttpServletResponse
     * @return 生成文件名称
     */
    public String commonExportExcelPrice(String sourcePath,String sheetTitle,String fileName,String[][] headers,
                    List<T> result,String BidAbbreviaion,HttpServletResponse response){
     try{
        //创建工作薄
        HSSFWorkbook wb = new HSSFWorkbook();
        //创建一个sheet页
        HSSFSheet sheet = wb.createSheet(sheetTitle);
        //设置默认列宽
        sheet.setDefaultColumnWidth((short)22);
        //设置一个样式
        HSSFCellStyle firstStyle = wb.createCellStyle();
        firstStyle.setAlignment(HSSFCellStyle.ALIGN_LEFT);//水平居中
        firstStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);//垂直居中
        //生成字体样式
        HSSFFont firstFont = wb.createFont();
        firstFont.setFontName("宋体");
        firstFont.setFontHeightInPoints((short)14);
        firstFont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
        // 把字体应用到当前的样式
        firstStyle.setFont(firstFont);

        //设置另一个样式
         HSSFCellStyle infoStyle = wb.createCellStyle();
         infoStyle.setAlignment(HSSFCellStyle.ALIGN_RIGHT);//水平居中
         infoStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);//垂直居中
         infoStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);//边框
         infoStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);
         infoStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);
         infoStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);
         infoStyle.setWrapText(true);
         //生成字体样式
         HSSFFont FontInfo = wb.createFont();
         FontInfo.setFontName("宋体");
         FontInfo.setFontHeightInPoints((short)12);
//         FontInfo.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
         // 把字体应用到当前的样式
         infoStyle.setFont(FontInfo);


         //设置第三个样式
         HSSFCellStyle StyleDou = wb.createCellStyle();
         StyleDou.setAlignment(HSSFCellStyle.ALIGN_LEFT);//水平居中
         StyleDou.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);//垂直居中
         StyleDou.setBorderBottom(HSSFCellStyle.BORDER_THIN);//边框
         StyleDou.setBorderLeft(HSSFCellStyle.BORDER_THIN);
         StyleDou.setBorderRight(HSSFCellStyle.BORDER_THIN);
         StyleDou.setBorderTop(HSSFCellStyle.BORDER_THIN);
         StyleDou.setWrapText(true);
         //生成字体样式
         HSSFFont FontDou = wb.createFont();
         FontDou.setFontName("宋体");
         FontDou.setFontHeightInPoints((short)12);
//         FontDou.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
         // 把字体应用到当前的样式
         StyleDou.setFont(FontDou);

         //设置第四个样式
         HSSFCellStyle StyleTitle = wb.createCellStyle();
         StyleTitle.setAlignment(HSSFCellStyle.ALIGN_LEFT);//水平居中
         StyleTitle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);//垂直居中
         StyleTitle.setBorderBottom(HSSFCellStyle.BORDER_THIN);//边框
         StyleTitle.setBorderLeft(HSSFCellStyle.BORDER_THIN);
         StyleTitle.setBorderRight(HSSFCellStyle.BORDER_THIN);
         StyleTitle.setBorderTop(HSSFCellStyle.BORDER_THIN);
         StyleTitle.setWrapText(true);
         //生成字体样式
         HSSFFont FontTitle = wb.createFont();
         FontTitle.setFontName("宋体");
         FontTitle.setFontHeightInPoints((short)12);
         FontTitle.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
         // 把字体应用到当前的样式
         StyleTitle.setFont(FontTitle);

        HSSFRow row = null;
        HSSFRow firstRow = null;
         HSSFRow[] rows = null;
         rows = new HSSFRow[headers.length];
         for (int i = 0;i<headers.length;i++){
             rows[i] = sheet.createRow( i+1);
             if(i==0){
                 rows[i].setHeight((short)(16 * 40));
             }else{
                 rows[i].setHeight((short)(16 * 28));
             }
             HSSFCell cellname = rows[i].createCell(0);
             cellname.setCellStyle(StyleTitle);
             cellname.setCellValue(headers[i][1]);
         }
        firstRow = sheet.createRow(0);
        sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 1));
        sheet.addMergedRegion(new CellRangeAddress(0, 0, 2, 3));
        sheet.addMergedRegion(new CellRangeAddress(0, 0, 4, 5));
        HSSFCell cell1 = firstRow.createCell(0);
        cell1.setCellStyle(firstStyle);
        String textValue = "评标价格计算得分表";
        cell1.setCellValue(textValue);
        cell1 = firstRow.createCell(2);
        cell1.setCellStyle(firstStyle);
        textValue = "招标编号：" + BidAbbreviaion;
        cell1.setCellValue(textValue);
        cell1 = firstRow.createCell(4);
        cell1.setCellStyle(firstStyle);
        textValue = "单位：万元人民币";
        cell1.setCellValue(textValue);
         if (result.size()>6){
             sheet.addMergedRegion(new CellRangeAddress(0, 0, 6, result.size()));
             cell1 = firstRow.createCell(6);
             cell1.setCellStyle(firstStyle);
             cell1.setCellValue("");
         }
            for (int index=0;index<result.size();index++) {
                T n = (T) result.get(index);
                Field[] fields = n.getClass().getDeclaredFields();
                for (int i = 0;i<headers.length;i++){
                    for (int f = 0; f < fields.length; f++) {
                        Field field = fields[f];
                        String fieldName = field.getName();
                        if (Arrays.binarySearch(headers[i], fieldName) >= 0) {
                            HSSFCell cell = rows[i].createCell((short) index + 1);
    //                        cell.setCellStyle(style);
                            String getMethodName = "get" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
                            Class tCls = n.getClass();
                            Method mothod = tCls.getMethod(getMethodName, new Class[]{});
                            Object value = mothod.invoke(n, new Object[]{});
                            textValue = null;
                            if (value instanceof Boolean) {
                                boolean bValue = (Boolean) value;
                                textValue = "true";
                                if (!bValue) {
                                    textValue = "false";
                                }
                            } else if (value instanceof byte[]) {
                                row.setHeightInPoints(60);
                                sheet.setColumnWidth((short) i, (short) (35.7 * 80));
                                @SuppressWarnings("unused")
                                byte[] bsValue = (byte[]) value;
                                HSSFClientAnchor anchor = new HSSFClientAnchor(0,
                                        0, 1023, 255, (short) 6, index, (short) 6,
                                        index);
                                anchor.setAnchorType(2);
                            }else if (value instanceof Date) {
                                Date date = (Date) value;
                                SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
                                textValue = sdf.format(date);
                            }  else {
                                if (value != null) {
                                    textValue = value.toString();
                                }
                            }
                            if (textValue != null) {
                                //正则表达式验证是否是数字组成
                                Pattern p = Pattern.compile("^\\d+(\\.\\d+)?$");
                                Matcher matcher = p.matcher(textValue);
                                if (matcher.matches()) {
                                    cell.setCellStyle(infoStyle);
                                    cell.setCellValue(textValue);
                                } else {
                                    HSSFRichTextString richString = new HSSFRichTextString(
                                            textValue);
                                    cell.setCellStyle(StyleDou);
                                    cell.setCellValue(richString);
                                }
                            }
                        }

                    }
            }
            }
            if (result.size()<5){
                for (int num=result.size();num<5-result.size();num++){
                    for (int r=0;r<rows.length;r++){
                        HSSFCell cell = rows[r].createCell(num+1);
                        cell.setCellStyle(infoStyle);
                        cell.setCellValue("");
                    }
                }
            }
        try {
            File file = new File(sourcePath + "/");
            //如果文件夹不存在则创建
            if  (!file .exists()  && !file .isDirectory())
            {
                file .mkdir();
            }
            String newTitle = getFileName(fileName,sourcePath);
            System.out.println( sourcePath + "\\" + newTitle + ".xls");
            OutputStream out = new FileOutputStream(sourcePath + "/" + newTitle + ".xls");
            wb.write(out);
            out.close();
            return  newTitle;
        }catch (Exception exp){
            exp.printStackTrace();
        }
     }catch (Exception exp){
         exp.printStackTrace();
     }
     return null;
    }


    /***
     * 导出Excel评标价格计算得分表
     * @param sheetName sheet页名字
     * @param fileName 文件名称
     * @param headers 表头和字段对应数组
     * @param result 导出数据集合
     * @param BidAbbreviaion 标段简称
     * @param response HttpServletResponse
     */
    public void priceExcel(String sheetName,String fileName,String[][] headers,
                          List<T> result,String BidAbbreviaion,HttpServletResponse response){
        try{
            //创建工作薄
            HSSFWorkbook wb = new HSSFWorkbook();
            //创建一个sheet页
            HSSFSheet sheet = wb.createSheet(sheetName);
            //设置默认列宽
            sheet.setDefaultColumnWidth((short)22);
            //设置一个样式
            HSSFCellStyle firstStyle = wb.createCellStyle();
            firstStyle.setAlignment(HSSFCellStyle.ALIGN_LEFT);//水平居中
            firstStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);//垂直居中
            firstStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);//边框
            firstStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);
            firstStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);
            firstStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);
            //生成字体样式
            HSSFFont firstFont = wb.createFont();
            firstFont.setFontName("宋体");
            firstFont.setFontHeightInPoints((short)14);
            firstFont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
            // 把字体应用到当前的样式
            firstStyle.setFont(firstFont);

            //设置另一个样式
            HSSFCellStyle Styleinfo = wb.createCellStyle();
            Styleinfo.setAlignment(HSSFCellStyle.ALIGN_RIGHT);//水平居中
            Styleinfo.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);//垂直居中
            Styleinfo.setBorderBottom(HSSFCellStyle.BORDER_THIN);//边框
            Styleinfo.setBorderLeft(HSSFCellStyle.BORDER_THIN);
            Styleinfo.setBorderRight(HSSFCellStyle.BORDER_THIN);
            Styleinfo.setBorderTop(HSSFCellStyle.BORDER_THIN);
            Styleinfo.setWrapText(true);
            //生成字体样式
            HSSFFont FontInfo = wb.createFont();
            FontInfo.setFontName("宋体");
            FontInfo.setFontHeightInPoints((short)12);
            // 把字体应用到当前的样式
            Styleinfo.setFont(FontInfo);


            //设置第三个样式
            HSSFCellStyle StyleDou = wb.createCellStyle();
            StyleDou.setAlignment(HSSFCellStyle.ALIGN_LEFT);//水平居中
            StyleDou.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);//垂直居中
            StyleDou.setBorderBottom(HSSFCellStyle.BORDER_THIN);//边框
            StyleDou.setBorderLeft(HSSFCellStyle.BORDER_THIN);
            StyleDou.setBorderRight(HSSFCellStyle.BORDER_THIN);
            StyleDou.setBorderTop(HSSFCellStyle.BORDER_THIN);
            StyleDou.setWrapText(true);
            //生成字体样式
            HSSFFont FontDou = wb.createFont();
            FontDou.setFontName("宋体");
            FontDou.setFontHeightInPoints((short)12);
            // 把字体应用到当前的样式
            StyleDou.setFont(FontDou);

            //设置第四个样式
            HSSFCellStyle StyleTitle = wb.createCellStyle();
            StyleTitle.setAlignment(HSSFCellStyle.ALIGN_LEFT);//水平居中
            StyleTitle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);//垂直居中
            StyleTitle.setBorderBottom(HSSFCellStyle.BORDER_THIN);//边框
            StyleTitle.setBorderLeft(HSSFCellStyle.BORDER_THIN);
            StyleTitle.setBorderRight(HSSFCellStyle.BORDER_THIN);
            StyleTitle.setBorderTop(HSSFCellStyle.BORDER_THIN);
            StyleTitle.setWrapText(true);
            //生成字体样式
            HSSFFont FontTitle = wb.createFont();
            FontTitle.setFontName("宋体");
            FontTitle.setFontHeightInPoints((short)12);
            FontTitle.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
            // 把字体应用到当前的样式
            StyleTitle.setFont(FontTitle);

            HSSFRow row = null;
            HSSFRow firstRow = null;
            HSSFRow[] rows = null;
            rows = new HSSFRow[headers.length];
            for (int i = 0;i<headers.length;i++){
                rows[i] = sheet.createRow( i+1);
                if(i==0){
                    rows[i].setHeight((short)(16 * 40));
                }else{
                    rows[i].setHeight((short)(16 * 28));
                }
                HSSFCell cellname = rows[i].createCell(0);
                cellname.setCellStyle(StyleTitle);
                cellname.setCellValue(headers[i][1]);
            }
            firstRow = sheet.createRow(0);
            sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 1));
            sheet.addMergedRegion(new CellRangeAddress(0, 0, 2, 3));
            sheet.addMergedRegion(new CellRangeAddress(0, 0, 4, 5));
            HSSFCell cell1 = firstRow.createCell(0);
            cell1.setCellStyle(firstStyle);
            String textValue = "评标价格计算得分表";
            cell1.setCellValue(textValue);
            cell1 = firstRow.createCell(2);
            cell1.setCellStyle(firstStyle);
            textValue = "招标编号：" + BidAbbreviaion;
            cell1.setCellValue(textValue);
            cell1 = firstRow.createCell(4);
            cell1.setCellStyle(firstStyle);
            textValue = "单位：万元人民币";
            cell1.setCellValue(textValue);
            if (result.size()>6){
                sheet.addMergedRegion(new CellRangeAddress(0, 0, 6, result.size()));
                cell1 = firstRow.createCell(6);
                cell1.setCellStyle(firstStyle);
                cell1.setCellValue("");
            }
            for (int index=0;index<result.size();index++) {
                T n = (T) result.get(index);
                Field[] fields = n.getClass().getDeclaredFields();
                for (int i = 0;i<headers.length;i++){
                    for (int f = 0; f < fields.length; f++) {
                        Field field = fields[f];
                        String fieldName = field.getName();
                        if (Arrays.binarySearch(headers[i], fieldName) >= 0) {
                            HSSFCell cell = rows[i].createCell((short) index + 1);
                            //                        cell.setCellStyle(style);
                            String getMethodName = "get" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
                            Class tCls = n.getClass();
                            Method mothod = tCls.getMethod(getMethodName, new Class[]{});
                            Object value = mothod.invoke(n, new Object[]{});
                            textValue = null;
                            if (value instanceof Boolean) {
                                boolean bValue = (Boolean) value;
                                textValue = "true";
                                if (!bValue) {
                                    textValue = "false";
                                }
                            } else if (value instanceof byte[]) {
                                row.setHeightInPoints(60);
                                sheet.setColumnWidth((short) i, (short) (35.7 * 80));
                                @SuppressWarnings("unused")
                                byte[] bsValue = (byte[]) value;
                                HSSFClientAnchor anchor = new HSSFClientAnchor(0,
                                        0, 1023, 255, (short) 6, index, (short) 6,
                                        index);
                                anchor.setAnchorType(2);
                            }else if (value instanceof Date) {
                                Date date = (Date) value;
                                SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
                                textValue = sdf.format(date);
                            }  else {
                                if (value != null) {
                                    textValue = value.toString();
                                }
                            }
                            if (textValue != null) {
                                //正则表达式验证是否是数字组成
                                Pattern p = Pattern.compile("^\\d+(\\.\\d+)?$");
                                Matcher matcher = p.matcher(textValue);
                                if (matcher.matches()) {
                                    cell.setCellStyle(Styleinfo);
                                    cell.setCellValue(textValue);
                                } else {
                                    HSSFRichTextString richString = new HSSFRichTextString(
                                            textValue);
                                    cell.setCellStyle(StyleDou);
                                    cell.setCellValue(richString);
                                }
                            }
                        }

                    }
                }
            }
            if (result.size()<5){
                for (int num=result.size();num<5-result.size();num++){
                    for (int r=0;r<rows.length;r++){
                        HSSFCell cell = rows[r].createCell(num+1);
                        cell.setCellStyle(Styleinfo);
                        cell.setCellValue("");
                    }
                }
            }
            response.setContentType("application/vnd.ms-excel;charset=UTF-8");
            response.setHeader("content-disposition",
                    "attachment;filename=" + new String(fileName.getBytes("gb2312"), "ISO8859-1")+".xls");
            OutputStream os = response.getOutputStream();// 取得输出流
            wb.write(os);
            os.close();
        }catch (Exception exp){
            exp.printStackTrace();
        }
    }


    /***
     * 导出时候判断有无同名文件，如果有创建（index+1）名字文件
     * @param title 当前文件名
     * @param path 文件路径
     * @return 创建文件名
     */
    public static String getFileName(String title,String path)
    {
        File file = new File(path);
        String [] fileName = file.list();
        int index = 1;
        boolean flag = false;
        for (int i=0;i<fileName.length;i++){
            String[] name = fileName[i].split("\\.");
            if (fileName[i].split("\\.")[1].equals("xls") && fileName[i].split("\\.")[1].equals("xlsx")){continue;}
            name =name[0].split("\\(");
            if (title.equals(name[0])){
                flag = true;
                if (name.length==1)
                {
                    continue;
                }
                name =name[1].split("\\)");
                if(Integer.parseInt(name[0])>index){
                    index = Integer.parseInt(name[0]);
                }
                if(Integer.parseInt(name[0])==index){
                    index ++;
                }
            }
        }
        if(flag){
            title = title+"(" + index + ")";
        }
        return title;
    }

}
