package cn.com.hqep.templet.model;

/**
 * 包-供应商表实体 表名：TBL_BBGS_PACKAGE_SUPPLIER
 *
 * @author shaowenqiang
 * @date 2017年10月10日 上午10:31:03
 */
public class tblBbgsPackageSupplierModel {
    private String id;
    private String packagename;//包名
    private String supplier;//供应商名称
    private String flagBit;//标志位(废标 无报价一次，初评一次区分开)
    private String offer;//报价
    private String technologyScore;//技术得分
    private String priceScore;//报价得分
    private String businessScore;//商务得分
    private String totalScore;//总分
    private String sort;//排名
    private String isWin;//是否中标
    private String bidSetion;//标段全称
    private String bidAbbreviaion;//标段简称
    private String bid;//标名
    private String technologyExpertGroup;
    private String technologyExpertGroupName;
    private String businessExpertGroup;
    private String businessExpertGroupName;
    private String flagInvalid;
    private String bidderPath;
    private String technologyPath;
    private String businessPath;
    private String flagBidInvalid;//包流标标志位(0为流标，1或者空为正常)

    @Override
    public String toString() {
        return "tblBbgsPackageSupplierModel{" +
                "id='" + id + '\'' +
                ", packagename='" + packagename + '\'' +
                ", supplier='" + supplier + '\'' +
                ", flagBit='" + flagBit + '\'' +
                ", offer='" + offer + '\'' +
                ", technologyScore='" + technologyScore + '\'' +
                ", priceScore='" + priceScore + '\'' +
                ", businessScore='" + businessScore + '\'' +
                ", totalScore='" + totalScore + '\'' +
                ", sort='" + sort + '\'' +
                ", isWin='" + isWin + '\'' +
                ", bidSetion='" + bidSetion + '\'' +
                ", bidAbbreviaion='" + bidAbbreviaion + '\'' +
                ", bid='" + bid + '\'' +
                ", technologyExpertGroup='" + technologyExpertGroup + '\'' +
                ", technologyExpertGroupName='" + technologyExpertGroupName + '\'' +
                ", businessExpertGroup='" + businessExpertGroup + '\'' +
                ", businessExpertGroupName='" + businessExpertGroupName + '\'' +
                ", flagInvalid='" + flagInvalid + '\'' +
                ", bidderPath='" + bidderPath + '\'' +
                ", technologyPath='" + technologyPath + '\'' +
                ", businessPath='" + businessPath + '\'' +
                ", flagBidInvalid='" + flagBidInvalid + '\'' +
                '}';
    }

    public String getFlagBidInvalid() {
        return flagBidInvalid;
    }

    public void setFlagBidInvalid(String flagBidInvalid) {
        this.flagBidInvalid = flagBidInvalid;
    }

    public String getBusinessPath() {
        return businessPath;
    }

    public void setBusinessPath(String businessPath) {
        this.businessPath = businessPath;
    }

    public String getTechnologyPath() {
        return technologyPath;
    }

    public void setTechnologyPath(String technologyPath) {
        this.technologyPath = technologyPath;
    }

    public String getBidderPath() {
        return bidderPath;
    }

    public void setBidderPath(String bidderPath) {
        this.bidderPath = bidderPath;
    }

    public String getFlagInvalid() {
        return flagInvalid;
    }

    public void setFlagInvalid(String flagInvalid) {
        this.flagInvalid = flagInvalid;
    }

    public String getBusinessExpertGroupName() {
        return businessExpertGroupName;
    }

    public void setBusinessExpertGroupName(String businessExpertGroupName) {
        this.businessExpertGroupName = businessExpertGroupName;
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

    public String getOffer() {
        return offer;
    }

    public void setOffer(String offer) {
        this.offer = offer;
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

    public String getTechnologyExpertGroup() {
        return technologyExpertGroup;
    }

    public void setTechnologyExpertGroup(String technologyExpertGroup) {
        this.technologyExpertGroup = technologyExpertGroup;
    }

    public String getTechnologyExpertGroupName() {
        return technologyExpertGroupName;
    }

    public void setTechnologyExpertGroupName(String technologyExpertGroupName) {
        this.technologyExpertGroupName = technologyExpertGroupName;
    }

    public String getBusinessExpertGroup() {
        return businessExpertGroup;
    }

    public void setBusinessExpertGroup(String businessExpertGroup) {
        this.businessExpertGroup = businessExpertGroup;
    }

}
