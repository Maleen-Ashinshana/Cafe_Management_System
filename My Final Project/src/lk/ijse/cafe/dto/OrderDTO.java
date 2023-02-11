package lk.ijse.cafe.dto;

import java.util.Date;

public class OrderDTO {
    private String order_id;
    private Date date;
    private String customer_id;

//    public OrderDTO(String text, String lblOrderDateText, String lblPayCustomerIdText) {
//    }


    public OrderDTO(String order_id, String date, String customer_id) {
    }

    public OrderDTO(String order_id) {

        this.order_id = order_id;
    }

    public OrderDTO(String order_id, Date date, String customer_id) {
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
        return "OrderDTO{" +
                "order_id='" + order_id + '\'' +
                ", date=" + date +
                ", customer_id='" + customer_id + '\'' +
                '}';
    }
}
