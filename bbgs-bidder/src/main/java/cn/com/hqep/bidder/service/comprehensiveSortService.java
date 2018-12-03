package cn.com.hqep.bidder.service;

import cn.com.hqep.bidder.model.tblBbgsPackageSupplierModel;
import cn.com.hqep.bidder.model.tblBbgsTechnologyBusinessModel;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

/**
 * Created by sssJl on 2017-10-25.
 * 综合排序的Service
 */
public interface comprehensiveSortService {

    /**
     * 通过 ID 更新数据
     * @return 更新结果
     */
    Map updateSortDataById();

    List<tblBbgsPackageSupplierModel> querySortList(tblBbgsPackageSupplierModel model);

    /**
     * 查询：
     * 查询所有技术/商务打分情况表数据
     * 使用：导出平均分汇总表
     * @return 实体集合
     */
    List<tblBbgsTechnologyBusinessModel> queryListToTechnologyBusiness();

    /**
     * 查询：
     * 查询所有专家名字
     * @return 实体集合
     * */
    List<String> queryExpertNames();
    /***
     * 查询：
     * 查询去除重复的供应商和标段简称
     * @return
     */
    List<tblBbgsTechnologyBusinessModel> queryListToBidAbbreviaionSupplier();

    /**
     * 导出平均分汇总、综合排序
     * @return 文件名
     */
    Map queryScoreGroupList();

    boolean dealSideFirstData(tblBbgsPackageSupplierModel model);
}
