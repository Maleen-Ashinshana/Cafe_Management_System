package lk.ijse.cafe.service.custom.impl;

import com.mongodb.DB;
import lk.ijse.cafe.dao.custom.OrderDAO;
import lk.ijse.cafe.dao.custom.OrderDetailsDAO;
import lk.ijse.cafe.dao.util.DAOFactory;
import lk.ijse.cafe.dao.util.DaoTypes;
import lk.ijse.cafe.db.DBConnection;
import lk.ijse.cafe.dto.OrderDetailsDTO;
import lk.ijse.cafe.entity.OrderDetailsEntity;
import lk.ijse.cafe.entity.OrderEntity;
import lk.ijse.cafe.service.custom.OrderDetailsService;
import lk.ijse.cafe.service.custom.OrderService;
import lk.ijse.cafe.service.exception.DuplicateException;
import lk.ijse.cafe.service.util.Convertor;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

public class OrderDetailsServiceIMPL implements OrderDetailsService {
    private final OrderDetailsDAO orderDetailsDAO;
    private final Convertor convertor;
    private final  Connection connection;

    public OrderDetailsServiceIMPL() throws SQLException, ClassNotFoundException {
        connection = DBConnection.getInstance().getConnection();
        orderDetailsDAO = DAOFactory.getInstance().getDAO(connection, DaoTypes.ORDERDETAILS);
        convertor = new Convertor();
    }

//    @Override
//    public OrderDetailsDTO saveOrderDetails(OrderDetailsDTO orderDetailsDTO) throws DuplicateException {
////         orderDetailsDAO.save(convertor.toOrderDetails(orderDetailsDTO));
////
////        return orderDetailsDTO;
//        for (OrderDetailsDTO orderDetailsDTO1 :
//        ) {
//
//        }
//    }

    @Override
    public ArrayList<OrderDetailsDTO> save(ArrayList<OrderDetailsDTO> orderDetailsDTOS) throws DuplicateException {
        for (OrderDetailsDTO orderDetailsDTO : orderDetailsDTOS) {
            orderDetailsDAO.save(convertor.toOrderDetails(orderDetailsDTO));
        }
        return orderDetailsDTOS;
    }
//    public static  boolean save(OrderDetailsDTO  orderDetailsDTO){
//
//    }
}