package lk.ijse.cafe.dao.custom;

import lk.ijse.cafe.dao.util.CrudDAO;
import lk.ijse.cafe.entity.StokeItemsEntity;
import lk.ijse.cafe.to.StockItems;

import java.sql.SQLException;
import java.util.ArrayList;

public interface StokeItemsDAO extends CrudDAO<StokeItemsEntity,String> {
    public ArrayList<String> LoadStokeId();

    boolean updateQty(StokeItemsEntity stokeItemsEntity);
}
