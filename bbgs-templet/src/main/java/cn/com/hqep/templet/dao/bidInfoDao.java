package cn.com.hqep.templet.dao;

import cn.com.hqep.templet.model.tblBbgsBidInformationModel;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * tblBbgsBidInfoDao 对数据库中 TBL_BBGS_BID_INFORMATION 表进行操作
 *
 * @author hq4
 * @date 2017年10月10日 下午01:12:07
 */
@Repository
public interface bidInfoDao {
    /**
     * 新增记录 使用 oneBid
     *
     * @param tblBbgsBidInformationModel 标段信息实体
     * @return
     */
    int saveBidInfo(tblBbgsBidInformationModel tblBbgsBidInformationModel);

    /**
     * 查询全部记录
     */
    List<tblBbgsBidInformationModel> queryAll();

    /**
     * 查询 标段-包信息 取 flag为N 或 为 null 的记录
     *
     * @return
     */
    List<tblBbgsBidInformationModel> queryAllBid();

    /**
     * 查询 标段-包信息 操作记录 只取 flag为Y的记录
     *
     * @return
     */
    List<tblBbgsBidInformationModel> queryAllBidRecord();
    /**
     * 查询 符合条件的 操作记录
     *
     * @return
     */
    List<tblBbgsBidInformationModel> queryAllByModel(tblBbgsBidInformationModel model);


    /**
     * 更新标段-包 模板信息
     *
     * @param data
     */
    int updateBidInfo(tblBbgsBidInformationModel data);

    /**
     * 更新标段-包 标志位
     *
     * @param model
     * @return
     */
    boolean updateBidInfoFlag(tblBbgsBidInformationModel model);

    List<tblBbgsBidInformationModel> queryAllBidUseIN(List<String> bids);
    /**
     * 查询标段号和标段名称集合（用于绑定标段信息）
     * @return List
     */
    List getBidNameList();
}
