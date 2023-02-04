package lk.ijse.cafe.dto;

import java.sql.Date;

public class PaymentDTO {
     private String id;
     private Date date;

     private double price;
     private String customer_id;
     private String customer_name;
     private String order_id;
     private double total;
     private double amount;
     private double reaming;

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public PaymentDTO(String id, Date date, double price, String customer_id, String customer_name, String order_id) {
        this.id = id;
        this.date = date;
        this.price = price;
        this.customer_id = customer_id;
        this.customer_name = customer_name;
        this.order_id = order_id;
    }

    public PaymentDTO(String text, String lblPeyMeantDateText, String lblPayCustomerIdText, String lblPeymentCustomerNameText, String lblPayOrderIdText, double total) {
    }

    public PaymentDTO(String id) {
        this.id = id;
    }

    public PaymentDTO(String id, Date date, String customer_id, String customer_name, String order_id) {
        this.id = id;
        this.date = date;
        this.customer_id = customer_id;
        this.customer_name = customer_name;
        this.order_id = order_id;
    }

    public PaymentDTO(String id, Date date, String customer_id, String customer_name, String order_id, double total) {
        this.id = id;
        this.date = date;
        this.customer_id = customer_id;
        this.customer_name = customer_name;
        this.order_id = order_id;
        this.total = total;
    }

    public PaymentDTO(String id, Date date, String customer_id, String customer_name, String order_id, double total, double amount, double reaming) {
        this.id = id;
        this.date = date;
        this.customer_id = customer_id;
        this.customer_name = customer_name;
        this.order_id = order_id;
        this.total = total;
        this.amount = amount;
        this.reaming = reaming;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(String customer_id) {
        this.customer_id = customer_id;
    }

    public String getCustomer_name() {
        return customer_name;
    }

    public void setCustomer_name(String customer_name) {
        this.customer_name = customer_name;
    }

    public String getOrder_id() {
        return order_id;
    }

    public void setOrder_id(String order_id) {
        this.order_id = order_id;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public double getReaming() {
        return reaming;
    }

    public void setReaming(double reaming) {
        this.reaming = reaming;
    }

    @Override
    public String toString() {
        return "PaymentDTO{" +
                "id='" + id + '\'' +
                ", date=" + date +
                ", price=" + price +
                ", customer_id='" + customer_id + '\'' +
                ", customer_name='" + customer_name + '\'' +
                ", order_id='" + order_id + '\'' +
                ", total=" + total +
                ", amount=" + amount +
                ", reaming=" + reaming +
                '}';
    }
}
