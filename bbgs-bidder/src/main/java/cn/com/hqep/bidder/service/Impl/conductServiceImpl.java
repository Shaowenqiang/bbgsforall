package cn.com.hqep.bidder.service.Impl;

import cn.com.hqep.bidder.dao.bidderDao;
import cn.com.hqep.bidder.dao.conductDao;
import cn.com.hqep.bidder.model.tblBbgsBidInformationModel;
import cn.com.hqep.bidder.model.tblBbgsPackageSupplierModel;
import cn.com.hqep.bidder.service.conductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by sssJL on 2017-10-12.
 * 操作流标和废标的Service 的实现类
 */
@Repository
public class conductServiceImpl implements conductService {

    @Autowired
    conductDao dao; //加载conductDao 使用查询方法

    @Autowired
    bidderDao bidderDao; //加载bidderDao 使用更新方法

    /***
     * 查询所有 标段信息表 中的数据
     * @return 标段信息表的 list 集合
     */
    public List<tblBbgsBidInformationModel> queryList() {
        List<tblBbgsBidInformationModel> list = null;
        try{
            list = dao.queryList();
        }catch (Exception exp){
            exp.printStackTrace();
        }
        return list;
    }

    public List<tblBbgsBidInformationModel> queryByName(tblBbgsBidInformationModel model) {
        List<tblBbgsBidInformationModel> list = null;
        try {
            list = dao.queryByName(model);
        }catch (Exception exp){
            exp.printStackTrace();
        }
        return list;
    }
    public boolean updateBidInfo(tblBbgsBidInformationModel model){
        boolean flag = true;
        try {
            dao.updateBidInfo(model);
        }catch (Exception exp){
            exp.printStackTrace();
            flag = false;
        }
        return flag;
    }

    public boolean updateLikeBidName(tblBbgsPackageSupplierModel model){
        tblBbgsBidInformationModel updateModel = new tblBbgsBidInformationModel();
        boolean flag = true;
        try {
            if ("0".equals(model.getFlagBidInvalid())){
                updateModel.setInvalid(model.getFlagBidInvalid());
                updateModel.setBidAbbreviaion(model.getBid()+"-");
                dao.updateLikeBidName(updateModel);
            }
            if ("1".equals(model.getFlagBidInvalid())){
                List<tblBbgsPackageSupplierModel> list = bidderDao.queryListByName(model);
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
