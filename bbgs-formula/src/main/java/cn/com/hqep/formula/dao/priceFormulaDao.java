package cn.com.hqep.formula.dao;

import cn.com.hqep.formula.model.*;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by spring on 2017/10/10.
 */
@Repository
public interface priceFormulaDao {
    public List<HashMap> queryBIDInfomation(HashMap param);
    public List<priceFormulaModel> queryInfoToCalculate(Map param);
    public List<priceFormulaModel> queryInfoToExport(Map param);
    public int savePriceInfo(priceFormulaModel model);
    public int savePriceInfoList(List<priceFormulaModel> list);
    public int deletePriceInfo(priceFormulaModel model);
    public int deleteFormulaConditions(formulaConditionsModel model);
    public List<formulaExecuteFunModel> queryFormulaExecuteFun(formulaExecuteFunModel param);
    public List<formulaConditionsModel> queryFormulaConditions(formulaConditionsModel params);
    public int addFormulaConditions(formulaConditionsModel param);
    public int updateFormulaConditions(formulaConditionsModel param);
    public List<supplierModel> querySupplierInfoToCalculateIfWin(Map param);
    public List<supplierModel> querySupplierInfoToCalculateIfWinByPrice(Map param);
    public List<supplierModel> querySupplierInfo(Map param);
    public int updateSupplierInfoList(List<supplierModel> list);
    public int updateSupplierInfo(supplierModel supplierModel);
    public int resetSupplierWin();

    String queryBatchName();

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
     * 查询供应商行分数
     * @return
     */
    List<supplierRowPriceModel> querySupplierRowPrice(@Param("bidAbbr") String bidAbbr);

    int updateSupplierRowPriceModel(supplierRowPriceModel item);

    double querySupplierRowPriceTotal(@Param("supplier")String supplier, @Param("bidAbbr")String bidAbbr);
}
