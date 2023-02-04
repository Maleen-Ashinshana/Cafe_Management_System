package lk.ijse.cafe.dao.custom;

import lk.ijse.cafe.dao.util.CrudDAO;
import lk.ijse.cafe.entity.CustomerEntity;

import java.util.ArrayList;

public interface CustomerDAO extends CrudDAO<CustomerEntity,String> {
    public ArrayList<String> LoadCustomerId();
}
