package cn.com.hqep.templet.common;

import cn.com.hqep.templet.model.tblBbgsPackageSupplierModel;
import com.jacob.activeX.ActiveXComponent;
import com.jacob.com.Dispatch;
import com.jacob.com.Variant;
import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.PrintSetup;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.*;

public class ExcelUtil {
    /**
     * 将excel 转成htm保存到 sourcePath 并命名为excelName
     *
     * @param sourcePath htm 保存路径
     * @param excelName  Excel 转成htm 后 htm 命名
     * @return
     */
    public static String ExcelToHtm(String sourcePath, String excelName) {
        // 启动excel
        ActiveXComponent app = new ActiveXComponent("Excel.Application");
        String htmName = sourcePath + excelName.replace(".xlsx", "").replace(".xls", "") + ".htm";
        try {
            app.setProperty("Visible", new Variant(false));
            Dispatch excels = app.getProperty("Workbooks").toDispatch();
            Dispatch excel = Dispatch.invoke(
                    excels,
                    "Open",
                    Dispatch.Method,
                    new Object[]{sourcePath + excelName, new Variant(false),
                            new Variant(true)}, new int[1]).toDispatch();
            Dispatch sheets = Dispatch.get(excel, "sheets").toDispatch();
            int count = Dispatch.get(sheets, "count").toInt();
            List combol = new ArrayList();
            for (int pdfi = 1; pdfi <= count; pdfi++) {
                Dispatch sheet = Dispatch.invoke(sheets, "Item", Dispatch.Get, new Object[]{pdfi}, new int[1]).toDispatch();
            }
            Dispatch.invoke(excel, "SaveAs", Dispatch.Method, new Object[]{
                    htmName, new Variant("44")}, new int[1]);
            Variant f = new Variant(false);
            Dispatch.call(excel, "Close", f);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            app.invoke("Quit", new Variant[]{});
        }
        return htmName;
    }

