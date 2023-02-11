package lk.ijse.cafe.dao.custom.impl;

import lk.ijse.cafe.dao.custom.StokeDAO;
import lk.ijse.cafe.dao.exception.ConstraintViolationException;
import lk.ijse.cafe.entity.OrderEntity;
import lk.ijse.cafe.entity.StokeEntity;
import lk.ijse.cafe.service.exception.NotFoundException;
import lk.ijse.cafe.util.CrudUtil;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class StokeDAOIMPL implements StokeDAO {
    private Connection connection;

    public StokeDAOIMPL(Connection connection) {

        this.connection = connection;
    }

    @Override
    public StokeEntity save(StokeEntity entity) throws ConstraintViolationException {
        try {
            if (CrudUtil.execute("INSERT INTO stok(stok_id,date,supplyer_id)VALUES (?,?,?)",
                    entity.getStoke_id(),entity.getDate(),entity.getSupplyer_id())){
                System.out.println(entity==null);

            }
            return entity;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
//return null;
    }

    @Override
    public StokeEntity update(StokeEntity entity) throws ConstraintViolationException {
        try {
            if (CrudUtil.execute("UPDATE stok SET date=?,supplyer_id=? WHERE stok_id=?",
                    entity.getDate(),entity.getSupplyer_id(),entity.getStoke_id()));
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
            CrudUtil.execute("Delete From stok where stok_id= '"+code+"'");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

//    @Override
//    public StokeEntity deleteByPk(String id) throws ConstraintViolationException, SQLException, ClassNotFoundException {
//        try {
//            return CrudUtil.execute("Delete From stoke where estoke_id='"+id+"'");
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        } catch (ClassNotFoundException e) {
//            throw new RuntimeException(e);
//        }
//
//    }

    @Override
    public List<StokeEntity> findAll() {
        try {
            ResultSet resultSet=CrudUtil.execute("SELECT * FROM stok");
            return getStokeList(resultSet);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }

    private List<StokeEntity> getStokeList(ResultSet resultSet) {
        try {
            List<StokeEntity> stokeList = new ArrayList<>();
            while (resultSet.next()){
                //OrderEntity orderEntity=new OrderEntity(resultSet.getString("order_id"),resultSet.getDate("date"),resultSet.getString("customer_id"));
                StokeEntity stokeEntity=new StokeEntity(resultSet.getString("stok_id"),resultSet.getString("date"),resultSet.getString("supplyer_id"));
                stokeList.add(stokeEntity);
            }
            return stokeList;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    public Optional<StokeEntity> findByPk(String id) {
        try{
            ResultSet rst = CrudUtil.execute("SELECT * FROM stok WHERE stok_id=?", id);
            if(rst.next()){
                return  Optional.of(new StokeEntity(rst.getString("stok_id"),rst.getString("date"),rst.getString("supplyer_id")));
            }
            return Optional.empty();
        } catch (SQLException e) {
            throw new RuntimeException("Failed to find the book details");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public boolean existByPk(String id) {
        try {
            ResultSet rst = CrudUtil.execute("SELECT * FROM stoke WHERE stok_id=?", id);
            return rst.next();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public StokeEntity search(StokeEntity entity) throws ConstraintViolationException {
        try {
            ResultSet rst=CrudUtil.execute("SELECT * FROM stok WHERE stok?_id=?");
            if (rst.next()){
                return new StokeEntity(rst.getString("stok_id"),rst.getString("date"),rst.getString("supplyer_id"));
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
            ResultSet rst  = CrudUtil.execute("SELECT COUNT(stok_id) AS count FROM stok");
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

            ResultSet result=CrudUtil.execute("SELECT stok_id FROM stok ORDER BY stok_id DESC LIMIT 1");
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
}
