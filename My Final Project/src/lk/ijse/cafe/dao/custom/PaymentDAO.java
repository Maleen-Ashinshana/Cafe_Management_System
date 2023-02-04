package lk.ijse.cafe.dao.custom;

import lk.ijse.cafe.dao.util.CrudDAO;
import lk.ijse.cafe.entity.PaymentEntitys;
import lk.ijse.cafe.entity.PaymnetEntity;

public interface PaymentDAO extends CrudDAO<PaymentEntitys,String> {
    public String getNextId();
    public String generateNextId(String PrefId,String LsatId);
}
