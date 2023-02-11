package lk.ijse.cafe.dao.custom.impl;

import lk.ijse.cafe.dao.custom.PaymentDAO;
import lk.ijse.cafe.dao.exception.ConstraintViolationException;
import lk.ijse.cafe.entity.OrderEntity;
import lk.ijse.cafe.entity.PaymentEntitys;
import lk.ijse.cafe.entity.PaymnetEntity;
import lk.ijse.cafe.service.exception.NotFoundException;
import lk.ijse.cafe.util.CrudUtil;

import javax.print.DocFlavor;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class PaymentDAOIMPL implements PaymentDAO {
    private Connection connection;

    public PaymentDAOIMPL(Connection connection) {
        this.connection = connection;
    }

    @Override
    public String getNextId() {
        try {
            ResultSet result=CrudUtil.execute("SELECT id FROM payment ORDER BY id DESC LIMIT 1");
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
    public String generateNextId(String PrefId, String LsatId) {

        if (LsatId!=null){
            int newId=Integer.parseInt(LsatId.replace(PrefId,""))+1;
            return String.format(PrefId+"%03d",newId);

        }else {
            return PrefId+000;
        }
    }

    @Override
    public PaymentEntitys save(PaymentEntitys entity) throws ConstraintViolationException {
        try {
            if (CrudUtil.execute("INSERT INTO payment(id,date,price,order_id,customer_id,customer_name) VALUES (?,?,?,?,?,?)",
                   entity.getId(),entity.getDate(),entity.getPrice(),entity.getOrder_id(),entity.getCustomer_id(),entity.getCustomer_name() ));
            return entity;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public PaymentEntitys update(PaymentEntitys entity) throws ConstraintViolationException {
        try {
            if (CrudUtil.execute("UPDATE payment SET date=?,price=?,order_id=?,customer_id=?,customer_name=? WHERE id=?",
                    entity.getDate(),entity.getPrice(),entity.getOrder_id(),entity.getCustomer_id(),entity.getCustomer_name(),entity.getId())){
                return entity;
            }
            throw new SQLException("Fail");
        }catch (SQLException e){
            throw new ConstraintViolationException( e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void delete(String code) throws NotFoundException {
        try {
            CrudUtil.execute("Delete From payment where id= '"+code+"'");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

//    @Override
//    public PaymentEntitys deleteByPk(String pk) throws ConstraintViolationException, SQLException, ClassNotFoundException {
//        return null;
//    }

    @Override
    public List<PaymentEntitys> findAll() {
        try {
            ResultSet rst=CrudUtil.execute("SELECT * FROM payment");
            return getPaymentList(rst);
        }catch (SQLException e){
            throw  new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }
    private List<PaymentEntitys> getPaymentList(ResultSet rst){
        try {
            List<PaymentEntitys> paymnetEntityList=new ArrayList<>();
            while (rst.next()){
                PaymentEntitys paymentEntitys=new PaymentEntitys(rst.getString("id"),rst.getString("date"),rst.getDouble("price"),rst.getString("order_id"),rst.getString("customer_id"),rst.getString("customer_name"));
                paymnetEntityList.add(paymentEntitys);
            }
            return paymnetEntityList;
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public Optional<PaymentEntitys> findByPk(String pk) {
        try{
            ResultSet rst=CrudUtil.execute("SELECT * FROM payment");
            if (rst.next()){
                return Optional.of(new PaymentEntitys(rst.getString("id"),rst.getString("date"),rst.getDouble("price"),rst.getString("order_id"),rst.getString("customer_id"),rst.getString("customer_name")));
            }
            return Optional.empty();
        }catch (SQLException e){
            throw new RuntimeException("Fail");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean existByPk(String pk) {
       try {
           ResultSet rst=CrudUtil.execute("SELECT * FROM payment WHERE id=?",pk);
           return rst.next();
       }catch (SQLException e){
           throw new RuntimeException(e);
       } catch (ClassNotFoundException e) {
           throw new RuntimeException(e);
       }
    }

    @Override
    public PaymentEntitys search(PaymentEntitys entity) throws ConstraintViolationException {
        try {
            ResultSet resultSet=CrudUtil.execute("SELECT * FROM payment WHERE id=?");
            if (resultSet.next()){
                return new PaymentEntitys(resultSet.getString(1),resultSet.getString(2),resultSet.getDouble(3),resultSet.getString(4),resultSet.getString(5),resultSet.getString(6));
            }
            return null;
        }catch (SQLException e){
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public long count() {
        try {
            ResultSet rst = CrudUtil.execute("SELECT COUNT(id) AS count FROM payment");
            rst.next();
            return rst.getInt(1);
        }catch (SQLException e){
            throw  new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
//
//    @Override
//    public PaymnetEntity save(PaymnetEntity entity) throws ConstraintViolationException {
////        try {
////            if (CrudUtil.execute("INSERT INTO payment VALUES (?,?,?,?,?,?)",
////                    entity.getId(),entity.getDate(),entity.getPrice(),entity.getOrder_id(),entity.getCustomer_id(),entity.getCustomer_name()));
////            return entity;
////        } catch (SQLException e) {
////            throw new RuntimeException(e);
////        } catch (ClassNotFoundException e) {
////            throw new RuntimeException(e);
////        }
//        try {
//            if (CrudUtil.execute("INSERT INTO payment(id,date,price,order_id,customer_id,customer_name) VALUES (?,?,?,?,?,?)",
//                    //entity.getId(),entity.getDate(),entity.getPrice(),entity.getOrder_id(),entity.getCustomer_id(),entity.getCustomer_name()));
//                entity.getId(),entity.getDate(),entity.getPrice(),entity.getOrder_id(),entity.getCustomer_id(),entity.getCustomer_name()));
//                    return entity;
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        } catch (ClassNotFoundException e) {
//            throw new RuntimeException(e);
//        }
//    }
//
//    @Override
//    public PaymnetEntity update(PaymnetEntity entity) throws ConstraintViolationException {
//        try {
//            if (CrudUtil.execute("UPDATE payment SET date=?,price=?,order_id,customer_id=?,customer_name WHERE id=?",
//                    entity.getDate(),entity.getPrice(),entity.getOrder_id(),entity.getCustomer_id(),entity.getCustomer_name(),entity.getId()));
//            return entity;
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        } catch (ClassNotFoundException e) {
//            throw new RuntimeException(e);
//        }
//
//    }
//
//    @Override
//    public Object deleteByPk(String id) throws ConstraintViolationException, SQLException, ClassNotFoundException {
//        try {
//            return CrudUtil.execute("Delete From payment where payment='"+id+"'");
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        } catch (ClassNotFoundException e) {
//            throw new RuntimeException(e);
//        }
//
//    }
//
//    @Override
//    public List<PaymnetEntity> findAll() {
//        try {
//            ResultSet resultSet=CrudUtil.execute("SELECT * FROM payment");
//            return getPaymentList(resultSet);
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        } catch (ClassNotFoundException e) {
//            throw new RuntimeException(e);
//        }
//
//    }
//
//
//    @Override
//    public Optional<PaymnetEntity> findByPk(String id) {
//        try{
//            ResultSet rst = CrudUtil.execute("SELECT * FROM payment WHERE id=?", id);
//            if(rst.next()){
//                return Optional.of(new PaymnetEntity(rst.getString("id"),rst.getDate("date"),rst.getDouble("price"),rst.getString("order_id"),rst.getString("customer_id"),rst.getString("customer_name")));
//            }
//            return Optional.empty();
//        } catch (SQLException e) {
//            throw new RuntimeException("Failed to find the book details");
//        } catch (ClassNotFoundException e) {
//            throw new RuntimeException(e);
//        }
//
//    }
//
//    @Override
//    public boolean existByPk(String id) {
//        try {
//            ResultSet rst = CrudUtil.execute("SELECT * FROM payment WHERE id=?", id);
//            return rst.next();
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        } catch (ClassNotFoundException e) {
//            throw new RuntimeException(e);
//        }
//
//    }
//
//    @Override
//    public PaymnetEntity search(PaymnetEntity entity) throws ConstraintViolationException {
//        try {
//            ResultSet rst=CrudUtil.execute("SELECT * FROM payment WHERE id=?");
//            if (rst.next()){
//                return new PaymnetEntity(rst.getString("id"),rst.getDate("date"),rst.getDouble("price"),rst.getString("order_id"),rst.getString("customer_id"),rst.getString("customer_name"));
//            }
//            return null;
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        } catch (ClassNotFoundException e) {
//            throw new RuntimeException(e);
//        }
//
//    }
//
//    @Override
//    public long count() {
//        try {
//            ResultSet rst  = CrudUtil.execute("SELECT COUNT(id) AS count FROM payment");
//            rst.next();
//            return rst.getInt(1);
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        } catch (ClassNotFoundException e) {
//            throw new RuntimeException(e);
//        }
//
//    }
//
//    @Override
//    public String generateNextId(String PrefId,String LsatId) {
//        if (LsatId!=null){
//            int newId=Integer.parseInt(LsatId.replace(PrefId,""))+1;
//            return String.format(PrefId+"%03d",newId);
//
//        }else {
//            return PrefId+000;
//        }
//
//    }
//
//    @Override
//    public String getNextId() {
//        try {
//
//            ResultSet result=CrudUtil.execute("SELECT id FROM payment ORDER BY id DESC LIMIT 1");
//            if (result.next()){
//                return generateNextId("O",result.getString(1));
//
//            }
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        } catch (ClassNotFoundException e) {
//            throw new RuntimeException(e);
//        }
//        return generateNextId("O",null);
//
//    }
//
//    private List<PaymnetEntity> getPaymentList(ResultSet resultSet) {
//        try {
//            List<PaymnetEntity> paymentList = new ArrayList<>();
//            while (resultSet.next()){
//                PaymnetEntity paymnetEntity=new PaymnetEntity(resultSet.getString("id"),resultSet.getDate("date"),resultSet.getDouble("price"),resultSet.getString("order_id"),resultSet.getString("customer_id"),resultSet.getString("customer_name"));
//                paymentList.add(paymnetEntity);
//            }
//            return paymentList;
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//    }
    }

