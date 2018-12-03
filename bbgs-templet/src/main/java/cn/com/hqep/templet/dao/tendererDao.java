package cn.com.hqep.templet.dao;

import cn.com.hqep.templet.model.tblBbgsTendererModel;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * 对 投标人信息 表 TBL_BBGS_TENDERER 进行操作
 *
 * @author shaowenqiang
 * @date 2017年10月09日 上午10:59:35
 */
@Repository
public interface tendererDao {
    /**
     * 对 TBL_BBGS_TENDERER 表进行插入操作
     *
     * @param Tenderer
     * @return
     */
    int saveTenderer(tblBbgsTendererModel Tenderer);

    /**
     * 清空当前批次数据
     */
    void delTableData(@Param("tableName") String tableName);

    /**
     * 查询投标人明细
     *
     * @param tblBbgsTendererModel
     * @return
     */
    List<tblBbgsTendererModel> contentAll(tblBbgsTendererModel tblBbgsTendererModel);

    /**
     * 查询全部
     *
     * @param tblBbgsTendererModel
     * @return
     */
    List<tblBbgsTendererModel> queryAllByModel(tblBbgsTendererModel tblBbgsTendererModel);

    /**
     * 清空数据 插入技术一组 商务一组
     */
    void deleteAllData();

    /**
     * 导入报价时，先删除表内信息
     */
    void deleteForSaveOffer();

    /**
     * 插入行价格
     * @param rowMap
     * @return
     */
    int insertRowPrice(Map rowMap);

    /**
     * 通过行价格统计出供应商总报价
     * @return
     */
    List<tblBbgsTendererModel> querySupplierTotalPriceForBid();

    /**
     * 查询是否存在重复行单价
     * @param oneRow
     * @return
     */
    List<String> queryRowPrice(Map<String, String> oneRow);
}
