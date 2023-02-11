//package lk.ijse.cafe.model;
//
//import lk.ijse.cafe.to.Cashier;
//import lk.ijse.cafe.util.CrudUtil;
//
//import java.sql.SQLException;
//
//public class CashierModel {
//    public static boolean save(Cashier cashier) throws SQLException, ClassNotFoundException {
//        String sql="INSERT INTO system_uses VALUES(?,?,?,?,?,?,?)";
//        return CrudUtil.execute(sql,cashier.getId(),cashier.getName(),cashier.getAddress(),cashier.getCon(),cashier.getType(),cashier.getEmail(),cashier.getPassword());
//    }
//}
