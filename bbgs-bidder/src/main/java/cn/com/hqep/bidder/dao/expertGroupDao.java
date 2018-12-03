package cn.com.hqep.bidder.dao;

import cn.com.hqep.bidder.model.tblBbgsExpertGroupModel;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by HQSpring on 2017/10/17.
 */
@Repository
public interface expertGroupDao {
    int saveExpertGroup(tblBbgsExpertGroupModel model);

    List<tblBbgsExpertGroupModel> queryGroupList(tblBbgsExpertGroupModel model);

    boolean updateGroupById(tblBbgsExpertGroupModel model);

    List<tblBbgsExpertGroupModel> queryCount(tblBbgsExpertGroupModel model);

    int delExpertGroup(tblBbgsExpertGroupModel model);

    List<tblBbgsExpertGroupModel> queryName(tblBbgsExpertGroupModel model);
}
