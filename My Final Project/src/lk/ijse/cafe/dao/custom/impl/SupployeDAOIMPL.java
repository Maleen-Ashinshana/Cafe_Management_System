package lk.ijse.cafe.dao.custom.impl;

import lk.ijse.cafe.dao.custom.SupployerDAO;
import lk.ijse.cafe.dao.exception.ConstraintViolationException;
import lk.ijse.cafe.entity.OrderEntity;
import lk.ijse.cafe.entity.SupployerEntity;
import lk.ijse.cafe.util.CrudUtil;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class SupployeDAOIMPL implements SupployerDAO {
    private Connection connection;

    public SupployeDAOIMPL(Connection connection) {
        this.connection = connection;
    }

    @Override
    public SupployerEntity save(SupployerEntity entity) throws ConstraintViolationException {
        try {
            if (CrudUtil.execute("INSERT INTO supplyer(supplyer_id,name,address)VALUES (?,?,?)",
                    entity.getSupplyer_id(),entity.getName(),entity.getAddress()));
            return entity;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public SupployerEntity update(SupployerEntity entity) throws ConstraintViolationException {
        try {
            if (CrudUtil.execute("UPDATE order SET name=?,address=? WHERE supplyer_id=?",
                    entity.getName(),entity.getAddress(),entity.getSupplyer_id()));
            return entity;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public Object deleteByPk(String id) throws ConstraintViolationException, SQLException, ClassNotFoundException {
        try {
            return CrudUtil.execute("Delete From supplyer where supplyer_id='"+id+"'");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public List<SupployerEntity> findAll() {
        try {
            ResultSet resultSet=CrudUtil.execute("SELECT * FROM supplyer");
            return getSupployarList(resultSet);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }


    }

    private List<SupployerEntity> getSupployarList(ResultSet resultSet) {
        try {
            List<SupployerEntity> supployarList = new ArrayList<>();
            while (resultSet.next()){
                SupployerEntity supployerEntity=new SupployerEntity(resultSet.getString("supplyer_id"),resultSet.getString("name"),resultSet.getString("address"));
                supployarList.add(supployerEntity);
            }
            return supployarList;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Optional<SupployerEntity> findByPk(String id) {
        try{
            ResultSet rst = CrudUtil.execute("SELECT * FROM supplyer WHERE supplyer_id=?",id);
            if(rst.next()){
                 return Optional.of(new SupployerEntity(rst.getString("supplyer_id"),rst.getString("name"),rst.getString("address")));
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
            ResultSet rst = CrudUtil.execute("SELECT * FROM supplyer WHERE supplyer_id=?", id);
            return rst.next();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public SupployerEntity search(SupployerEntity entity) throws ConstraintViolationException {
        try {
            ResultSet rst=CrudUtil.execute("SELECT * FROM supplyer WHERE supplyer_id=?");
            if (rst.next()){
                 return new SupployerEntity(rst.getString("supplyer_id"),rst.getString("name"),rst.getString("address"));
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
            ResultSet rst  = CrudUtil.execute("SELECT COUNT(supplyer_id) AS count FROM supplyer");
            rst.next();
            return rst.getInt(1);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
