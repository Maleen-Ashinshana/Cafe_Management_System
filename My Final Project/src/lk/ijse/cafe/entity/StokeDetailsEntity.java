package lk.ijse.cafe.entity;

public class StokeDetailsEntity implements SupperEntity{
    private String stoke_id;
    private String stoke_item_id;
    private double unitPrice;
    private int qty;

    public StokeDetailsEntity() {
    }

    public StokeDetailsEntity(String stoke_id, String stoke_item_id, double unitPrice, int qty) {
        this.stoke_id = stoke_id;
        this.stoke_item_id = stoke_item_id;
        this.unitPrice = unitPrice;
        this.qty = qty;
    }

    public String getStoke_id() {
        return stoke_id;
    }

    public void setStoke_id(String stoke_id) {
        this.stoke_id = stoke_id;
    }

    public String getStoke_item_id() {
        return stoke_item_id;
    }

    public void setStoke_item_id(String stoke_item_id) {
        this.stoke_item_id = stoke_item_id;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    @Override
    public String toString() {
        return "StokeDetailsEntity{" +
                "stoke_id='" + stoke_id + '\'' +
                ", stoke_item_id='" + stoke_item_id + '\'' +
                ", unitPrice=" + unitPrice +
                ", qty=" + qty +
                '}';
    }
}
