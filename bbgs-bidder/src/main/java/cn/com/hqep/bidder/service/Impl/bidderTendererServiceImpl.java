package cn.com.hqep.bidder.service.Impl;

import cn.com.hqep.bidder.dao.bidderTendererDao;
import cn.com.hqep.bidder.model.tblBbgsTendererModel;
import cn.com.hqep.bidder.service.bidderTendererService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by hq6 on 2017-10-25.
 */
@Repository
public class bidderTendererServiceImpl implements bidderTendererService {

    @Autowired
    bidderTendererDao dao;
    public List<tblBbgsTendererModel> queryList() {
        List<tblBbgsTendererModel> list = null;
        try {
            list = dao.queryList();
        }catch (Exception exp){
            exp.printStackTrace();
        }
        return list;
    }
}
