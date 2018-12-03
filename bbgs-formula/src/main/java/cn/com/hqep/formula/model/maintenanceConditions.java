package cn.com.hqep.formula.model;

/**
 * 公式条件
 * Created by spring on 2017/10/14.
 */
public class maintenanceConditions {

    String id;
    String type; //类型0:物资1:非物资
    String describe; //公式描述
    String createtime; //创建时间
    String updatetime; //修改时间
    String conditions; //公式的条件
    String formulafun; //公式执行方法路径
    String remark; //备注(公式详细描述)


    String startvalue; //起始值
    String endvalue; //结束值
    String submaxpricecount; //去掉最高价个数
    String subminpricecount; //去掉最低价个数
    String conditionsid; //条件id

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
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

    public String getConditions() {
        return conditions;
    }

    public void setConditions(String conditions) {
        this.conditions = conditions;
    }

    public String getFormulafun() {
        return formulafun;
    }

    public void setFormulafun(String formulafun) {
        this.formulafun = formulafun;
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

    public String getConditionsid() {
        return conditionsid;
    }

    public void setConditionsid(String conditionsid) {
        this.conditionsid = conditionsid;
    }
}
