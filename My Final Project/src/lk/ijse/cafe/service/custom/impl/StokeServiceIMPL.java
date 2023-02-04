package lk.ijse.cafe.service.custom.impl;

import lk.ijse.cafe.dao.custom.StokeDAO;
import lk.ijse.cafe.dao.util.DAOFactory;
import lk.ijse.cafe.dao.util.DaoTypes;
import lk.ijse.cafe.db.DBConnection;
import lk.ijse.cafe.dto.StokeDTO;
import lk.ijse.cafe.entity.StokeEntity;
import lk.ijse.cafe.service.custom.StokeService;
import lk.ijse.cafe.service.exception.NotFoundException;
import lk.ijse.cafe.service.util.Convertor;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Optional;

public class StokeServiceIMPL implements StokeService {
    private final StokeDAO stokeDAO;
    private final Convertor convertor;
    private final Connection connection;

    public StokeServiceIMPL() throws SQLException, ClassNotFoundException {
        connection= DBConnection.getInstance().getConnection();
        stokeDAO= DAOFactory.getInstance().getDAO(connection, DaoTypes.STOKE);
        convertor=new Convertor();
    }

    @Override
    public StokeDTO findById(String id) throws NotFoundException {
        Optional<StokeEntity> optional=stokeDAO.findByPk(id);
        if (!optional.isPresent())throw new NotFoundException("Stoke Not Found");

        return convertor.froStoke(optional.get());
    }
}
