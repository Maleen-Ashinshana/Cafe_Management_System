package lk.ijse.cafe.entity;

import lk.ijse.cafe.dao.util.SuperDAO;

import java.sql.Date;

public class OrderEntity implements SupperEntity {
    private String order_id;
    private Date date;
    private String customer_id;

    public OrderEntity() {
    }

    public OrderEntity(String order_id) {
        this.order_id = order_id;
    }

    public OrderEntity(String order_id, Date date, String customer_id) {
        this.order_id = order_id;
        this.date = date;
        this.customer_id = customer_id;
    }

    public String getOrder_id() {
        return order_id;
    }

    public void setOrder_id(String order_id) {
        this.order_id = order_id;
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

    @Override
    public String toString() {
        return "OrderEntity{" +
                "order_id='" + order_id + '\'' +
                ", date=" + date +
                ", customer_id='" + customer_id + '\'' +
                '}';
    }
}
