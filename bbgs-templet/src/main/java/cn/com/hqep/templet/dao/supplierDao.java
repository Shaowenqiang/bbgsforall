package cn.com.hqep.templet.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * 对 供应商 表TBL_BBGS_SUPPLIER进行操作
 *
 * @author shaowenqiang
 * @date 2017年10月09日 上午11:17:51
 */
@Repository
public interface supplierDao {
    /**
     * TBL_BBGS_TENDERER 表进行插入操作
     *
     * @param supplier
     * @return
     */
    public int saveSupplier(@Param("supplier") String supplier);
}
