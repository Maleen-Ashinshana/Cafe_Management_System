package lk.ijse.cafe.dao.custom.impl;

import lk.ijse.cafe.dao.custom.OrderDetailsDAO;
import lk.ijse.cafe.dao.exception.ConstraintViolationException;
import lk.ijse.cafe.entity.OrderDetailsEntity;
import lk.ijse.cafe.util.CrudUtil;

import java.sql.Connection;
import java.sql.SQLException;

public class OrderDetailsDAOIMPL implements OrderDetailsDAO {
private Connection connection;

    public OrderDetailsDAOIMPL(Connection connection) {

       this .connection = connection;
    }

    @Override
    public OrderDetailsEntity save(OrderDetailsEntity entity) throws ConstraintViolationException {
        try {
            if (CrudUtil.execute("INSERT INTO order_details(order_id,item_code,qty,unitPrice,date)VALUES(?,?,?,?,?)",
                    entity.getOrder_id(),entity.getItem_code(),entity.getQty(),entity.getUnitPrice(),entity.getDate()));
                return entity;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
