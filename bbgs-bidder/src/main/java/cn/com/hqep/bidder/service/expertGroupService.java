package cn.com.hqep.bidder.service;

import cn.com.hqep.bidder.model.tblBbgsExpertGroupModel;

import java.util.List;
import java.util.Map;

/**
 * Created by HQSpring on 2017/10/17.
 */
public interface expertGroupService {
    Map<String,String> saveExpertGroup(tblBbgsExpertGroupModel model);

    List<tblBbgsExpertGroupModel> queryGroupList(tblBbgsExpertGroupModel model);

    boolean updateGroupById(tblBbgsExpertGroupModel model);

    int queryCount(tblBbgsExpertGroupModel model);

    boolean delExpertGroup(tblBbgsExpertGroupModel model);
}
