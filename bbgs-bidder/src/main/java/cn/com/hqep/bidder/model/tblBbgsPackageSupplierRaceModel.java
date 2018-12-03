package cn.com.hqep.bidder.model;

/**
 * Created by HQSpring on 2018-04-08.
 */
public class tblBbgsPackageSupplierRaceModel {
    private String id;
    private String packagename;//包名
    private String supplier;//供应商名称
    private String flagBit;//标志位(废标 无报价一次，初评一次区分开)
    private String flagInvalid;//标志位(流标)
    private String modOffer;//最终金额报价
    private String finalRate;//最终折扣比例
    private String technologyScore;//技术得分
    private String priceScore;//报价得分
    private String businessScore;//商务得分
    private String totalScore;//总分
    private String sort;//排名
    private String isWin;//是否中标
    private String bidSetion;//标段全称
    private String bidAbbreviaion;//标段简称
    private String bid;//标名
    private String flagBidInvalid;//流标 标段的志位            //《==新增字段

    private String  basePrice; //评标基准价      //《==接收数据使用 数据库中没有该字段

    @Override
    public String toString() {
        return "tblBbgsPackageSupplierRaceModel{" +
                "id='" + id + '\'' +
                ", packagename='" + packagename + '\'' +
                ", supplier='" + supplier + '\'' +
                ", flagBit='" + flagBit + '\'' +
                ", flagInvalid='" + flagInvalid + '\'' +
                ", modOffer='" + modOffer + '\'' +
                ", finalRate='" + finalRate + '\'' +
                ", technologyScore='" + technologyScore + '\'' +
                ", priceScore='" + priceScore + '\'' +
                ", businessScore='" + businessScore + '\'' +
                ", totalScore='" + totalScore + '\'' +
                ", sort='" + sort + '\'' +
                ", isWin='" + isWin + '\'' +
                ", bidSetion='" + bidSetion + '\'' +
                ", bidAbbreviaion='" + bidAbbreviaion + '\'' +
                ", bid='" + bid + '\'' +
                ", flagBidInvalid='" + flagBidInvalid + '\'' +
                ", basePrice='" + basePrice + '\'' +
                '}';
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

    public String getFlagBit() {
        return flagBit;
    }

    public void setFlagBit(String flagBit) {
        this.flagBit = flagBit;
    }

    public String getFlagInvalid() {
        return flagInvalid;
    }

    public void setFlagInvalid(String flagInvalid) {
        this.flagInvalid = flagInvalid;
    }

    public String getModOffer() {
        return modOffer;
    }

    public void setModOffer(String modOffer) {
        this.modOffer = modOffer;
    }

    public String getFinalRate() {
        return finalRate;
    }

    public void setFinalRate(String finalRate) {
        this.finalRate = finalRate;
    }

    public String getTechnologyScore() {
        return technologyScore;
    }

    public void setTechnologyScore(String technologyScore) {
        this.technologyScore = technologyScore;
    }

    public String getPriceScore() {
        return priceScore;
    }

    public void setPriceScore(String priceScore) {
        this.priceScore = priceScore;
    }

    public String getBusinessScore() {
        return businessScore;
    }

    public void setBusinessScore(String businessScore) {
        this.businessScore = businessScore;
    }

    public String getTotalScore() {
        return totalScore;
    }

    public void setTotalScore(String totalScore) {
        this.totalScore = totalScore;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public String getIsWin() {
        return isWin;
    }

    public void setIsWin(String isWin) {
        this.isWin = isWin;
    }

    public String getBidSetion() {
        return bidSetion;
    }

    public void setBidSetion(String bidSetion) {
        this.bidSetion = bidSetion;
    }

    public String getBidAbbreviaion() {
        return bidAbbreviaion;
    }

    public void setBidAbbreviaion(String bidAbbreviaion) {
        this.bidAbbreviaion = bidAbbreviaion;
    }

    public String getBid() {
        return bid;
    }

    public void setBid(String bid) {
        this.bid = bid;
    }

    public String getFlagBidInvalid() {
        return flagBidInvalid;
    }

    public void setFlagBidInvalid(String flagBidInvalid) {
        this.flagBidInvalid = flagBidInvalid;
    }

    public String getBasePrice() {
        return basePrice;
    }

    public void setBasePrice(String basePrice) {
        this.basePrice = basePrice;
    }
}
