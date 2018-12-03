package cn.com.hqep.templet.model;

/**
 * 模板实体类 数据库表名 TBL_BBGS_TEMPLATE
 *
 * @author hq4
 * @date 2017年10月13日 上午08:35:18
 */

public class tblBbgsTemplateModel {
    private String id;
    private String fileName;//模板文件名称
    private String realName;//真实文件名称
    private String filePath;//文件保存路径
    private String templateType;//模版类型(0技术打分模版，1商务打分模版，2价格打分模版，3技术阅标记录模版，4商务阅标记录模版，5唱标报价模版，6投标人清单)
    private String isDefault;//是否为默认模板 每一个类型只能有一个为Y
    private String isMaterial;//Y 是物资 N 非物资
    private String uptime;  //上传模板时间

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getTemplateType() {
        return templateType;
    }

    public void setTemplateType(String templateType) {
        this.templateType = templateType;
    }

    public String getIsDefault() {
        return isDefault;
    }

    public void setIsDefault(String isDefault) {
        this.isDefault = isDefault;
    }

    public String getIsMaterial() {
        return isMaterial;
    }

    public void setIsMaterial(String isMaterial) {
        this.isMaterial = isMaterial;
    }

    public String getUptime() {
        return uptime;
    }

    public void setUptime(String uptime) {
        this.uptime = uptime;
    }

    @Override
    public String toString() {
        return "tblBbgsTemplateModel{" +
                "id='" + id + '\'' +
                ", fileName='" + fileName + '\'' +
                ", realName='" + realName + '\'' +
                ", filePath='" + filePath + '\'' +
                ", templateType='" + templateType + '\'' +
                ", isDefault='" + isDefault + '\'' +
                ", isMaterial='" + isMaterial + '\'' +
                ", uptime='" + uptime + '\'' +
                '}';
    }
}
