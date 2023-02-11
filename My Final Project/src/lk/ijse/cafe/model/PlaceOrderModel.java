//package lk.ijse.cafe.model;
//
//import lk.ijse.cafe.db.DBConnection;
//import lk.ijse.cafe.dto.CartDetailDTO;
//import lk.ijse.cafe.to.Order;
//
//import java.sql.SQLException;
//import java.util.ArrayList;
//
//public class PlaceOrderModel {
////    public  String getNextCustomerId() throws SQLException, ClassNotFoundException {
////        //ResultSet result=CrudUtil.execute("SELECT customer_id FROM customer CUSTOMER BY customer_id DESC LIMIT 1 ");
////        ResultSet result=CrudUtil.execute("SELECT customer_id FROM customer ORDER BY customer_id DESC LIMIT 1");
////        if (result.next()){
////            return generateNextCustomerId("C",result.getString(1));
////
////        }
////        return generateNextCustomerId("C",null);
////    }
////    private  String generateNextCustomerId(String prefId,String lastId){
//////        if (currentCustomerId!=null){
//////            String[] ids=currentCustomerId.split("C000");
//////            int id=Integer.parseInt(ids[0]);
//////            id+=1;
//////            return "C000"+id;
//////        }
//////        return "C0001";
////        if (lastId!=null){
////            int newId=Integer.parseInt(lastId.replace(prefId,""))+1;
////            return String.format(prefId+"%03d",newId);
////
////        }else {
////             return prefId+000;
////        }
////    }
//
////    public static boolean placeOrder(String orderId, String date, String customerId, ArrayList<CartDetailDTO> cartDetails) throws SQLException, ClassNotFoundException {
////
////        try {
////        DBConnection.getInstance().getConnection().setAutoCommit(false);
////        boolean isOrderAdded = OrderModel.save(new Order(orderId,date,customerId));
////
////
////        if (isOrderAdded) {
////            boolean isOrderDetailsAdded = OrderDetailModel.savaOrderDetails(cartDetails);
////            if (isOrderDetailsAdded) {
////                DBConnection.getInstance().getConnection().commit();
////                return true;
////            }
////            //boolean isUpdate = ItemModel.updateQty(cartDetails);
//////            if (isUpdate) {
//////                for (CartDetail cartDetail: cartDetails) {
//////                    System.out.println("ooooooooooooooooooo");
//////                    System.out.println(cartDetail);
//////                    System.out.println("cgauhci");
//////                }
//////
//////            }
////        }
////        DBConnection.getInstance().getConnection().rollback();
////        return false;
////
////        }finally {
////            DBConnection.getInstance().getConnection().setAutoCommit(true);
////        }
////    }
//}