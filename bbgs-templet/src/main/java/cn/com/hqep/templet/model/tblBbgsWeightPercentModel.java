package cn.com.hqep.templet.model;

public class tblBbgsWeightPercentModel {
    private String id;
    private String weightName;
    private String weightContent;

    @Override
    public String toString() {
        return "tblBbgsWeightPercentModel{" +
                "id='" + id + '\'' +
                ", weightName='" + weightName + '\'' +
                ", weightContent='" + weightContent + '\'' +
                ", isDefault='" + isDefault + '\'' +
                '}';
    }

    public String getIsDefault() {
        return isDefault;
    }

    public void setIsDefault(String isDefault) {
        this.isDefault = isDefault;
    }

    private String isDefault;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getWeightName() {
        return weightName;
    }

    public void setWeightName(String weightName) {
        this.weightName = weightName;
    }

    public String getWeightContent() {
        return weightContent;
    }

    public void setWeightContent(String weightContent) {
        this.weightContent = weightContent;
    }

}
