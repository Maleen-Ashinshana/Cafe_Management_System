package lk.ijse.cafe.service.custom.impl;

import lk.ijse.cafe.dao.custom.StokeItemsDAO;
import lk.ijse.cafe.dao.exception.ConstraintViolationException;
import lk.ijse.cafe.dao.util.DAOFactory;
import lk.ijse.cafe.dao.util.DaoTypes;
import lk.ijse.cafe.db.DBConnection;
import lk.ijse.cafe.dto.StokeDetailsDTO;
import lk.ijse.cafe.dto.StokeItemsDTO;
import lk.ijse.cafe.entity.StokeItemsEntity;
import lk.ijse.cafe.service.custom.StokeItemService;
import lk.ijse.cafe.service.exception.DuplicateException;
import lk.ijse.cafe.service.exception.InUseException;
import lk.ijse.cafe.service.exception.NotFoundException;
import lk.ijse.cafe.service.util.Convertor;
import lk.ijse.cafe.to.StockItems;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class StokeItemServiceIMPL  implements StokeItemService {
    private final StokeItemsDAO stokeItemsDAO;
    private final Convertor convertor;
    private final Connection connection;

    public StokeItemServiceIMPL() throws SQLException, ClassNotFoundException {
        connection = DBConnection.getInstance().getConnection();
        stokeItemsDAO = DAOFactory.getInstance().getDAO(connection, DaoTypes.STOKEITEMS);
        convertor = new Convertor();
    }

    @Override
    public StokeItemsDTO saveStokeItem(StokeItemsDTO stokeItemsDTO) throws DuplicateException {
        if (stokeItemsDAO.existByPk(stokeItemsDTO.getId())) throw new DuplicateException("This Items Already Saved!");
        stokeItemsDAO.save(convertor.tostokeItems(stokeItemsDTO));
        return stokeItemsDTO;
    }

    @Override
    public StokeItemsDTO updateStokeItem(StokeItemsDTO stokeItemsDTO) throws NotFoundException {
        if (!stokeItemsDAO.existByPk(stokeItemsDTO.getId())) throw new NotFoundException("Stoke Item Mot Found");
        stokeItemsDAO.update(convertor.tostokeItems(stokeItemsDTO));

        return stokeItemsDTO;
    }

    @Override
    public void deleteStokeItem(String id) throws NotFoundException, InUseException {
        if (!stokeItemsDAO.existByPk(id)) throw new NotFoundException("StokeItems No Found");
        try {
            stokeItemsDAO.delete(id);
        } catch (ConstraintViolationException e) {
            throw new RuntimeException();
        }

    }

    @Override
    public List<StokeItemsDTO> findAllStokeItems() {
        return stokeItemsDAO.findAll().stream().map(stokeItem -> convertor.fromStokeItems(stokeItem)).collect(Collectors.toList());
    }

    @Override
    public StokeItemsDTO searchStokeItem(String text) {
        Optional<StokeItemsEntity> stokeItemsEntity = stokeItemsDAO.findByPk(text);
        if (!stokeItemsEntity.isPresent()) throw new NotFoundException("Stoke Not Found");

        return convertor.fromStokeItems(stokeItemsEntity.get());
    }

    @Override
    public boolean updateQty(ArrayList<StokeDetailsDTO> stokeDetailsDTOS) {
        for (StokeDetailsDTO stok : stokeDetailsDTOS) {
            return stokeItemsDAO.updateQty(new StokeItemsEntity(stok.getStoke_id(), stok.getStoke_item_id(), stok.getUnitPrice(), stok.getQty()));

        }
        return false;
    }
}
