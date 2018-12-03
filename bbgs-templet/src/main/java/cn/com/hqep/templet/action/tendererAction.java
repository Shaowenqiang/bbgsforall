package cn.com.hqep.templet.action;

import cn.com.hqep.templet.model.tblBbgsTendererModel;
import cn.com.hqep.templet.service.tendererService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by hq2 on 2017/10/9.
 */

/*投标人信息
* 郭
* */
@Controller
@RequestMapping("/tenderer")
public class tendererAction {
    @Autowired
    private tendererService tendererService;

    @ResponseBody
    @RequestMapping("/all")
    /*查询投标信息  传递参数 标段名称  供应商名称*/
    public List<tblBbgsTendererModel> contentAll(String sbdname, String gysname) {
        tblBbgsTendererModel tblBbgsTendererModel = new tblBbgsTendererModel();
        tblBbgsTendererModel.setBidAbbreviaion(sbdname);
        tblBbgsTendererModel.setSupplier(gysname);
        List<tblBbgsTendererModel> list = tendererService.contentAll(tblBbgsTendererModel);
        return list;
    }


}
