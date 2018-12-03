package cn.com.hqep.bidder.dao;

import cn.com.hqep.bidder.model.tblBbgsPackageSupplierModel;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by sssJL on 2017-10-25.
 * 计算综合排序Dao
 */
@Repository
public interface comprehensiveSortDao {

    /**
     * 更新：
     * 通过ID 更新数据
     * @param model 带更新条件的实体
     * @return 更新结果 true or false
     */
    boolean updateSortDataById(tblBbgsPackageSupplierModel model);


    /**
     * 查询：
     * 查询全部 标段（包）—供应商关系表
     * @return 实体集合
     */
    List<tblBbgsPackageSupplierModel> queryList();


    /**
     * 查询全部 标段（包）—供应商关系表 的标段简称
     * @return 实体集合
     */
    List<tblBbgsPackageSupplierModel> queryListToName();

    /**
     * 分组查询：
     * 用于导出评分汇总表
     * @return 实体集合
     */
    List<tblBbgsPackageSupplierModel> querySortList(tblBbgsPackageSupplierModel model);

    boolean updateOneToTwo(tblBbgsPackageSupplierModel model);

    boolean updateByNameAndSupplier(tblBbgsPackageSupplierModel model);
}
