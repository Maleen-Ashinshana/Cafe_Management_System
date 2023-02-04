package lk.ijse.cafe.dao.custom.impl;

import lk.ijse.cafe.dao.custom.StokeItemsDAO;
import lk.ijse.cafe.dao.exception.ConstraintViolationException;
import lk.ijse.cafe.entity.StokeItemsEntity;
import lk.ijse.cafe.util.CrudUtil;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class StokeItemsDAOIMPL implements StokeItemsDAO {
    private Connection connection;

    public StokeItemsDAOIMPL(Connection connection) {
        this.connection = connection;
    }

    @Override
    public StokeItemsEntity save(StokeItemsEntity entity) throws ConstraintViolationException {
        try {
            if (CrudUtil.execute("INSERT INTO stoke_items(id,description,unitPrice,qty)VALUES (?,?,?,?)",
                    entity.getId(),entity.getDescription(),entity.getUnitPrice(),entity.getQty()));
                  return entity;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public StokeItemsEntity update(StokeItemsEntity entity) throws ConstraintViolationException {
        try {
            if (CrudUtil.execute("UPDATE stoke_items SET description=?,unitPrice=?,qty WHERE id=?",
                    entity.getDescription(),entity.getUnitPrice(),entity.getQty(),entity.getId()));
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
            return CrudUtil.execute("Delete From stoke_items where id='"+id+"'");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public List<StokeItemsEntity> findAll() {
        try {
            ResultSet resultSet=CrudUtil.execute("SELECT * FROM stoke_items");
            return getStokeItemList(resultSet);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private List<StokeItemsEntity> getStokeItemList(ResultSet resultSet) {
        try {
            List<StokeItemsEntity> stokeItemList = new ArrayList<>();
            while (resultSet.next()){
                StokeItemsEntity stokeItemsEntity=new StokeItemsEntity(resultSet.getString("id"),resultSet.getString("description"),resultSet.getDouble("unitPrice"),resultSet.getInt("qty"));
                stokeItemList.add(stokeItemsEntity);
            }
            return stokeItemList;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    public Optional<StokeItemsEntity> findByPk(String id) {
        try{
            ResultSet rst = CrudUtil.execute("SELECT * FROM stoke_items WHERE id=?", id);
            if(rst.next()){
                return Optional.of(new StokeItemsEntity(rst.getString("id"),rst.getString("description"),rst.getDouble("unitPrice"),rst.getInt("qty")));
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
            ResultSet rst = CrudUtil.execute("SELECT * FROM stoke_items WHERE id=?", id);
            return rst.next();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public StokeItemsEntity search(StokeItemsEntity entity) throws ConstraintViolationException {
        try {
            ResultSet rst=CrudUtil.execute("SELECT * FROM stoke_items WHERE id=?");
            if (rst.next()){
                return  new StokeItemsEntity(rst.getString("id"),rst.getString("description"),rst.getDouble("unitPrice"),rst.getInt("qty"));
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
            ResultSet rst  = CrudUtil.execute("SELECT COUNT(id) AS count FROM stoke_items");
            rst.next();
            return rst.getInt(1);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public ArrayList<String> LoadStokeId() {

        try {
            ResultSet resultSet = CrudUtil.execute("SELECT FROM stoke_items");
            ArrayList<String> idList=new ArrayList<>();
            while (resultSet.next()){
                idList.add(resultSet.getString(1));
            }
            return  idList;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}

