package cn.com.hqep.bidder.model;

/**
 * Created by HQSpring on 2017/10/17.
 */
public class tblBbgsExpertModel {
    private String id;              //专家id
    private String expertName;      //专家姓名
    private String groupId;         //专家组id


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
}
