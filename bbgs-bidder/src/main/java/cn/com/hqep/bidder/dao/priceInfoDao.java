package cn.com.hqep.bidder.dao;

import cn.com.hqep.bidder.model.tblBbgsPriceInformationModel;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by sssJl on 2017-10-13.
 * 价格打分信息 dao
 */

@Repository
public interface priceInfoDao {

    /**
     * 查询：
     * 通过 标段简称查询数据
     * @param model 查询条件实体
     * @return 实体集合
     */
    List<tblBbgsPriceInformationModel> queryListByName(tblBbgsPriceInformationModel model);


    /**
     * 查询：
     *  通过标段简称 和 供应商名称 查询
     * @param model 查询条件
     * @return 实体集合
     */
    List<tblBbgsPriceInformationModel> queryListByNameAndSupplier(tblBbgsPriceInformationModel model);

}
