package cn.com.hqep.templet.model;

import java.io.File;
import java.util.List;

/**
 * ${todo}(这里用一句话描述这个类的作用)
 *
 * @author ${user}    ${date} ${time}
 * @date 15:02
 */
public class testModel {
    String name;
    List<File> files;

    public void setName(String name) {
        this.name = name;
    }

    public void setFiles(List<File> files) {
        this.files = files;
    }

    @Override
    public String toString() {
        return "testModel{" +
                "name='" + name + '\'' +
                ", files=" + files +
                '}';
    }
}
