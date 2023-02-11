package lk.ijse.cafe.dto;

import java.sql.Date;

public class OrderDetailsDTO {
    private String order_id;
    private String item_code;
    private int qty;
    private double unitPrice;
    private String date;

    public OrderDetailsDTO() {
    }

    public OrderDetailsDTO(String order_id, String item_code, int qty, double unitPrice, String date) {
        this.order_id = order_id;
        this.item_code = item_code;
        this.qty = qty;
        this.unitPrice = unitPrice;
        this.date = date;
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
        return "OrderDetailsDTO{" +
                "order_id='" + order_id + '\'' +
                ", item_code='" + item_code + '\'' +
                ", qty=" + qty +
                ", unitPrice=" + unitPrice +
                ", date=" + date +
                '}';
    }
}
