package lk.ijse.cafe.service.custom.impl;

import lk.ijse.cafe.dao.custom.ItemDAO;
import lk.ijse.cafe.dao.util.DAOFactory;
import lk.ijse.cafe.dao.util.DaoTypes;
import lk.ijse.cafe.db.DBConnection;
import lk.ijse.cafe.dto.ItemDTO;
import lk.ijse.cafe.entity.ItemEntity;
import lk.ijse.cafe.service.custom.ItemService;
import lk.ijse.cafe.service.exception.DuplicateException;
import lk.ijse.cafe.service.exception.InUseException;
import lk.ijse.cafe.service.exception.NotFoundException;
import lk.ijse.cafe.service.util.Convertor;
import net.sf.cglib.core.Converter;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class ItemServiceIMPL implements ItemService {
private  final ItemDAO itemDAO;
//private  final Converter converter;
    private  final Convertor convertor;
private final Connection connection;

    public ItemServiceIMPL() throws SQLException, ClassNotFoundException {
        connection= DBConnection.getInstance().getConnection();
        itemDAO= DAOFactory.getInstance().getDAO(connection, DaoTypes.ITEM);
        convertor=new Convertor();
    }

    @Override
    public ItemDTO saveItem(ItemDTO itemDTO) throws DuplicateException {
        if (itemDAO.existByPk(itemDTO.getCode())) throw new DuplicateException("Item already saved");
        itemDAO.save(convertor.toItem(itemDTO));

        return itemDTO;
    }

    @Override
    public ItemDTO updateItem(ItemDTO itemDTO) throws NotFoundException {
        if (!itemDAO.existByPk(itemDTO.getCode()))throw  new NotFoundException("Item Not Found");
        itemDAO.update(convertor.toItem(itemDTO));
        return itemDTO;
    }

    @Override
    public void deleteItem(String code) throws NotFoundException, InUseException {

    }

    @Override
    public List<ItemDTO> findAll() {
        return null;
    }

    @Override
    public ItemDTO findItemByCode(String code) throws NotFoundException {
        Optional<ItemEntity> optionalItem=itemDAO.findByPk(code);
        if (!optionalItem.isPresent())throw  new NotFoundException("Item Not Found");
        return convertor.fromItem(optionalItem.get());

    }

    @Override
    public List<ItemDTO> searchByText(String text) {
        return null;
    }

    @Override
    public ItemDTO searchItem(String text) {
        return null;
    }
}
