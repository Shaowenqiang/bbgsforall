package cn.com.hqep.templet.common;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class cmdOrderUtil {
    public static void fixHeaerAndCss(String sourcePath, String param, String vbsPath) {
        Process process = null;
        try {
            process = Runtime.getRuntime().exec("cmd /c CScript  " + vbsPath + "  " + sourcePath + " " + param + "");
        } catch (IOException e) {
            e.printStackTrace();
        }
        BufferedReader br = new BufferedReader(new InputStreamReader(process.getInputStream()));//读取标准缓冲区内容（就是读取vbs的dos命令窗口的内容）
        String line = null;
        try {
            while ((line = br.readLine()) != null)
                System.out.println(line);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            int exitVal = process.waitFor();
            process.destroy();
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }
}
