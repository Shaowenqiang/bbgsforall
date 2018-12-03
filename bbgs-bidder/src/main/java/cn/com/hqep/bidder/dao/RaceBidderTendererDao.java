package cn.com.hqep.bidder.dao;

import cn.com.hqep.bidder.model.tblBbgsTendererRaceModel;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by sssJL on 2017-10-25.
 * 投标人表（原数据表）Dao
 */
@Repository
public interface RaceBidderTendererDao {

    /**
     * 查询所有投标人表（原数据表）数据
     * @return 实体集合
     */
    List<tblBbgsTendererRaceModel> queryList();
}
