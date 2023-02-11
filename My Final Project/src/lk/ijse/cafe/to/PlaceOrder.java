package lk.ijse.cafe.to;

import lk.ijse.cafe.dto.CartDetailDTO;

import java.util.ArrayList;

public class PlaceOrder {
    private String customerId;
    private String orderId;

    private String orderDate;
    private ArrayList<CartDetailDTO> orderDetails=new ArrayList<>();

    public PlaceOrder(String customerId, String orderId, String orderDate) {
        this.customerId = customerId;
        this.orderId = orderId;
        this.orderDate = orderDate;
    }

    public PlaceOrder(String customerId, String orderId, String orderDate, ArrayList<CartDetailDTO> orderDetails) {
        this.customerId = customerId;
        this.orderId = orderId;
        this.orderDate = orderDate;
        this.orderDetails = orderDetails;
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

    public ArrayList<CartDetailDTO> getOrderDetails() {
        return orderDetails;
    }

    public void setOrderDetails(ArrayList<CartDetailDTO> orderDetails) {
        this.orderDetails = orderDetails;
    }


    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    @Override
    public String toString() {
        return "PlaceOrder{" +
                "customerId='" + customerId + '\'' +
                ", orderId='" + orderId + '\'' +
                ", orderDetails=" + orderDetails +'\''+
                ",OrderDate="+orderDate+
                '}';
    }

}
