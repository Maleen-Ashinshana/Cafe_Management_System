package lk.ijse.cafe.entity;

import java.sql.Date;

public class OrderDetailsEntity implements SupperEntity{
    private String order_id;
    private String item_code;
    private int qty;
    private double unitPrice;
    private String date;

    public OrderDetailsEntity() {
    }

    public OrderDetailsEntity(String order_id, String item_code, int qty, double unitPrice, String date) {
        this.order_id = order_id;
        this.item_code = item_code;
        this.qty = qty;
        this.unitPrice = unitPrice;
        this.date = date;
    }

    public OrderDetailsEntity(String orderId, java.util.Date date, String customerId) {
    }

    public String getOrder_id() {
        return order_id;
    }

    public void setOrder_id(String order_id) {
        this.order_id = order_id;
    }

    public String getItem_code() {
        return item_code;
    }

    public void setItem_code(String item_code) {
        this.item_code = item_code;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
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
    public String toString() {
        return "OrderDetailsEntity{" +
                "order_id='" + order_id + '\'' +
                ", item_code='" + item_code + '\'' +
                ", qty=" + qty +
                ", unitPrice=" + unitPrice +
                ", date=" + date +
                '}';
    }
}
