package mybatis.learn.pojo;

public class Order {

    private Integer id;
    private String orderno;
    private String productName;

    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getOrderno() {
        return orderno;
    }
    public void setOrderno(String orderno) {
        this.orderno = orderno;
    }
    public String getProductName() {
        return productName;
    }
    public void setProductName(String productName) {
        this.productName = productName;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", orderno='" + orderno + '\'' +
                ", productName='" + productName + '\'' +
                '}';
    }
}
