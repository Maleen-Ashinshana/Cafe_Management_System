package lk.ijse.cafe.service.custom;

import lk.ijse.cafe.dto.OrderDetailsDTO;
import lk.ijse.cafe.service.SuperSevice;

import java.sql.SQLException;
import java.util.ArrayList;

public interface PlaceService extends SuperSevice {
    public static boolean placeOrder(String orderId, String date, String customerId, ArrayList<OrderDetailsDTO> orderDetailsDTOS) throws SQLException, ClassNotFoundException {
        return true;
    }
}
