package lk.ijse.cafe.dao.custom.impl;

import lk.ijse.cafe.dao.custom.SystemUsesDAO;
import lk.ijse.cafe.dao.exception.ConstraintViolationException;
import lk.ijse.cafe.entity.SystemUsesEntity;
import lk.ijse.cafe.util.CrudUtil;

import java.sql.Connection;
import java.sql.SQLException;

public class SystemUsesDAOIMPL implements SystemUsesDAO {
    private final Connection connection;
    public SystemUsesDAOIMPL(Connection connection) {
        this.connection = connection;
    }
    @Override
    public SystemUsesEntity save(SystemUsesEntity entity) throws ConstraintViolationException {
        try {
            if (CrudUtil.execute("INSERT INTO system_uses(system_user_id, name, address, contact_number,type,email,password) VALUES (?,?,?,?,?,?,?)",
                    entity.getSystem_user_id(),entity.getName(),entity.getAddress(),entity.getContact_number(),entity.getType(),entity.getEmail(),entity.getPassword()));
            return entity;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }
}

