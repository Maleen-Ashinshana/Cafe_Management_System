package lk.ijse.cafe.service.custom;

import lk.ijse.cafe.dto.OrderDetailsDTO;
import lk.ijse.cafe.service.SuperSevice;
import lk.ijse.cafe.service.exception.DuplicateException;

public interface OrderDetailsService extends SuperSevice {
    public OrderDetailsDTO saveOrderDetails(OrderDetailsDTO orderDetailsDTO)throws DuplicateException;
//    public static boolean Save(OrderDetailsDTO orderDetailsDTO);
}
