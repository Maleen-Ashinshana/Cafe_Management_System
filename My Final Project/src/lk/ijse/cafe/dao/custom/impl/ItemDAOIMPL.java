package lk.ijse.cafe.dao.custom.impl;

import lk.ijse.cafe.dao.custom.ItemDAO;
import lk.ijse.cafe.dao.exception.ConstraintViolationException;
import lk.ijse.cafe.entity.ItemEntity;
import lk.ijse.cafe.to.Item;
import lk.ijse.cafe.util.CrudUtil;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ItemDAOIMPL implements ItemDAO{
    private final Connection connection;

    public ItemDAOIMPL(Connection connection) {
        this.connection = connection;
    }


    @Override
    public List<ItemEntity> searchByText(String text) {
        try {
            text="%"+text+"%";
            ResultSet rst=CrudUtil.execute("SELECT * FROM items WHERE item_code LIKE ? OR description LIKE ? ");
            return  getItemList(rst);
        }catch (SQLException e){
            throw  new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
//        try {
//            ResultSet rst=CrudUtil.execute("SELECT * FROM items WHERE item_code=?");
//            if (rst.next()){
//                return (List<ItemEntity>) new ItemEntity(rst.getString(1),rst.getString(2),rst.getDouble(3));
//
//            }
//            return null;
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        } catch (ClassNotFoundException e) {
//            throw new RuntimeException(e);
//        }

    }

    @Override
    public ArrayList<String> LoadItemCode() {
        //String sql="SELECT item_code FROM items";
        //ResultSet resultSet= null;
        try {
            ResultSet resultSet = CrudUtil.execute("SELECT item_code FROM items");
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

    @Override
    public ItemEntity save(ItemEntity entity) throws ConstraintViolationException {
        try {
            if (CrudUtil.execute("INSERT INTO items(item_code,description,unit_price)VALUES (?,?,?)",
                    entity.getCode(),entity.getDescription(),entity.getUnit_price()));
             return entity;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new ConstraintViolationException(e);
        }
    }

    @Override
    public ItemEntity update(ItemEntity entity) throws ConstraintViolationException {
        try {
            if (CrudUtil.execute("UPDATE items SET description=? ,unit_price=? WHERE item_code=?",
                    entity.getDescription(), entity.getUnit_price(), entity.getCode())) {
                return entity;
            }
            throw new SQLException("FAIL");
        }catch (SQLException e){
            throw new ConstraintViolationException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

//        return  CrudUtil.execute("Update items set description=?,unit_price=? where item_code=?",
//                entity.getDescription(),
//                entity.getUnit_price(),
//                entity.getCode()
//        );
    }

    @Override
    public ItemEntity deleteByPk(String code) throws ConstraintViolationException, SQLException, ClassNotFoundException {
//        try {
//            if (!CrudUtil.execute("DELETE FROM items WHERE code=?",pk)) throw new SQLException("FAIl");
//        } catch (SQLException e) {
//            throw new ConstraintViolationException(e);
//        } catch (ClassNotFoundException e) {
//            throw new RuntimeException(e);
//        }
        return CrudUtil.execute("Delete From items where item_code= '"+code+"'");

//        try {
//            if(!CrudUtil.execute("DELETE FROM Book WHERE isbn=?",code)) throw new SQLException("Failed to delete the book");
//        } catch (SQLException | ClassNotFoundException e) {
//            throw new ConstraintViolationException(e);
//        }

    }

    @Override
    public List<ItemEntity> findAll() {
        try {
            ResultSet rst=CrudUtil.execute("SELECT * from items");
            return  getItemList(rst);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Optional<ItemEntity> findByPk(String pk) {
        try {
            ResultSet rst=CrudUtil.execute("SELECT * FROM items WHERE item_code=?",pk);
            if (rst.next()){
                return Optional.of(new ItemEntity(rst.getString("item_code"),rst.getString("description"),rst.getInt("unit_price")));

            }
            return Optional.empty();
        }catch (SQLException e){
            throw  new RuntimeException("Faild");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean existByPk(String pk) {
        try {
            ResultSet rst = CrudUtil.execute("SELECT * FROM items WHERE item_code=?", pk);
            return rst.next();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }

   // @Override
//    public ItemEntity search(ItemEntity entity) throws ConstraintViolationException {
//        return null;
//    }

    @Override
    public ItemEntity search(ItemEntity entity) throws ConstraintViolationException {
        try {
            ResultSet rst=CrudUtil.execute("SELECT * FROM items WHERE item_code=?");
            if (rst.next()){
                return new ItemEntity(rst.getString(1),rst.getString(2),rst.getDouble(3));

            }
            return null;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

//        String sql="SELECT  * FROM items WHERE item_code = ?";
//        ResultSet result= CrudUtil.execute(sql,code);
//        if (result.next()){
//            return  new Item(
//                    result.getString(1),
//                    result.getString(2),
//                    result.getDouble(3)
//            );
//        }
//        return  null;
    }

    @Override
    public long count() {
        try {
            ResultSet rst  = CrudUtil.execute("SELECT COUNT(item_code) AS count FROM items");
            rst.next();
            return rst.getInt(1);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    private  List<ItemEntity> getItemList(ResultSet rst){
        try {
            List<ItemEntity> itemList=new ArrayList<>();
            while (rst.next()){
                ItemEntity itemEntity=new ItemEntity(rst.getString("item_Code"),rst.getString("description"),rst.getInt("unit_price"));
                itemList.add(itemEntity);

            }
            return  itemList;
        }catch (SQLException e){
            throw  new RuntimeException(e);
        }

    }
}
