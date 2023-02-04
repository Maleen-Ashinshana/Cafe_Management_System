package lk.ijse.cafe.service.custom;

import lk.ijse.cafe.dto.EmployeDTO;
import lk.ijse.cafe.dto.OrderDTO;
import lk.ijse.cafe.service.SuperSevice;
import lk.ijse.cafe.service.exception.DuplicateException;
import lk.ijse.cafe.service.exception.InUseException;
import lk.ijse.cafe.service.exception.NotFoundException;
import org.apache.hadoop.hive.metastore.api.Order;

import java.util.List;

public interface OrderService extends SuperSevice {
    public OrderDTO saveOrder(OrderDTO orderDTO)throws DuplicateException;

    public OrderDTO updateOrder(OrderDTO orderDTO)throws NotFoundException;

    public void deleteOrder(String order_id)throws NotFoundException, InUseException;

    public List<OrderDTO> findAllOrder();

    public OrderDTO searchOrder(String text);
}
