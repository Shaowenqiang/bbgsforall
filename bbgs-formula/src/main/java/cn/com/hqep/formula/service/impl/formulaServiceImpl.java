package cn.com.hqep.formula.service.impl;

import cn.com.hqep.formula.dao.priceFormulaDao;
import cn.com.hqep.formula.model.*;
import cn.com.hqep.formula.service.formulaService;
import cn.com.hqep.formula.util.ExcelOutTemplate;
import org.apache.ibatis.annotations.Param;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.ss.util.CellReference;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.context.ContextLoader;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.io.*;
import java.lang.reflect.Array;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

/**
 * Created by spring on 2017/10/10.
 */
@Repository
public class formulaServiceImpl implements formulaService {
    @Autowired
    private priceFormulaDao dao;

    public List<priceFormulaModel> queryInfoToCalculate(Map param) {
        return dao.queryInfoToCalculate(param);
    }

    public List<priceFormulaModel> queryInfoToExport(Map param) {
        return dao.queryInfoToExport(param);
    }

    public int savePriceInfo(priceFormulaModel model) {
        return dao.savePriceInfo(model);
    }

    public void savePriceInfoList(List<priceFormulaModel> list) {
        dao.savePriceInfoList(list);
    }

    public int deletePriceInfo(priceFormulaModel model) {
        return dao.deletePriceInfo(model);
    }

    // 格式化小数点位数
    public String decimalFormatNum(double num, int ws) {
        return String.format("%." + ws + "f", num);
    }

    public ArrayList<priceFormulaModel> calculatePriceScore(ArrayList<priceFormulaModel> list, Map param) {
        String priceFormulaPath = (String) param.get("priceFun");
        ArrayList<priceFormulaModel> result = null;
        HashMap hashMap1 = new HashMap(param);
//        List<HashMap> hashMap2 = dao.queryBIDInfomation(hashMap1);
        if (list.size() > 0) {
            priceFormulaPath = String.valueOf(list.get(0).getPrice_formula());
        }
        if ("formulaService@materials_priceFormula_1".equals(priceFormulaPath)) {
            this.materials_priceFormula_1(list);
        }
        if ("formulaService@materials_priceFormula_2".equals(priceFormulaPath)) {
            this.materials_priceFormula_2(list);
        }
        if ("formulaService@notmaterials_priceFormula_1".equals(priceFormulaPath)) {
            this.notmaterials_priceFormula_1(list);
        }
        if ("formulaService@notmaterials_priceFormula_2".equals(priceFormulaPath)) {
            this.notmaterials_priceFormula_2(list);
        }
        if ("formulaService@notmaterials_priceFormula_3".equals(priceFormulaPath)) {
            this.notmaterials_priceFormula_3(list);
        }
        if ("formulaService@materials_priceFormula_3".equals(priceFormulaPath)) {
//            计算行价格
            this.calculateRowPrice(list.get(0).getBid_abbreviaion());
            this.materials_priceFormula_3(list);
        }
        return list;
    }

    public ArrayList<supplierModel> calculateSupplierIfWin(ArrayList<supplierModel> list, Map param) {
        ArrayList<supplierModel> result = new ArrayList<>(list.size() / 2);
        ArrayList<String> arr_bid_abb = new ArrayList<>(0);
        ArrayList<String> arr_supplier = new ArrayList<>(0);
        Iterator<supplierModel> iterator = list.iterator();
        String bid, bid_abb, supplierName, sort;
        //supplier_sortByStringField(list,"sort",true);
        /**
         * 每个list都是按照以下字段依次排序好
         * bid，sort，supplier，offer desc，total_score desc，technology_score desc，price_score desc
         * bid，sort: 此排序之后此标段所有包的第一名将排前面
         * supplier，offer desc: 在之前的排序基础上，此排序之后相同的供应商将按报价高低聚集
         * total_score desc，technology_score desc，price_score desc: 在之前的排序基础上，此排序之后得分最高的会在最前
         * 以上排序不要变
         * */
        while (iterator.hasNext()) {
            supplierModel item = iterator.next();
            bid_abb = item.getBid_abbreviaion();
            supplierName = item.getSupplier();
            sort = item.getSort();
            //此标段包未选标
            if (arr_bid_abb.contains(bid_abb) == false) {
                //第一名的供应商未选
                if (arr_supplier.contains(supplierName) == false) {
                    item.setIs_win("1");
                    result.add(item);
                    arr_bid_abb.add(bid_abb);
                    arr_supplier.add(supplierName);
                }
            }
        }
        return result;
    }

    public int resetSupplierWin() {
        return dao.resetSupplierWin();
    }

    @Override
    public String queryBatchName() {
        return dao.queryBatchName();
    }

    private <T> ArrayList<T> slice(ArrayList<T> list, int start, int end) {
        ArrayList result;
        T[] array = (T[]) list.toArray();
        return new ArrayList<T>(Arrays.asList(slice(array, start, end)));
    }

    private <T> List<T> slice(List<T> list, int start, int limit) {
        T[] array = (T[]) list.toArray();
        return Arrays.asList(slice(array, start, limit));
    }

    private <T> T[] slice(T[] array, int start, int limit) {
        int from = Math.max(start, 0);
        int to = from + Math.max(limit, 0);
        to = Math.min(to, array.length);
        return copyOfRange(array, from, to);
    }

    private <T> T[] copyOfRange(T[] original, int from, int to) {
        return copyOfRange(original, from, to, (Class<? extends T[]>) original.getClass());
    }

    private <T, U> T[] copyOfRange(U[] original, int from, int to, Class<? extends T[]> newType) {
        int newLength = to - from;
        if (newLength < 0)
            throw new IllegalArgumentException(from + " > " + to);
        @SuppressWarnings("unchecked")
        T[] copy = ((Object) newType == (Object) Object[].class)
                ? (T[]) new Object[newLength]
                : (T[]) Array.newInstance(newType.getComponentType(), newLength);
        System.arraycopy(original, from, copy, 0,
                Math.min(original.length - from, newLength));
        return copy;
    }

