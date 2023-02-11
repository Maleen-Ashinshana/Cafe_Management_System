package lk.ijse.cafe.dto;

import java.sql.Date;

public class StokeDTO {
    private String stoke_id;
    private String date;
    private String supplyer_id;
    //private ArrayList<StokeDetailsDTO> stokeDetailsDTOS;


    public StokeDTO() {
    }

    public StokeDTO(String stoke_id, String date, String supplyer_id) {
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

//    public ArrayList<StokeDetailsDTO> getStokeDetailsDTOS() {
//        return stokeDetailsDTOS;
//    }
//
//    public void setStokeDetailsDTOS(ArrayList<StokeDetailsDTO> stokeDetailsDTOS) {
//        this.stokeDetailsDTOS = stokeDetailsDTOS;
//    }

    @Override
    public String toString() {
        return "StokeDTO{" +
                "stoke_id='" + stoke_id + '\'' +
                ", date=" + date +
                ", supplyer_id='" + supplyer_id + '\'' +
                '}';
    }
}
