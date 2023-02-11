package lk.ijse.cafe.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lk.ijse.cafe.to.Supplyer;
import lk.ijse.cafe.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class SupplyeerModel {
    public static ArrayList<String> loadSupplyerId() throws SQLException, ClassNotFoundException {
        String sql="SELECT supplyer_id FROM supplyer";
        ResultSet result= CrudUtil.execute(sql);

        ArrayList<String> idList=new ArrayList<>();
        while ((result.next())){
            idList.add(result.getString(1));

        }
        return idList;
    }

    public static Supplyer search(String id) throws SQLException, ClassNotFoundException {
        String sql="SELECT * FROM supplyer WHERE supplyer_id=?";
        ResultSet result=CrudUtil.execute(sql,id);

        if (result.next()){
            return  new Supplyer(
                    result.getString(1),
                    result.getString(2),
                    result.getString(3)

            );
        }
        return null;

    }
//
//    public static boolean isAdded(Supplyer supplyer) throws SQLException, ClassNotFoundException {
//        return  CrudUtil.execute("INSERT INTO supplyer VALUES(?,?,?) ",
//                supplyer.getId(),
//                supplyer.getName(),
//                supplyer.getAddress()
//        );
//
//    }
//
//    public static boolean deleteSuppler(String id) throws SQLException, ClassNotFoundException {
//        return CrudUtil.execute("DELETE FROM supplyer WHERE supplyer_id='"+id+"'");
//    }
//
//    public static boolean update(Supplyer supplyer) throws SQLException, ClassNotFoundException {
//        return CrudUtil.execute("UPDATE supplyer set name=?,address=? WHERE supplyer_id=?",
//                supplyer.getName(),
//                supplyer.getAddress(),
//                supplyer.getId()
//
//        );
//
//    }
//
//    public static ObservableList<Supplyer> getAll() throws SQLException, ClassNotFoundException {
//        String sql= "SELECT * FROM supplyer";
//        ResultSet resultSet=CrudUtil.execute(sql);
//        ObservableList<Supplyer>supplyerObservableList= FXCollections.observableArrayList();
//        while (resultSet.next()){
//            Supplyer supplyer=new Supplyer(resultSet.getString("supplyer_id"),resultSet.getString("name"),resultSet.getString("address"));
//        supplyerObservableList.add(supplyer);
//        }
//        return supplyerObservableList;
//    }

}
