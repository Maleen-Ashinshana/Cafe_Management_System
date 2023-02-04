package lk.ijse.cafe.dao.util;

import lk.ijse.cafe.dao.exception.ConstraintViolationException;
import lk.ijse.cafe.entity.SupperEntity;

import java.io.Serializable;

public interface SaveDAO<T extends SupperEntity,ID extends Serializable>extends SuperDAO {

    T save(T entity)throws ConstraintViolationException;
}
