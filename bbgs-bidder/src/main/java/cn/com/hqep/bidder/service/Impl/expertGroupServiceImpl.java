package cn.com.hqep.bidder.service.Impl;

import cn.com.hqep.bidder.dao.expertGroupDao;
import cn.com.hqep.bidder.service.expertGroupService;
import cn.com.hqep.bidder.model.tblBbgsExpertGroupModel;
import org.springframework.beans.factory.annotation.Autowired;
import cn.com.hqep.bidder.common.jsonMsg;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * Created by HQSpring on 2017/10/17.
 */
@Repository
public class expertGroupServiceImpl implements expertGroupService {
    @Autowired
    private expertGroupDao dao;
    public Map saveExpertGroup(tblBbgsExpertGroupModel egm){
        egm.setId(UUID.randomUUID().toString().replace("-", ""));
        if (dao.queryCount(egm).size() > 0){
            return jsonMsg.toJosn("error","组名重复！");
        }
        if (dao.saveExpertGroup(egm) > 0) {
            return jsonMsg.toJosn("success","保存成功！");
        }
        return jsonMsg.toJosn("error","保存失败！");
    }


    public List<tblBbgsExpertGroupModel> queryGroupList(tblBbgsExpertGroupModel egm) {
        return dao.queryGroupList(egm);
    }

    public boolean updateGroupById(tblBbgsExpertGroupModel egm) {
        boolean flag = true;
        try {
            dao.updateGroupById(egm);
        }catch (Exception exp){
            exp.printStackTrace();
            flag = false;
        }
        return flag;
    }

    /*
     *  查询是否存在
     */
    public int queryCount(tblBbgsExpertGroupModel egm) {
        List<tblBbgsExpertGroupModel> list = dao.queryCount(egm);
        return list.size();
    }

    /**
     *
     * @param egm
     * @return
     */
    public boolean delExpertGroup(tblBbgsExpertGroupModel egm) {
        return dao.delExpertGroup(egm) > 0;
    }
}
