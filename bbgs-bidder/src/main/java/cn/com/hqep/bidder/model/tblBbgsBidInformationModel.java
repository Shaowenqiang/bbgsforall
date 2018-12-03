package cn.com.hqep.bidder.model;

/**
 * 标段信息实体 表名 TBL_BBGS_BID_INFORMATION
 *
 * @author shaowenqiang
 */
public class tblBbgsBidInformationModel {
    String id;
    String bidAbbreviaion;//标段简称
    String technologyTemplate;//技术打分模板
    String businessTemplate;//商务打分模版
    String technologyBidRecord;//技术阅标记录模版
    String businessBidRecord;//商务阅标记录模版
    String bidderList;//投标人清单模板
    String priceScoreTemplate;//价格得分模板
    String businessExpertGroup;//专家分组
    String technologyExpertGroup;//专家分组
    String businessWeight;//商务权重
    String technologyWeight;//技术权重
    String priceWeight;//价格权重
    String invalid;//流标
    String flag;//标志位
    String priceFormula;//价格公式
    String m;//物资类第一种公式m参数

    @Override
    public String toString() {
        return "tblBbgsBidInformationModel{" +
                "id='" + id + '\'' +
                ", bidAbbreviaion='" + bidAbbreviaion + '\'' +
                ", technologyTemplate='" + technologyTemplate + '\'' +
                ", businessTemplate='" + businessTemplate + '\'' +
                ", technologyBidRecord='" + technologyBidRecord + '\'' +
                ", businessBidRecord='" + businessBidRecord + '\'' +
                ", bidderList='" + bidderList + '\'' +
                ", priceScoreTemplate='" + priceScoreTemplate + '\'' +
                ", businessExpertGroup='" + businessExpertGroup + '\'' +
                ", technologyExpertGroup='" + technologyExpertGroup + '\'' +
                ", businessWeight='" + businessWeight + '\'' +
                ", technologyWeight='" + technologyWeight + '\'' +
                ", priceWeight='" + priceWeight + '\'' +
                ", invalid='" + invalid + '\'' +
                ", flag='" + flag + '\'' +
                ", priceFormula='" + priceFormula + '\'' +
                ", m='" + m + '\'' +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBidAbbreviaion() {
        return bidAbbreviaion;
    }

    public void setBidAbbreviaion(String bidAbbreviaion) {
        this.bidAbbreviaion = bidAbbreviaion;
    }

    public String getTechnologyTemplate() {
        return technologyTemplate;
    }

    public void setTechnologyTemplate(String technologyTemplate) {
        this.technologyTemplate = technologyTemplate;
    }

    public String getBusinessTemplate() {
        return businessTemplate;
    }

    public void setBusinessTemplate(String businessTemplate) {
        this.businessTemplate = businessTemplate;
    }

    public String getTechnologyBidRecord() {
        return technologyBidRecord;
    }

    public void setTechnologyBidRecord(String technologyBidRecord) {
        this.technologyBidRecord = technologyBidRecord;
    }

    public String getBusinessBidRecord() {
        return businessBidRecord;
    }

    public void setBusinessBidRecord(String businessBidRecord) {
        this.businessBidRecord = businessBidRecord;
    }

    public String getBidderList() {
        return bidderList;
    }

    public void setBidderList(String bidderList) {
        this.bidderList = bidderList;
    }

    public String getPriceScoreTemplate() {
        return priceScoreTemplate;
    }

    public void setPriceScoreTemplate(String priceScoreTemplate) {
        this.priceScoreTemplate = priceScoreTemplate;
    }

    public String getBusinessExpertGroup() {
        return businessExpertGroup;
    }

    public void setBusinessExpertGroup(String businessExpertGroup) {
        this.businessExpertGroup = businessExpertGroup;
    }

    public String getTechnologyExpertGroup() {
        return technologyExpertGroup;
    }

    public void setTechnologyExpertGroup(String technologyExpertGroup) {
        this.technologyExpertGroup = technologyExpertGroup;
    }

    public String getBusinessWeight() {
        return businessWeight;
    }

    public void setBusinessWeight(String businessWeight) {
        this.businessWeight = businessWeight;
    }

    public String getTechnologyWeight() {
        return technologyWeight;
    }

    public void setTechnologyWeight(String technologyWeight) {
        this.technologyWeight = technologyWeight;
    }

    public String getPriceWeight() {
        return priceWeight;
    }

    public void setPriceWeight(String priceWeight) {
        this.priceWeight = priceWeight;
    }

    public String getInvalid() {
        return invalid;
    }

    public void setInvalid(String invalid) {
        this.invalid = invalid;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public String getPriceFormula() {
        return priceFormula;
    }

    public void setPriceFormula(String priceFormula) {
        this.priceFormula = priceFormula;
    }

    public String getM() {
        return m;
    }

    public void setM(String m) {
        this.m = m;
    }
}
