package lk.ijse.cafe.service.custom.impl;

import lk.ijse.cafe.dao.custom.OrderDAO;
import lk.ijse.cafe.dao.util.DAOFactory;
import lk.ijse.cafe.dao.util.DaoTypes;
import lk.ijse.cafe.db.DBConnection;
import lk.ijse.cafe.dto.OrderDTO;
import lk.ijse.cafe.entity.OrderEntity;
import lk.ijse.cafe.service.custom.OrderService;
import lk.ijse.cafe.service.exception.DuplicateException;
import lk.ijse.cafe.service.exception.InUseException;
import lk.ijse.cafe.service.exception.NotFoundException;
import lk.ijse.cafe.service.util.Convertor;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class OrderServiceIMPL implements OrderService {
    private final OrderDAO orderDAO;
    private  final Convertor convertor;
    private final Connection  connection;

    public OrderServiceIMPL() throws SQLException, ClassNotFoundException {
        connection= DBConnection.getInstance().getConnection();
        orderDAO= DAOFactory.getInstance().getDAO(connection, DaoTypes.ORDER);
        convertor=new Convertor();
    }

    @Override
    public OrderDTO saveOrder(OrderDTO orderDTO) throws DuplicateException {
       if (orderDAO.existByPk(orderDTO.getOrder_id()))throw  new DuplicateException("Order Already Saved!");
       orderDAO.save(convertor.toOrder(orderDTO));
        return orderDTO;
    }

    @Override
    public OrderDTO updateOrder(OrderDTO orderDTO) throws NotFoundException {
        return null;
    }

    @Override
    public void deleteOrder(String order_id) throws NotFoundException, InUseException {

    }

    @Override
    public List<OrderDTO> findAllOrder() {
        return null;
    }

    @Override
    public OrderDTO searchOrder(String text) {
        Optional<OrderEntity> orderEntity=orderDAO.findByPk(text);
        if (!orderEntity.isPresent())throw new NotFoundException("Order Not Found");

        return convertor.fromOrder(orderEntity.get());
    }
}
