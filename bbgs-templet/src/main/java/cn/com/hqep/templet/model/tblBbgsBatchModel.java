package cn.com.hqep.templet.model;

/**
 * 批次表
 *
 * @author shaowenqiang
 * @date
 */
public class tblBbgsBatchModel {
    String id;
    String batchName;//批次名称
    String isMaterial;//物资类别 1：物资 2：非物资

    public tblBbgsBatchModel(String id, String batchName, String isMaterial) {
        this.id = id;
        this.batchName = batchName;
        this.isMaterial = isMaterial;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBatchName() {
        return batchName;
    }

    public void setBatchName(String batchName) {
        this.batchName = batchName;
    }

    public String getIsMaterial() {
        return isMaterial;
    }

    public void setIsMaterial(String isMaterial) {
        this.isMaterial = isMaterial;
    }

    @Override
    public String toString() {
        return "tblBbgsBatch{" +
                "id='" + id + '\'' +
                ", batchName='" + batchName + '\'' +
                ", isMaterial='" + isMaterial + '\'' +
                '}';
    }
}
