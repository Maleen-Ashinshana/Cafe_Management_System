package lk.ijse.cafe.service.custom.impl;

import lk.ijse.cafe.dao.custom.ItemDAO;
import lk.ijse.cafe.dao.custom.OrderDAO;
import lk.ijse.cafe.dao.util.DAOFactory;
import lk.ijse.cafe.dao.util.DaoTypes;
import lk.ijse.cafe.db.DBConnection;
import lk.ijse.cafe.dto.OrderDetailsDTO;
import lk.ijse.cafe.service.custom.PlaceService;
import lk.ijse.cafe.service.util.Convertor;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

public class PalceServiceIMPL implements PlaceService {
    private  final OrderDAO orderDAO;
    //private  final Converter converter;
    private  final Convertor convertor;
    private final Connection connection;

    public PalceServiceIMPL() throws SQLException, ClassNotFoundException {
        connection=DBConnection.getInstance().getConnection();
        orderDAO=DAOFactory.getInstance().getDAO(connection,DaoTypes.ORDER);
        convertor=new Convertor();
    }

    //    public Pl throws SQLException, ClassNotFoundException {
//        connection= DBConnection.getInstance().getConnection();
//        itemDAO= DAOFactory.getInstance().getDAO(connection, DaoTypes.ITEM);
//        convertor=new Convertor();
//    }
    public static boolean placeOrder(String orderId, String date, String customerId, ArrayList<OrderDetailsDTO> orderDetailsDTOS) throws SQLException, ClassNotFoundException {
//        try {
//            DBConnection.getInstance().getConnection().setAutoCommit(false);
//            boolean isOrderAdded=o
//        }
        return true;
    }
}
