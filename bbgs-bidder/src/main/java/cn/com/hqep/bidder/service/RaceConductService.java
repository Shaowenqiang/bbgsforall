package cn.com.hqep.bidder.service;

import cn.com.hqep.bidder.model.tblBbgsBidInformationRaceModel;
import cn.com.hqep.bidder.model.tblBbgsPackageSupplierRaceModel;

import java.util.List;

/**
 * Created by HQSpring on 2018-04-08.
 */
public interface RaceConductService {

    /**
     * 查询全部
     * @return 实体集合
     */
    List<tblBbgsBidInformationRaceModel> queryList();


    /**
     * 通过标段简称查询
     * @param model 查询条件
     * @return 实体集合
     */
    List<tblBbgsBidInformationRaceModel> queryByName(tblBbgsBidInformationRaceModel model);

    boolean updateBidInfo(tblBbgsBidInformationRaceModel model);

    boolean updateLikeBidName(tblBbgsPackageSupplierRaceModel model);
}
