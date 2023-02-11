//package lk.ijse.cafe.model;
//
//import lk.ijse.cafe.to.Stock;
//import lk.ijse.cafe.to.StockItems;
//import lk.ijse.cafe.util.CrudUtil;
//
//import java.sql.ResultSet;
//import java.sql.SQLException;
//
//public class StockModel {
//    public static String getNextId() throws SQLException, ClassNotFoundException {
//        ResultSet result=CrudUtil.execute("SELECT stok_id FROM stok ORDER BY stok_id DESC LIMIT 1");
//        if (result.next()){
//            return generateNextId("E",result.getString(1));
//
//        }
//        return generateNextId("E",null);
//
//    }
//    private static String generateNextId(String PrefId,String LsatId){
//        if (LsatId!=null){
//            int newId=Integer.parseInt(LsatId.replace(PrefId,""))+1;
//            return String.format(PrefId+"%03d",newId);
//
//        }else {
//            return PrefId+000;
//        }
//    }
//
//    public static StockItems search(String code) throws SQLException, ClassNotFoundException {
//        String sql="SELECT * FROM stoke_items WHERE id= ?";
//        ResultSet result=CrudUtil.execute(sql,code);
//        if (result.next()){
//            return new StockItems(
//                    result.getString(1),
//                    result.getString(2),
//                    result.getDouble(3),
//                    result.getInt(4)
//
//            );
//
//        }
//        return  null;
//
//    }
//
//    public static boolean save(Stock stock) throws SQLException, ClassNotFoundException {
//        String sql = "INSERT INTO stok VALUES(?, ?, ?)";
//        return CrudUtil.execute(sql,stock.getId(),stock.getDate(),stock.getSupplierId());
//
//    }
//
////    public static String getNextId() throws SQLException, ClassNotFoundException {
////        ResultSet result= CrudUtil.execute("SELECT stok_id FROM stok ORDER BY id DESC LIMIT 1 ");
////        if (result.next()){
////            return generateNextId(result.getString(1));
////        }
////        return generateNextId(null);
////
////    }
////
////    public static StockItems search(String code) throws SQLException, ClassNotFoundException {
////        String sql="SELECT * FROM stoke_items WHERE id= ?";
////        ResultSet result=CrudUtil.execute(sql,code);
////        if (result.next()){
////            return new StockItems(
////                    result.getString(1),
////                    result.getString(2),
////                    result.getDouble(3),
////                    result.getInt(4)
////
////            );
////
////        }
////        return  null;
////    }
//
//}
