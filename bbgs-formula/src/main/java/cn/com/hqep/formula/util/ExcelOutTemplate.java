package cn.com.hqep.formula.util;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;

import java.util.*;


/**
 * jsp调用如下：
 * ＜%@page import="com.olive.Jexcel.out.ExcelOutTemplate" %＞
 * ＜%
 * 　 response.reset();
 * 　 response.setContentType("application/vnd.ms-excel");
 * ExcelOutTemplate excelOut = new ExcelOutTemplate();
 * 　 excelOut.writeExcel(response.getOutputStream());
 * %＞
 */

/**
 *
 */
public class ExcelOutTemplate {

    public void setData(Cell cell, Object data) {
        if (data instanceof Date) {
            cell.setCellValue((Date) data);
        } else if (data instanceof String) {
            cell.setCellValue((String) data);
        } else if (data instanceof Calendar) {
            cell.setCellValue((Calendar) data);
        } else if (data instanceof RichTextString) {
            cell.setCellValue((RichTextString) data);
        } else {
            if (data != null) {
                cell.setCellValue(data.toString());
            }
        }
    }

    public void setData(Cell cell, double data) {
        cell.setCellValue(data);
    }

    public void setData(Cell cell, boolean data) {
        cell.setCellValue(data);
    }

    public void setData(Sheet sheet, Object data, int row, int column) {
        setData(sheet, data, row, column, Cell.CELL_TYPE_STRING);
    }

    public void setData(Sheet sheet, Object data, int row, int column, CellStyle cellStyle) {
        setData(sheet, data, row, column, Cell.CELL_TYPE_STRING, cellStyle);
    }

    public void setData(Sheet sheet, Object data, int row, int column, int cellType) {
        Cell cell = getCell(sheet, row, column, cellType);
        setData(cell, data);
    }

    public void setData(Sheet sheet, Object data, int row, int column, int cellType, CellStyle cellStyle) {
        Cell cell = getCell(sheet, row, column, cellType, cellStyle);
        setData(cell, data);
    }

    public void setData(Sheet sheet, double data, int row, int column) {
        Cell cell = getCell(sheet, row, column, Cell.CELL_TYPE_STRING);
        setData(cell, data);
    }

    public void setData(Sheet sheet, double data, int row, int column, CellStyle cellStyle) {
        Cell cell = getCell(sheet, row, column, Cell.CELL_TYPE_STRING, cellStyle);
        setData(cell, data);
    }

    public void setData(Sheet sheet, double data, int row, int column, int cellType) {
        Cell cell = getCell(sheet, row, column, cellType);
        setData(cell, data);
    }

    public void setData(Sheet sheet, double data, int row, int column, int cellType, CellStyle cellStyle) {
        Cell cell = getCell(sheet, row, column, cellType, cellStyle);
        setData(cell, data);
    }

    public void setData(Sheet sheet, boolean data, int row, int column) {
        Cell cell = getCell(sheet, row, column, Cell.CELL_TYPE_STRING);
        setData(cell, data);
    }

    public void setData(Sheet sheet, boolean data, int row, int column, CellStyle cellStyle) {
        Cell cell = getCell(sheet, row, column, Cell.CELL_TYPE_STRING, cellStyle);
        setData(cell, data);
    }

    public void setData(Sheet sheet, boolean data, int row, int column, int cellType) {
        Cell cell = getCell(sheet, row, column, cellType);
        setData(cell, data);
    }

    public void setData(Sheet sheet, boolean data, int row, int column, int cellType, CellStyle cellStyle) {
        Cell cell = getCell(sheet, row, column, cellType, cellStyle);
        setData(cell, data);
    }

    public Cell getCell(Row row, int cellIdx) {
        return getCell(row, cellIdx, Cell.CELL_TYPE_STRING);
    }

    public Cell getCell(Row row, int cellIdx, int cellType) {
        Cell cell = row.getCell(cellIdx);
        cell = cell == null ? row.createCell(cellIdx) : cell;
        cell.setCellType(cellType);
        return cell;
    }

    public Cell getCell(Sheet sheet, int row, int column) {
        return getCell(getRow(sheet, row), column, Cell.CELL_TYPE_STRING);
    }

    public Cell getCell(Sheet sheet, int row, int column, int cellType) {
        return getCell(getRow(sheet, row), column, cellType);
    }

