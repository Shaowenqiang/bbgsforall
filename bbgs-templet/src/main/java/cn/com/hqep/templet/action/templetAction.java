package cn.com.hqep.templet.action;

import cn.com.hqep.templet.common.ExcelUtil_poi;
import cn.com.hqep.templet.common.jsonMsg;
import cn.com.hqep.templet.model.tblBbgsTemplateModel;
import cn.com.hqep.templet.service.templetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * 模板信息维护页面controller
 *
 * @author swq
 * @date
 */
@Controller
@RequestMapping("/templet")
public class templetAction {
    @Autowired
    private templetService templetService;

    /**
     * 新增模板分类下子模板
     *
     * @return
     */
    @ResponseBody
    @RequestMapping("/addTemplet")
    public Map addTemplet(@RequestParam("file") MultipartFile file, String templateName, String templetParentId, HttpServletRequest request) {
        //模板文件储存路径
//        String sourcePath = request.getSession().getServletContext().getRealPath("") + "WEB-INF/page/bbgs/excelHtml/";
        String sourcePath = "WEB-INF/page/bbgs/excelHtml/";
        String fileRealName = UUID.randomUUID().toString().replace("-", "") + "." + file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(".") + 1);
        try {
            ExcelUtil_poi.savePic(file.getInputStream(), fileRealName, request.getSession().getServletContext().getRealPath("")+sourcePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
        tblBbgsTemplateModel model = new tblBbgsTemplateModel();
        model.setTemplateType(templetParentId);
        model.setFileName(templateName);
        model.setRealName(fileRealName);
        model.setFilePath(sourcePath + fileRealName);
        return templetService.saveTemplet(model);
    }

    /**
     * 查询模板分类下的子模板
     *
     * @return
     */
    @ResponseBody
    @RequestMapping("/listAll")
    public List<tblBbgsTemplateModel> listAllByType(tblBbgsTemplateModel model) {
        return templetService.listAll(model);
    }

    /**
     * 查看子模板Excel文件详情
     *
     * @param request
     * @param id
     * @return
     */
    @ResponseBody
    @RequestMapping("/viewExcel")
    public Map viewExcelByExcelName(HttpServletRequest request, String id) {
        String sourcePath = request.getSession().getServletContext().getRealPath("");
        return templetService.viewDetail(id, sourcePath);
    }

    /**
     * 删除模板分类下的子模板
     *
     * @param model
     * @return
     */
    @ResponseBody
    @RequestMapping("/delTemplet")
    public Map delTemplet(tblBbgsTemplateModel model) {
        return jsonMsg.booleanToMap(templetService.delTemplet(model));
    }

    /**
     * 设置为默认模板
     */
    @ResponseBody
    @RequestMapping("/setDefault")
    public Map setDefault(String id) {
        return jsonMsg.booleanToMap(templetService.setDefault(id));
    }

}
