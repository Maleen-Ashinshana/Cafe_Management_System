//package lk.ijse.cafe.model;
//
//import lk.ijse.cafe.to.StockCartDeteils;
//import lk.ijse.cafe.util.CrudUtil;
//
//import java.sql.SQLException;
//import java.util.ArrayList;
//
//public class StockDetailsModel {
//
//    public static boolean saveStockDetails(ArrayList<StockCartDeteils>stockCartDeteils) throws SQLException, ClassNotFoundException {
//        for (StockCartDeteils cartDeteils:stockCartDeteils){
//            if (!save(cartDeteils)){
//                return false;
//            }
//        }
//        return true;
//
//
//    }
//    private static boolean save(StockCartDeteils stockCartDeteils) throws SQLException, ClassNotFoundException {
//        String sql="INSERT INTO stoke_details VALUES(?, ?, ?, ?)";
//        return CrudUtil.execute(sql,stockCartDeteils.getStockId(),stockCartDeteils.getCode(),stockCartDeteils.getUnitPrice(),stockCartDeteils.getQty());
//    }
//}
