package lk.ijse.cafe.dao.custom;

import lk.ijse.cafe.dao.util.CrudDAO;
import lk.ijse.cafe.entity.StokeEntity;

public interface StokeDAO extends CrudDAO<StokeEntity,String> {
    public String getNextId();
    public String generateNextId(String PrefId,String LsatId);
}