    /**
     * 读取Excel 格式为 .xls 的数据
     *
     * @param file   文件
     * @param varify 验证的列数
     * @return
     */
    public static Map readXls(MultipartFile file, List<Integer> varify) {
        HashMap map = new HashMap();
        try {
            InputStream is = new BufferedInputStream(file.getInputStream(), 1024);
            Workbook rwb = Workbook.getWorkbook(is);
            Sheet[] sheets = rwb.getSheets();
            if (sheets != null) {
                for (int i = 0; i < sheets.length; i++) {
                    HashMap sheetmap = new HashMap();
                    int sheetPage = i;
                    String msg = "";
                    String title = "";
                    boolean flag = false;
                    String sheetName = sheets[i].getName();
                    List<HashMap> list = new ArrayList<HashMap>();
                    int rows = sheets[i].getRows();
                    int cols = sheets[i].getColumns();
                    if (rows == 0 || cols == 0) {
                        msg = msg + "第" + (i + 1) + "个sheet页 为空；";
                        flag = false;
                    } else {
                        msg = msg + "第" + (i + 1) + "个sheet页:";
                        title = sheets[i].getCell(0, 0).getContents().replace(" ", "");
                        ;
                        if (title == null || title.equals("")) {
                            msg = msg + "批次名称为空！";
                            flag = false;
                        } else {
                            for (int row = 1; row < rows; row++) {
                                HashMap rowmap = new HashMap();
                                rowmap.put("rowNum", row + 1);
                                for (int col = 0; col < cols; col++) {
                                    Cell ct = sheets[i].getCell(col, 1);
                                    String ctvalue = ct.getContents().replace(" ", "");
                                    boolean isvarify = false;
                                    Cell c = sheets[i].getCell(col, row);
                                    String value = c.getContents().replace(" ", "");
                                    if (ctvalue != null && !ctvalue.equals("")) {
                                        rowmap.put(ctvalue, value);
                                    }
                                    if (varify != null)
                                        isvarify = varify.contains((col + 1));
                                    if (isvarify) {
                                        if (ctvalue != null && !ctvalue.equals("")) {
                                            if (value == null || value.equals("")) {
                                                msg = msg + "第" + (row + 1) + "行第" + (col + 1) + "列：值为空！";
                                                flag = false;
                                                break;
                                            } else {
                                                flag = true;
                                            }

                                        } else {
                                            msg = msg + "第2行第" + (col + 1) + "列：标题为空！";
                                            flag = false;
                                            break;
                                        }
                                    }
                                }
                                if (flag) {
                                    list.add(rowmap);
                                } else {
                                    break;
                                }

                            }
                        }
                    }
                    if (flag)
                        msg = msg + "验证通过";
                    sheetmap.put("title", title);
                    sheetmap.put("msg", msg);
                    sheetmap.put("flag", flag);
                    sheetmap.put("data", list);
                    map.put(sheetName, sheetmap);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return map;
    }

    /**
     * 读取 技术、商务评分表 总分 模板信息
     *
     * @param file
     * @return
     */
    public static Map readscoreXls(File file) {

        HashMap map = new HashMap();
        try {
            InputStream is = new BufferedInputStream(new FileInputStream(file), 1024);
            Workbook rwb = Workbook.getWorkbook(is);
            Sheet[] sheets = rwb.getSheets();
            if (sheets != null) {
                for (int i = 0; i < sheets.length; i++) {
                    HashMap sheetmap = new HashMap();
                    String msg = "";
                    String title = "";
                    boolean flag = true;
                    String sheetName = sheets[i].getName();
                    List<HashMap> list = new ArrayList<HashMap>();
                    int rows = sheets[i].getRows();
                    int cols = sheets[i].getColumns();
                    if (rows == 0 || cols == 0) {
                        msg = msg + "第" + (i + 1) + "个sheet页 为空；";
                        flag = false;
                    } else {
                        msg = msg + "第" + (i + 1) + "个sheet页:";
                        title = sheets[i].getCell(1, 0).getContents().replace(" ", "");
                        ;
                        if (title == null || title.equals("")) {
                            msg = msg + "标段包名称为空！";
                            flag = false;
                        } else {
                            int scorenum = 0;
                            for (int row = 1; row < rows; row++) {
                                HashMap rowmap = new HashMap();
                                for (int col = 0; col < cols; col++) {
                                    Cell ct = sheets[i].getCell(col, 1);
                                    String ctvalue = ct.getContents().replace(" ", "");
                                    boolean isvarify = false;
                                    Cell c = sheets[i].getCell(col, row);
                                    String value = c.getContents().replace(" ", "");
                                    if (ctvalue != null && !ctvalue.equals("")) {
                                        rowmap.put(ctvalue, value);
                                    }

                                    if (sheets[i].getCell(1, row).getContents().replace(" ", "").equals("总分"))
                                        scorenum = row;

                                    if (col >= 4 && scorenum == 0)
                                        isvarify = true;
                                    if (isvarify) {
                                        if (ctvalue != null && !ctvalue.equals("")) {
                                            if (value == null || value.equals("")) {
                                                msg = msg + "第" + (row + 1) + "行第" + (col + 1) + "列：值为空！";
                                                flag = false;
                                                break;
                                            } else {
                                                flag = true;
                                            }

                                        } else {
                                            msg = msg + "第2行第" + (col + 1) + "列：标题为空！";
                                            flag = false;
                                            break;
                                        }
                                    }
                                }
                                if (flag) {
                                    list.add(rowmap);
                                } else {
                                    break;
                                }

                            }
                        }
                    }
                    if (flag)
                        msg = msg + "验证通过";
                    sheetmap.put("title", title);
                    sheetmap.put("msg", msg);
                    sheetmap.put("flag", flag);
                    sheetmap.put("data", list);
                    map.put(sheetName, sheetmap);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return map;
    }

    /**
     * 读取 Excel 格式 为xlsx 的数据
     *
     * @param file
     * @param varify
     * @return
     */
    public static Map readXlsx(MultipartFile file, List<Integer> varify) {
        HashMap map = new HashMap();
        try {
            InputStream is = new BufferedInputStream(file.getInputStream(), 1024);
            XSSFWorkbook rwb = new XSSFWorkbook(is);
            int sheetnum = rwb.getNumberOfSheets();
            if (sheetnum > 0) {
                for (int i = 0; i < sheetnum; i++) {
                    XSSFSheet xssfSheet = rwb.getSheetAt(i);
                    HashMap sheetmap = new HashMap();
                    String msg = "";
                    String title = "";
                    boolean flag = false;
                    String sheetName = xssfSheet.getSheetName();
                    List<HashMap> list = new ArrayList<HashMap>();
                    int rows = xssfSheet.getLastRowNum();
                    int cols = xssfSheet.getRow(1).getLastCellNum();

                    if (rows == 0 || cols == 0) {
                        msg = msg + "第" + (i + 1) + "个sheet页 为空；";
                        flag = false;
                    } else {
                        msg = msg + "第" + (i + 1) + "个sheet页:";
                        title = xssfSheet.getRow(0).getCell(0).toString();
                        if (title == null || title.equals("")) {
                            msg = msg + "批次名称为空！";
                            flag = false;
                        } else {
                            for (int row = 1; row <= rows; row++) {
                                HashMap rowmap = new HashMap();
                                rowmap.put("rowNum", row + 1);
                                for (int col = 0; col < cols; col++) {
                                    String ctvalue = xssfSheet.getRow(1).getCell(col).toString();
                                    boolean isvarify = false;
                                    String value = xssfSheet.getRow(row).getCell(col).toString();
                                    if (ctvalue != null && !ctvalue.equals("")) {
                                        rowmap.put(ctvalue, value);
                                    }
                                    if (varify != null)
                                        isvarify = varify.contains((col + 1));
                                    if (isvarify) {
                                        if (ctvalue != null && !ctvalue.equals("")) {
                                            if (value == null || value.equals("")) {
                                                msg = msg + "第" + (row + 1) + "行第" + (col + 1) + "列：值为空！";
                                                flag = false;
                                                break;
                                            } else {
                                                flag = true;
                                            }

                                        } else {
                                            msg = msg + "第2行第" + (col + 1) + "列：标题为空！";
                                            flag = false;
                                            break;
                                        }
                                    }
                                }
                                if (flag) {
                                    list.add(rowmap);
                                } else {
                                    break;
                                }

                            }
                        }
                    }
                    if (flag)
                        msg = msg + "验证通过";
                    sheetmap.put("title", title);
                    sheetmap.put("msg", msg);
                    sheetmap.put("flag", flag);
                    sheetmap.put("data", list);
                    map.put(sheetName, sheetmap);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return map;
    }

    /**
     * 读取Excel
     *
     * @param file
     * @param varify
     * @return
     */
    public static Map readExcel(MultipartFile file, List<Integer> varify) {
        boolean fileflag = false;
        Map map = new HashMap();
        Map filemap = new HashMap();
        String filemsg = "";
        if (file != null) {
            String fileName = file.getName();
            fileflag = true;
            filemsg = "文件不为空!";
            if (fileName.endsWith(".xls")) {
                map = readXls(file, varify);
            }
            if (fileName.endsWith(".xlsx")) {
                map = readXlsx(file, varify);
            }
        } else {
            filemsg = filemsg + "文件对象为空！";
            fileflag = false;
        }
        filemap.put("filedata", map);
        filemap.put("filemsg", filemsg);
        filemap.put("fileflag", fileflag);
        return filemap;
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
     * 导出商务打分模板
     *
     * @param sourcePath
     * @param sheetTitle
     * @param fileName
     * @param result
     * @param BidAbbreviaion
     * @param response
     */
    public static void ExportelTemplate(String sourcePath, String sheetTitle, String fileName,
                                        List<tblBbgsPackageSupplierModel> result, String BidAbbreviaion, HttpServletResponse response) {
        try {
            HSSFWorkbook workbook = new HSSFWorkbook();
            HSSFSheet sheet = workbook.createSheet(BidAbbreviaion);
            Map styles = createStyles(workbook);

            //设置列宽
            sheet.setColumnWidth(0, 8 * 256);
            sheet.setColumnWidth(1, 18 * 256);
            sheet.setColumnWidth(2, 8 * 256);
            sheet.setColumnWidth(3, 40 * 256);
            sheet.setDefaultColumnWidth(5);
            // 设置表格是否显示网格
            //turn off gridlines
            //sheet.setDisplayGridlines(false);
            sheet.setPrintGridlines(false);
            sheet.setFitToPage(true);
            sheet.setHorizontallyCenter(true);
            PrintSetup printSetup = sheet.getPrintSetup();
            printSetup.setLandscape(true);

            //the following three statements are required only for HSSF
            sheet.setAutobreaks(true);
            printSetup.setFitHeight((short) 1);
            printSetup.setFitWidth((short) 1);

            //第一行，在索引0的位置创建行（最顶端的行）
            HSSFRow row = sheet.createRow((short) 0);
            //设置行高
            row.setHeightInPoints(30);
            //在索引0的位置创建单元格（左上端）
            HSSFCell cell = row.createCell(0);
            //定义单元格为字符串类型
            cell.setCellType(HSSFCell.CELL_TYPE_STRING);
            //合并单元格
            //sheet.addMergedRegion(CellRangeAddress.valueOf("$A$1:$H$1"));
            sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 2));
            // 第一列
            cell = row.createCell(0);
            //设置单元格内容
            cell.setCellValue("商务评分表");
            //获取title的样式
            cell.setCellStyle((HSSFCellStyle) styles.get("title"));
            //第二列
            cell = row.createCell(3);
            //定义单元格为字符串类型
            cell.setCellType(HSSFCell.CELL_TYPE_STRING);
            cell.setCellValue(BidAbbreviaion);
            cell.setCellStyle((HSSFCellStyle) styles.get("packagename"));

            //创建第二行
            HSSFRow secondRow = sheet.createRow(1);
            secondRow.setRowStyle((HSSFCellStyle) styles.get("warpText"));

            //设置行高
            secondRow.setHeightInPoints(70);
//            第一列
            cell = secondRow.createCell(0);
            //定义单元格为字符串类型
            cell.setCellType(HSSFCell.CELL_TYPE_STRING);
            cell.setCellValue("序号");
            cell.setCellStyle((HSSFCellStyle) styles.get("warpTextBold"));
//            第二列
            cell = secondRow.createCell(1);
            cell.setCellType(HSSFCell.CELL_TYPE_STRING);
            cell.setCellValue("评定项目");
            cell.setCellStyle((HSSFCellStyle) styles.get("warpTextBold"));
            //            第三列
            cell = secondRow.createCell(2);
            cell.setCellType(HSSFCell.CELL_TYPE_STRING);
            cell.setCellValue("满分");
            cell.setCellStyle((HSSFCellStyle) styles.get("warpTextBold"));
            //第四列
            cell = secondRow.createCell(3);
            cell.setCellType(HSSFCell.CELL_TYPE_STRING);
            cell.setCellValue("评分细则");
            cell.setCellStyle((HSSFCellStyle) styles.get("warpTextBold"));
            //第二行第五列到最后
            int num = 4;
            for (tblBbgsPackageSupplierModel model : result) {
                num = num++;
                cell = secondRow.createCell(num++);
                cell.setCellType(HSSFCell.CELL_TYPE_STRING);
                cell.setCellValue(model.getSupplier());
                cell.setCellStyle((HSSFCellStyle) styles.get("warpText"));
            }
//            第三行到第13行
            HSSFRow third = null;
            for (int i = 2; i < 9; i++) {
                third = sheet.createRow(i);
                third.setHeightInPoints(45);
                cell = third.createCell(0);
                cell.setCellValue(i - 1);
                cell.setCellStyle((HSSFCellStyle) styles.get("warpText"));

                cell = third.createCell(1);
                if (i == 2) {
                    cell.setCellValue("投标文件的响应性");
                } else if (i == 3) {
                    cell.setCellValue("财务状况");
                } else if (i == 4) {
                    cell.setCellValue("投标人资质");
                } else if (i == 5) {
                    cell.setCellValue("管理体系");
                } else if (i == 6) {
                    cell.setCellValue("企业荣誉");
                } else if (i == 7) {
                    cell.setCellValue("履约评价");
                } else if (i == 8) {
                    cell.setCellValue("资信证明");
                }
//                else if (i == 9) {
//                    cell.setCellValue("报价质量");
//                } else if (i == 10) {
//                    cell.setCellValue("总分");
//                } else if (i == 11) {
//                    cell.setCellValue("权重得分");
//                }
                cell.setCellStyle((HSSFCellStyle) styles.get("warpText"));
                cell = third.createCell(2);
                if (i == 2) {
                    cell.setCellValue(5);
                } else if (i == 3) {
                    cell.setCellValue(20);
                } else if (i == 4) {
                    cell.setCellValue(20);
                } else if (i == 5) {
                    cell.setCellValue(20);
                } else if (i == 6) {
                    cell.setCellValue(10);
                } else if (i == 7) {
                    cell.setCellValue(10);
                } else if (i == 8) {
                    cell.setCellValue(5);
                }
//                else if (i == 9) {
//                    cell.setCellValue(10);
//                } else if (i == 10) {
//                    cell.setCellValue(100);
//                }
                cell.setCellStyle((HSSFCellStyle) styles.get("warpText"));
//                if (i == 11) {
//                    cell.setCellValue("权重得分");
//                    cell.setCellStyle((HSSFCellStyle) styles.get("packagename"));
//                }
                cell = third.createCell(3);
                if (i == 2) {
                    cell.setCellValue("根据投标文件商务部分对招标文件响应的全面性、准确性进行评价，好10-9分、较好8-7分、一般6分，以1分为级差。");
                } else if (i == 3) {
                    cell.setCellValue("根据投标人经审计的财务报表(资产负债表、现金流量表、损益表)对投标人的资产规模、营业收入、盈利能力、偿债能力等方面进行评价，好10-9分、较好8-7分、一般6分，以1分为级差。");
                } else if (i == 4) {
                    cell.setCellValue("根据投标人及分包单位(若有)资质情况进行评价，满足招标文件要求资质得3分，高于招标文件要求资质的酌情加分，达到本类最高资质的得5分。");
                } else if (i == 5) {
                    cell.setCellValue("具有有效的GB/T19000系列质量保证体系、GB/T24000系列环境管理体系、GB/T28000系列职业健康安全管理体系认证(或等同标准认证)证书，每项得1分，最低7分。");
                } else if (i == 6) {
                    cell.setCellValue("根据投标文件提供的政府部门、行业颁发的荣誉称号进行评价，以1分为级差，最低6分。");
                } else if (i == 7) {
                    cell.setCellValue("根据投标文件提供的用户履约评价或用户证明文件进行评价，以1分为级差，最低12分。");
                } else if (i == 8) {
                    cell.setCellValue("具有银行分行及以上颁发的AAA级资信证书5分；银行分行及以上颁发的AA级或其他银行颁发的AAA级资信证书4分，评估公司颁发的资信证书或银行出具的资金状况良好证明3分；最低2分。");
                }
//                else if (i == 9) {
//                    cell.setCellValue("根据投标报价的完整性，计算的准确性，有无缺漏项进行评价，好15-13分、较好12-10分、一般9分，以1分为级差。");
//                } else if (i == 10) {
//                    cell.setCellValue("根据投标报价中分项报价构成的合理性、均衡性进行评价，好15-13分、较好12-10分、一般9分，以1分为级差。");
//                } else if (i == 11) {
//                    cell.setCellValue("");
//                }
                cell.setCellStyle((HSSFCellStyle) styles.get("warpTextLeft"));
                for (int j = 0; j < result.size(); j++) {
                    cell = third.createCell(j + 4);
//                    cell.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
                    cell.setCellStyle((HSSFCellStyle) styles.get("warpText"));
                }


            }
            third = sheet.createRow(9);
            cell = third.createCell(0);
            cell.setCellValue("8");
            cell = third.createCell(1);
            cell.setCellValue("报价质量");
            cell.setCellStyle((HSSFCellStyle) styles.get("warpText"));
            cell = third.createCell(2);
            cell.setCellValue(15);
            cell.setCellStyle((HSSFCellStyle) styles.get("warpText"));
            cell = third.createCell(3);
            cell.setCellValue("根据投标报价的完整性，计算的准确性，有无缺漏项进行评价，好15-13分、较好12-10分、一般9分，以1分为级差。");
            third = sheet.createRow(10);
            cell = third.createCell(2);
            cell.setCellValue(15);
            cell.setCellStyle((HSSFCellStyle) styles.get("warpText"));
            cell = third.createCell(3);
            cell.setCellValue("根据投标报价中分项报价构成的合理性、均衡性进行评价，好15-13分、较好12-10分、一般9分，以1分为级差");
            cell.setCellStyle((HSSFCellStyle) styles.get("warpTextLeft"));
            sheet.addMergedRegion(new CellRangeAddress(9, 10, 0, 0));

            third = sheet.createRow(13);
            cell = third.createCell(0);
            cell.setCellValue("专家签字：");
            cell = third.createCell(5);
            cell.setCellValue("监督人签字：");
            sheet.addMergedRegion(new CellRangeAddress(13, 13, 0, 3));

            third = sheet.createRow(14);
            sheet.addMergedRegion(new CellRangeAddress(14, 14, 0, 3));
            third.createCell(0);
            cell = third.createCell(0);
            cell.setCellValue("日期：");
            cell = third.createCell(5);
            cell.setCellValue("日期：");
            File file = new File(sourcePath + "/");
            //如果文件夹不存在则创建
            if (!file.exists() && !file.isDirectory()) {
                System.out.println("//不存在：" + sourcePath);
                file.mkdir();
            } else {
                System.out.println("//目录存在：" + sourcePath);
            }
            String newTitle = getFileName(fileName, sourcePath);
            System.out.println(sourcePath + "\\" + newTitle + ".xls");
            OutputStream out = new FileOutputStream(sourcePath + "/" + newTitle + ".xls");
            workbook.write(out);
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 导出技术打分模板
     *
     * @param sourcePath
     * @param sheetTitle
     * @param fileName
     * @param result
     * @param BidAbbreviaion
     * @param response
     */
    public static void ExportelTechTemplate(String sourcePath, String sheetTitle, String fileName,
                                            List<tblBbgsPackageSupplierModel> result, String BidAbbreviaion, HttpServletResponse response) {
        try {
            HSSFWorkbook workbook = new HSSFWorkbook();
            HSSFSheet sheet = workbook.createSheet(BidAbbreviaion);
            Map styles = createStyles(workbook);

            //设置列宽
            sheet.setColumnWidth(0, 8 * 256);
            sheet.setColumnWidth(1, 18 * 256);
            sheet.setColumnWidth(2, 8 * 256);
            sheet.setColumnWidth(3, 40 * 256);
            sheet.setDefaultColumnWidth(5);
            // 设置表格是否显示网格
            //turn off gridlines
            //sheet.setDisplayGridlines(false);
            sheet.setPrintGridlines(false);
            sheet.setFitToPage(true);
            sheet.setHorizontallyCenter(true);
            PrintSetup printSetup = sheet.getPrintSetup();
            printSetup.setLandscape(true);

            //the following three statements are required only for HSSF
            sheet.setAutobreaks(true);
            printSetup.setFitHeight((short) 1);
            printSetup.setFitWidth((short) 1);

            //第一行，在索引0的位置创建行（最顶端的行）
            HSSFRow row = sheet.createRow((short) 0);
            //设置行高
            row.setHeightInPoints(30);
            //在索引0的位置创建单元格（左上端）
            HSSFCell cell = row.createCell(0);
            //定义单元格为字符串类型
            cell.setCellType(HSSFCell.CELL_TYPE_STRING);
            //合并单元格
            //sheet.addMergedRegion(CellRangeAddress.valueOf("$A$1:$H$1"));
            sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 2));
            // 第一列
            cell = row.createCell(0);
            //设置单元格内容
            cell.setCellValue("技术评分表");
            //获取title的样式
            cell.setCellStyle((HSSFCellStyle) styles.get("title"));
            //第二列
            cell = row.createCell(3);
            //定义单元格为字符串类型
            cell.setCellType(HSSFCell.CELL_TYPE_STRING);
            cell.setCellValue(BidAbbreviaion);
            cell.setCellStyle((HSSFCellStyle) styles.get("packagename"));

            //创建第二行
            HSSFRow secondRow = sheet.createRow(1);
            secondRow.setRowStyle((HSSFCellStyle) styles.get("warpText"));

            //设置行高
            secondRow.setHeightInPoints(70);
//            第一列
            cell = secondRow.createCell(0);
            //定义单元格为字符串类型
            cell.setCellType(HSSFCell.CELL_TYPE_STRING);
            cell.setCellValue("序号");
            cell.setCellStyle((HSSFCellStyle) styles.get("warpTextBold"));
//            第二列
            cell = secondRow.createCell(1);
            cell.setCellType(HSSFCell.CELL_TYPE_STRING);
            cell.setCellValue("评定项目");
            cell.setCellStyle((HSSFCellStyle) styles.get("warpTextBold"));
            //            第三列
            cell = secondRow.createCell(2);
            cell.setCellType(HSSFCell.CELL_TYPE_STRING);
            cell.setCellValue("满分");
            cell.setCellStyle((HSSFCellStyle) styles.get("warpTextBold"));
            //第四列
            cell = secondRow.createCell(3);
            cell.setCellType(HSSFCell.CELL_TYPE_STRING);
            cell.setCellValue("评分细则");
            cell.setCellStyle((HSSFCellStyle) styles.get("warpTextBold"));
            //第二行第五列到最后
            int num = 4;
            for (tblBbgsPackageSupplierModel model : result) {
                num = num++;
                cell = secondRow.createCell(num++);
                cell.setCellType(HSSFCell.CELL_TYPE_STRING);
                cell.setCellValue(model.getSupplier());
                cell.setCellStyle((HSSFCellStyle) styles.get("warpText"));
            }
//            第三行到第13行
            HSSFRow third = null;
            for (int i = 2; i < 12; i++) {
                third = sheet.createRow(i);
                third.setHeightInPoints(45);
                cell = third.createCell(0);
                cell.setCellValue(i - 1);
                cell.setCellStyle((HSSFCellStyle) styles.get("warpText"));

                cell = third.createCell(1);
                if (i == 2) {
                    cell.setCellValue("投标文件的响应性");
                } else if (i == 3) {
                    cell.setCellValue("设计方案");
                } else if (i == 4) {
                    cell.setCellValue("选型配置");
                } else if (i == 5) {
                    cell.setCellValue("性能保证指标");
                } else if (i == 6) {
                    cell.setCellValue("生产制造能力");
                } else if (i == 7) {
                    cell.setCellValue("质量保证");
                } else if (i == 8) {
                    cell.setCellValue("技术服务");
                } else if (i == 9) {
                    cell.setCellValue("业绩");
                } else if (i == 10) {
                    cell.setCellValue("总分");
                } else if (i == 11) {
                    cell.setCellValue("权重得分");
                }
                cell.setCellStyle((HSSFCellStyle) styles.get("warpText"));
                cell = third.createCell(2);
                if (i == 2) {
                    cell.setCellValue(5);
                } else if (i == 3) {
                    cell.setCellValue(20);
                } else if (i == 4) {
                    cell.setCellValue(20);
                } else if (i == 5) {
                    cell.setCellValue(20);
                } else if (i == 6) {
                    cell.setCellValue(10);
                } else if (i == 7) {
                    cell.setCellValue(10);
                } else if (i == 8) {
                    cell.setCellValue(5);
                } else if (i == 9) {
                    cell.setCellValue(10);
                } else if (i == 10) {
                    cell.setCellValue(100);
                }
                cell.setCellStyle((HSSFCellStyle) styles.get("warpText"));
                if (i == 11) {
                    cell.setCellValue("权重得分");
                    cell.setCellStyle((HSSFCellStyle) styles.get("packagename"));
                }
                cell = third.createCell(3);
                if (i == 2) {
                    cell.setCellValue("根据投标文件技术部分对招标文件响应的全面性、准确性进行评价，好5分、较好4分、一般3分。");
                } else if (i == 3) {
                    cell.setCellValue("从产品的功能、结构型式、工艺、总体布置、操作便捷性、配套性等方面进行评价，好20-18分、较好17-14分、一般13-12分，以1分为级差。");
                } else if (i == 4) {
                    cell.setCellValue("从主要部件的选型配置、配套与兼容性，主要材料与零部件的选择，外协外购件的选用，备品备件和专用工具配置等方面进行评价，好20-18分、较好17-14分、一般13-12分，以1分为级差。");
                } else if (i == 5) {
                    cell.setCellValue("从型式试验报告或相关检测报告的各性能指标，投标文件响应的技术参数、性能指标、使用环境条件等方面进行评价，好20-18分、较好17-14分、一般13-12分，以1分为级差。");
                } else if (i == 6) {
                    cell.setCellValue("从各类专业人员，自主研发能力，制造、加工、检验、试验等设备的配置，制造、加工工艺，供货能力，生产规模，生产组织保证措施等方面进行评价，好10-9分、较好8-7分、一般6分，以1分为级差。");
                } else if (i == 7) {
                    cell.setCellValue("从质量目标，质量保证体系，质量控制措施，外购外协件的质量检验措施，厂内/出厂检验、调试、验收方案，执行的标准等进行评价，好10-9分、较好8-7分、一般6分，以1分为级差。");
                } else if (i == 8) {
                    cell.setCellValue("从安装调试/安装调试指导方案，人员配置，售后服务承诺，质保期承诺，对运行、维护人员的培训方案等方面进行评价，好5分、较好4分、一般3分。");
                } else if (i == 9) {
                    cell.setCellValue("具有最多业绩为10分，以1分为级差，最低为6分。");
                } else if (i == 10) {
                    cell.setCellValue("");
                } else if (i == 11) {
                    cell.setCellValue("");
                }
                cell.setCellStyle((HSSFCellStyle) styles.get("warpTextLeft"));
                for (int j = 0; j < result.size(); j++) {
                    cell = third.createCell(j + 4);
//                    cell.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
                    cell.setCellStyle((HSSFCellStyle) styles.get("warpText"));
                }
                third = sheet.createRow(12);
                cell = third.createCell(0);
                cell.setCellValue("专家签字：");
                cell = third.createCell(5);
                cell.setCellValue("监督人签字：");

                sheet.addMergedRegion(new CellRangeAddress(12, 12, 0, 3));

                third = sheet.createRow(13);
                sheet.addMergedRegion(new CellRangeAddress(13, 13, 0, 3));
                third.createCell(0);
                cell = third.createCell(0);
                cell.setCellValue("日期：");
                cell = third.createCell(5);
                cell.setCellValue("日期：");


            }
            File file = new File(sourcePath + "/");
            //如果文件夹不存在则创建
            if (!file.exists() && !file.isDirectory()) {
                System.out.println("//不存在：" + sourcePath);
                file.mkdir();
            } else {
                System.out.println("//目录存在：" + sourcePath);
            }
            String newTitle = getFileName(fileName, sourcePath);
            System.out.println(sourcePath + "\\" + newTitle + ".xls");
            OutputStream out = new FileOutputStream(sourcePath + "/" + newTitle + ".xls");
            workbook.write(out);
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static Map createStyles(HSSFWorkbook workbook) {
        Map<String, HSSFCellStyle> map = new LinkedHashMap<>();
        //表头样式
        HSSFCellStyle titlestyle = workbook.createCellStyle();
        titlestyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);//水平居中
        titlestyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);//垂直居中
        titlestyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);//边框
        titlestyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        titlestyle.setBorderRight(HSSFCellStyle.BORDER_THIN);
        titlestyle.setBorderTop(HSSFCellStyle.BORDER_THIN);
        titlestyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        HSSFFont tilleFont = workbook.createFont();
        tilleFont.setFontHeightInPoints((short) 12);
        tilleFont.setFontName("宋体");
        tilleFont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
        // 把字体应用到当前的样式
        titlestyle.setFont(tilleFont);
        map.put("title", titlestyle);
        //packagename
        HSSFCellStyle packagename = workbook.createCellStyle();
        packagename.setAlignment(HSSFCellStyle.ALIGN_CENTER);//水平居中
        packagename.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);//垂直居中
        packagename.setBorderBottom(HSSFCellStyle.BORDER_THIN);//边框
        packagename.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        packagename.setBorderRight(HSSFCellStyle.BORDER_THIN);
        packagename.setBorderTop(HSSFCellStyle.BORDER_THIN);
        packagename.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        HSSFFont packageFont = workbook.createFont();
        packageFont.setFontHeightInPoints((short) 10);
        packageFont.setFontName("宋体");
        packageFont.setColor(HSSFColor.RED.index);
        packagename.setFont(packageFont);
//        font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
        map.put("packagename", packagename);
//自动换行
        HSSFCellStyle warpText = workbook.createCellStyle();
        warpText.setAlignment(HSSFCellStyle.ALIGN_CENTER);//水平居中
        warpText.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);//垂直居中
        warpText.setBorderBottom(HSSFCellStyle.BORDER_THIN);//边框
        warpText.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        warpText.setBorderRight(HSSFCellStyle.BORDER_THIN);
        warpText.setBorderTop(HSSFCellStyle.BORDER_THIN);
        warpText.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        HSSFFont warpTextFont = workbook.createFont();
        warpTextFont.setFontHeightInPoints((short) 9);
        warpTextFont.setFontName("宋体");
        warpText.setFont(warpTextFont);
        warpText.setWrapText(true);
//        font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
        map.put("warpText", warpText);
//自动换行 字体加粗
        HSSFCellStyle warpTextBold = workbook.createCellStyle();
        warpTextBold.setAlignment(HSSFCellStyle.ALIGN_CENTER);//水平居中
        warpTextBold.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);//垂直居中
        warpTextBold.setBorderBottom(HSSFCellStyle.BORDER_THIN);//边框
        warpTextBold.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        warpTextBold.setBorderRight(HSSFCellStyle.BORDER_THIN);
        warpTextBold.setBorderTop(HSSFCellStyle.BORDER_THIN);
        warpTextBold.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        HSSFFont warpTextBoldFont = workbook.createFont();
        warpTextBoldFont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
        warpTextBoldFont.setFontHeightInPoints((short) 9);
        warpTextBoldFont.setFontName("宋体");
        warpTextBold.setFont(warpTextBoldFont);
        warpTextBold.setWrapText(true);
        map.put("warpTextBold", warpTextBold);

        //自动换行
        HSSFCellStyle warpTextLeft = workbook.createCellStyle();
        warpTextLeft.setAlignment(HSSFCellStyle.ALIGN_LEFT);//水平居左
        warpTextLeft.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);//垂直居中
        warpTextLeft.setBorderBottom(HSSFCellStyle.BORDER_THIN);//边框
        warpTextLeft.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        warpTextLeft.setBorderRight(HSSFCellStyle.BORDER_THIN);
        warpTextLeft.setBorderTop(HSSFCellStyle.BORDER_THIN);
        HSSFFont warpTextLeftFont = workbook.createFont();
        warpTextLeftFont.setFontHeightInPoints((short) 9);
        warpTextLeftFont.setFontName("宋体");
        warpTextLeft.setFont(warpTextLeftFont);
        warpTextLeft.setWrapText(true);
        map.put("warpTextLeft", warpTextLeft);
        return map;
    }
}
