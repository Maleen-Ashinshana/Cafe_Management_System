package lk.ijse.cafe.service.custom.impl;

import lk.ijse.cafe.dao.custom.CashierDAO;
import lk.ijse.cafe.dao.custom.SystemUsesDAO;
import lk.ijse.cafe.dao.util.DAOFactory;
import lk.ijse.cafe.dao.util.DaoTypes;
import lk.ijse.cafe.db.DBConnection;
import lk.ijse.cafe.dto.SystemUsesDTO;
import lk.ijse.cafe.service.custom.SystemUsesService;
import lk.ijse.cafe.service.exception.DuplicateException;
import lk.ijse.cafe.service.util.Convertor;

import java.sql.Connection;
import java.sql.SQLException;

public class SystemServiceIMPL implements SystemUsesService {
    private  final SystemUsesDAO systemUsesDAO;
    private final Convertor convertor;
    private final Connection connection;

    public SystemServiceIMPL() throws SQLException, ClassNotFoundException {
        connection= DBConnection.getInstance().getConnection();
        systemUsesDAO= DAOFactory.getInstance().getDAO(connection, DaoTypes.SYSTEMUSERS);
        convertor=new Convertor();
    }
    @Override
    public SystemUsesDTO saveCashier(SystemUsesDTO systemUsesDTO) throws DuplicateException {
        systemUsesDAO.save(convertor.toSystemUses(systemUsesDTO));
        return systemUsesDTO;

    }
}
