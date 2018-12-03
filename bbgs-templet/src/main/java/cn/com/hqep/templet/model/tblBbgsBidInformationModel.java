package cn.com.hqep.templet.model;

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
    String technologyTemplateChn;//技术打分模板  中文
    String technologyTemplatePath;//技术打分模板文件路径
    String businessTemplateChn;//商务打分模版  中文
    String businessTemplatePath;//商务打分模板文件路径
    String technologyBidRecordChn;//技术阅标记录模版   中文
    String businessBidRecordChn;//商务阅标记录模版   中文
    String bidderListChn;//投标人清单模板  汉字
    String priceScoreTemplateChn;//价格得分模板  中文
    String describeName;
    String technologyExpertGroupName;
    String businessExpertGroupName;
    String technologyBidRecordPath;//技术阅标模板路径
    String businessBidRecordPath;//商务阅标模板路径
    String bidderListPath;//投标人清单模板

    public tblBbgsBidInformationModel(String id, String bidAbbreviaion, String technologyTemplate, String businessTemplate, String technologyBidRecord, String businessBidRecord, String bidderList, String priceScoreTemplate, String businessExpertGroup, String technologyExpertGroup, String businessWeight, String technologyWeight, String priceWeight, String invalid, String flag, String priceFormula, String m, String technologyTemplateChn, String technologyTemplatePath, String businessTemplateChn, String businessTemplatePath, String technologyBidRecordChn, String businessBidRecordChn, String bidderListChn, String priceScoreTemplateChn, String describeName, String technologyExpertGroupName, String businessExpertGroupName, String technologyBidRecordPath, String businessBidRecordPath, String bidderListPath) {
        this.id = id;
        this.bidAbbreviaion = bidAbbreviaion;
        this.technologyTemplate = technologyTemplate;
        this.businessTemplate = businessTemplate;
        this.technologyBidRecord = technologyBidRecord;
        this.businessBidRecord = businessBidRecord;
        this.bidderList = bidderList;
        this.priceScoreTemplate = priceScoreTemplate;
        this.businessExpertGroup = businessExpertGroup;
        this.technologyExpertGroup = technologyExpertGroup;
        this.businessWeight = businessWeight;
        this.technologyWeight = technologyWeight;
        this.priceWeight = priceWeight;
        this.invalid = invalid;
        this.flag = flag;
        this.priceFormula = priceFormula;
        this.m = m;
        this.technologyTemplateChn = technologyTemplateChn;
        this.technologyTemplatePath = technologyTemplatePath;
        this.businessTemplateChn = businessTemplateChn;
        this.businessTemplatePath = businessTemplatePath;
        this.technologyBidRecordChn = technologyBidRecordChn;
        this.businessBidRecordChn = businessBidRecordChn;
        this.bidderListChn = bidderListChn;
        this.priceScoreTemplateChn = priceScoreTemplateChn;
        this.describeName = describeName;
        this.technologyExpertGroupName = technologyExpertGroupName;
        this.businessExpertGroupName = businessExpertGroupName;
        this.technologyBidRecordPath = technologyBidRecordPath;
        this.businessBidRecordPath = businessBidRecordPath;
        this.bidderListPath = bidderListPath;
    }

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
                ", technologyTemplateChn='" + technologyTemplateChn + '\'' +
                ", technologyTemplatePath='" + technologyTemplatePath + '\'' +
                ", businessTemplateChn='" + businessTemplateChn + '\'' +
                ", businessTemplatePath='" + businessTemplatePath + '\'' +
                ", technologyBidRecordChn='" + technologyBidRecordChn + '\'' +
                ", businessBidRecordChn='" + businessBidRecordChn + '\'' +
                ", bidderListChn='" + bidderListChn + '\'' +
                ", priceScoreTemplateChn='" + priceScoreTemplateChn + '\'' +
                ", describeName='" + describeName + '\'' +
                ", technologyExpertGroupName='" + technologyExpertGroupName + '\'' +
                ", businessExpertGroupName='" + businessExpertGroupName + '\'' +
                ", technologyBidRecordPath='" + technologyBidRecordPath + '\'' +
                ", businessBidRecordPath='" + businessBidRecordPath + '\'' +
                ", bidderListPath='" + bidderListPath + '\'' +
                '}';
    }

    public String getTechnologyBidRecordPath() {
        return technologyBidRecordPath;
    }

    public void setTechnologyBidRecordPath(String technologyBidRecordPath) {
        this.technologyBidRecordPath = technologyBidRecordPath;
    }

    public String getBusinessBidRecordPath() {
        return businessBidRecordPath;
    }

    public void setBusinessBidRecordPath(String businessBidRecordPath) {
        this.businessBidRecordPath = businessBidRecordPath;
    }

    public String getBidderListPath() {
        return bidderListPath;
    }

    public void setBidderListPath(String bidderListPath) {
        this.bidderListPath = bidderListPath;
    }

    public String getBusinessExpertGroupName() {
        return businessExpertGroupName;
    }

    public void setBusinessExpertGroupName(String businessExpertGroupName) {
        this.businessExpertGroupName = businessExpertGroupName;
    }

    public String getTechnologyExpertGroupName() {
        return technologyExpertGroupName;
    }

    public void setTechnologyExpertGroupName(String technologyExpertGroupName) {
        this.technologyExpertGroupName = technologyExpertGroupName;
    }

    public String getDescribeName() {
        return describeName;
    }

    public void setDescribeName(String describeName) {
        this.describeName = describeName;
    }

    public String getTechnologyTemplatePath() {
        return technologyTemplatePath;
    }

    public void setTechnologyTemplatePath(String technologyTemplatePath) {
        this.technologyTemplatePath = technologyTemplatePath;
    }

    public String getBusinessTemplatePath() {
        return businessTemplatePath;
    }

    public void setBusinessTemplatePath(String businessTemplatePath) {
        this.businessTemplatePath = businessTemplatePath;
    }

    public String getTechnologyTemplateChn() {
        return technologyTemplateChn;
    }

    public void setTechnologyTemplateChn(String technologyTemplateChn) {
        this.technologyTemplateChn = technologyTemplateChn;
    }

    public String getBusinessTemplateChn() {
        return businessTemplateChn;
    }

    public void setBusinessTemplateChn(String businessTemplateChn) {
        this.businessTemplateChn = businessTemplateChn;
    }

    public String getTechnologyBidRecordChn() {
        return technologyBidRecordChn;
    }

    public void setTechnologyBidRecordChn(String technologyBidRecordChn) {
        this.technologyBidRecordChn = technologyBidRecordChn;
    }

    public String getBusinessBidRecordChn() {
        return businessBidRecordChn;
    }

    public void setBusinessBidRecordChn(String businessBidRecordChn) {
        this.businessBidRecordChn = businessBidRecordChn;
    }

    public String getBidderListChn() {
        return bidderListChn;
    }

    public void setBidderListChn(String bidderListChn) {
        this.bidderListChn = bidderListChn;
    }

    public String getPriceScoreTemplateChn() {
        return priceScoreTemplateChn;
    }

    public void setPriceScoreTemplateChn(String priceScoreTemplateChn) {
        this.priceScoreTemplateChn = priceScoreTemplateChn;
    }

    public tblBbgsBidInformationModel(String id, String bidAbbreviaion, String technologyTemplate, String businessTemplate, String technologyBidRecord, String businessBidRecord, String bidderList, String priceScoreTemplate, String businessExpertGroup, String technologyExpertGroup, String businessWeight, String technologyWeight, String priceWeight, String invalid, String flag, String priceFormula, String m, String technologyTemplateChn, String businessTemplateChn, String technologyBidRecordChn, String businessBidRecordChn, String bidderListChn, String priceScoreTemplateChn) {
        this.id = id;
        this.bidAbbreviaion = bidAbbreviaion;
        this.technologyTemplate = technologyTemplate;
        this.businessTemplate = businessTemplate;
        this.technologyBidRecord = technologyBidRecord;
        this.businessBidRecord = businessBidRecord;
        this.bidderList = bidderList;
        this.priceScoreTemplate = priceScoreTemplate;
        this.businessExpertGroup = businessExpertGroup;
        this.technologyExpertGroup = technologyExpertGroup;
        this.businessWeight = businessWeight;
        this.technologyWeight = technologyWeight;
        this.priceWeight = priceWeight;
        this.invalid = invalid;
        this.flag = flag;
        this.priceFormula = priceFormula;
        this.m = m;
        this.technologyTemplateChn = technologyTemplateChn;
        this.businessTemplateChn = businessTemplateChn;
        this.technologyBidRecordChn = technologyBidRecordChn;
        this.businessBidRecordChn = businessBidRecordChn;
        this.bidderListChn = bidderListChn;
        this.priceScoreTemplateChn = priceScoreTemplateChn;
    }

    public tblBbgsBidInformationModel(String id, String bidAbbreviaion, String technologyTemplate, String businessTemplate, String technologyBidRecord, String businessBidRecord, String bidderList, String priceScoreTemplate, String businessExpertGroup, String technologyExpertGroup, String businessWeight, String technologyWeight, String priceWeight, String invalid, String flag, String priceFormula, String m) {
        this.id = id;
        this.bidAbbreviaion = bidAbbreviaion;
        this.technologyTemplate = technologyTemplate;
        this.businessTemplate = businessTemplate;
        this.technologyBidRecord = technologyBidRecord;
        this.businessBidRecord = businessBidRecord;
        this.bidderList = bidderList;
        this.priceScoreTemplate = priceScoreTemplate;
        this.businessExpertGroup = businessExpertGroup;
        this.technologyExpertGroup = technologyExpertGroup;
        this.businessWeight = businessWeight;
        this.technologyWeight = technologyWeight;
        this.priceWeight = priceWeight;
        this.invalid = invalid;
        this.flag = flag;
        this.priceFormula = priceFormula;
        this.m = m;
    }

    public tblBbgsBidInformationModel() {

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
