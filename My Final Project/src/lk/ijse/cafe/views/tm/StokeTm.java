package lk.ijse.cafe.views.tm;

import java.util.Date;

public class StokeTm {
    private String stoke_id;
    private Date date;
    private String supplyer_id;

    public StokeTm() {
    }

    public StokeTm(String stoke_id, Date date, String supplyer_id) {
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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
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
        return "StokeTm{" +
                "stoke_id='" + stoke_id + '\'' +
                ", date=" + date +
                ", supplyer_id='" + supplyer_id + '\'' +
                '}';
    }
}
