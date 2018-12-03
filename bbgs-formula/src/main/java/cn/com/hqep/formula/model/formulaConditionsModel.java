package cn.com.hqep.formula.model;

/**
 * 公式条件
 * Created by spring on 2017/10/14.
 */
public class formulaConditionsModel {

    String id;
    String conditionname; //条件名
    String conditionid; //条件id
    String executefun; //方法路径
    String executefunid; //方法id
    String executefunname;//方法中文名
    String remark; //备注(条件详细描述)


    String startvalue; //起始值
    String endvalue; //结束值
    String submaxpricecount; //去掉最高价个数
    String subminpricecount; //去掉最低价个数
    String createtime; //创建时间
    String updatetime; //修改时间

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getExecutefunname() {
        return executefunname;
    }

    public void setExecutefunname(String executefunname) {
        this.executefunname = executefunname;
    }

    public String getConditionname() {
        return conditionname;
    }

    public void setConditionname(String conditionname) {
        this.conditionname = conditionname;
    }

    public String getConditionid() {
        return conditionid;
    }

    public void setConditionid(String conditionid) {
        this.conditionid = conditionid;
    }

    public String getExecutefun() {
        return executefun;
    }

    public void setExecutefun(String executefun) {
        this.executefun = executefun;
    }

    public String getExecutefunid() {
        return executefunid;
    }

    public void setExecutefunid(String executefunid) {
        this.executefunid = executefunid;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getStartvalue() {
        return startvalue;
    }

    public void setStartvalue(String startvalue) {
        this.startvalue = startvalue;
    }

    public String getEndvalue() {
        return endvalue;
    }

    public void setEndvalue(String endvalue) {
        this.endvalue = endvalue;
    }

    public String getSubmaxpricecount() {
        return submaxpricecount;
    }

    public void setSubmaxpricecount(String submaxpricecount) {
        this.submaxpricecount = submaxpricecount;
    }

    public String getSubminpricecount() {
        return subminpricecount;
    }

    public void setSubminpricecount(String subminpricecount) {
        this.subminpricecount = subminpricecount;
    }

    public String getCreatetime() {
        return createtime;
    }

    public void setCreatetime(String createtime) {
        this.createtime = createtime;
    }

    public String getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(String updatetime) {
        this.updatetime = updatetime;
    }
}
