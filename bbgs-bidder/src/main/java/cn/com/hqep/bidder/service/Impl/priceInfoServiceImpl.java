package cn.com.hqep.bidder.service.Impl;

import cn.com.hqep.bidder.dao.priceInfoDao;
import cn.com.hqep.bidder.model.tblBbgsPriceInformationModel;
import cn.com.hqep.bidder.service.priceInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by sssJL on 2017-10-13.
 * 价格打分信息 Service 实现类impl
 */
@Repository
public class priceInfoServiceImpl implements priceInfoService {

    @Autowired
    priceInfoDao dao; //注入dao

    /***
     * 根据 标段简称 得到所有对应供应商
     * @param model 带查询条件的实体
     * @return 实体集合
     */
    public List<tblBbgsPriceInformationModel> queryListByName(tblBbgsPriceInformationModel model) {
        List<tblBbgsPriceInformationModel> list = null;
        try {
            list = dao.queryListByName(model);
        }catch (Exception exp){
            exp.printStackTrace();
            list = null;
        }
        return list;
    }

    public List<tblBbgsPriceInformationModel> queryListByNameAndSupplier(tblBbgsPriceInformationModel model) {
        List<tblBbgsPriceInformationModel> list = null;
        try {
            list = dao.queryListByNameAndSupplier(model);
        }catch (Exception exp){
            exp.printStackTrace();
        }
        return list;
    }
}
