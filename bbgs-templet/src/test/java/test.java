import cn.com.hqep.templet.common.DbBackUpMethod;
import cn.com.hqep.templet.common.ExcelUtil_poi;
import org.junit.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/10/7.
 */
public class test {
    public static void main(String[] args) {
        String bidAtt = "001-变压器包1";
        String sourcePath = "D:/test/scoreTemplate";
        String str = "技术一组";
        String type = "技术打分表";
        String name = "王玉成";

        String newPath = sourcePath + "/" + str + "专家打分表/" + str + "专家打分表（" + name + "）";
        File file = new File(newPath);
        if (!file.exists()) {
            //删除文件夹及其下所有文件
//            deleteFile(file);
            //创建新的文件夹
            file.mkdirs();
        }
        List<String> list = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            list.add("厂商一");
            list.add("厂商一");
        }

//        ExcelUtil_poi.exportScoreTemplate(newPath + "/" + type + bidAtt + ".xls", bidAtt, list, "D:/技术、商务打分表014-1.xls");
    }
    public String subName(String type){
        type = type.substring(type.lastIndexOf("/")+1,type.lastIndexOf("."));
        return type.trim().substring(0,2);
    }
    public String subExcelName(String name){
        name = name.replace(name.substring(0,name.lastIndexOf("/")+1),"");
        return name;
    }
    @Test
    public void testStr(){
        String str = "003-变压器包1";
        if (str.lastIndexOf("包")>-1){
            str = str.substring(0,str.lastIndexOf("包"));
        }
//        String str = "1组打分表（王育晨）/技术、商务打分表010-1.xls";
////        System.out.print(str.substring(0,str.lastIndexOf("/")));
////        str = str.substring(0,str.lastIndexOf("/"));
////        System.out.print(str.substring(str.lastIndexOf("（")+1,str.lastIndexOf("）")));
//        System.out.print(subExcelName(str));
    }
    @Test
    public void testZip(){
//        System.out.println("001-互-感器".substring(0,"001互-感-器".indexOf("-")));
    }

    public static void deleteFile(File file) {
        for (File f : file.listFiles()) {
            if (f.isDirectory()) {
                deleteFile(f);
            } else {
                f.delete();
            }
        }
        file.delete();
    }
    @Test
    public void testDbBackUpThread(){
        DbBackUpMethod.getDbBackUpMethod().backup("tbl_bbgs_user");
    }

    @Test
    public void testImport(){
        ExcelUtil_poi.getExcelList(new File(""),"");
    }



    @Test
    public void testGetPath(){
        Thread.currentThread().getContextClassLoader().getResource("/").getPath();
    }

    @Test
    public void testZlie(){
        System.out.println(excelColIndexToStr(99));
    }

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

}
