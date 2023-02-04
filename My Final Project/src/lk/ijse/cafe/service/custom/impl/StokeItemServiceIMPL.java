package lk.ijse.cafe.service.custom.impl;

import lk.ijse.cafe.dao.custom.StokeItemsDAO;
import lk.ijse.cafe.dao.util.DAOFactory;
import lk.ijse.cafe.dao.util.DaoTypes;
import lk.ijse.cafe.db.DBConnection;
import lk.ijse.cafe.dto.StokeItemsDTO;
import lk.ijse.cafe.entity.StokeItemsEntity;
import lk.ijse.cafe.service.custom.StokeItemService;
import lk.ijse.cafe.service.exception.DuplicateException;
import lk.ijse.cafe.service.exception.InUseException;
import lk.ijse.cafe.service.exception.NotFoundException;
import lk.ijse.cafe.service.util.Convertor;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class StokeItemServiceIMPL  implements StokeItemService {
    private final StokeItemsDAO stokeItemsDAO;
    private final Convertor convertor;
    private final Connection connection;

    public StokeItemServiceIMPL() throws SQLException, ClassNotFoundException {
        connection= DBConnection.getInstance().getConnection();
        stokeItemsDAO= DAOFactory.getInstance().getDAO(connection, DaoTypes.STOKEITEMS);
        convertor=new Convertor();
    }

    @Override
    public StokeItemsDTO saveStokeItem(StokeItemsDTO stokeItemsDTO) throws DuplicateException {
        if (stokeItemsDAO.existByPk(stokeItemsDTO.getId())) throw new DuplicateException("This Items Already Saved!");
        stokeItemsDAO.save(convertor.tostokeItems(stokeItemsDTO));
        return stokeItemsDTO;
    }

    @Override
    public StokeItemsDTO updateStokeItem(StokeItemsDTO StokeItemsDTO) throws NotFoundException {
        return null;
    }

    @Override
    public void deleteStokeItem(String id) throws NotFoundException, InUseException {

    }

    @Override
    public List<StokeItemsDTO> findAllStokeItems() {
        return null;
    }

    @Override
    public StokeItemsDTO searchStokeItem(String text) {
        Optional<StokeItemsEntity> stokeItemsEntity=stokeItemsDAO.findByPk(text);
        if (!stokeItemsEntity.isPresent())throw new NotFoundException("Stoke Not Found");

        return convertor.fromStokeItems(stokeItemsEntity.get());
    }
}
