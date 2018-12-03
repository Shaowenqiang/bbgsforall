package cn.com.hqep.bidder.model;

public class tblBbgsTechnologyBusinessModel {
  private String id;
  private String expertName;
  private String bidAbbreviaion;
  private String supplier;
  private String type;
  private String price;
  private String technologyWeight;
   private String businessWeight;
  private String expertGroupName;

  @Override
  public String toString() {
    return "tblBbgsTechnologyBusinessModel{" +
            "id='" + id + '\'' +
            ", expertName='" + expertName + '\'' +
            ", bidAbbreviaion='" + bidAbbreviaion + '\'' +
            ", supplier='" + supplier + '\'' +
            ", type='" + type + '\'' +
            ", price='" + price + '\'' +
            ", technologyWeight='" + technologyWeight + '\'' +
            ", businessWeight='" + businessWeight + '\'' +
            ", expertGroupName='" + expertGroupName + '\'' +
            '}';
  }

  public String getTechnologyWeight() {
    return technologyWeight;
  }

  public void setTechnologyWeight(String technologyWeight) {
    this.technologyWeight = technologyWeight;
  }

  public String getBusinessWeight() {
    return businessWeight;
  }

  public void setBusinessWeight(String businessWeight) {
    this.businessWeight = businessWeight;
  }

  public String getExpertGroupName() {
    return expertGroupName;
  }

  public void setExpertGroupName(String expertGroupName) {
    this.expertGroupName = expertGroupName;
  }

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

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public String getPrice() {
    return price;
  }

  public void setPrice(String price) {
    this.price = price;
  }
}
