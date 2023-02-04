package lk.ijse.cafe.to;

import java.time.LocalDate;

public class Stock {
    private String id;
    private LocalDate date;
    private String supplierId;

    public Stock() {
    }

    public Stock(String id, LocalDate date, String supplierId) {
        this.id = id;
        this.date = date;
        this.supplierId = supplierId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(String supplierId) {
        this.supplierId = supplierId;
    }
}
