package cn.com.hqep.bidder.dao;

import cn.com.hqep.bidder.model.tblBbgsBidInformationModel;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface wordExportDao {

    /**
     * 查询所有查询所有标段简称
     * @return
     */
    List<tblBbgsBidInformationModel> queryAllBidInfo();
    /**
     *
     */
    List<Map> queryNums();

    /**
     * 查询项目名
     * @return
     */
    String queryBatchName();

    /**
     * 查询合计金额
     * @return
     */
    Float queryP_hjje();
    /**
     *  查询标段报价
     */
    List<Map> queryBidOffer();

    /**
     * 查询是否计算过综合排序
     *
     * @return
     */
    String querySort();
}
