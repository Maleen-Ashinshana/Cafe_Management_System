package lk.ijse.cafe.to;

public class CartDetail {
    private String orderId;
    private String code;
    private int qty;
   private String description;
    private double unitPrice;
    private String date;

    public CartDetail() {
    }

    public CartDetail(String orderId, String code, int qty, String description, double unitPrice, String date) {
        this.orderId = orderId;
        this.code = code;
        this.qty = qty;
        this.description = description;
        this.unitPrice = unitPrice;
        this.date = date;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }
    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
    @Override
     public String toString(){
        return "CartDetail{" +
                "orderId='" +orderId+'\''+
                "code='" +code+'\''+
                ", qty=" + qty +
                 ", description='" + description + '\'' +
                    ", unitPrice=" + unitPrice +
                '}';
     }



//    public String getDate() {
//        return date;
//    }
//
//    public void setDate(String date) {
//        this.date = date;
//    }
}
