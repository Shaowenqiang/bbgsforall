package cn.com.hqep.templet.action;

import cn.com.hqep.templet.common.ExcelUtil_poi;
import cn.com.hqep.templet.common.FileAction;
import cn.com.hqep.templet.common.ZipUtils;
import cn.com.hqep.templet.common.jsonMsg;
import cn.com.hqep.templet.dao.flowStateDao;
import cn.com.hqep.templet.model.tblBbgsBidInformationModel;
import cn.com.hqep.templet.model.tblBbgsExpertModel;
import cn.com.hqep.templet.service.basicInfoService;
import cn.com.hqep.templet.service.scoreImportService;
import org.apache.commons.fileupload.disk.DiskFileItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * 专家评分数据输入页面Controller
 *
 * @author swq
 * @date 2017年10月17日 上午09:21:05
 */
@Controller
@RequestMapping("/scoreImport")
public class scoreImportAction {
    @Autowired
    private scoreImportService scoreImportService;
    @Autowired
    basicInfoService basicInfoService;
    @Autowired
    flowStateDao flowStateDao;
    /**
     * 导出技术、商务评分表（空）
     *
     * @param request
     */
    @ResponseBody
    @RequestMapping("/templetExport")
    public Map templetExport(HttpServletRequest request) {
        //评分表在服务器的存储路径（绝对路径）
        String sourcePath = request.getSession().getServletContext().getRealPath("") +
                "WEB-INF/page/bbgs/excel/技术、商务评分表";
        File f = new File(sourcePath);
        if (f.exists()) {
            FileAction.deleteFile(f);
        }
        if (!f.exists()) {
            f.mkdirs();
        }
        try {
            //查询流程 导入基础数据 节点是否完成
            String flowMessage = basicInfoService.queryFlowState("602");
            if(flowMessage!=null){
                return jsonMsg.toJosn("error",flowMessage);
            }
            //查询已经确定模板的 标段信息 flag为Y and 未流标
            tblBbgsBidInformationModel bidInfo = new tblBbgsBidInformationModel();
            //过滤出选择模板后的数据
            bidInfo.setFlag("Y");
            //过滤出未流标的数据
            bidInfo.setInvalid("1");
            List<tblBbgsBidInformationModel> tblBbgsBidInformations = basicInfoService.queryAllByModel(bidInfo);
            if (tblBbgsBidInformations.size() == 0) {
                return jsonMsg.toJosn("error", "无数据导出，检查步骤⑥中操作2是否完成");
            }
            //标段简称
            String bidAbbreviaion = null;
            for (tblBbgsBidInformationModel model : tblBbgsBidInformations) {
                bidAbbreviaion = model.getBidAbbreviaion();
                String businessWeight = model.getBusinessWeight();
                String technologyWeight = model.getTechnologyWeight();
                //查询供应厂商
                List<String> suppliers = scoreImportService.querySupplierName(bidAbbreviaion);
                //查询技术模板保存路径
                String technologyTemplatePath = model.getTechnologyTemplatePath();
                //查询商务模板保存路径
                String businessTemplatePath = model.getBusinessTemplatePath();
                //技术专家组名称 创建文件夹
                String technologyGroupName = model.getTechnologyExpertGroupName();
                List<String> technologyNames = scoreImportService.queryExpertNames(model.getTechnologyExpertGroup());
                //遍历专家组成员 创建属于该专家的文件夹
                for (String name : technologyNames) {
                    String newPath = sourcePath + "/" + technologyGroupName + "专家打分表/" + technologyGroupName +
                            "专家打分表（" + name + "）";
                    File file = new File(newPath);
                    if (!file.exists()) {
                        //创建新的文件夹
                        file.mkdirs();
                    }
                    if (technologyTemplatePath == null) {
                        return jsonMsg.toJosn("error", "您所使用的技术评分模板不存在");
                    }
                    ExcelUtil_poi.exportScoreTemplate(newPath + "/" + "技术打分表" + bidAbbreviaion + ".xls",
                            bidAbbreviaion, suppliers, technologyTemplatePath, "技术",technologyWeight);

                }
                //商务专家组名称
                String businessGroupName = model.getBusinessExpertGroupName();
                //商务专家组成员
                List<String> businessNames = scoreImportService.queryExpertNames(model.getBusinessExpertGroup());
                for (String name : businessNames) {
                    String newPath = sourcePath + "/" + businessGroupName + "专家打分表/" + businessGroupName + "专家打分表（" +
                            name + "）";
                    File file = new File(newPath);
                    if (!file.exists()) {
                        //创建新的文件夹
                        file.mkdirs();
                    }
                    if (businessTemplatePath == null) {
                        return jsonMsg.toJosn("error", "您所使用的商务评分模板不存在");
                    }
                    ExcelUtil_poi.exportScoreTemplate(newPath + "/" + "商务打分表" + bidAbbreviaion + ".xls",
                            bidAbbreviaion, suppliers, businessTemplatePath, "商务",businessWeight);
                }
            }
            ZipUtils.zip(sourcePath, sourcePath + ".zip", true);
            return jsonMsg.booleanToMap(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return jsonMsg.booleanToMap(false);
    }

    /**
     * 获取所有专家
     *
     * @return
     */
    @ResponseBody
    @RequestMapping("/getAllExpert")
    public List<tblBbgsExpertModel> getAllExpert() {
        return scoreImportService.getAllExpert();
    }

    @ResponseBody
    @RequestMapping("/getCount")
    public List<tblBbgsExpertModel> getCount() {
        return scoreImportService.getCount();
    }

    /**
     * 导入专家打分
     *
     * @param files
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping("/scoreImport")
    public List scoreImport(@RequestParam("file") MultipartFile[] files, HttpServletRequest request) {

        Map<String, File> map = new LinkedHashMap<>();
        String type = null;
        for (MultipartFile file : files) {
            CommonsMultipartFile cf = (CommonsMultipartFile) file;
            DiskFileItem fi = (DiskFileItem) cf.getFileItem();
            File f = fi.getStoreLocation();
            type = fi.getName();
            map.put(type, f);
        }

        return scoreImportService.save(map);
    }
}