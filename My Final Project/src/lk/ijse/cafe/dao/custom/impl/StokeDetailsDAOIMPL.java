package lk.ijse.cafe.dao.custom.impl;

import lk.ijse.cafe.dao.custom.StokeDetailsDAO;
import lk.ijse.cafe.dao.exception.ConstraintViolationException;
import lk.ijse.cafe.entity.StokeDetailsEntity;
import lk.ijse.cafe.util.CrudUtil;

import java.sql.Connection;
import java.sql.SQLException;

public class StokeDetailsDAOIMPL implements StokeDetailsDAO {
private Connection connection;

    public StokeDetailsDAOIMPL(Connection connection) {
        this.connection = connection;
    }

    @Override
    public StokeDetailsEntity save(StokeDetailsEntity entity) throws ConstraintViolationException {
        try {
            System.out.println(entity.getStoke_id());
            System.out.println(entity.getStoke_item_id());
            System.out.println(entity.getUnitPrice());
            System.out.println(entity.getQty());

            if (CrudUtil.execute("INSERT INTO stoke_details(stoke_id,stoke_item_id,unitPrice,qty)VALUES(?,?,?,?)",
                    entity.getStoke_id(),entity.getStoke_item_id(),entity.getUnitPrice(),entity.getQty()));
            return entity;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    }