    /**
     * 物资类
     * 区间复合平均价法（次低价平均）
     */
    private List<priceFormulaModel> materials_priceFormula_1(List<priceFormulaModel> list) {
        double A1, A2 = 0.0, A3, A4, minPrice, basePrice, sumAllPrice;
        int N, P, M, mStart, mEnd, cutLow = 0, cutHight = 0;

        //条件可由查询取得
        formulaConditionsModel param = new formulaConditionsModel();
        param.setExecutefun("formulaService@materials_priceFormula_1");
        param.setConditionname("M");
        List<formulaConditionsModel> conditionsModels = dao.queryFormulaConditions(param);
//        sliceByCountModel[] sliceByArr = new sliceByCountModel[conditionsModels.size()];
//        sliceByArr[0] = new sliceByCountModel(0, 10, 0, 0);
//        sliceByArr[1] = new sliceByCountModel(10, 20, 1, 1);
//        sliceByArr[2] = new sliceByCountModel(20, 30, 1, 2);
//        sliceByArr[3] = new sliceByCountModel(30, 1000, 2, 3);
//        sliceByCountModel match = new sliceByCountModel(0, 1000, 0, 0);
        M = list.size();
        for (int i = 0; i < conditionsModels.size(); i++) {
            formulaConditionsModel item = conditionsModels.get(i);
            int start = Integer.parseInt(item.getStartvalue());
            int end = Integer.parseInt(item.getEndvalue());
            if (start <= M && M < end) {
                cutLow = Integer.parseInt(item.getSubminpricecount());
                cutHight = Integer.parseInt(item.getSubmaxpricecount());
                break;
            }
        }
        ArrayList<priceFormulaModel> sortedBaseList = this.sortASCByPrice(list);
        ArrayList<priceFormulaModel> ListN = (ArrayList<priceFormulaModel>) slice(sortedBaseList, cutLow, M - cutHight);
        N = ListN.size();
        A1 = sumPrice(ListN, "price");
        A1 = A1 / N;

        ArrayList<priceFormulaModel> ListP = new ArrayList<priceFormulaModel>(0);
        Iterator iter = ListN.iterator();
        while (iter.hasNext()) {
            priceFormulaModel item = (priceFormulaModel) iter.next();
            double price = Double.valueOf(item.getPrice());
            if (isInRange(price, A1, 0.8, 1.1)) {
                ListP.add(item);
            }
        }
        P = ListP.size();
        if (P > 0) {
            A2 = sumPrice(ListP, "price");
            A2 = A2 / P;
            minPrice = Double.valueOf((ListP.get(0)).getPrice());
            A3 = (A2 + minPrice) / 2;
            basePrice = A3;
        } else {//P为0则说明listN都在范围之外
            A4 = sumPrice(sortedBaseList, "price");
            A4 = A4 / M;
            basePrice = A4;
        }

        for (int i = 0; i < sortedBaseList.size(); i++) {
            priceFormulaModel item = sortedBaseList.get(i);
            double priceScore = 0, itemPrice = Double.valueOf(item.getPrice()), priceWeight = Double.valueOf(item.getPrice_weight());
            item.setId(getID());
            //item.setA1(String.valueOf(A1));
            item.setA1(this.decimalFormatNum(A1, 7));
            //item.setA2(String.valueOf(A2));
            item.setA2(this.decimalFormatNum(A2, 7));
            item.setN(String.valueOf(N));
            item.setP(String.valueOf(P));
            //item.setBase_price(String.valueOf(basePrice));
            item.setBase_price(this.decimalFormatNum(basePrice, 7));
            priceWeight = 1;
            if (itemPrice >= basePrice) {
                priceScore = 100 - 100 * 1 * 1 * Math.abs(itemPrice - basePrice) / basePrice;
            } else if (itemPrice < basePrice) {
                priceScore = 100 - 100 * 1 * Double.valueOf(item.getM()) * Math.abs(itemPrice - basePrice) / basePrice;
            }
            priceScore = Math.max(priceScore, 0);
            //item.setPrice_score(String.valueOf(priceScore));
            item.setPrice_score(this.decimalFormatNum(priceScore, 2));
            item.setExport_type(priceFormulaModel.EXPORT_TYPE_MATERIALS);
        }
        //按价格得分排序,由高到低
        this.sortByNumberField(sortedBaseList, "Price_score", false);
        for (int i = 0; i < sortedBaseList.size(); i++) {
            priceFormulaModel item = sortedBaseList.get(i);
            item.setSort(String.valueOf(i + 1));
        }
        return sortedBaseList;
    }

