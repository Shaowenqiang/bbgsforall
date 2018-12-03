package cn.com.hqep.bidder.common;

import com.jacob.activeX.ActiveXComponent;
import com.jacob.com.Dispatch;
import com.jacob.com.Variant;
import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
//            Dispatch.call(excel, "Save");
            Dispatch.call(excel, "Close", f);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            app.invoke("Quit", new Variant[]{});
        }
        return htmName;
    }

    public static Map readXls(MultipartFile file, List<Integer> varify) {

        HashMap map = new HashMap();
        try {
            InputStream is = new BufferedInputStream(file.getInputStream(), 1024);
            Workbook rwb = Workbook.getWorkbook(is);
            Sheet[] sheets = rwb.getSheets();
            if (sheets != null) {
                for (int i = 0; i < sheets.length; i++) {
                    HashMap sheetmap = new HashMap();
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
                        title = sheets[i].getCell(0, 0).getContents();
                        if (title == null || title.equals("")) {
                            msg = msg + "批次名称为空！";
                            flag = false;
                        } else {
                            for (int row = 1; row < rows; row++) {
                                HashMap rowmap = new HashMap();
                                for (int col = 0; col < cols; col++) {
                                    Cell ct = sheets[i].getCell(col, 1);
                                    String ctvalue = ct.getContents();
                                    boolean isvarify = false;
                                    Cell c = sheets[i].getCell(col, row);
                                    String value = c.getContents();
                                    if (ctvalue != null && !ctvalue.equals("")) {
                                        rowmap.put(ctvalue, value);
                                    }
                                    if (varify != null)
                                        isvarify = varify.contains((col + 1));
                                    if (isvarify) {
                                        if (ctvalue != null && !ctvalue.equals("")) {
                                            if (value == null || value.equals("")) {
                                                msg = msg + "第" + (row + 1) + "行，" + ctvalue + "值为空！";
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
                                                msg = msg + "第" + (row + 1) + "行，" + ctvalue + "的值为空！";
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

    public static Map readExcel(MultipartFile file, List<Integer> varify, String fileName) {
        boolean fileflag = false;
        Map map = new HashMap();
        Map filemap = new HashMap();
        String filemsg = "";
        if (file != null) {
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
}
