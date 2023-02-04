package lk.ijse.cafe.model;

import lk.ijse.cafe.to.Order;
import lk.ijse.cafe.util.CrudUtil;
import rex.utils.S;

import java.sql.ResultSet;
import java.sql.SQLException;

public class OrderModel {

    public static boolean save(Order order) throws SQLException, ClassNotFoundException {
        String sql="INSERT  INTO orders VALUES (?,?,?)";

//        INSERT  INTO orders VALUES (?,?,?);
//
//        INSERT INTO orders VALUES(?,?,?)
        return CrudUtil.execute(sql,order.getOrderId(),order.getDate(),order.getCustomerId());
        //System.out.println(lblOrderId.getText()+"\n"+tm.getCode()+"\n"+tm.getDescription()+"\n"+tm.getQty()+"\n"+tm.getUnitPrice()+"\n"+lblOrderDate.getText());
    }
    public static String getNextOrderId() throws SQLException, ClassNotFoundException {
        ResultSet result=CrudUtil.execute("SELECT order_id FROM orders ORDER BY order_id DESC LIMIT 1");
        if (result.next()){
            return generateNextOrderId("O",result.getString(1));

        }
        return generateNextOrderId("O",null);
    }
    private static String  generateNextOrderId(String PrefId,String LsatId) {
//        if (currentOrderId!=null){
//            String[] ids=currentOrderId.split("D000");
//            int id=Integer.parseInt(ids[0]);
//            id+=1;
//            return "D000"+id;
//        }
//        return "D0001";
        if (LsatId!=null){
            int newId=Integer.parseInt(LsatId.replace(PrefId,""))+1;
            return String.format(PrefId+"%03d",newId);

        }else {
            return PrefId+000;
        }
    }
}
