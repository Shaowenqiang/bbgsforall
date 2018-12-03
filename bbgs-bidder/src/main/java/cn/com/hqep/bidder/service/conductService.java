package cn.com.hqep.bidder.service;


import cn.com.hqep.bidder.model.tblBbgsBidInformationModel;
import cn.com.hqep.bidder.model.tblBbgsPackageSupplierModel;

import java.util.List;

/**
 * Created by sssJL on 2017-10-12.
 * 操作流标和废标的Service 类
 */
public interface conductService {

    /**
     * 查询全部
     * @return 实体集合
     */
    List<tblBbgsBidInformationModel> queryList();


    /**
     * 通过标段简称查询
     * @param model 查询条件
     * @return 实体集合
     */
    List<tblBbgsBidInformationModel> queryByName(tblBbgsBidInformationModel model);

    boolean updateBidInfo(tblBbgsBidInformationModel model);

    boolean updateLikeBidName(tblBbgsPackageSupplierModel model);
}
