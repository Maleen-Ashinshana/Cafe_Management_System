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
import org.springframework.core.ConstantException;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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

    @Override
    public List<SupployerDTO> findAll() {
        return supployerDAO.findAll().stream().map(supployer->convertor.fromSupployer(supployer)).collect(Collectors.toList());

    }

    @Override
    public SupployerDTO findByid(String id) throws NotFoundException {
        Optional<SupployerEntity> optional=supployerDAO.findByPk(id);
        if (!optional.isPresent())throw  new NotFoundException("Supployer Not Found");

        return convertor.fromSupployer(optional.get());
    }

    @Override
    public SupployerDTO updateSupployer(SupployerDTO supployerDTO) throws NotFoundException {
        if (!supployerDAO.existByPk(supployerDTO.getSupplyer_id()))throw new NotFoundException("Suployer not found");
        supployerDAO.update(convertor.toSupployer(supployerDTO));
        return supployerDTO;
    }

    @Override
    public void delete(String code) throws NotFoundException {
        if (!supployerDAO.existByPk(code)) throw new NotFoundException("Supploye Not Found");
        try {
            supployerDAO.delete(code);
        }catch (ConstantException e){
            throw new RuntimeException();
        }

    }
    @Override
    public ArrayList<String> loadSupplerId(){
        ArrayList<String> optional=new ArrayList<>();
        optional.add(String.valueOf(1));

        return optional;
    }
}
