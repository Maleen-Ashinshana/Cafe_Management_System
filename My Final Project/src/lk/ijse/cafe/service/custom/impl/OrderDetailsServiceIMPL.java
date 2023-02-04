package lk.ijse.cafe.service.custom.impl;

import lk.ijse.cafe.dao.custom.OrderDetailsDAO;
import lk.ijse.cafe.dao.util.DAOFactory;
import lk.ijse.cafe.dao.util.DaoTypes;
import lk.ijse.cafe.db.DBConnection;
import lk.ijse.cafe.dto.OrderDetailsDTO;
import lk.ijse.cafe.service.custom.OrderDetailsService;
import lk.ijse.cafe.service.exception.DuplicateException;
import lk.ijse.cafe.service.util.Convertor;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

public class OrderDetailsServiceIMPL implements OrderDetailsService {
    private final OrderDetailsDAO  orderDetailsDAO;
    private final Convertor convertor;
    private final Connection connection;

    public OrderDetailsServiceIMPL() throws SQLException, ClassNotFoundException {
        connection= DBConnection.getInstance().getConnection();
        orderDetailsDAO= DAOFactory.getInstance().getDAO(connection, DaoTypes.ORDERDETAILS);
        convertor=new Convertor();
    }

    @Override
    public OrderDetailsDTO saveOrderDetails(OrderDetailsDTO orderDetailsDTO) throws DuplicateException {
         orderDetailsDAO.save(convertor.toOrderDetails(orderDetailsDTO));

        return orderDetailsDTO;
    }
    public ArrayList<OrderDetailsDTO> SaveOrderDetails(ArrayList<OrderDetailsDTO> orderDetailsDTOS){
        for (OrderDetailsDTO orderDetailsDTO:orderDetailsDTOS) {
        }
        return orderDetailsDTOS;
    }
//    public static  boolean save(OrderDetailsDTO  orderDetailsDTO){
//
//    }
}
