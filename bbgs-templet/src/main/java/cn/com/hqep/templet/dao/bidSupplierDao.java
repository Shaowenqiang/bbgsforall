package cn.com.hqep.templet.dao;

import cn.com.hqep.templet.model.tblBbgsPackageSupplierModel;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 包-供应商表Dao
 *
 * @author hq4
 * @date 2017年10月18日 上午10:20:35
 */
@Repository
public interface bidSupplierDao {
    /**
     * 查询 标段信息与供应商关联表 所有信息
     *
     * @param model
     * @return
     */
    List<tblBbgsPackageSupplierModel> queryList(tblBbgsPackageSupplierModel model);

    /**
     * 更新标段-供应商记录
     *
     * @param model
     */
    void updateScore(tblBbgsPackageSupplierModel model);

    /**
     * 新增标段-供应商记录
     *
     * @param model
     * @return
     */
    int saveBid(tblBbgsPackageSupplierModel model);

    /**
     * 查询所有包-供应商信息
     *
     * @param tblBbgsPackageSupplierModel
     * @return
     */
    List<tblBbgsPackageSupplierModel> queryAllListByModel(tblBbgsPackageSupplierModel tblBbgsPackageSupplierModel);
}
