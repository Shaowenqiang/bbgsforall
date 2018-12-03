package cn.com.hqep.bidder.service;

import cn.com.hqep.bidder.model.tblBbgsFlowStateModel;
import cn.com.hqep.bidder.model.tblBbgsPackageSupplierModel;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.List;
import java.util.Map;

/**
 * Created by sssJL on 2017-10-12.
 * 标段（包）—供应商关系表 Service类
 */
public interface bidderService {

     /**
      * 保存
      * @param file 上传文件
      * @return 反馈结果
      */
     Map<String,String> saveBid(MultipartFile file);


     /**
      * 更新：
      * 根据ID更新数据
      * @param model 更新实体
      * @return 更新结果
      */
     boolean updateById(tblBbgsPackageSupplierModel model);


     /**
      * 根据条件查询
      * @param model 查询条件
      * @return 实体集合
      */
     List<tblBbgsPackageSupplierModel> queryDataList(tblBbgsPackageSupplierModel model);

     /**
      * 根据 标段简称 查询
      * @param model 查询条件
      * @return 实体集合
      */
     List<tblBbgsPackageSupplierModel> queryListByName(tblBbgsPackageSupplierModel model);

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

     /***
      * 操作一个包 流标和招标的方法
      * @param model 操作信息 标段和包名和流标标志位
      * @return 操作结果
      */
     boolean updateInvalidActionOne(tblBbgsPackageSupplierModel model);

     /***
      * 操作一个标段 流标和招标的方法
      * @param model 操作信息 标段 流标标志位
      * @return 操作结果
      */
     boolean updateByModel(tblBbgsPackageSupplierModel model);

     List<tblBbgsPackageSupplierModel> queryDistinctBidAbbreviaionList();


     /***
      * 导出定标纪要
      * @return 结果信息
      */
     Map exportForMinutes();

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
