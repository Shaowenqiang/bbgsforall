package cn.com.hqep.formula.service;

import cn.com.hqep.formula.model.*;
import org.apache.ibatis.annotations.Param;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by spring on 2017/10/10.
 */
public interface formulaService {
    public List<priceFormulaModel> queryInfoToCalculate(Map param);

    public List<priceFormulaModel> queryInfoToExport(Map param);

    public int savePriceInfo(priceFormulaModel model);

    public void savePriceInfoList(List<priceFormulaModel> list);

    public int deletePriceInfo(priceFormulaModel model);

    public ArrayList<priceFormulaModel> calculatePriceScore(ArrayList<priceFormulaModel> list, Map param);

    public boolean exportToExcel(List exportList, String titleStr, String fileName, String parentPath, String type);
    public boolean exportWinToExcel(List exportList, String titleStr, String fileName, String parentPath);

    public List<formulaExecuteFunModel> queryFormulaExecuteFun(formulaExecuteFunModel param);

    public List<formulaConditionsModel> queryFormulaConditions(formulaConditionsModel param);

    public void deleteFormulaConditions(formulaConditionsModel param);

    public int addFormulaConditions(formulaConditionsModel param);

    public int updateFormulaConditions(formulaConditionsModel param);

    public int updateSupplierInfoList(List<supplierModel> list);

    public int updateSupplierInfo(supplierModel supplierModel);

    public List<supplierModel> querySupplierInfoToCalculateIfWin(Map param);
    public List<supplierModel> querySupplierInfo(Map param);
    public ArrayList<supplierModel> calculateSupplierIfWin(ArrayList<supplierModel> list, Map param);
    public int resetSupplierWin();

    /**
     * 查询当前批次批次名称
     * @return
     */
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
}
