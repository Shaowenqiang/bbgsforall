package cn.com.hqep.bidder.service.Impl;

import cn.com.hqep.bidder.dao.expertDao;
import cn.com.hqep.bidder.dao.expertGroupDao;
import cn.com.hqep.bidder.service.expertService;
import cn.com.hqep.bidder.model.tblBbgsExpertModel;
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
public class expertServiceImpl implements expertService {
    @Autowired
    private expertDao dao;
    @Autowired
    private expertGroupDao groupDao;
    public Map saveExpert(tblBbgsExpertModel egm){
        egm.setId(UUID.randomUUID().toString().replace("-", ""));
        if (dao.queryCount(egm).size() > 0){
            List<tblBbgsExpertModel> idList = dao.queryCount(egm);
            tblBbgsExpertGroupModel groupMo = new tblBbgsExpertGroupModel();
            for(tblBbgsExpertModel per:idList){
                String groupid = per.getGroupId();
                groupMo.setId(groupid);
            }
            List<tblBbgsExpertGroupModel> nameList = groupDao.queryName(groupMo);
            String groupName="";
            for(tblBbgsExpertGroupModel per:nameList){
                groupName = per.getExpertGroupName();
            }
            return jsonMsg.toJosn("error","与"+groupName+"专家姓名重复！");
        }
        if (dao.saveExpert(egm) > 0) {
            return jsonMsg.toJosn("success","保存成功！");
        }
        return jsonMsg.toJosn("error","保存失败！");
    }


    public List<tblBbgsExpertModel> queryExpertList(tblBbgsExpertModel egm) {
        return dao.queryExpertList(egm);
    }

    public List<tblBbgsExpertModel> queryByGroupId(tblBbgsExpertModel egm) {
        return dao.queryByGroupId(egm);
    }

    public boolean updateById(tblBbgsExpertModel egm) {
        boolean flag = true;
        try {
            dao.updateById(egm);
        }catch (Exception exp){
            exp.printStackTrace();
            flag = false;
        }
        return flag;
    }

    /*
     *  查询是否存在
     */
    public int queryCount(tblBbgsExpertModel egm) {
        List<tblBbgsExpertModel> list = dao.queryCount(egm);
        return list.size();
    }

    /**
     *
     * @param egm
     * @return
     */
    public boolean delExpert(tblBbgsExpertModel egm) {
        return dao.delExpert(egm) > 0;
    }

    /**
     *
     * @param egm
     * @return
     */
    public boolean delExpertByGroupId(tblBbgsExpertModel egm) {
        if (dao.queryByGroupId(egm).size()>0)
            return dao.delExpertByGroupId(egm) > 0;
        else
            return true;
    }
}
