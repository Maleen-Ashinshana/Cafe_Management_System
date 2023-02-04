package lk.ijse.cafe.service.custom.impl;

import lk.ijse.cafe.dao.custom.SupployerDAO;
import lk.ijse.cafe.dao.util.DAOFactory;
import lk.ijse.cafe.dao.util.DaoTypes;
import lk.ijse.cafe.db.DBConnection;
import lk.ijse.cafe.dto.SupployerDTO;
import lk.ijse.cafe.entity.SupployerEntity;
import lk.ijse.cafe.service.custom.SupployerService;
import lk.ijse.cafe.service.exception.DuplicateException;
import lk.ijse.cafe.service.exception.NotFoundException;
import lk.ijse.cafe.service.util.Convertor;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Optional;

public class SupployerServiceIMPL implements SupployerService {
    private final SupployerDAO supployerDAO;
    private final Convertor convertor;
    private Connection connection;

    public SupployerServiceIMPL() throws SQLException, ClassNotFoundException {
        connection= DBConnection.getInstance().getConnection();
        supployerDAO= DAOFactory.getInstance().getDAO(connection, DaoTypes.SUPPLOYER);
        convertor=new Convertor();
    }

    @Override
    public SupployerDTO saveSupployer(SupployerDTO supployerDTO) throws DuplicateException {
        if (supployerDAO.existByPk(supployerDTO.getSupplyer_id())) throw  new DuplicateException("Supployer Already Saved");
        supployerDAO.save(convertor.toSupployer(supployerDTO));
        return supployerDTO;
    }

    @Override
    public SupployerDTO searchSypployer(String id) throws NotFoundException {
        Optional<SupployerEntity> supployerEntity=supployerDAO.findByPk(id);
        if (!supployerEntity.isPresent())throw new NotFoundException("Suplloyer Not found");

        return convertor.fromSupployer(supployerEntity.get());
    }
}
