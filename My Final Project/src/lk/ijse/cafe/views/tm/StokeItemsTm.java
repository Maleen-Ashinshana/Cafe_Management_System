package lk.ijse.cafe.views.tm;

public class StokeItemsTm {
    private String id;
    private String description;
    private double unitPrice;
    private int qty;



    public StokeItemsTm() {
    }

    public StokeItemsTm(String id, String description, double unitPrice, int qty) {
        this.id = id;
        this.description = description;
        this.unitPrice = unitPrice;
        this.qty = qty;
    }
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    @Override
    public String toString() {
        return "StokeItemsTm{" +
                "id='" + id + '\'' +
                ", description='" + description + '\'' +
                ", unitPrice=" + unitPrice +
                ", qty=" + qty +
                '}';
    }
}
