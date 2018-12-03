package cn.com.hqep.bidder.dao;

import cn.com.hqep.bidder.model.tblBbgsBidInformationRaceModel;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by HQSpring on 2018-04-08.
 */
@Repository
public interface RaceConductDao {
    /**
     * 查询：
     * 查询全部 标段信息表 数据
     * @return 实体集合
     */
    List<tblBbgsBidInformationRaceModel> queryList();


    /**
     *更新：
     * 通过更新条件 更新数据
     * @param model 带更新条件的实体
     * @return 更新结果
     */
    boolean updateBidInfo(tblBbgsBidInformationRaceModel model);


    /**
     * 更新：
     * 通过ID 更新数据
     * @param model 带条件 的实体
     * @return 更新结果
     */
    boolean updateById(tblBbgsBidInformationRaceModel model);

    /**
     * 查询：
     * 通过标段简称 查询数据
     * @param model 带查询条件 的实体
     * @return 实体集合
     */
    List<tblBbgsBidInformationRaceModel> queryByName(tblBbgsBidInformationRaceModel model);

    boolean updateLikeBidName(tblBbgsBidInformationRaceModel model);
}
