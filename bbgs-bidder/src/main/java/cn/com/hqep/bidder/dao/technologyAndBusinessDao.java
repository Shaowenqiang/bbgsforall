package cn.com.hqep.bidder.dao;

import cn.com.hqep.bidder.model.tblBbgsTechnologyBusinessModel;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by sssJL on 2017-10-25.
 * 商务技术打分表 dao
 */
@Repository
public interface technologyAndBusinessDao {

    /**
     * 通过 标段简称 供应商名称 类型 查询数据
     * @param model 查询条件
     * @return 实体集合
     */
    List<tblBbgsTechnologyBusinessModel> queryListByNameAndSupplierAndType(tblBbgsTechnologyBusinessModel model);

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


    List<tblBbgsTechnologyBusinessModel> queryScoreGroupList();

    /**
     * 根据供应商名称 和 标段简称 查询 记录
     * @param model 条件实体
     * @return 实体集合
     */
    List<tblBbgsTechnologyBusinessModel> queryByParam(tblBbgsTechnologyBusinessModel model);

    List<tblBbgsTechnologyBusinessModel> queryByGroupName(tblBbgsTechnologyBusinessModel model);
}
