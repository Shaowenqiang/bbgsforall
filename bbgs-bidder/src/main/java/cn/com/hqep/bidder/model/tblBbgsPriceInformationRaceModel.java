package cn.com.hqep.bidder.model;

/**
 * 标段信息表
 *
 * bid_information
 */
public class tblBbgsPriceInformationRaceModel {
  private String id;  //ID主键
  private String modOffer; //最终金额报价
  private String finalRate; //最终折扣比例
  private String supplier; //供应商名称
  private String a1;//
  private String n;//
  private String a2;//
  private String p;//
  private String basePrice;//评标基准价
  private String priceScore;//评标价格得分
  private String sort;//排序
  private String packagename;//包名
  private String a3;//
  private String a4;//
  private String bid;//标段名
  private String m;//
  private String businessWeight;//商务权重
  private String technologyWeight;//技术权重
  private String priceWeight;//价格权重
  private String bidSetion;//标段全称
  private String bidAbbreviaion;//标段简称


  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getModOffer() {
    return modOffer;
  }

  public void setModOffer(String modOffer) {
    this.modOffer = modOffer;
  }

  public String getFinalRate() {
    return finalRate;
  }

  public void setFinalRate(String finalRate) {
    this.finalRate = finalRate;
  }

  public String getSupplier() {
    return supplier;
  }

  public void setSupplier(String supplier) {
    this.supplier = supplier;
  }

  public String getA1() {
    return a1;
  }

  public void setA1(String a1) {
    this.a1 = a1;
  }

  public String getN() {
    return n;
  }

  public void setN(String n) {
    this.n = n;
  }

  public String getA2() {
    return a2;
  }

  public void setA2(String a2) {
    this.a2 = a2;
  }

  public String getP() {
    return p;
  }

  public void setP(String p) {
    this.p = p;
  }

  public String getBasePrice() {
    return basePrice;
  }

  public void setBasePrice(String basePrice) {
    this.basePrice = basePrice;
  }

  public String getPriceScore() {
    return priceScore;
  }

  public void setPriceScore(String priceScore) {
    this.priceScore = priceScore;
  }

  public String getSort() {
    return sort;
  }

  public void setSort(String sort) {
    this.sort = sort;
  }

  public String getPackagename() {
    return packagename;
  }

  public void setPackagename(String packagename) {
    this.packagename = packagename;
  }

  public String getA3() {
    return a3;
  }

  public void setA3(String a3) {
    this.a3 = a3;
  }

  public String getA4() {
    return a4;
  }

  public void setA4(String a4) {
    this.a4 = a4;
  }

  public String getBid() {
    return bid;
  }

  public void setBid(String bid) {
    this.bid = bid;
  }

  public String getM() {
    return m;
  }

  public void setM(String m) {
    this.m = m;
  }

  public String getBusinessWeight() {
    return businessWeight;
  }

  public void setBusinessWeight(String businessWeight) {
    this.businessWeight = businessWeight;
  }

  public String getTechnologyWeight() {
    return technologyWeight;
  }

  public void setTechnologyWeight(String technologyWeight) {
    this.technologyWeight = technologyWeight;
  }

  public String getPriceWeight() {
    return priceWeight;
  }

  public void setPriceWeight(String priceWeight) {
    this.priceWeight = priceWeight;
  }

  public String getBidSetion() {
    return bidSetion;
  }

  public void setBidSetion(String bidSetion) {
    this.bidSetion = bidSetion;
  }

  public String getBidAbbreviaion() {
    return bidAbbreviaion;
  }

  public void setBidAbbreviaion(String bidAbbreviaion) {
    this.bidAbbreviaion = bidAbbreviaion;
  }
}
