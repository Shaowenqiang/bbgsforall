package cn.com.hqep.templet.dao;

import cn.com.hqep.templet.model.tblBbgsBatchModel;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 批次dao 对数据库中 TBL_BBGS_BATCH 表进行操作
 *
 * @author shaowenqiang
 */
@Repository
public interface batchDao {
    /**
     * 对TBL_BBGS_BATCH 表进行插入操作
     *
     * @param tblBbgsBatch
     * @return 插入条数
     */
    int saveBatch(tblBbgsBatchModel tblBbgsBatch);

    /**
     * 获取数据库中BL_BBGS_BATCH 表所有记录
     *
     * @return 返回实体集合
     */
    List<tblBbgsBatchModel> listAll();
}
