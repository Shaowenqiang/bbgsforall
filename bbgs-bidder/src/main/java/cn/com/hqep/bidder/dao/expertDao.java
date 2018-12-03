package cn.com.hqep.bidder.dao;

import cn.com.hqep.bidder.model.tblBbgsExpertModel;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by HQSpring on 2017/10/17.
 */
@Repository
public interface expertDao {
    int saveExpert(tblBbgsExpertModel model);

    List<tblBbgsExpertModel> queryExpertList(tblBbgsExpertModel model);

    List<tblBbgsExpertModel> queryByGroupId(tblBbgsExpertModel model);

    boolean updateById(tblBbgsExpertModel model);

    List<tblBbgsExpertModel> queryCount(tblBbgsExpertModel model);

    int delExpert(tblBbgsExpertModel model);

    int delExpertByGroupId(tblBbgsExpertModel model);

}