    /**
     * 电商类公式
     * 平均浮动双边曲线法
     */
    private List<priceFormulaModel> materials_priceFormula_3(List<priceFormulaModel> list) {
        double A1, A2 = 0.0, A3, A4, minPrice, basePrice, sumAllPrice;
        int N, P, M, mStart, mEnd, cutLow = 0, cutHight = 0;

        //条件可由查询取得
        formulaConditionsModel param = new formulaConditionsModel();
        param.setExecutefun("formulaService@materials_priceFormula_3");
        param.setConditionname("M");
        List<formulaConditionsModel> conditionsModels = dao.queryFormulaConditions(param);
//        sliceByCountModel[] sliceByArr = new sliceByCountModel[conditionsModels.size()];
//        sliceByArr[0] = new sliceByCountModel(0, 10, 0, 0);
//        sliceByArr[1] = new sliceByCountModel(10, 20, 1, 1);
//        sliceByArr[2] = new sliceByCountModel(20, 30, 1, 2);
//        sliceByArr[3] = new sliceByCountModel(30, 1000, 2, 3);
//        sliceByCountModel match = new sliceByCountModel(0, 1000, 0, 0);
        M = list.size();
        for (int i = 0; i < conditionsModels.size(); i++) {
            formulaConditionsModel item = conditionsModels.get(i);
            int start = Integer.parseInt(item.getStartvalue());
            int end = Integer.parseInt(item.getEndvalue());
            if (start <= M && M < end) {
                cutLow = Integer.parseInt(item.getSubminpricecount());
                if (start<=0){
                    cutHight= Integer.parseInt(item.getSubmaxpricecount())+1 ;
                }else{
                    cutHight = Integer.parseInt(item.getSubmaxpricecount());
                }
                break;
            }
        }
        ArrayList<priceFormulaModel> sortedBaseList = this.sortASCByPrice(list);
        ArrayList<priceFormulaModel> ListN = (ArrayList<priceFormulaModel>) slice(sortedBaseList, cutLow, M - cutHight);
        N = ListN.size();
        A1 = sumPrice(ListN, "price");
        A1 = A1 / N;
        double a = 0.01;
        //基准价 A2
        A2 = A1 + A1 * a;
        basePrice = A2;

        for (int i = 0; i < sortedBaseList.size(); i++) {
            priceFormulaModel item = sortedBaseList.get(i);
            double priceScore = 0, itemPrice = Double.valueOf(item.getPrice()), priceWeight = Double.valueOf(item.getPrice_weight());
            item.setId(getID());
            //item.setA1(String.valueOf(A1));
            item.setA1(this.decimalFormatNum(A1, 7));
            //item.setA2(String.valueOf(A2));
            item.setA2(this.decimalFormatNum(A2, 7));
            item.setN(String.valueOf(N));
//            item.setP(String.valueOf(P));
            //item.setBase_price(String.valueOf(basePrice));
            item.setBase_price(this.decimalFormatNum(basePrice, 7));
            priceWeight = 1;
            if (itemPrice >= basePrice) {
                priceScore = 0.4 * 100 * (basePrice/itemPrice);
            } else if (itemPrice < basePrice) {
                priceScore = 0.4 * 100 * Math.pow((itemPrice/basePrice),0.6);
            }
            String supplier = item.getSupplier();
            String bidAbbr =item.getBid_abbreviaion();
            // 查询行价格分数总数
            double rowPriceScore = dao.querySupplierRowPriceTotal(supplier,bidAbbr);
            // 取60%行价格分数
            priceScore = priceScore + 0.6 * rowPriceScore;
            priceScore = Math.max(priceScore, 0);
            //item.setPrice_score(String.valueOf(priceScore));
            item.setPrice_score(this.decimalFormatNum(priceScore, 2));
            item.setExport_type(priceFormulaModel.EXPORT_TYPE_MATERIALS);
        }
        //按价格得分排序,由高到低
        this.sortByNumberField(sortedBaseList, "Price_score", false);
        for (int i = 0; i < sortedBaseList.size(); i++) {
            priceFormulaModel item = sortedBaseList.get(i);
            item.setSort(String.valueOf(i + 1));
        }
        return sortedBaseList;
    }

    /**
     * 电商公式
     * 计算行价格
     * @param bidAbbr 标段简称
     * @return
     */
    public List calculateRowPrice (String bidAbbr){

        //条件可由查询取得
        formulaConditionsModel param = new formulaConditionsModel();
        param.setExecutefun("formulaService@materials_priceFormula_3");
        param.setConditionname("M");
        List<formulaConditionsModel> conditionsModels = dao.queryFormulaConditions(param);

        List<supplierRowPriceModel> list = dao.querySupplierRowPrice(bidAbbr);
        Map<String,List<supplierRowPriceModel>> map = new LinkedHashMap<>();
        for (supplierRowPriceModel model : list){
            String rowType = model.getRow_type();
            if(!map.keySet().contains(rowType)){
                List<supplierRowPriceModel> l = new ArrayList<>();
                l.add(model);
                map.put(rowType,l);
            }else {
                List<supplierRowPriceModel> l = map.get(rowType);
                l.add(model);
                map.put(rowType,l);
            }
        }
        for(String type:map.keySet()){
            //计算一个行类别的
            calculateOneTypeRowPrice(conditionsModels,map.get(type));
        }
        return null;
    }

    private void calculateOneTypeRowPrice(List<formulaConditionsModel> conditionsModels, List<supplierRowPriceModel> supplierRowPriceModels) {
        double A1, A2 = 0.0, A3, A4, minPrice, basePrice, sumAllPrice;
        int N, P, M, mStart, mEnd, cutLow = 0, cutHight = 0;

        M = supplierRowPriceModels.size();
        for (int i = 0; i < conditionsModels.size(); i++) {
            formulaConditionsModel item = conditionsModels.get(i);
            int start = Integer.parseInt(item.getStartvalue());
            int end = Integer.parseInt(item.getEndvalue());
            if (start <= M && M < end) {
                cutLow = Integer.parseInt(item.getSubminpricecount());
                if (start!=0){
                    cutHight= Integer.parseInt(item.getSubmaxpricecount())+1 ;
                }else{
                    cutHight = Integer.parseInt(item.getSubmaxpricecount());
                }
                break;
            }
        }
        ArrayList<supplierRowPriceModel> sortedBaseList = this.sortASCByPriceForRowPice(supplierRowPriceModels);
        ArrayList<supplierRowPriceModel> ListN = (ArrayList<supplierRowPriceModel>) slice(sortedBaseList, cutLow, M - cutHight);
        N = ListN.size();
        A1 = sumPriceForRowPrice(ListN, "row_price");
        A1 = A1 / N;
        double a = 0.01;
        //基准价 A2
        A2 = A1 + A1 * a;
        basePrice = A2;

        for (int i = 0; i < sortedBaseList.size(); i++) {
            supplierRowPriceModel item = sortedBaseList.get(i);
            double priceScore=0,itemPrice = Double.valueOf(item.getRow_price()),rowPriceWeight=Double.valueOf(item.getRow_price_weight());
            if (itemPrice >= basePrice) {
                priceScore = 100 * (basePrice/itemPrice) * rowPriceWeight;
            } else if (itemPrice < basePrice) {
                priceScore = 100 * Math.pow((itemPrice/basePrice),0.6) * rowPriceWeight;
            }
            item.setBase_price(this.decimalFormatNum(basePrice, 7));
            item.setScore(this.decimalFormatNum(Math.max(priceScore, 0), 7));
            item.setA1(this.decimalFormatNum(A1, 7));
            item.setA2(this.decimalFormatNum(A2, 7));
            item.setN(String.valueOf(N));
            item.setM(String.valueOf(M));
            dao.updateSupplierRowPriceModel(item);
        }
    }

