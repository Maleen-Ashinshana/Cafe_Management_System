package lk.ijse.cafe.dao.custom.impl;

import lk.ijse.cafe.dao.custom.OrderDAO;
import lk.ijse.cafe.dao.exception.ConstraintViolationException;
import lk.ijse.cafe.entity.CustomerEntity;
import lk.ijse.cafe.entity.ItemEntity;
import lk.ijse.cafe.entity.OrderEntity;
import lk.ijse.cafe.service.exception.NotFoundException;
import lk.ijse.cafe.util.CrudUtil;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class OrderDAOIMPL implements OrderDAO {
    private Connection connection;

    public OrderDAOIMPL(Connection connection) {
        this.connection = connection;
    }

    @Override
    public OrderEntity save(OrderEntity entity) throws ConstraintViolationException {
        try {
            if (CrudUtil.execute("INSERT INTO orders(order_id,date,customerId)VALUES (?,?,?)",
                    entity.getOrder_id(),entity.getDate(),entity.getCustomer_id()));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return entity;
    }

    @Override
    public OrderEntity update(OrderEntity entity) throws ConstraintViolationException {
        try {
            if (CrudUtil.execute("UPDATE order SET date=?,customer_id=? WHERE order_id=?",
                    entity.getDate(),entity.getCustomer_id(),entity.getOrder_id()));
            return entity;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void delete(String code) throws NotFoundException {
        try {
            CrudUtil.execute("Delete From order where order_id= '"+code+"'");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }

//    @Override
//    public OrderEntity deleteByPk(String id) throws ConstraintViolationException {
////        try {
////            if (!CrudUtil.execute("DELETE FROM order WHERE order_id",id));
////            throw new RuntimeException("Fail");
////        } catch (SQLException e) {
////            throw new RuntimeException(e);
////        } catch (ClassNotFoundException e) {
////            throw new RuntimeException(e);
////        }
//        try {
//            return CrudUtil.execute("Delete From order where order_id='"+id+"'");
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        } catch (ClassNotFoundException e) {
//            throw new RuntimeException(e);
//        }
//
//    }

    @Override
    public List<OrderEntity> findAll() {

        try {
            ResultSet resultSet=CrudUtil.execute("SELECT * FROM order");
            return getOrderList(resultSet);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public Optional<OrderEntity> findByPk(String pk) {
        try{
            ResultSet rst = CrudUtil.execute("SELECT * FROM order WHERE order_id=?", pk);
            if(rst.next()){
               // return Optional.of(new Book(rst.getString("isbn"), rst.getString("title"), rst.getString("author"), rst.getInt("qty")));
              return Optional.of(new OrderEntity(rst.getString("order-id,"),rst.getString("date"),rst.getString("customer_id")));
            }
            return Optional.empty();
        } catch (SQLException e) {
            throw new RuntimeException("Failed to find the book details");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public boolean existByPk(String pk) {
        try {
            ResultSet rst = CrudUtil.execute("SELECT * FROM order WHERE order_id=?", pk);
            return rst.next();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public OrderEntity search(OrderEntity entity) throws ConstraintViolationException {
        try {
            ResultSet rst=CrudUtil.execute("SELECT * FROM order WHERE order_id=?");
            if (rst.next()){
               // return new ItemEntity(rst.getString(1),rst.getString(2),rst.getDouble(3));
                return new OrderEntity(rst.getString("order_id"),rst.getString("date"),rst.getString("customer_id"));

            }
            return null;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public long count() {
        try {
            ResultSet rst  = CrudUtil.execute("SELECT COUNT(order_id) AS count FROM order");
            rst.next();
            return rst.getInt(1);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }


    @Override
    public String getNextId() {
        try {

            ResultSet result=CrudUtil.execute("SELECT order_id FROM orders ORDER BY order_id DESC LIMIT 1");
            if (result.next()){
                return generateNextId("O",result.getString(1));

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return generateNextId("O",null);

    }

    @Override
    public String generateNextId(String PrefId,String LsatId) {
        if (LsatId!=null){
            int newId=Integer.parseInt(LsatId.replace(PrefId,""))+1;
            return String.format(PrefId+"%03d",newId);

        }else {
            return PrefId+000;
        }


    }
    private List<OrderEntity> getOrderList(ResultSet resultSet){
        try {
            List<OrderEntity> orderList = new ArrayList<>();
            while (resultSet.next()){
                OrderEntity orderEntity=new OrderEntity(resultSet.getString("order_id"),resultSet.getString("date"),resultSet.getString("customer_id"));
                orderList.add(orderEntity);
            }
            return orderList;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
