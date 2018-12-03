package cn.com.hqep.bidder.dao;

import cn.com.hqep.bidder.model.tblBbgsFlowStateModel;
import cn.com.hqep.bidder.model.tblBbgsPackageSupplierModel;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by sssJL on 2017-10-12.
 * 标段（包）—供应商关系表 dao类
 */

@Repository
public interface bidderDao {

     /**
      * 保存
      * @param model 插入实体
      * @return 插入结果
      */
     boolean saveBid(tblBbgsPackageSupplierModel model);

     /**
      * 通过条件查询
      * @param model 查询条件的实体
      * @return 实体集合
      */
     List<tblBbgsPackageSupplierModel> queryList(tblBbgsPackageSupplierModel model);


     /**
      * 更新：
      * 通过标段简称 更新数据
      * @param model 带有更新数据的实体
      * @return 更新结果
      */
     boolean updateByModel(tblBbgsPackageSupplierModel model);


     /**
      * 更新：
      * 通过ID 更新数据
      * @param model 带有更新数据的实体
      * @return 更新结果
      */
     boolean updateById(tblBbgsPackageSupplierModel model);


     /**
      * 查询：
      * 通过标段简称 查询数据
      * @param model 查询条件实体
      * @return 实体集合
      */
     List<tblBbgsPackageSupplierModel> queryListByName(tblBbgsPackageSupplierModel model);


     /**
      * 更新：
      * 通过标段简称 更新数据
      * @param model 带有更新数据的实体
      * @return 更新结果
      */
     boolean updateByBidAbbreviaionAndSupplier(tblBbgsPackageSupplierModel model);


     /**
      * 查询去重的所有标段
      * @return 实体集合
      */
     List<tblBbgsPackageSupplierModel> queryDistinctBidList();

     /**
      * 根据标段 查询包并去除重复
      * @param model 带查询条件的实体
      * @return 查询结果（实体集合）
      */
     List<tblBbgsPackageSupplierModel> queryDistinctPackageList(tblBbgsPackageSupplierModel model);

     /**
      * 查询去重的所有标段简称
      * @return 查询结果（实体集合）
      */
     List<tblBbgsPackageSupplierModel> queryDistinctBidAbbreviaionList();


     boolean deleteByModel(tblBbgsPackageSupplierModel model);

     List<tblBbgsPackageSupplierModel> queryListForMinutes();

     /**
      * 查询进行到了哪一个节点及提示信息
      * @return
      */
     tblBbgsFlowStateModel queryMinIdByState();

     /**
      * 通过id 更新流程状态
      * id 为 null 时 更新所有记录
      * @param id
      * @param state
      */
     void updateStateById(@Param("id") String id, @Param("state") String state);

     void deleteByModel_2(tblBbgsPackageSupplierModel model);



     /**
      * 新增方法 查询条件改为in的方式
      * 根据标段 查询包并去除重复
      * @param model 带查询条件的实体
      * @return 查询结果（实体集合）
      */
     List<tblBbgsPackageSupplierModel> queryDistinctPackageListIn(tblBbgsPackageSupplierModel model);


     /**
      * 新增方法 查询条件改为in的方式
      * 通过标段简称 查询数据
      * @param model 查询条件实体
      * @return 实体集合
      */
     List<tblBbgsPackageSupplierModel> queryListByNameIn(tblBbgsPackageSupplierModel model);

     /**
      * 新增方法：更新废标复标供应商 in（）
      * 通过ID 更新数据
      * @param model 带有更新数据的实体
      * @return 更新结果
      */
     boolean updateByInParams(tblBbgsPackageSupplierModel model);


}
