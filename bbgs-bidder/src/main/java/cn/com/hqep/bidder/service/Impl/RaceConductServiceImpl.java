package cn.com.hqep.bidder.service.Impl;

import cn.com.hqep.bidder.dao.RaceBidderDao;
import cn.com.hqep.bidder.dao.RaceConductDao;
import cn.com.hqep.bidder.model.tblBbgsBidInformationRaceModel;
import cn.com.hqep.bidder.model.tblBbgsPackageSupplierRaceModel;
import cn.com.hqep.bidder.service.RaceConductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by sssJL on 2017-10-12.
 * 操作流标和废标的Service 的实现类
 */
@Repository
public class RaceConductServiceImpl implements RaceConductService {

    @Autowired
    RaceConductDao dao; //加载conductDao 使用查询方法

    @Autowired
    RaceBidderDao bidderDao; //加载bidderDao 使用更新方法

    /***
     * 查询所有 标段信息表 中的数据
     * @return 标段信息表的 list 集合
     */
    public List<tblBbgsBidInformationRaceModel> queryList() {
        List<tblBbgsBidInformationRaceModel> list = null;
        try{
            list = dao.queryList();
        }catch (Exception exp){
            exp.printStackTrace();
        }
        return list;
    }

    public List<tblBbgsBidInformationRaceModel> queryByName(tblBbgsBidInformationRaceModel model) {
        List<tblBbgsBidInformationRaceModel> list = null;
        try {
            list = dao.queryByName(model);
        }catch (Exception exp){
            exp.printStackTrace();
        }
        return list;
    }
    public boolean updateBidInfo(tblBbgsBidInformationRaceModel model){
        boolean flag = true;
        try {
            dao.updateBidInfo(model);
        }catch (Exception exp){
            exp.printStackTrace();
            flag = false;
        }
        return flag;
    }

    public boolean updateLikeBidName(tblBbgsPackageSupplierRaceModel model){
        tblBbgsBidInformationRaceModel updateModel = new tblBbgsBidInformationRaceModel();
        boolean flag = true;
        try {
            if ("0".equals(model.getFlagBidInvalid())){
                updateModel.setInvalid(model.getFlagBidInvalid());
                updateModel.setBidAbbreviaion(model.getBid()+"-");
                dao.updateLikeBidName(updateModel);
            }
            if ("1".equals(model.getFlagBidInvalid())){
                List<tblBbgsPackageSupplierRaceModel> list = bidderDao.queryListByName(model);
                for (int i=0;i<list.size();i++){
                    updateModel.setBidAbbreviaion(list.get(i).getBidAbbreviaion());
                    updateModel.setInvalid(list.get(i).getFlagInvalid());
                    dao.updateBidInfo(updateModel);
                }

            }
        }catch (Exception exp){
            exp.printStackTrace();
            flag = false;
        }
        return flag;
    }
}
