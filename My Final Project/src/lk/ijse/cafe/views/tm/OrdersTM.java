package lk.ijse.cafe.views.tm;

public class OrdersTM {
    private String orderId;
    private String itemCode;
    private String description;
    private int qty;
    private String date;
    private String customerId;
     private double unitPrice;

    public OrdersTM() {
    }

    public OrdersTM(String orderId, String itemCode, String description, int qty, String date, String customerId, double unitPrice) {
        this.orderId = orderId;
        this.itemCode = itemCode;
        this.description = description;
        this.qty = qty;
        this.date = date;
        this.customerId = customerId;
        this.unitPrice = unitPrice;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getItemCode() {
        return itemCode;
    }

    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }
}
