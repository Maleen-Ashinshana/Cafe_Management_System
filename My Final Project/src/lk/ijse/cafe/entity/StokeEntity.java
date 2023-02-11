package lk.ijse.cafe.entity;

import java.sql.Date;

public class StokeEntity implements SupperEntity{
    private String stoke_id;
    private String date;
    private String supplyer_id;

    public StokeEntity() {
    }

    public StokeEntity(String stoke_id, String date, String supplyer_id) {
        this.stoke_id = stoke_id;
        this.date = date;
        this.supplyer_id = supplyer_id;
    }

    public String getStoke_id() {
        return stoke_id;
    }

    public void setStoke_id(String stoke_id) {
        this.stoke_id = stoke_id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getSupplyer_id() {
        return supplyer_id;
    }

    public void setSupplyer_id(String supplyer_id) {
        this.supplyer_id = supplyer_id;
    }

    @Override
    public String toString() {
        return "StokeEntity{" +
                "stoke_id='" + stoke_id + '\'' +
                ", date='" + date + '\'' +
                ", supplyer_id='" + supplyer_id + '\'' +
                '}';
    }
}
