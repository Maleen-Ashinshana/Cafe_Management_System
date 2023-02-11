package lk.ijse.cafe.service.custom.impl;

import lk.ijse.cafe.dao.custom.OrderDAO;
import lk.ijse.cafe.dao.custom.OrderDetailsDAO;
import lk.ijse.cafe.dao.exception.ConstraintViolationException;
import lk.ijse.cafe.dao.util.DAOFactory;
import lk.ijse.cafe.dao.util.DaoTypes;
import lk.ijse.cafe.db.DBConnection;
import lk.ijse.cafe.dto.OrderDTO;
import lk.ijse.cafe.dto.OrderDetailsDTO;
import lk.ijse.cafe.entity.OrderDetailsEntity;
import lk.ijse.cafe.entity.OrderEntity;

import lk.ijse.cafe.service.custom.OrderService;
import lk.ijse.cafe.service.exception.DuplicateException;
import lk.ijse.cafe.service.exception.InUseException;
import lk.ijse.cafe.service.exception.NotFoundException;
import lk.ijse.cafe.service.util.Convertor;
import lk.ijse.cafe.dto.CartDetailDTO;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class OrderServiceIMPL implements OrderService {
    private final OrderDAO orderDAO;
    private  final Convertor convertor;
    private final Connection  connection;
    private OrderDetailsDAO orderDetailDao;

    public OrderServiceIMPL() throws SQLException, ClassNotFoundException {
        connection= DBConnection.getInstance().getConnection();
        orderDAO= DAOFactory.getInstance().getDAO(connection, DaoTypes.ORDER);
        orderDetailDao= DAOFactory.getInstance().getDAO(connection, DaoTypes.ORDERDETAILS);
        convertor=new Convertor();
    }

    @Override
    public OrderDTO saveOrder(OrderDTO orderDTO) throws DuplicateException {
       if (orderDAO.existByPk(orderDTO.getOrder_id()))throw  new DuplicateException("Order Already Saved!");
       orderDAO.save(convertor.toOrder(orderDTO));
        return orderDTO;
//        try {
//            connection.setAutoCommit(false);
//           // List<OrderDTO> list=orderDAO.findByPk(orderDTO.getOrder_id().)
//
//        }
    }

    @Override
    public OrderDTO updateOrder(OrderDTO orderDTO) throws NotFoundException {
        return null;
    }

    @Override
    public void deleteOrder(String order_id) throws NotFoundException, InUseException {
        if (!orderDAO.existByPk(order_id))throw new NotFoundException("Order Not Found");
        try {
            orderDAO.delete(order_id);
        }catch (ConstraintViolationException e){
            throw  new RuntimeException();
        }

    }

    @Override
    public List<OrderDTO> findAllOrder() {
        return orderDAO.findAll().stream().map(order->convertor.fromOrder(order)).collect(Collectors.toList());
    }

    @Override
    public OrderDTO searchOrder(String text) {
        Optional<OrderEntity> orderEntity=orderDAO.findByPk(text);
        if (!orderEntity.isPresent())throw new NotFoundException("Order Not Found");

        return convertor.fromOrder(orderEntity.get());
    }
    @Override
    public  boolean placeOrder(String orderId, String date, String customerId, ArrayList<OrderDetailsDTO> orderDetailsDTOS) throws SQLException, ClassNotFoundException {
        for (OrderDetailsDTO orderDetailsDTO : orderDetailsDTOS) {
            System.out.println(orderDetailsDTO.getItem_code());
        }
        try {
            DBConnection.getInstance().getConnection().setAutoCommit(false);
            //boolean isOrderAdded = OrderModel.save(new Order(orderId,date,customerId));


            if (orderDAO.save(new OrderEntity(orderId, date, customerId))!=null) {
                for (OrderDetailsEntity orderDetailsEntity : orderDetailsDTOS.stream().map(cartDetailDTO -> convertor.toOrderDetails(cartDetailDTO)).collect(Collectors.toList())){
                    orderDetailsEntity.setOrder_id(orderId);
                    System.out.println(orderDetailsEntity.getItem_code());
                    if (orderDetailDao.save(orderDetailsEntity)==null) {
                        DBConnection.getInstance().getConnection().rollback();
                        DBConnection.getInstance().getConnection().setAutoCommit(true);
                        return false;
                    }

                }
                DBConnection.getInstance().getConnection().commit();
                DBConnection.getInstance().getConnection().setAutoCommit(true);
                return true;

            }

            DBConnection.getInstance().getConnection().rollback();
            DBConnection.getInstance().getConnection().setAutoCommit(true);
            return false;

        }finally {
            DBConnection.getInstance().getConnection().setAutoCommit(true);
        }
    }

    @Override
    public String getNextOrderId() {
        return null;
    }
}