    /**
     * 物资类
     * 最低评标价法
     */
    private List<priceFormulaModel> materials_priceFormula_2(List<priceFormulaModel> list) {
        double A1, A2, A3, A4, minPrice, basePrice, sumAllPrice;
        int N, P, M, mStart, mEnd, cutLow, cutHight;
        ArrayList<priceFormulaModel> sortedBaseList = this.sortASCByPrice(list);
        basePrice = Double.parseDouble(sortedBaseList.get(0).getPrice());
        for (int i = 0; i < sortedBaseList.size(); i++) {
            priceFormulaModel item = sortedBaseList.get(i);
            double priceScore = 0, itemPrice = Double.valueOf(item.getPrice()), priceWeight = Double.valueOf(item.getPrice_weight());
            item.setId(getID());
            item.setA1(String.valueOf(0));
            item.setA2(String.valueOf(0));
            item.setN(String.valueOf(0));
            item.setP(String.valueOf(0));
            //item.setBase_price(String.valueOf(basePrice));
            item.setBase_price(this.decimalFormatNum(basePrice, 7));
            priceWeight = 1;
            priceScore = 100 - 100 * 1 * Math.abs(itemPrice - basePrice) / basePrice * priceWeight;
            priceScore = Math.max(priceScore, 0);
            //item.setPrice_score(String.valueOf(priceScore));
            item.setPrice_score(this.decimalFormatNum(priceScore, 2));
            item.setExport_type(priceFormulaModel.EXPORT_TYPE_MATERIALS);
        }
        //按价格得分排序,由高到低
        this.sortByNumberField(sortedBaseList, "Price_score", false);
        for (int i = 0; i < sortedBaseList.size(); i++) {
            priceFormulaModel item = sortedBaseList.get(i);
            item.setSort(String.valueOf(i + 1));
        }
        return sortedBaseList;
    }


    /**
     * 非物资类
     * 公式1
     */
    private List<priceFormulaModel> notmaterials_priceFormula_1(List<priceFormulaModel> list) {
        double A1, A2, A3, A4, minPrice, basePrice, sumAllPrice, floatCoefficient = 0;
        int N, P, M, mStart, mEnd, cutLow = 0, cutHight = 0;
        //条件可由查询取得
        formulaConditionsModel param = new formulaConditionsModel();
        param.setExecutefun("formulaService@notmaterials_priceFormula_1");
        param.setConditionname("N");
        List<formulaConditionsModel> conditionsModels = dao.queryFormulaConditions(param);
        M = list.size();
        for (int i = 0; i < conditionsModels.size(); i++) {
            formulaConditionsModel item = conditionsModels.get(i);
            int start = Integer.parseInt(item.getStartvalue());
            int end = Integer.parseInt(item.getEndvalue());
            if (start <= M && M <= end) {
                cutLow = Integer.parseInt(item.getSubminpricecount());
                cutHight = Integer.parseInt(item.getSubmaxpricecount());
                break;
            }
        }
        ArrayList<priceFormulaModel> sortedBaseList = this.sortASCByPrice(list);
        ArrayList<priceFormulaModel> ListN = (ArrayList<priceFormulaModel>) slice(sortedBaseList, cutLow, M - cutHight);
        N = ListN.size();
        A1 = sumPrice(ListN, "price");
        A1 = A1 / N;
        basePrice = A1 * (1 + floatCoefficient);
        for (int i = 0; i < sortedBaseList.size(); i++) {
            priceFormulaModel item = sortedBaseList.get(i);
            double priceScore = 0, itemPrice = Double.valueOf(item.getPrice()), priceWeight = Double.valueOf(item.getPrice_weight());
            item.setId(getID());
            item.setA1(String.valueOf(0));
            item.setA2(String.valueOf(0));
            item.setN(String.valueOf(0));
            item.setP(String.valueOf(0));
            //item.setBase_price(String.valueOf(basePrice));
            item.setBase_price(this.decimalFormatNum(basePrice, 7));
            priceWeight = 1;
            if (itemPrice < basePrice) {
                priceScore = (priceWeight - priceWeight * 1 * (Math.abs(itemPrice - basePrice) / basePrice)) * 100;
            } else if (itemPrice > basePrice) {
                priceScore = (priceWeight - priceWeight * 2 * (Math.abs(itemPrice - basePrice) / basePrice)) * 100;
            } else if (itemPrice == basePrice) {
                priceScore = 100;
            }
            priceScore = Math.max(priceScore, 0);
            item.setPrice_score(String.format("%.2f", priceScore));
            item.setExport_type(priceFormulaModel.EXPORT_TYPE_NOTMATERIALS);
        }
        //按价格得分排序,由高到低
        this.sortByNumberField(sortedBaseList, "Price_score", false);
        for (int i = 0; i < sortedBaseList.size(); i++) {
            priceFormulaModel item = sortedBaseList.get(i);
            item.setSort(String.valueOf(i + 1));
        }
        return sortedBaseList;
    }

    /**
     * 非物资类
     * 公式2
     */
    /**
     * TODO(这里用一句话描述这个类的作用)
     *
     * @author hq4
     * @date 2017年11月01日 下午01:46:37
     */
    private List<priceFormulaModel> notmaterials_priceFormula_2(List<priceFormulaModel> list) {
        double A1, A2, A3, A4, minPrice, basePrice, sumAllPrice, floatCoefficient = 0;
        int N, P, M, mStart, mEnd, cutLow = 0, cutHight = 0;
        //条件可由查询取得
        formulaConditionsModel param = new formulaConditionsModel();
        param.setExecutefun("formulaService@notmaterials_priceFormula_2");
        param.setConditionname("N");
        List<formulaConditionsModel> conditionsModels = dao.queryFormulaConditions(param);
        M = list.size();
        for (int i = 0; i < conditionsModels.size(); i++) {
            formulaConditionsModel item = conditionsModels.get(i);
            int start = Integer.parseInt(item.getStartvalue());
            int end = Integer.parseInt(item.getEndvalue());
            if (start <= M && M <= end) {
                cutLow = Integer.parseInt(item.getSubminpricecount());
                cutHight = Integer.parseInt(item.getSubmaxpricecount());
                break;
            }
        }
        ArrayList<priceFormulaModel> sortedBaseList = this.sortASCByPrice(list);
        ArrayList<priceFormulaModel> ListN = (ArrayList<priceFormulaModel>) slice(sortedBaseList, cutLow, M - cutHight);
        N = ListN.size();
        A1 = sumPrice(ListN, "price");
        A1 = A1 / N;
        basePrice = A1 * (1 + floatCoefficient);
        for (int i = 0; i < sortedBaseList.size(); i++) {
            priceFormulaModel item = sortedBaseList.get(i);
            double priceScore = 0, itemPrice = Double.valueOf(item.getPrice()), priceWeight = Double.valueOf(item.getPrice_weight());
            item.setId(getID());
            item.setA1(String.valueOf(0));
            item.setA2(String.valueOf(0));
            item.setN(String.valueOf(0));
            item.setP(String.valueOf(0));
            //item.setBase_price(String.valueOf(basePrice));
            item.setBase_price(this.decimalFormatNum(basePrice, 7));
            priceWeight = 1;
            priceScore = (basePrice / itemPrice) * 100;
            priceScore = Math.max(priceScore, 0);
            item.setPrice_score(String.format("%.2f", priceScore));
            item.setExport_type(priceFormulaModel.EXPORT_TYPE_NOTMATERIALS);
        }
        //按价格得分排序,由高到低
        this.sortByNumberField(sortedBaseList, "Price_score", false);
        for (int i = 0; i < sortedBaseList.size(); i++) {
            priceFormulaModel item = sortedBaseList.get(i);
            item.setSort(String.valueOf(i + 1));
        }
        return sortedBaseList;
    }

