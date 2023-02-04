package lk.ijse.cafe.model;

import lk.ijse.cafe.to.CartDetail;
import lk.ijse.cafe.util.CrudUtil;

import java.sql.SQLException;
import java.util.ArrayList;

public class OrderDetailModel {

    public static boolean savaOrderDetails(ArrayList<CartDetail> cartDetails) throws SQLException, ClassNotFoundException {

        for (CartDetail cartDetail:cartDetails) {
            System.out.println("ooooooooooooooooooo");
            if (!save(cartDetail)){
                 return false;
            }
            System.out.println("cgauhci");
        }
        System.out.println("ihgoiguifufgugouyhouig");
        return  true;
    }
    private static boolean save(CartDetail cartDetail) throws SQLException, ClassNotFoundException {
        String sql="INSERT INTO order_details VALUES(?,?,?,?,?)";

        return CrudUtil.execute(sql,cartDetail.getOrderId(),cartDetail.getCode(),cartDetail.getQty(),cartDetail.getUnitPrice(),cartDetail.getDate());

    }
}
