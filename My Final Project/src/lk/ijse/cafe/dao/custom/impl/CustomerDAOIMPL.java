package lk.ijse.cafe.dao.custom.impl;

import lk.ijse.cafe.dao.custom.CustomerDAO;
import lk.ijse.cafe.dao.exception.ConstraintViolationException;
import lk.ijse.cafe.entity.CustomerEntity;
import lk.ijse.cafe.entity.ItemEntity;
import lk.ijse.cafe.util.CrudUtil;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CustomerDAOIMPL implements CustomerDAO {
    private Connection connection;

    public CustomerDAOIMPL(Connection connection) {
        this.connection = connection;
    }

    @Override
    public CustomerEntity save(CustomerEntity entity) throws ConstraintViolationException {
        try {
            if (CrudUtil.execute("INSERT INTO customer(customer_id,name) VALUES(?,?)",
                   entity.getCustomer_id(),entity.getName() ));

            return entity;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public CustomerEntity update(CustomerEntity entity) throws ConstraintViolationException {
        try {
            if (CrudUtil.execute("UPDATE customer SET name=?  WHERE customer_id=?",
                    entity.getName(),entity.getCustomer_id()));
                    return entity;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public Object deleteByPk(String id) throws ConstraintViolationException, SQLException, ClassNotFoundException {
        //return CrudUtil.execute("Delete From items where item_code= '"+id+"'");
//        try {
//            if(!CrudUtil.execute("DELETE FROM customer WHERE customer_id=?",id)) throw new SQLException("Failed to delete the book");
//        } catch (SQLException e) {
//            throw new ConstraintViolationException(e);
//        }
        return CrudUtil.execute("Delete From customer where customer='"+id+"'");

    }

    @Override
    public List<CustomerEntity> findAll() {
        try {
            ResultSet rst=CrudUtil.execute("SELECT * FROM Book");
            return getCustomerList(rst);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Optional<CustomerEntity> findByPk(String id) {
        try {
            ResultSet rst = CrudUtil.execute("SELECT * FROM customer WHERE customer_id=?", id);
            if (rst.next()){
                return Optional.of(new CustomerEntity(rst.getString("customer_id"),rst.getString("name")));
            }
            return Optional.empty();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public boolean existByPk(String id) {
        try {
            ResultSet rst = CrudUtil.execute("SELECT * FROM customer WHERE customer_id=?", id);
            return rst.next();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public CustomerEntity search(CustomerEntity entity) throws ConstraintViolationException {
        try {
            ResultSet rst=CrudUtil.execute("SELECT * FROM customer WHERE customer_id =?");
            if (rst.next()){
               // return new ItemEntity(rst.getString(1),rst.getString(2),rst.getDouble(3));
                return new CustomerEntity(rst.getString("customer_id"),rst.getString("name"));
            }
            return null;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

//    @Override
//    public CustomerEntity search(CustomerEntity entity) throws ConstraintViolationException {
//        return null;
//    }

    @Override
    public long count() {
        try {
            ResultSet rst = CrudUtil.execute("SELECT COUNT(id) AS count FROM customer");
            return rst.getInt(1);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }
    private List<CustomerEntity> getCustomerList(ResultSet rst){
        try {
            List<CustomerEntity> list=new ArrayList<>();
            while (rst.next()){
                  CustomerEntity customerEntity=new CustomerEntity(rst.getString("customer_id"),rst.getString("name"));
                  list.add(customerEntity);
            }
            return list;
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }


    @Override
    public ArrayList<String> LoadCustomerId() {
        try {
            ResultSet resultSet = CrudUtil.execute("SELECT *FROM customer");
            ArrayList<String> codeList=new ArrayList<>();
            while (resultSet.next()){
                codeList.add(resultSet.getString(1));
            }
            return  codeList;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }
}
