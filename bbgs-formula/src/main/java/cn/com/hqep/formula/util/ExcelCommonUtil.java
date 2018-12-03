package cn.com.hqep.formula.util;

/**
 * Created by spring on 2017/10/13.
 */
public class ExcelCommonUtil {

    public static boolean isNotEmpty(String str){
        if(str==null || str.length()==0) {
            return false;
        }else{
            return true;
        }
    }

    // 判断是否为老版本的excel: isExcel2003
    public static boolean isExcel2003(String filePath){
        return isNotEmpty(filePath) && filePath.matches("^.+\\.(?i)(xls)$");
    }

    // 判断是否为新版本的excel: isExcel2007
    public static boolean isExcel2007(String filePath){
        return isNotEmpty(filePath) && filePath.matches("^.+\\.(?i)(xlsx)$");
    }


}
