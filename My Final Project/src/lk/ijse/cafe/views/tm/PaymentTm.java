package lk.ijse.cafe.views.tm;

import java.util.Date;

public class PaymentTm {
    private String id;
    private Date date;
    private double price;
    private String order_id;
    private String customer_id;
    private String customer_name;

    public PaymentTm() {
    }

    public PaymentTm(String id) {
        this.id = id;
    }

    public PaymentTm(String id, Date date, double price, String order_id, String customer_id, String customer_name) {
        this.id = id;
        this.date = date;
        this.price = price;
        this.order_id = order_id;
        this.customer_id = customer_id;
        this.customer_name = customer_name;
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

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getOrder_id() {
        return order_id;
    }

    public void setOrder_id(String order_id) {
        this.order_id = order_id;
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

    @Override
    public String toString() {
        return "PaymentTm{" +
                "id='" + id + '\'' +
                ", date=" + date +
                ", price=" + price +
                ", order_id='" + order_id + '\'' +
                ", customer_id='" + customer_id + '\'' +
                ", customer_name='" + customer_name + '\'' +
                '}';
    }
}
