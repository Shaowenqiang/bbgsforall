package cn.com.hqep.bidder.service;

import cn.com.hqep.bidder.model.tblBbgsPriceInformationModel;

import java.util.List;

/**
 * Created by sssJL on 2017-10-13.
 * 价格表Service
 */
public interface priceInfoService {

    /**
     * 通过标段简称 查询数据
     * @param model 查询条件
     * @return 实体集合
     */
    List<tblBbgsPriceInformationModel> queryListByName(tblBbgsPriceInformationModel model);


    /**
     * 通过 标段简称 供应商名称 查询数据
     * @param model 查询条件
     * @return 实体集合
     */
    List<tblBbgsPriceInformationModel> queryListByNameAndSupplier(tblBbgsPriceInformationModel model);
}
