package cn.com.hqep.bidder.common;

import org.apache.poi.poifs.filesystem.DirectoryEntry;
import org.apache.poi.poifs.filesystem.DocumentEntry;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;

import java.io.*;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by Administrator on 2017/11/21.
 */
public class ExportWord {
    public  void exportWord(List<Map> list1,List<Map> list2,List<Map> list3,List<Map> list4,List<Map> list5,Map m,OutputStream os,String path)
    {
        String newContent = "";
        String content = this.readReplace(path+"/word.htm",m);
        String content1 = this.readReplace(path+"/list1.txt",list1);
        String content2 = this.readReplace(path+"/list2.txt",list2);
        String content3 = this.readReplace(path+"/list3.txt",list3);
        String content4 = this.readReplace(path+"/list4.txt",list4);
        String content5 = this.readReplace(path+"/list5.txt",list5);
        newContent = content.replace("$list1$",content1).replace("$list2$",content2).replace("$list3$",content3).replace("$list4$",content4).replace("$list5$",content5);
       System.out.println(newContent);
        this.writeWord(os,newContent);
    }
    private  String readWord(String path)
    {
        File file = new File(path);
        String content ="";
        try {
            FileInputStream fis=new FileInputStream(file);
            InputStreamReader isr = new InputStreamReader(fis);
            BufferedReader br=new BufferedReader(isr);
            StringBuffer line=new StringBuffer();
            String str=null;
            while((str=br.readLine())!=null){
                line.append(str);
                line.append("\n");
            }
            content =  line.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return content;
    }
    private  String readReplace(String path,List<Map> l)
    {
        String content = this.readWord(path);
        content = this.replaceContent(content,l);
        return content;
    }
    private  String readReplace(String path,Map map)
    {
        String content = this.readWord(path);
        content = this.replaceContent(content,map);
        return content;
    }
    private  String replaceContent(String content,List<Map> l)
    {
        String allcontent ="";
        if(content!=null&& l != null) {
            int i=0;
            int length = l.size();
            for (Map m : l) {
                i++;
                String newcontent=content;
                Set<Map.Entry<String, String>> entryseSet = m.entrySet();
                for (Map.Entry<String, String> entry : entryseSet)
                {
                    String key = entry.getKey();
                    String value  =String.valueOf(entry.getValue());
                    newcontent = newcontent.replace("$"+key+"$",value);
                    if(newcontent.indexOf("$大序号$")!=-1)
                        newcontent = newcontent.replace("$大序号$",i+"");
                }
                if(i==length)
                    newcontent = newcontent.replace("$p_style$","double windowtext 1.5pt;");
                else
                    newcontent = newcontent.replace("$p_style$","solid windowtext 1.0pt;");
                if(allcontent.equals(""))
                    allcontent = newcontent;
                else
                    allcontent = allcontent+newcontent;
            }
        }
        return allcontent;
    }
    private  String replaceContent(String content,Map map)
    {
        if(content!=null) {
                Set<Map.Entry<String, String>> entryseSet = map.entrySet();
                for (Map.Entry<String, String> entry : entryseSet)
                {
                    String key = entry.getKey().toString();
                    String value  =entry.getValue().toString();
                    if(key.indexOf("p_date")!=-1)
                        value = this.changeBigDate(value);
                    content = content.replace("$"+key+"$",value);

                }
        }
        return content;
    }
    private void writeWord(OutputStream os,String content){
        try {
        byte b[] = content.getBytes();
        ByteArrayInputStream bais = new ByteArrayInputStream(b);
        POIFSFileSystem poifs = new POIFSFileSystem();
        DirectoryEntry directory = poifs.getRoot();
        DocumentEntry documentEntry = directory.createDocument("WordDocument", bais);
        poifs.writeFilesystem(os);
        bais.close();
        os.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private String changeBigDate(String date){
        char[] upper = "零一二三四五六七八九十".toCharArray();
        //支持yyyy-MM-dd、yyyy/MM/dd、yyyyMMdd等格式
        if(date == null) return null;
        //非数字的都去掉
        date = date.replaceAll("\\D", "");
        if(date.length() != 8) return null;
        StringBuilder sb = new StringBuilder();
        for (int i=0;i<4;i++) {//年
            sb.append(upper[Integer.parseInt(date.substring(i, i+1))]);
        }
        sb.append("年");//拼接年
        int month = Integer.parseInt(date.substring(4, 6));
        if(month <= 10) {
            sb.append(upper[month]);
        } else {
            sb.append("十").append(upper[month%10]);
        }
        sb.append("月");//拼接月

        int day = Integer.parseInt(date.substring(6));
        if (day <= 10) {
            sb.append(upper[day]);
        } else if(day < 20) {
            sb.append("十").append(upper[day % 10]);
        } else {
            sb.append(upper[day / 10]).append("十");
            int tmp = day % 10;
            if (tmp != 0) sb.append(upper[tmp]);
        }
        sb.append("日");//拼接日
        return sb.toString();
    }
}
