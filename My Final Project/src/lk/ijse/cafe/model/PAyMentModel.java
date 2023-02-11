//package lk.ijse.cafe.model;
//
//import lk.ijse.cafe.to.Payment;
//import lk.ijse.cafe.util.CrudUtil;
//
//import java.sql.ResultSet;
//import java.sql.SQLException;
//
//public class PAyMentModel {
//    public static boolean save(Payment payment) throws SQLException, ClassNotFoundException {
//          return  CrudUtil.execute("INSERT INTO  payment VALUES (?,?,?,?,?,?)",
//                  payment.getId(),
//                  payment.getDate(),
//                  payment.getPrice(),
//                  payment.getOrderId(),
//                  payment.getCustomer_id(),
//                  payment.getCustomerName()
//          );
//    }
//    public static String getNextPayMentId() throws SQLException, ClassNotFoundException {
//        ResultSet resultSet= CrudUtil.execute("SELECT id FROM payment ORDER BY id DESC LIMIT 1");
//        if (resultSet.next()){
//            return genarateNextPeymentId("P",resultSet.getString(1));
//        }
//        return genarateNextPeymentId("P",null);
//    }
//    private static String genarateNextPeymentId(String PrefId,String LsatId){
//        if (LsatId!=null){
//            int newId=Integer.parseInt(LsatId.replace(PrefId,""))+1;
//            return String.format(PrefId+"%03d",newId);
//
//        }else {
//            return PrefId+000;
//        }
//    }
//}