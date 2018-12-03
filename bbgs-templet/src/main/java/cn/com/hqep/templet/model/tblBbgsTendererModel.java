package cn.com.hqep.templet.model;

/**
 * 投标人基础信息表实体 TBL_BBGS_TENDERER
 *
 * @author shaowenqiang
 * @date 2017年10月09日 上午10:57:40
 */
public class tblBbgsTendererModel {
    private String id;
    private String batchTitle;//批次名
    private String bidAbbreviaion;//标段简称
    private String supplier;//供应商
    private String tel;//联系电话
    private String email;//电子邮件
    private String remit;//是否汇款
    private String send;//是否发送
    private String offer; //报价

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBatchTitle() {
        return batchTitle;
    }

    public void setBatchTitle(String batchTitle) {
        this.batchTitle = batchTitle;
    }

    public String getBidAbbreviaion() {
        return bidAbbreviaion;
    }

    public void setBidAbbreviaion(String bidAbbreviaion) {
        this.bidAbbreviaion = bidAbbreviaion;
    }

    public String getSupplier() {
        return supplier;
    }

    public void setSupplier(String supplier) {
        this.supplier = supplier;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRemit() {
        return remit;
    }

    public void setRemit(String remit) {
        this.remit = remit;
    }

    public String getSend() {
        return send;
    }

    public void setSend(String send) {
        this.send = send;
    }

    public String getOffer() {
        return offer;
    }

    public void setOffer(String offer) {
        this.offer = offer;
    }

    @Override
    public String toString() {
        return "tblBbgsTendererModel{" +
                "id='" + id + '\'' +
                ", batchTitle='" + batchTitle + '\'' +
                ", bidAbbreviaion='" + bidAbbreviaion + '\'' +
                ", supplier='" + supplier + '\'' +
                ", tel='" + tel + '\'' +
                ", email='" + email + '\'' +
                ", remit='" + remit + '\'' +
                ", send='" + send + '\'' +
                ", offer='" + offer + '\'' +
                '}';
    }
}
