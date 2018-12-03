package cn.com.hqep.templet.model;

/**
 * 厂商表实体TBL_BBGS_SUPPLIER
 *
 * @author hq4
 * @date 2017年10月09日 上午11:05:16
 */
public class tblBbgsSupplierModel {
    private String id;
    private String supplier;//供应商名称

    public tblBbgsSupplierModel(String id, String supplier) {
        this.id = id;
        this.supplier = supplier;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSupplier() {
        return supplier;
    }

    public void setSupplier(String supplier) {
        this.supplier = supplier;
    }

    @Override
    public String toString() {
        return "tblBbgsSupplier{" +
                "id='" + id + '\'' +
                ", supplier='" + supplier + '\'' +
                '}';
    }
}
