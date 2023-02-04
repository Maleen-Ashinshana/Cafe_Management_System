package lk.ijse.cafe.to;

import java.time.LocalDate;

public class Order {
    private String OrderId;
    private String date;
    private String customerId;

    public Order() {
    }

    public Order(String orderId, String date, String customerId) {
        OrderId = orderId;
        this.date = date;
        this.customerId = customerId;
    }

    public String getOrderId() {
        return OrderId;
    }

    public void setOrderId(String orderId) {
        OrderId = orderId;
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
    @Override
    public String toString(){
        return "Order{"+
                "orderId='"+OrderId+'\''+
                ",date='"+date+'\''+
                ",customerId='"+customerId+'\''+
                '}';
    }
}
