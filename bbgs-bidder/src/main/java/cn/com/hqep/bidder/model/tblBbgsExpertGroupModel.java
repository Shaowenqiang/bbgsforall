package cn.com.hqep.bidder.model;

/**
 * Created by HQSpring on 2017/10/17.
 */
public class tblBbgsExpertGroupModel {
    private String id;              //专家组id
    private String expertGroupName; //专家组名
    private String type;            //专家组类型
    private String sortid;          //排序


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getSortid() {
        return sortid;
    }

    public void setSortid(String sortid) {
        this.sortid = sortid;
    }
}
