//package lk.ijse.cafe.model;
//
//import lk.ijse.cafe.to.Manager;
//import lk.ijse.cafe.util.CrudUtil;
//
//import java.sql.SQLException;
//
//public class ManagerModel {
//    public static boolean save(Manager manager) throws SQLException, ClassNotFoundException {
//        String sql="INSERT INTO system_uses VALUES(?,?,?,?,?,?,?)";
//        return CrudUtil.execute(sql,manager.getId(),manager.getName(),manager.getAddress(),manager.getContact_number(),manager.getType(),manager.getEmail(),manager.getPassword());
//
//    }
//}
