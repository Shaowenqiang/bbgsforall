package cn.com.hqep.bidder.service.Impl;

import cn.com.hqep.bidder.dao.RaceBidderTendererDao;
import cn.com.hqep.bidder.model.tblBbgsTendererRaceModel;
import cn.com.hqep.bidder.service.RaceBidderTendererService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by hq6 on 2017-10-25.
 */
@Repository
public class RaceBidderTendererServiceImpl implements RaceBidderTendererService {

    @Autowired
    RaceBidderTendererDao dao;
    public List<tblBbgsTendererRaceModel> queryList() {
        List<tblBbgsTendererRaceModel> list = null;
        try {
            list = dao.queryList();
        }catch (Exception exp){
            exp.printStackTrace();
        }
        return list;
    }
}
