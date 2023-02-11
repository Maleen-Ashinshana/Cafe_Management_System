package lk.ijse.cafe.dao.custom;

import lk.ijse.cafe.dao.util.CrudDAO;
import lk.ijse.cafe.entity.SupployerEntity;

import java.sql.SQLException;
import java.util.ArrayList;

public interface SupployerDAO extends CrudDAO<SupployerEntity,String> {
    ArrayList<String> loadSupplyerId() throws SQLException, ClassNotFoundException;
}
