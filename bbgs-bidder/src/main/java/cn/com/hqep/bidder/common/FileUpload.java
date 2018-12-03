package cn.com.hqep.bidder.common;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.*;

public class FileUpload {
    private static final Log logger = LogFactory.getLog(FileUpload.class);
    private static int maxPostSize = 10 * 1024 * 1024 * 1024;

    public static String upload(HttpServletRequest request, String sourcePath, String filename, Integer fileMax) {
        try {
            logger.info("importReport filename: " + sourcePath + "/" + filename);
            DiskFileItemFactory factory = new DiskFileItemFactory();
            factory.setSizeThreshold(maxPostSize);
            ServletFileUpload upload = new ServletFileUpload(factory);
            upload.setSizeMax(fileMax != null ? fileMax : maxPostSize);
            Iterator<FileItem> iter = upload.parseRequest(request).iterator();
            String wholeName = "";
            while (iter.hasNext()) {
                FileItem item = iter.next();
                if (!item.isFormField()) {
                    File dir = new File(sourcePath + "/");
                    if (!dir.exists() || !dir.isDirectory())
                        dir.mkdirs();
                    wholeName = filename+item.getName().substring(item.getName().indexOf("."));
                    item.write(new File(dir,wholeName ));
                }
                else{
                    if ("imageType".equals(item.getFieldName())) {
                        System.out.println(item.getString());
                    }
                }
            }
            return sourcePath+"/"+wholeName;
        } catch (Exception e) {
            logger.error("上传失败" + e.getMessage(), e);
            return null;
        }
    }
    /**
     *
     * 获取文件和对应表单参数值   
     * @param request 用于获取图片和信息
     * @param sourcePath 文件存储信息
     * @param filename 文件的新名字
     * @param fileMax  文件限制最大值
     * @param list  需要返回的数据
     * @return
     */
    public static List<Map<String,Object>> uploadReturnData(HttpServletRequest request, String sourcePath, String filename, Integer fileMax,List<String> list) {
        //用于储存返回数据
        List<Map<String,Object>> returnList= new ArrayList<Map<String,Object>>();
        Map<String,Object> map = new HashMap<String, Object>();
        try {
            //日志信息
            logger.info("importReport filename: " + sourcePath + "/" + filename);
            DiskFileItemFactory factory = new DiskFileItemFactory();
            factory.setSizeThreshold(maxPostSize);
            ServletFileUpload upload = new ServletFileUpload(factory);
            //设置文件最大限制size 没有则为 maxPostSize 默认做大值
            upload.setSizeMax(fileMax != null ? fileMax : maxPostSize);
            Iterator<FileItem> iter = upload.parseRequest(request).iterator();
            while (iter.hasNext()) {
                FileItem item = iter.next();
                if (!item.isFormField()) {
                    File dir = new File(sourcePath + "/");
                    if (!dir.exists() || !dir.isDirectory())
                        dir.mkdirs();
                    for(String str : list){
                        if (str.equals(item.getFieldName())) {
//                            String wholeName = filename+item.getName().substring(item.getName().indexOf("."));
                            String wholeName = item.getName();
                            //文件全名带有文件格式后缀
                            map.put(str+"OldName", item.getName());
                            map.put(str+"Name", wholeName);
                            item.write(new File(dir,wholeName));
                            map.put(str, new File(dir,wholeName));
                        }
                    }
//                    
//                    String wholeName = filename+item.getName().substring(item.getName().indexOf("."));
//                    //文件全名带有文件格式后缀
//                    map.put("wholeName", wholeName);
//                    item.write(new File(dir,wholeName));
//                    map.put("file", new File(dir,wholeName));
                }
                if (item.isFormField()) {
                    //获取需要的数据
                    for(String str : list){
                        if (str.equals(item.getFieldName())) {
                            map.put(str, item.getString("UTF-8"));
                        }
                    }
                }
            }
            returnList.add(map);
            return returnList;
        } catch (Exception e) {
            logger.error("上传失败" + e.getMessage(), e);
            return null;
        }
    }
}
