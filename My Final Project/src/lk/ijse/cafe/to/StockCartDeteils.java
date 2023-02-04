package lk.ijse.cafe.to;

public class StockCartDeteils {
     private String stockId;
     private String  code;
     private String description;
     private double unitPrice;
     private int qty;
      private String date;

    public StockCartDeteils() {
    }

    public StockCartDeteils(String stockId, String code, String description, double unitPrice, int qty, String date) {
        this.stockId = stockId;
        this.code = code;
        this.description = description;
        this.unitPrice = unitPrice;
        this.qty = qty;
        this.date = date;
    }

    public String getStockId() {
        return stockId;
    }

    public void setStockId(String stockId) {
        this.stockId = stockId;
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

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }
    @Override
    public String toString() {
        return "StockCartDeteils{" +
                "stockId='" + stockId + '\'' +
                ", code='" + code + '\'' +
                ", description=" + description +
                ", unitPrice='" + unitPrice + '\'' +
                ", qty =" + qty +
                '}';
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
