package lk.ijse.cafe.to;

import rex.utils.S;

public class Payment {
    private String id;
    private String date;
    private double price;
    private String customer_id;
     private String orderId;
     private String customerName;

     private double amount;
     private double remain;

    public Payment() {
    }

    public Payment(String id, String date, double price, String customer_id, String orderId, String customerName, double amount, double remain) {
        this.id = id;
        this.date = date;
        this.price = price;
        this.customer_id = customer_id;
        this.orderId = orderId;
        this.customerName = customerName;
        this.amount = amount;
        this.remain = remain;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(String customer_id) {
        this.customer_id = customer_id;
    }


    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public double getRemain() {
        return remain;
    }

    public void setRemain(double remain) {
        this.remain = remain;
    }
}
