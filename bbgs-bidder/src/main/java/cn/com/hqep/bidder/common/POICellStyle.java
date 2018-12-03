package cn.com.hqep.bidder.common;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sssJL on 2017-11-14.
 */
public class POICellStyle {
    public List<HSSFCellStyle> getHssfCellStyle(HSSFWorkbook workbook){
        List<HSSFCellStyle> list = new ArrayList<HSSFCellStyle>();
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
        infostyle.setWrapText(true);
        // 生成一个字体
        HSSFFont font = workbook.createFont();
        font.setFontHeightInPoints((short) 12);
        font.setFontName("宋体");
        // 把字体应用到当前的样式
        infostyle.setFont(font);
        list.add(infostyle);
        return  list;

    }
    public List<XSSFCellStyle> getXssfCellStyle(XSSFWorkbook workbook){
        List<XSSFCellStyle> list = new ArrayList<XSSFCellStyle>();
        // 生成一个样式
        XSSFCellStyle titleStyle = workbook.createCellStyle();
        // 设置这些样式
        titleStyle.setAlignment(XSSFCellStyle.ALIGN_CENTER);//水平居中
        titleStyle.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER);//垂直居中
        titleStyle.setBorderBottom(XSSFCellStyle.BORDER_THIN);//底边框
        titleStyle.setBorderLeft(XSSFCellStyle.BORDER_THIN);//左边框
        titleStyle.setBorderRight(XSSFCellStyle.BORDER_THIN);//右边框
        titleStyle.setBorderTop(XSSFCellStyle.BORDER_THIN);//上边框
        titleStyle.setWrapText(true);
        // 生成一个字体
        XSSFFont font = workbook.createFont();
        font.setFontHeightInPoints((short) 14);
        font.setFontName("宋体");
        font.setBoldweight(XSSFFont.BOLDWEIGHT_BOLD);//粗体显示
        // 把字体应用到当前的样式
        titleStyle.setFont(font);
        list.add(titleStyle);

        // 生成一个样式
        XSSFCellStyle infoStyle = workbook.createCellStyle();
        // 设置这些样式
        infoStyle.setAlignment(XSSFCellStyle.ALIGN_CENTER);//水平居中
        infoStyle.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER);//垂直居中
        infoStyle.setBorderBottom(XSSFCellStyle.BORDER_THIN);//底边框
        infoStyle.setBorderLeft(XSSFCellStyle.BORDER_THIN);//左边框
        infoStyle.setBorderRight(XSSFCellStyle.BORDER_THIN);//右边框
        infoStyle.setBorderTop(XSSFCellStyle.BORDER_THIN);//上边框
        infoStyle.setWrapText(true);
        // 生成一个字体
        XSSFFont infoFont = workbook.createFont();
        infoFont.setFontHeightInPoints((short) 12);
        infoFont.setFontName("宋体");
//        infoFont.setBoldweight(XSSFFont.BOLDWEIGHT_BOLD);//粗体显示
        // 把字体应用到当前的样式
        infoStyle.setFont(infoFont);
        list.add(infoStyle);
        return  list;

    }
}
