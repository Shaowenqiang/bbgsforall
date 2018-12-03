package cn.com.hqep.bidder.action;

import cn.com.hqep.bidder.dao.expertDao;
import cn.com.hqep.bidder.model.tblBbgsExpertGroupModel;
import cn.com.hqep.bidder.model.tblBbgsExpertModel;
import cn.com.hqep.bidder.service.expertGroupService;
import cn.com.hqep.bidder.service.expertService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.*;
import java.util.Map;

/**
 * Created by HQSpring on 2017/10/17.
 */
@Controller
@RequestMapping("/expert")
public class expertAction {
    @Autowired
    expertService expertService;

    @Autowired
    expertGroupService groupService;

    /**
     * 查询专家组信息
     * @param model
     * @return
     */
    @ResponseBody
    @RequestMapping("/queryGroupList")
    public List<tblBbgsExpertGroupModel> queryGroupList(tblBbgsExpertGroupModel model){
        List<tblBbgsExpertGroupModel> list = null;
        try {
            list = groupService.queryGroupList(model);
        }catch (Exception exp){
            exp.printStackTrace();
        }
        return list;
    }

    /**
     * 保存专家组信息
     * @param model
     * @return
     */
    @ResponseBody
    @RequestMapping("/saveExpertGroup")
    public Map saveExpertGroup(tblBbgsExpertGroupModel model){
        return groupService.saveExpertGroup(model);
    }

    /**
     * 删除专家组信息
     * @param model
     * @return
     */
    @ResponseBody
    @RequestMapping("/delExpertGroup")
    public boolean delExpertGroup(tblBbgsExpertGroupModel model){
        return groupService.delExpertGroup(model);
    }

    /**
     * 更新专家组信息
     * @param model
     * @return
     */
    @ResponseBody
    @RequestMapping("/updateGroupById")
    public boolean updateById(tblBbgsExpertGroupModel model){
        return groupService.updateGroupById(model);
    }

    /**
     * 查询全部专家信息
     * @param model
     * @return
     */
    @ResponseBody
    @RequestMapping("/queryExpertList")
    public List<tblBbgsExpertModel> queryGroupList(tblBbgsExpertModel model){
        List<tblBbgsExpertModel> list = null;
        try {
            list = expertService.queryExpertList(model);
        }catch (Exception exp){
            exp.printStackTrace();
        }
        return list;
    }

    /**
     * 通过专家组id查询专家信息
     * @param model
     * @return
     */
    @ResponseBody
    @RequestMapping("/queryByGroupId")
    public List<tblBbgsExpertModel> queryByGroupId(tblBbgsExpertModel model){
        List<tblBbgsExpertModel> list = null;
        try {
            list = expertService.queryByGroupId(model);
        }catch (Exception exp){
            exp.printStackTrace();
        }
        return list;
    }

    /**
     * 保存专家信息
     * @param model
     * @return
     */
    @ResponseBody
    @RequestMapping("/saveExpert")
    public Map saveExpert(tblBbgsExpertModel model){
        return expertService.saveExpert(model);
    }

    /**
     * 删除专家信息
     * @param model
     * @return
     */
    @ResponseBody
    @RequestMapping("/delExpert")
    public boolean delExpert(tblBbgsExpertModel model){
        return expertService.delExpert(model);
    }

    /**
     * 通过专家组id删除专家信息
     * @param model
     * @return
     */
    @ResponseBody
    @RequestMapping("/delExpertByGroupId")
    public boolean delExpertByGroupId(tblBbgsExpertModel model){
        return expertService.delExpertByGroupId(model);
    }
}
