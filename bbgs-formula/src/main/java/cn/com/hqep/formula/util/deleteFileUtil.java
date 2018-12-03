package cn.com.hqep.formula.util;

import java.io.File;

/**
 * Created by sssJL on 2017-11-14.
 * 删除目录及目录下所有文件
 */
public class deleteFileUtil {
    /***
     * 删除文件夹 及文件夹下的所有文件
     * @param dir 文件
     * @return 删除结果 true or false
     */
    public boolean deleteDir(File dir) {
        if (dir.isDirectory()) {
            String[] children = dir.list();
            if (children != null){
                for (int i=0; i<children.length; i++) {
                    boolean success = deleteDir(new File(dir, children[i]));
                    if (!success) {
                        return false;
                    }
                }
            }
        }
        // 目录此时为空，可以删除
        return dir.delete();
    }

}