    public Cell getCell(Sheet sheet, int row, int column, int cellType, CellStyle cellStyle) {
        Cell cell = getCell(getRow(sheet, row), column, cellType);
        cell.setCellStyle(cellStyle);
        return cell;
    }

    public Cell getCell(Sheet sheet, String name, int cellType) {
        return null;
    }

    public Row getRow(Sheet sheet, int rowIdx) {
        Row row = sheet.getRow(rowIdx);
        return row == null ? sheet.createRow(rowIdx) : row;
    }

    public boolean copyRow(Row srcRow, Row destRow) {
        return copyRow(srcRow, destRow, false);
    }

    public boolean copyRow(Row srcRow, Row destRow, boolean copyFormat) {
        destRow.setRowStyle(srcRow.getRowStyle());
        destRow.setHeight(srcRow.getHeight());
        Iterator<Cell> iterator = srcRow.cellIterator();
        Cell srcCell, destCell = null;
        int col, srcCellType;
        try {
            while (iterator.hasNext()) {
                srcCell = iterator.next();
                col = srcCell.getColumnIndex();
                srcCellType = srcCell.getCellType();
                destCell = destRow.getCell(col);
                if (destCell == null) {
                    destCell = destRow.createCell(col);
                }
                copyCellStyle(srcCell, destCell, copyFormat);
                if (srcCellType == Cell.CELL_TYPE_BLANK) {

                } else if (srcCellType == Cell.CELL_TYPE_BOOLEAN) {
                    setData(destCell, srcCell.getBooleanCellValue());
                } else if (srcCellType == Cell.CELL_TYPE_ERROR) {
                    setData(destCell, srcCell.getErrorCellValue());
                } else if (srcCellType == Cell.CELL_TYPE_FORMULA) {
                    setData(destCell, srcCell.getCellFormula());
                } else if (srcCellType == Cell.CELL_TYPE_NUMERIC) {
                    if (DateUtil.isCellDateFormatted(srcCell)) {
                        setData(destCell, srcCell.getDateCellValue());
                    } else {
                        setData(destCell, srcCell.getNumericCellValue());
                    }
                } else {
                    setData(destCell, srcCell.getRichStringCellValue());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            if (destCell != null) {
                setData(destCell, 0);
            }
            return false;
        }
        return true;
    }

    public CellStyle copyCellStyle(CellStyle fromStyle,
                                   CellStyle toStyle) {
        return copyCellStyle(fromStyle, toStyle, false);
    }

    public CellStyle copyCellStyle(CellStyle fromStyle,
                                   CellStyle toStyle, boolean copyFormat) {
        toStyle.setAlignment(fromStyle.getAlignment());
        //边框和边框颜色
        toStyle.setBorderBottom(fromStyle.getBorderBottom());
        toStyle.setBorderLeft(fromStyle.getBorderLeft());
        toStyle.setBorderRight(fromStyle.getBorderRight());
        toStyle.setBorderTop(fromStyle.getBorderTop());
        toStyle.setTopBorderColor(fromStyle.getTopBorderColor());
        toStyle.setBottomBorderColor(fromStyle.getBottomBorderColor());
        toStyle.setRightBorderColor(fromStyle.getRightBorderColor());
        toStyle.setLeftBorderColor(fromStyle.getLeftBorderColor());

        //背景和前景
        toStyle.setFillBackgroundColor(fromStyle.getFillBackgroundColor());
        toStyle.setFillForegroundColor(fromStyle.getFillForegroundColor());

        if (copyFormat) {
            toStyle.setDataFormat(fromStyle.getDataFormat());
        }
        toStyle.setFillPattern(fromStyle.getFillPattern());
//      toStyle.setFont(XSSFFont.fromStyle.getFontIndex());
        toStyle.setHidden(fromStyle.getHidden());
        toStyle.setIndention(fromStyle.getIndention());//首行缩进
        toStyle.setLocked(fromStyle.getLocked());
        toStyle.setRotation(fromStyle.getRotation());//旋转
        toStyle.setVerticalAlignment(fromStyle.getVerticalAlignment());
        toStyle.setWrapText(fromStyle.getWrapText());
        return toStyle;
    }

    public CellStyle copyCellStyle(CellStyle fromStyle,
                                   Workbook workbook) {
        CellStyle toStyle = workbook.createCellStyle();
        return copyCellStyle(fromStyle, toStyle);
    }

    public void copyCellStyle(Cell fromCell,
                              Cell toCell) {
        copyCellStyle(fromCell, toCell, false);
    }

    public void copyCellStyle(Cell fromCell,
                              Cell toCell, boolean copyFormat) {
        Workbook workbook = fromCell.getSheet().getWorkbook();
        CellStyle fromStyle = fromCell.getCellStyle();
        CellStyle toStyle = toCell.getCellStyle();
        copyCellStyle(fromStyle, toStyle, copyFormat);
        toStyle.setFont(workbook.getFontAt(fromStyle.getFontIndex()));

    }

    /**
     * takes in a column reference portion of a CellRef and converts it from
     * ALPHA-26 number format to 0-based base 10.
     * 'A' -> 0
     * 'Z' -> 25
     * 'AA' -> 26
     * 'IV' -> 255
     *
     * @return zero based column index
     */
    public int convertColStringToIndex(String ref) {
        int retval = 0;
        char[] refs = ref.toUpperCase().toCharArray();
        for (int k = 0; k < refs.length; k++) {
            char thechar = refs[k];
            if (thechar == '$') {
                if (k != 0) {
//                    throw new IllegalArgumentException("Bad col ref format '" + ref + "'");
                }
                continue;
            }

            // Character is uppercase letter, find relative value to A
            retval = (retval * 26) + (thechar - 'A' + 1);
        }
        return retval - 1;
    }

    /**
     * Takes in a 0-based base-10 column and returns a ALPHA-26
     * representation.
     * eg column #3 -> D
     */
    public String convertNumToColString(int col) {
        // Excel counts column A as the 1st column, we
        //  treat it as the 0th one
        int excelColNum = col + 1;

        StringBuilder colRef = new StringBuilder(2);
        int colRemain = excelColNum;

        while (colRemain > 0) {
            int thisPart = colRemain % 26;
            if (thisPart == 0) {
                thisPart = 26;
            }
            colRemain = (colRemain - thisPart) / 26;

            // The letter A is at 65
            char colChar = (char) (thisPart + 64);
            colRef.insert(0, colChar);
        }

        return colRef.toString();
    }

    /**
     * 根据单元格名取单元格
     *
     * @param sheet 表
     * @param adr   名称，如"E5"
     * @return 行/单元格不存在时返回null
     */
    public Cell getCellByAddress(Sheet sheet, String adr) {
        return getCellByAddress(sheet, adr, false);
    }

    /**
     * 根据单元格名取单元格
     *
     * @param sheet 表
     * @param adr   名称，如"E5"
     * @param add   不存在行/单元格时是否直接创建
     * @return 返回指定位置的单元格, cell对象或者null
     */
    public Cell getCellByAddress(Sheet sheet, String adr, boolean add) {
        int loc = 0, length = adr.length(), rowIdx = 0, colIdx = 0;
        adr = adr.replace("$", "");
        for (; loc < length; loc++) {
            char ch = adr.charAt(loc);
            if (Character.isDigit(ch)) {
                break;
            }
        }
        colIdx = convertColStringToIndex(adr.substring(0, loc));
        rowIdx = Integer.parseInt(adr.substring(loc));
        Row row = sheet.getRow(rowIdx);
        Cell cell = null;
        if (row == null) {
            if (add) {
                cell = sheet.createRow(rowIdx).createCell(colIdx);
            }
        } else {
            cell = row.getCell(colIdx);
            if (add) {
                cell = row.createCell(colIdx);
            }
        }
        return cell;
    }

    public int[] isMergedRegion(Sheet sheet, int row, int column) {
        int sheetMergeCount = sheet.getNumMergedRegions(), firstColumn, lastColumn, firstRow, lastRow;
        for (int i = 0; i < sheetMergeCount; i++) {
            CellRangeAddress range = sheet.getMergedRegion(i);
            firstColumn = range.getFirstColumn();
            lastColumn = range.getLastColumn();
            firstRow = range.getFirstRow();
            lastRow = range.getLastRow();
            if (row >= firstRow && row <= lastRow) {
                if (column >= firstColumn && column <= lastColumn) {
                    return new int[]{1, firstRow, lastRow, firstColumn, lastColumn};
                }
            }
        }
        return new int[]{0, 0, 0, 0, 0};
    }

    public int[] isMergedRegion(Cell cell) {
        return isMergedRegion(cell.getSheet(), cell.getRowIndex(), cell.getColumnIndex());
    }
}
