package lk.ijse.cafe.dao.util;

import lk.ijse.cafe.dao.exception.ConstraintViolationException;
import lk.ijse.cafe.entity.SupperEntity;
import lk.ijse.cafe.service.exception.NotFoundException;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface CrudDAO<T extends SupperEntity,ID extends Serializable>extends SuperDAO {
    T save(T entity) throws ConstraintViolationException;
    T update(T entity) throws ConstraintViolationException;
    //T deleteByPk(ID pk) throws ConstraintViolationException, SQLException, ClassNotFoundException;
    public void delete(String code)throws NotFoundException;
    List<T>findAll();
    Optional<T> findByPk(ID pk);
    boolean existByPk(ID pk);
    public  T search(T entity)throws ConstraintViolationException;
    long count();

}
