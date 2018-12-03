package cn.com.hqep.templet.common;

import java.io.File;

/**
 * Created by swq on 2017/10/22.
 */
public class FileAction {
    /**
     * 删除文件夹 及其下包含所有文件
     *
     * @param file
     */
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
}
