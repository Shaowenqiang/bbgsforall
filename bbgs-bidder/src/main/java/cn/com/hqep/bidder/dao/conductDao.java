package cn.com.hqep.bidder.dao;

import cn.com.hqep.bidder.model.tblBbgsBidInformationModel;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by sssJL on 2017-10-13.
 * 标段信息表 Dao
 */
@Repository
public interface conductDao {

    /**
     * 查询：
     * 查询全部 标段信息表 数据
     * @return 实体集合
     */
    List<tblBbgsBidInformationModel> queryList();


    /**
     *更新：
     * 通过更新条件 更新数据
     * @param model 带更新条件的实体
     * @return 更新结果
     */
    boolean updateBidInfo(tblBbgsBidInformationModel model);


    /**
     * 更新：
     * 通过ID 更新数据
     * @param model 带条件 的实体
     * @return 更新结果
     */
    boolean updateById(tblBbgsBidInformationModel model);

    /**
     * 查询：
     * 通过标段简称 查询数据
     * @param model 带查询条件 的实体
     * @return 实体集合
     */
    List<tblBbgsBidInformationModel> queryByName(tblBbgsBidInformationModel model);

    boolean updateLikeBidName(tblBbgsBidInformationModel model);
}
