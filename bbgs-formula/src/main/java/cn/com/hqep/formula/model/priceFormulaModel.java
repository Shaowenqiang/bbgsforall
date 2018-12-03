package cn.com.hqep.formula.model;

/**
 * 招标公式实体类
 * Created by spring on 2017/10/10.
 */
public class priceFormulaModel {
    public final static String EXPORT_TYPE_MATERIALS = "0";
    public final static String EXPORT_TYPE_NOTMATERIALS = "1";
    public final static String EXPORT_TYPE_NOTMATERIALS_FWJT = "2";

    String id; //主键
    String price; //投标价格
    String supplier; //供应商名称
    String a1;
    String n;
    String a2;
    String p;
    String base_price; //评标基准价
    String price_score; //评标价格得分
    String sort; //排序
    String packagename; //包名
    String bid;//标名
    String m;
    String price_formula;//标段使用的公式
    String business_weight;//商务权重
    String technology_weight;//技术权重
    String price_weight;//价格权重
    String bid_abbreviaion;//标段简称
    String bid_setion;//标段全称
    String export_type;
    String final_rate;//最终折扣比例
    String mod_offer;//最终金额报价

    public String getFinal_rate() {
        return final_rate;
    }

    public void setFinal_rate(String final_rate) {
        this.final_rate = final_rate;
    }

    public String getMod_offer() {
        return mod_offer;
    }

    public void setMod_offer(String mod_offer) {
        this.mod_offer = mod_offer;
    }

    public String getExport_type() {
        return export_type;
    }

    public void setExport_type(String export_type) {
        this.export_type = export_type;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getSupplier() {
        return supplier;
    }

    public void setSupplier(String supplier) {
        this.supplier = supplier;
    }

    public String getA1() {
        return a1;
    }

    public void setA1(String a1) {
        this.a1 = a1;
    }

    public String getN() {
        return n;
    }

    public void setN(String n) {
        this.n = n;
    }

    public String getA2() {
        return a2;
    }

    public void setA2(String a2) {
        this.a2 = a2;
    }

    public String getP() {
        return p;
    }

    public void setP(String p) {
        this.p = p;
    }

    public String getBase_price() {
        return base_price;
    }

    public void setBase_price(String base_price) {
        this.base_price = base_price;
    }

    public String getPrice_score() {
        return price_score;
    }

    public void setPrice_score(String price_score) {
        this.price_score = price_score;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public String getPackagename() {
        return packagename;
    }

    public void setPackagename(String packagename) {
        this.packagename = packagename;
    }

    public String getBid() {
        return bid;
    }

    public void setBid(String bid) {
        this.bid = bid;
    }

    public String getM() {
        return m;
    }

    public void setM(String m) {
        this.m = m;
    }

    public String getBusiness_weight() {
        return business_weight;
    }

    public void setBusiness_weight(String business_weight) {
        this.business_weight = business_weight;
    }

    public String getTechnology_weight() {
        return technology_weight;
    }

    public void setTechnology_weight(String technology_weight) {
        this.technology_weight = technology_weight;
    }

    public String getPrice_weight() {
        return price_weight;
    }

    public void setPrice_weight(String price_weight) {
        this.price_weight = price_weight;
    }

    public String getBid_abbreviaion() {
        return bid_abbreviaion;
    }

    public void setBid_abbreviaion(String bid_abbreviaion) {
        this.bid_abbreviaion = bid_abbreviaion;
    }

    public String getBid_setion() {
        return bid_setion;
    }

    public void setBid_setion(String bid_setion) {
        this.bid_setion = bid_setion;
    }

    public String getPrice_formula() {
        return price_formula;
    }

    public void setPrice_formula(String price_formula) {
        this.price_formula = price_formula;
    }


}