    /**
     * 非物资类
     * 竞谈公式
     */
    private List<priceFormulaModel> notmaterials_priceFormula_3(List<priceFormulaModel> list) {
        double A1, A2, A3, A4, minPrice, basePrice, sumAllPrice, floatCoefficient = 0;
        int N, P, M, mStart, mEnd, cutLow = 0, cutHight = 0;
        //条件可由查询取得
//        formulaConditionsModel param = new formulaConditionsModel();
//        param.setExecutefun("formulaService@notmaterials_priceFormula_3");
//        param.setConditionname("N");
//        List<formulaConditionsModel> conditionsModels = dao.queryFormulaConditions(param);
//        M = list.size();
//        for (int i = 0; i < conditionsModels.size(); i++) {
//            formulaConditionsModel item = conditionsModels.get(i);
//            int start = Integer.parseInt(item.getStartvalue());
//            int end = Integer.parseInt(item.getEndvalue());
//            if (start <= M && M <= end) {
//                cutLow = Integer.parseInt(item.getSubminpricecount());
//                cutHight = Integer.parseInt(item.getSubmaxpricecount());
//                break;
//            }
//        }
        if (!list.isEmpty()) {

            priceFormulaModel first = list.get(0);
            String mod=first.getMod_offer(),rate=first.getFinal_rate();
            if("".equals(mod)==false){
                return notmaterials_priceFormula_3_1(list);
            }else if("".equals(rate)==false){
                return notmaterials_priceFormula_3_2(list);
            }
        }
        return list;
    }

    /**
     * 非物资类
     * 竞谈公式
     * 规则1:按金额报价计算
     */
    private List<priceFormulaModel> notmaterials_priceFormula_3_1(List<priceFormulaModel> list) {
        double A1, A2, A3, A4, minPrice, basePrice, sumAllPrice, floatCoefficient = 0;
        int N, P, M, mStart, mEnd, cutLow = 0, cutHight = 0;

        ArrayList<priceFormulaModel> sortedBaseList = this.sortByNumberField(list,"mod_offer",true);
        ArrayList<priceFormulaModel> ListN = sortedBaseList;
        basePrice = Double.valueOf(ListN.get(0).getMod_offer());
        for (int i = 0; i < sortedBaseList.size(); i++) {
            priceFormulaModel item = sortedBaseList.get(i);
            double priceScore = 0, itemPrice = Double.valueOf(item.getMod_offer()), priceWeight = Double.valueOf(item.getPrice_weight());
            item.setId(getID());
            item.setA1(String.valueOf(0));
            item.setA2(String.valueOf(0));
            item.setN(String.valueOf(0));
            item.setP(String.valueOf(0));
            //item.setBase_price(String.valueOf(basePrice));
            item.setBase_price(this.decimalFormatNum(basePrice, 7));
            priceWeight = 1;
            priceScore = (basePrice / itemPrice) * 100;
            priceScore = Math.max(priceScore, 0);
            item.setPrice_score(String.format("%.2f", priceScore));
            item.setExport_type(priceFormulaModel.EXPORT_TYPE_NOTMATERIALS_FWJT);
        }
        //按价格得分排序,由高到低
        this.sortByNumberField(sortedBaseList, "Price_score", false);
        for (int i = 0; i < sortedBaseList.size(); i++) {
            priceFormulaModel item = sortedBaseList.get(i);
            item.setSort(String.valueOf(i + 1));
        }
        return sortedBaseList;
    }
    /**
     * 非物资类
     * 竞谈公式
     * 规则2:按折扣比例计算
     */
    private List<priceFormulaModel> notmaterials_priceFormula_3_2(List<priceFormulaModel> list) {
        double A1, A2, A3, A4, minPrice, basePrice, sumAllPrice, floatCoefficient = 0;
        int N, P, M, mStart, mEnd, cutLow = 0, cutHight = 0;

        ArrayList<priceFormulaModel> sortedBaseList = this.sortByNumberField(list,"final_rate",true);
        ArrayList<priceFormulaModel> ListN = sortedBaseList;
        basePrice = Double.valueOf(ListN.get(0).getFinal_rate());
        for (int i = 0; i < sortedBaseList.size(); i++) {
            priceFormulaModel item = sortedBaseList.get(i);
            double priceScore = 0, itemPrice = Double.valueOf(item.getFinal_rate()), priceWeight = Double.valueOf(item.getPrice_weight());
            item.setId(getID());
            item.setA1(String.valueOf(0));
            item.setA2(String.valueOf(0));
            item.setN(String.valueOf(0));
            item.setP(String.valueOf(0));
            //item.setBase_price(String.valueOf(basePrice));
            item.setBase_price(this.decimalFormatNum(basePrice, 7));
            priceWeight = 1;
            priceScore = (basePrice / itemPrice) * 100;
            priceScore = Math.max(priceScore, 0);
            item.setPrice_score(String.format("%.2f", priceScore));
            item.setExport_type(priceFormulaModel.EXPORT_TYPE_NOTMATERIALS_FWJT);
        }
        //按价格得分排序,由高到低
        this.sortByNumberField(sortedBaseList, "Price_score", false);
        for (int i = 0; i < sortedBaseList.size(); i++) {
            priceFormulaModel item = sortedBaseList.get(i);
            item.setSort(String.valueOf(i + 1));
        }
        return sortedBaseList;
    }

