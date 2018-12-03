package cn.com.hqep.bidder.dao;

import cn.com.hqep.bidder.model.tblBbgsFlowStateRaceModel;
import cn.com.hqep.bidder.model.tblBbgsPackageSupplierRaceModel;
import cn.com.hqep.bidder.model.tblBbgsPriceInformationRaceModel;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by HQSpring on 2018-04-08.
 */
@Repository
public interface RaceBidderDao {

        /**
         * 保存
         *
         * @param model 插入实体
         * @return 插入结果
         */
        boolean saveBid(tblBbgsPackageSupplierRaceModel model);

        /**
         * 通过条件查询
         *
         * @param model 查询条件的实体
         * @return 实体集合
         */
        List<tblBbgsPackageSupplierRaceModel> queryList(tblBbgsPackageSupplierRaceModel model);


        /**
         * 更新：
         * 通过标段简称 更新数据
         *
         * @param model 带有更新数据的实体
         * @return 更新结果
         */
        boolean updateByModel(tblBbgsPackageSupplierRaceModel model);


        /**
         * 更新：
         * 通过ID 更新数据
         *
         * @param model 带有更新数据的实体
         * @return 更新结果
         */
        boolean updateById(tblBbgsPackageSupplierRaceModel model);


        /**
         * 查询：
         * 通过标段简称 查询数据
         *
         * @param model 查询条件实体
         * @return 实体集合
         */
        List<tblBbgsPackageSupplierRaceModel> queryListByName(tblBbgsPackageSupplierRaceModel model);


        /**
         * 更新：
         * 通过标段简称 更新数据
         *
         * @param model 带有更新数据的实体
         * @return 更新结果
         */
        boolean updateByBidAbbreviaionAndSupplier(tblBbgsPackageSupplierRaceModel model);


        /**
         * 查询去重的所有标段
         *
         * @return 实体集合
         */
        List<tblBbgsPackageSupplierRaceModel> queryDistinctBidList();

        /**
         * 根据标段 查询包并去除重复
         *
         * @param model 带查询条件的实体
         * @return 查询结果（实体集合）
         */
        List<tblBbgsPackageSupplierRaceModel> queryDistinctPackageList(tblBbgsPackageSupplierRaceModel model);

        /**
         * 查询去重的所有标段简称
         *
         * @return 查询结果（实体集合）
         */
        List<tblBbgsPackageSupplierRaceModel> queryDistinctBidAbbreviaionList();


        boolean deleteByModel(tblBbgsPackageSupplierRaceModel model);

        List<tblBbgsPackageSupplierRaceModel> queryListForMinutes();

        /**
         * 查询进行到了哪一个节点及提示信息
         *
         * @return
         */
        tblBbgsFlowStateRaceModel queryMinIdByState();

        /**
         * 通过id 更新流程状态
         * id 为 null 时 更新所有记录
         *
         * @param id
         * @param state
         */
        void updateStateById(@Param("id") String id, @Param("state") String state);

        List<tblBbgsPriceInformationRaceModel> queryPriceListByName(tblBbgsPriceInformationRaceModel model);

        List<tblBbgsPackageSupplierRaceModel> querySortList(tblBbgsPackageSupplierRaceModel model);
}
