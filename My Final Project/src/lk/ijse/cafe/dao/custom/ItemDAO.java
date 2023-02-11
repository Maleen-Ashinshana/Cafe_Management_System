package lk.ijse.cafe.dao.custom;

import lk.ijse.cafe.dao.util.CrudDAO;
import lk.ijse.cafe.entity.ItemEntity;
import lk.ijse.cafe.service.exception.NotFoundException;
import lk.ijse.cafe.util.CrudUtil;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface ItemDAO extends CrudDAO<ItemEntity,String> {
    public List<ItemEntity> searchByText(String text);
    public  ArrayList<String> LoadItemCode();
    public void deleteItem(String code)throws NotFoundException;
}
