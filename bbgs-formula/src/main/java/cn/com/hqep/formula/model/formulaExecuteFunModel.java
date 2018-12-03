package cn.com.hqep.formula.model;

/**
 * 公式条件
 * Created by spring on 2017/10/14.
 */
public class formulaExecuteFunModel {

    String id;
    String type; //类型0:物资1:非物资
    String descript; //公式描述
    String createtime; //创建时间
    String updatetime; //修改时间
    String formulafun; //公式执行方法路径
    String remark; //备注(公式详细描述)

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

    public String getDescript() { return descript; }

    public void setDescript(String descript) { this.descript = descript; }

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
}
