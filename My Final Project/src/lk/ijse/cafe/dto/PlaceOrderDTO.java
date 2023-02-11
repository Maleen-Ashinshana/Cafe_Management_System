package lk.ijse.cafe.dto;

import java.util.ArrayList;
import java.util.Date;

public class PlaceOrderDTO {
    private String customerId;
    private String orderId;
    private String orderDate;
    private ArrayList<OrderDetailsDTO> orderDetails=new ArrayList<>();

    public PlaceOrderDTO(String customer_id, String order_id, Date date) {
    }

    public PlaceOrderDTO(String customerId, String orderId, String orderDate) {
        this.customerId = customerId;
        this.orderId = orderId;
        this.orderDate = orderDate;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    @Override
    public String toString() {
        return "PlaceOrderDTO{" +
                "customerId='" + customerId + '\'' +
                ", orderId='" + orderId + '\'' +
                ", orderDate='" + orderDate + '\'' +
                '}';
    }
}
