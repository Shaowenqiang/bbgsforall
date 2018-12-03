package cn.com.hqep.templet.model;

public class tblBbgsExpertModel {
    private String id;
    private String expertName;
    private String groupId;
    private String expertGroupName;
    private String type;
    private String totalnum;
    private String scoreNum_t;
    private String scoreNum_b;

    @Override
    public String toString() {
        return "tblBbgsExpertModel{" +
                "id='" + id + '\'' +
                ", expertName='" + expertName + '\'' +
                ", groupId='" + groupId + '\'' +
                ", expertGroupName='" + expertGroupName + '\'' +
                ", type='" + type + '\'' +
                ", totalnum='" + totalnum + '\'' +
                ", scoreNum_t='" + scoreNum_t + '\'' +
                ", scoreNum_b='" + scoreNum_b + '\'' +
                ", importNum='" + importNum + '\'' +
                '}';
    }

    public String getImportNum() {
        return importNum;
    }

    public void setImportNum(String importNum) {
        this.importNum = importNum;
    }

    private String importNum;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getExpertName() {
        return expertName;
    }

    public void setExpertName(String expertName) {
        this.expertName = expertName;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public String getExpertGroupName() {
        return expertGroupName;
    }

    public void setExpertGroupName(String expertGroupName) {
        this.expertGroupName = expertGroupName;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTotalnum() {
        return totalnum;
    }

    public void setTotalnum(String totalnum) {
        this.totalnum = totalnum;
    }

    public String getScoreNum_t() {
        return scoreNum_t;
    }

    public void setScoreNum_t(String scoreNum_t) {
        this.scoreNum_t = scoreNum_t;
    }

    public String getScoreNum_b() {
        return scoreNum_b;
    }

    public void setScoreNum_b(String scoreNum_b) {
        this.scoreNum_b = scoreNum_b;
    }

}
