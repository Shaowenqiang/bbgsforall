package cn.com.hqep.bidder.service;

import cn.com.hqep.bidder.model.tblBbgsExpertModel;

import java.util.List;
import java.util.Map;

/**
 * Created by HQSpring on 2017/10/17.
 */
public interface expertService {
    Map<String,String> saveExpert(tblBbgsExpertModel model);

    List<tblBbgsExpertModel> queryExpertList(tblBbgsExpertModel model);

    List<tblBbgsExpertModel> queryByGroupId(tblBbgsExpertModel model);

    boolean updateById(tblBbgsExpertModel model);

    int queryCount(tblBbgsExpertModel model);

    boolean delExpert(tblBbgsExpertModel model);

    boolean delExpertByGroupId(tblBbgsExpertModel model);
}
