package cn.com.hqep.bidder.service;

import cn.com.hqep.bidder.model.tblBbgsTendererRaceModel;

import java.util.List;

/**
 * Created by HQSpring on 2018-04-08.
 */
public interface RaceBidderTendererService {
    /**
     * 查询全部投标人表（原数据表）数据
     * @return 实体集合
     */
    List<tblBbgsTendererRaceModel> queryList();
}
