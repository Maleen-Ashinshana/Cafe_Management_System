package lk.ijse.cafe.dao.custom;

import lk.ijse.cafe.dao.exception.ConstraintViolationException;
import lk.ijse.cafe.dao.util.CrudDAO;
import lk.ijse.cafe.dao.util.SuperDAO;
import lk.ijse.cafe.entity.OrderEntity;
import lk.ijse.cafe.entity.SupperEntity;
import org.apache.poi.hssf.record.formula.functions.T;

import java.io.Serializable;

public interface OrderDAO extends CrudDAO<OrderEntity,String> {
    public String getNextId();
    public String generateNextId(String PrefId,String LsatId);

}
