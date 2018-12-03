package cn.com.hqep.bidder.service;

import cn.com.hqep.bidder.model.tblBbgsTendererModel;

import java.util.List;

/**
 * Created by sssJL on 2017-10-25.
 * 投标人表（原数据表）Service
 */
public interface bidderTendererService {

    /**
     * 查询全部投标人表（原数据表）数据
     * @return 实体集合
     */
    List<tblBbgsTendererModel> queryList();
}
