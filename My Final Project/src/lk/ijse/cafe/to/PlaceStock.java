package lk.ijse.cafe.to;

import java.util.ArrayList;

public class PlaceStock {
  private String supplierId;
  private String stockId;
  private ArrayList<StockCartDeteils> stockCartDeteils=new ArrayList<>();

    public PlaceStock() {
    }

    public PlaceStock(String supplierId, String stockId, ArrayList<StockCartDeteils> stockCartDeteils) {
        this.supplierId = supplierId;
        this.stockId = stockId;
        this.stockCartDeteils = stockCartDeteils;
    }

    public String getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(String supplierId) {
        this.supplierId = supplierId;
    }

    public String getStockId() {
        return stockId;
    }

    public void setStockId(String stockId) {
        this.stockId = stockId;
    }

    public ArrayList<StockCartDeteils> getStockCartDeteils() {
        return stockCartDeteils;
    }

    public void setStockCartDeteils(ArrayList<StockCartDeteils> stockCartDeteils) {
        this.stockCartDeteils = stockCartDeteils;
    }
    @Override
    public String toString() {
        return "PlaceStock{" +
                "supplierId='" + supplierId + '\'' +
                ", stockId='" + stockId + '\'' +
                ", stockCartDeteils=" + stockCartDeteils +
                '}';
    }
}