    public int updateSupplierInfoList(List<supplierModel> list) {
        return dao.updateSupplierInfoList(list);
    }

    public int updateSupplierInfo(supplierModel supplierModel) {
        return dao.updateSupplierInfo(supplierModel);
    }

    public List<supplierModel> querySupplierInfoToCalculateIfWin(Map param) {
        if ("price".equals(param.get("type"))) {
            return dao.querySupplierInfoToCalculateIfWinByPrice(param);
        }
        return dao.querySupplierInfoToCalculateIfWin(param);
    }

    public List<supplierModel> querySupplierInfo(Map param) {
        return dao.querySupplierInfo(param);
    }

    private ArrayList sortByStringField(List<priceFormulaModel> list, String fieldName, final boolean isASC) {
        ArrayList result = null;
        PropertyDescriptor pd;
        final Method getMethod;
        try {
            pd = new PropertyDescriptor(fieldName,
                    priceFormulaModel.class);
            getMethod = pd.getReadMethod();
            if (getMethod != null) {
                list.sort(new Comparator<priceFormulaModel>() {
                    public int compare(priceFormulaModel o1, priceFormulaModel o2) {
                        Object o1Value = 0.0, o2Value = 0.0;
                        int cmpASC = 0, cmpDESC = 0;
                        try {
                            o1Value = getMethod.invoke(o1, new Object[0]);
                            o2Value = getMethod.invoke(o2, new Object[0]);
                        } catch (IllegalAccessException e) {
                            e.printStackTrace();
                        } catch (InvocationTargetException e) {
                            e.printStackTrace();
                        }
                        cmpASC = ((String) o1Value).compareTo((String) o2Value);
                        cmpDESC = ((String) o2Value).compareTo((String) o1Value);
                        return isASC ? cmpASC : cmpDESC;
                    }
                });
            }
        } catch (IntrospectionException e) {
            e.printStackTrace();
        }
        result = (ArrayList) list;
        return result;
    }

    private ArrayList supplier_sortByStringField(List<supplierModel> list, String fieldName, final boolean isASC) {
        ArrayList result = null;
        PropertyDescriptor pd;
        final Method getMethod;
        try {
            pd = new PropertyDescriptor(fieldName,
                    supplierModel.class);
            getMethod = pd.getReadMethod();
            if (getMethod != null) {
                list.sort(new Comparator<supplierModel>() {
                    public int compare(supplierModel o1, supplierModel o2) {
                        Object o1Value = 0.0, o2Value = 0.0;
                        int cmpASC = 0, cmpDESC = 0;
                        try {
                            o1Value = getMethod.invoke(o1, new Object[0]);
                            o2Value = getMethod.invoke(o2, new Object[0]);
                        } catch (IllegalAccessException e) {
                            e.printStackTrace();
                        } catch (InvocationTargetException e) {
                            e.printStackTrace();
                        }
                        cmpASC = ((String) o1Value).compareTo((String) o2Value);
                        cmpDESC = ((String) o2Value).compareTo((String) o1Value);
                        return isASC ? cmpASC : cmpDESC;
                    }
                });
            }
        } catch (IntrospectionException e) {
            e.printStackTrace();
        }
        result = (ArrayList) list;
        return result;
    }

    private ArrayList sortByNumberField(List<priceFormulaModel> list, String fieldName, final boolean isASC) {
        ArrayList result = null;
        PropertyDescriptor pd;
        final Method getMethod;
        try {
            pd = new PropertyDescriptor(fieldName,
                    priceFormulaModel.class);
            getMethod = pd.getReadMethod();
            if (getMethod != null) {
                list.sort(new Comparator<priceFormulaModel>() {
                    public int compare(priceFormulaModel o1, priceFormulaModel o2) {
                        Object o1Value = 0.0, o2Value = 0.0;
                        int cmpASC = 0, cmpDESC = 0;
                        try {
                            o1Value = getMethod.invoke(o1, new Object[0]);
                            o2Value = getMethod.invoke(o2, new Object[0]);
                        } catch (IllegalAccessException e) {
                            e.printStackTrace();
                        } catch (InvocationTargetException e) {
                            e.printStackTrace();
                        }
                        cmpASC = Double.compare(Double.valueOf((String) o1Value), Double.valueOf((String) o2Value));
                        cmpDESC = Double.compare(Double.valueOf((String) o2Value), Double.valueOf((String) o1Value));
                        return isASC ? cmpASC : cmpDESC;
                    }
                });
            }
        } catch (IntrospectionException e) {
            e.printStackTrace();
        }
        result = (ArrayList) list;
        return result;
    }

    private ArrayList sortASCByPrice(List<priceFormulaModel> list) {
        ArrayList result = null;
        list.sort(new Comparator<priceFormulaModel>() {
            public int compare(priceFormulaModel o1, priceFormulaModel o2) {
                return Double.compare(Double.valueOf(o1.getPrice()), Double.valueOf(o2.getPrice()));
            }
        });
        result = (ArrayList) list;
        return result;
    }

    private ArrayList sortASCByPriceForRowPice(List<supplierRowPriceModel> list) {
        ArrayList result = null;
        list.sort(new Comparator<supplierRowPriceModel>() {
            public int compare(supplierRowPriceModel o1, supplierRowPriceModel o2) {
                return Double.compare(Double.valueOf(o1.getRow_price()), Double.valueOf(o2.getRow_price()));
            }
        });
        result = (ArrayList) list;
        return result;
    }

    private ArrayList sortDESCByPrice(List<priceFormulaModel> list) {
        ArrayList result = null;
        list.sort(new Comparator<priceFormulaModel>() {
            public int compare(priceFormulaModel o1, priceFormulaModel o2) {
                return Double.compare(Double.valueOf(o2.getPrice()), Double.valueOf(o1.getPrice()));
            }
        });
        return result;
    }

