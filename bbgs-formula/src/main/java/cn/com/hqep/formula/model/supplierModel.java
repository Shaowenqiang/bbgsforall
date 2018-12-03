package cn.com.hqep.formula.model;

/**
 * 唱标价格实体类
 * Created by spring on 2017/10/10.
 */
public class supplierModel {

    private String id;
    private String packagename;
    private String supplier;
    private String flag_bit;
    private String offer;
    private String technology_score;
    private String price_score;
    private String business_score;
    private String total_score;
    private String sort;
    private String is_win;
    private String bid_setion;
    private String bid_abbreviaion;
    private String bid;
    private String flag_invalid;
    private String flag_bid_invalid;

    public String getFlag_bid_invalid() { return flag_bid_invalid; }

    public void setFlag_bid_invalid(String flag_bid_invalid) {
        this.flag_bid_invalid = flag_bid_invalid;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPackagename() {
        return packagename;
    }

    public void setPackagename(String packagename) {
        this.packagename = packagename;
    }

    public String getSupplier() {
        return supplier;
    }

    public void setSupplier(String supplier) {
        this.supplier = supplier;
    }

    public String getFlag_bit() {
        return flag_bit;
    }

    public void setFlag_bit(String flag_bit) {
        this.flag_bit = flag_bit;
    }

    public String getOffer() {
        return offer;
    }

    public void setOffer(String offer) {
        this.offer = offer;
    }

    public String getTechnology_score() {
        return technology_score;
    }

    public void setTechnology_score(String technology_score) {
        this.technology_score = technology_score;
    }

    public String getPrice_score() {
        return price_score;
    }

    public void setPrice_score(String price_score) {
        this.price_score = price_score;
    }

    public String getBusiness_score() {
        return business_score;
    }

    public void setBusiness_score(String business_score) {
        this.business_score = business_score;
    }

    public String getTotal_score() {
        return total_score;
    }

    public void setTotal_score(String total_score) {
        this.total_score = total_score;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public String getIs_win() {
        return is_win;
    }

    public void setIs_win(String is_win) {
        this.is_win = is_win;
    }

    public String getBid_setion() {
        return bid_setion;
    }

    public void setBid_setion(String bid_setion) {
        this.bid_setion = bid_setion;
    }

    public String getBid_abbreviaion() {
        return bid_abbreviaion;
    }

    public void setBid_abbreviaion(String bid_abbreviaion) {
        this.bid_abbreviaion = bid_abbreviaion;
    }

    public String getBid() {
        return bid;
    }

    public void setBid(String bid) {
        this.bid = bid;
    }

    public String getFlag_invalid() {
        return flag_invalid;
    }

    public void setFlag_invalid(String flag_invalid) {
        this.flag_invalid = flag_invalid;
    }
}
