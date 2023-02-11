package lk.ijse.cafe.views.tm;

import javafx.scene.control.Button;

public class PlaceOrderTM {
     private String code;
     private String description;
     private int qty;
     private double unitPrice;
     private double total;


    public PlaceOrderTM() {
    }

    public PlaceOrderTM(String code, String description, int qty, double unitPrice, double total, Button btnDelete) {
        this.code = code;
        this.description = description;
        this.qty = qty;
        this.unitPrice = unitPrice;
        this.total = total;
        //this.btnDelete = btnDelete;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

//    public Button getBtnDelete() {
//        return btnDelete;
//    }
//
//    public void setBtnDelete(Button btnDelete) {
//        this.btnDelete = btnDelete;
//    }
    @Override
    public String toString(){
        return "PlaceOrderTM{" +
                "code='"+ getCode() + '\'' +
                ", description='" + getDescription() + '\'' +
                ", qty=" + getQty() +
                ", unitPrice=" + getUnitPrice() +
                ", total=" + getTotal() +
                '}';
    }
}
