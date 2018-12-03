package cn.com.hqep.formula.model;

/**
 *
 * 流程表 实体类
 * @author swq
 * @date
 */
public class supplierRowPriceModel {
    private String id;
    private String bid_abbreviaion;
    private String supplier;
    private String row_type;
    private String row_price;
    private String row_price_weight;
    private String row_price_avg;
    private String A1;
    private String A2;
    private String M;
    private String N;
    private String  base_price;
    private String  score;

    @Override
    public String toString() {
        return "supplierRowPriceModel{" +
                "id='" + id + '\'' +
                ", bid_abbreviaion='" + bid_abbreviaion + '\'' +
                ", supplier='" + supplier + '\'' +
                ", row_type='" + row_type + '\'' +
                ", row_price='" + row_price + '\'' +
                ", row_price_weight='" + row_price_weight + '\'' +
                ", row_price_avg='" + row_price_avg + '\'' +
                ", A1='" + A1 + '\'' +
                ", A2='" + A2 + '\'' +
                ", M='" + M + '\'' +
                ", N='" + N + '\'' +
                ", base_price='" + base_price + '\'' +
                ", score='" + score + '\'' +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBid_abbreviaion() {
        return bid_abbreviaion;
    }

    public void setBid_abbreviaion(String bid_abbreviaion) {
        this.bid_abbreviaion = bid_abbreviaion;
    }

    public String getSupplier() {
        return supplier;
    }

    public void setSupplier(String supplier) {
        this.supplier = supplier;
    }

    public String getRow_type() {
        return row_type;
    }

    public void setRow_type(String row_type) {
        this.row_type = row_type;
    }

    public String getRow_price() {
        return row_price;
    }

    public void setRow_price(String row_price) {
        this.row_price = row_price;
    }

    public String getRow_price_weight() {
        return row_price_weight;
    }

    public void setRow_price_weight(String row_price_weight) {
        this.row_price_weight = row_price_weight;
    }

    public String getRow_price_avg() {
        return row_price_avg;
    }

    public void setRow_price_avg(String row_price_avg) {
        this.row_price_avg = row_price_avg;
    }

    public String getA1() {
        return A1;
    }

    public void setA1(String a1) {
        A1 = a1;
    }

    public String getA2() {
        return A2;
    }

    public void setA2(String a2) {
        A2 = a2;
    }

    public String getM() {
        return M;
    }

    public void setM(String m) {
        M = m;
    }

    public String getN() {
        return N;
    }

    public void setN(String n) {
        N = n;
    }

    public String getBase_price() {
        return base_price;
    }

    public void setBase_price(String base_price) {
        this.base_price = base_price;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }
}