    private double sumPrice(List<priceFormulaModel> list, String fieldName) {
        double result = 0;
        PropertyDescriptor pd;
        Method getMethod, setMethod;
        try {
            for (int i = 0; i < list.size(); i++) {
                priceFormulaModel item = list.get(i);
                pd = new PropertyDescriptor(fieldName,
                        item.getClass());
                getMethod = pd.getReadMethod();
                setMethod = pd.getWriteMethod();
                Double newValue = 0.0;
                if (getMethod != null) {
                    newValue = Double.valueOf(getMethod.invoke(item, new Object[0]).toString());
                }
                result += newValue;
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (IntrospectionException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return result;
    }

    private double sumPriceForRowPrice(List<supplierRowPriceModel> list, String fieldName) {
        double result = 0;
        PropertyDescriptor pd;
        Method getMethod, setMethod;
        try {
            for (int i = 0; i < list.size(); i++) {
                supplierRowPriceModel item = list.get(i);
                pd = new PropertyDescriptor(fieldName,
                        item.getClass());
                getMethod = pd.getReadMethod();
                setMethod = pd.getWriteMethod();
                Double newValue = 0.0;
                if (getMethod != null) {
                    newValue = Double.valueOf(getMethod.invoke(item, new Object[0]).toString());
                }
                result += newValue;
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (IntrospectionException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return result;
    }

    private boolean isInRange(double source, double compare, double start, double end) {
        return source >= compare * start && source <= compare * end;
    }

    public List<formulaExecuteFunModel> queryMaintenance(formulaExecuteFunModel param) {
        List<formulaExecuteFunModel> list = new ArrayList<formulaExecuteFunModel>();
        list = dao.queryFormulaExecuteFun(param);
        return list;
    }

    public boolean exportToExcel(List exportList, String titleStr, String fileName, String parentPath, String type) {
        //物资类
        if (priceFormulaModel.EXPORT_TYPE_MATERIALS.equals(type)) {
            exportPriceInfoToTemplate_1(exportList, titleStr, fileName, parentPath);
        }
        //服务类
        if (priceFormulaModel.EXPORT_TYPE_NOTMATERIALS.equals(type)) {
            exportPriceInfoToTemplate_2(exportList, titleStr, fileName, parentPath);
        }
        //服务类-服务竞谈
        if (priceFormulaModel.EXPORT_TYPE_NOTMATERIALS_FWJT.equals(type)) {
            exportPriceInfoToTemplate_3(exportList, titleStr, fileName, parentPath);
        }
        return true;
    }

    public boolean exportPriceInfoToTemplate_1(List exportList, String titleStr, String fileName, String parentPath) {
        fileName += ".xlsx";
        String webPath = ContextLoader.getCurrentWebApplicationContext().getServletContext().getRealPath("/");
        String tempPath = webPath + "export/priceScore/招标价格得分表模板.xlsx";
        OutputStream outputStream;

        try {
            // 声明excel摸板
            ExcelOutTemplate excelOutTemplate = new ExcelOutTemplate();
            // 读取模板工程
            XSSFWorkbook workbook = new XSSFWorkbook(new FileInputStream(tempPath));

            // 读取表单
            Sheet sheet = workbook.getSheetAt(0);

            // 向表单写标题
            excelOutTemplate.setData(sheet, titleStr, 0, 2);
            if (!exportList.isEmpty()) {
                for (int i = 0; i < exportList.size(); i++) {
                    priceFormulaModel model = (priceFormulaModel) exportList.get(i);
                    excelOutTemplate.setData(sheet, model.getSupplier(), 1, i + 2);
                    excelOutTemplate.setData(sheet, model.getPrice(), 2, i + 2);
                    excelOutTemplate.setData(sheet, model.getA1(), 13, i + 2);
                    excelOutTemplate.setData(sheet, model.getN(), 14, i + 2);
                    excelOutTemplate.setData(sheet, model.getA2(), 15, i + 2);
                    excelOutTemplate.setData(sheet, model.getP(), 16, i + 2);
                    excelOutTemplate.setData(sheet, model.getBase_price(), 17, i + 2);
                    excelOutTemplate.setData(sheet, model.getPrice_score(), 18, i + 2);
                    //动态获取权重
                    excelOutTemplate.setData(sheet, Double.valueOf(model.getPrice_weight()) * Double.valueOf(model.getPrice_score()), 19, i + 2);
                    excelOutTemplate.setData(sheet, model.getSort(), 20, i + 2);
                }
            }
            File dirFile = new File(parentPath);
            if (!dirFile.exists()) {
                dirFile.mkdirs();
            }
            File excelFile = new File(dirFile, fileName);
            outputStream = new FileOutputStream(excelFile);
            workbook.write(outputStream);
            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public boolean exportPriceInfoToTemplate_2(List exportList, String titleStr, String fileName, String parentPath) {
        fileName += ".xlsx";
        String webPath = ContextLoader.getCurrentWebApplicationContext().getServletContext().getRealPath("/");
        String tempPath = webPath + "export/priceScore/招标价格得分模板_服务类.xlsx";
        OutputStream outputStream;

        try {
            // 声明excel摸板
            ExcelOutTemplate excelOutTemplate = new ExcelOutTemplate();
            // 读取模板工程
            XSSFWorkbook workbook = new XSSFWorkbook(new FileInputStream(tempPath));

            // 读取表单
            Sheet sheet = workbook.getSheetAt(0);
            Row footRow = sheet.getRow(19);
            Row templast = sheet.createRow(1000);
            excelOutTemplate.copyRow(footRow, templast);

            Cell demoCell = sheet.getRow(4).getCell(1);
            CellStyle bodyCellStyle = excelOutTemplate.copyCellStyle(demoCell.getCellStyle(), workbook);
            // 向表单写标题
            excelOutTemplate.setData(sheet, titleStr, 0, 2);
            if (!exportList.isEmpty()) {
                for (int i = 0; i < exportList.size(); i++) {
                    priceFormulaModel model = (priceFormulaModel) exportList.get(i);
                    excelOutTemplate.setData(sheet, i + 1, i + 4, 0);
                    excelOutTemplate.setData(sheet, model.getSupplier(), i + 4, 1, bodyCellStyle);
                    excelOutTemplate.setData(sheet, model.getPrice(), i + 4, 2, bodyCellStyle);
                    excelOutTemplate.setData(sheet, model.getPrice_score(), i + 4, 5, bodyCellStyle);
                    excelOutTemplate.getRow(sheet, i + 4).setHeight(demoCell.getRow().getHeight());
                }
            }
            if (exportList.size() > 20) {
                Row newlast = sheet.createRow(exportList.size() + 4);
                excelOutTemplate.copyRow(templast, newlast);
                sheet.removeRow(templast);
                //合并单元格
//                sheet.addMergedRegion(new CellRangeAddress(4, exportList.size() + 3, 4, 4));
            }
            File dirFile = new File(parentPath);
            if (!dirFile.exists()) {
                dirFile.mkdirs();
            }
            File excelFile = new File(dirFile, fileName);
            outputStream = new FileOutputStream(excelFile);
            workbook.write(outputStream);
            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public boolean exportPriceInfoToTemplate_3(List exportList, String titleStr, String fileName, String parentPath) {
        fileName += ".xlsx";
        String webPath = ContextLoader.getCurrentWebApplicationContext().getServletContext().getRealPath("/");
        String tempPath = webPath + "export/priceScore/招标价格得分模板_服务竞谈.xlsx";
        OutputStream outputStream;

        try {
            // 声明excel摸板
            ExcelOutTemplate excelOutTemplate = new ExcelOutTemplate();
            // 读取模板工程
            XSSFWorkbook workbook = new XSSFWorkbook(new FileInputStream(tempPath));

            // 读取表单
            Sheet sheet = workbook.getSheetAt(0);
            Row footRow = sheet.getRow(19);
            Row templast = sheet.createRow(1000);
            excelOutTemplate.copyRow(footRow, templast);

            Cell demoCell = sheet.getRow(4).getCell(1);
            CellStyle bodyCellStyle = excelOutTemplate.copyCellStyle(demoCell.getCellStyle(), workbook);
            // 向表单写标题
            excelOutTemplate.setData(sheet, titleStr, 0, 2);
            if (!exportList.isEmpty()) {
                for (int i = 0; i < exportList.size(); i++) {
                    priceFormulaModel model = (priceFormulaModel) exportList.get(i);
                    excelOutTemplate.setData(sheet, i + 1, i + 4, 0);
                    excelOutTemplate.setData(sheet, model.getSupplier(), i + 4, 1, bodyCellStyle);
                    excelOutTemplate.setData(sheet, model.getMod_offer(), i + 4, 2, bodyCellStyle);
                    excelOutTemplate.setData(sheet, model.getFinal_rate(), i + 4, 3, bodyCellStyle);
                    excelOutTemplate.setData(sheet, model.getPrice_score(), i + 4, 6, bodyCellStyle);
                    excelOutTemplate.getRow(sheet, i + 4).setHeight(demoCell.getRow().getHeight());
                }
            }
            if (exportList.size() > 20) {
                Row newlast = sheet.createRow(exportList.size() + 4);
                excelOutTemplate.copyRow(templast, newlast);
                sheet.removeRow(templast);
                //合并单元格
//                sheet.addMergedRegion(new CellRangeAddress(4, exportList.size() + 3, 4, 4));
            }
            File dirFile = new File(parentPath);
            if (!dirFile.exists()) {
                dirFile.mkdirs();
            }
            File excelFile = new File(dirFile, fileName);
            outputStream = new FileOutputStream(excelFile);
            workbook.write(outputStream);
            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public boolean exportWinToExcel(List exportList, String titleStr, String fileName, String parentPath) {
        fileName += ".xlsx";
        String webPath = ContextLoader.getCurrentWebApplicationContext().getServletContext().getRealPath("/");
        String tempPath = webPath + "export/priceScore/中标结果导出模板.xlsx";
        OutputStream outputStream;

        try {
            // 声明excel摸板
            ExcelOutTemplate excelOutTemplate = new ExcelOutTemplate();
            // 读取模板工程
            XSSFWorkbook workbook = new XSSFWorkbook(new FileInputStream(tempPath));
            // 读取表单
            Sheet sheet = workbook.getSheetAt(0);
            // 向表单写标题
            excelOutTemplate.setData(sheet, titleStr, 0, 2); // 是否中标行0列2：[是否中标]填入[中标结果]
            if (!exportList.isEmpty()) {
                for (int i = 0; i < exportList.size(); i++) {
                    supplierModel model = (supplierModel) exportList.get(i);
                    excelOutTemplate.setData(sheet, model.getBid_abbreviaion(), i + 1, 0);
                    excelOutTemplate.setData(sheet, model.getSupplier(), i + 1, 1);
                    if ("1".equals(model.getIs_win())) {
                        excelOutTemplate.setData(sheet, "是", i + 1, 2);
                    } else {
                        excelOutTemplate.setData(sheet, "否", i + 1, 2);
                    }
                    excelOutTemplate.setData(sheet, model.getOffer(), i + 1, 3);
                    excelOutTemplate.setData(sheet, model.getTechnology_score(), i + 1, 4);
                    excelOutTemplate.setData(sheet, model.getPrice_score(), i + 1, 5);
                    excelOutTemplate.setData(sheet, model.getBusiness_score(), i + 1, 6);
                    excelOutTemplate.setData(sheet, model.getTotal_score(), i + 1, 7);
                }
            }
            File dirFile = new File(parentPath);
            if (!dirFile.exists()) {
                dirFile.mkdirs();
            }
            File excelFile = new File(dirFile, fileName);
            outputStream = new FileOutputStream(excelFile);
            workbook.write(outputStream);
            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public List<formulaExecuteFunModel> queryFormulaExecuteFun(formulaExecuteFunModel param) {
        return dao.queryFormulaExecuteFun(param);
    }

    public List<formulaConditionsModel> queryFormulaConditions(formulaConditionsModel param) {
        return dao.queryFormulaConditions(param);
    }

    public void deleteFormulaConditions(formulaConditionsModel param) {
        dao.deleteFormulaConditions(param);
    }

    public int addFormulaConditions(formulaConditionsModel param) {
        return dao.addFormulaConditions(param);
    }

    public int updateFormulaConditions(formulaConditionsModel param) {
        return dao.updateFormulaConditions(param);
    }

    public static String getID() {
        return UUID.randomUUID().toString().replace("-", "");
    }

    /**
     * 查询进行到了哪一个节点及提示信息
     * @return
     */
    public tblBbgsFlowStateModel queryMinIdByState(){ return dao.queryMinIdByState(); }

    /**
     * 通过id 更新流程状态
     * id 为 null 时 更新所有记录
     * @param id
     * @param state
     */
    public void updateStateById(@Param("id") String id, @Param("state") String state){ dao.updateStateById(id,state);}
}
