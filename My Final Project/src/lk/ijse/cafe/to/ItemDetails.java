package lk.ijse.cafe.to;

public class ItemDetails {
    private String code;
    private String description;
    private double unitPrice;

    public ItemDetails() {
    }

    public ItemDetails(String code, String description, double unitPrice) {
        this.code = code;
        this.description = description;
        this.unitPrice = unitPrice;
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

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }
    @Override
    public  String toString(){
        return  "Item{"+"code="+ getCode() +",description="+ getDescription() +",unitPrice="+ getUnitPrice() +'}';
    }
}
