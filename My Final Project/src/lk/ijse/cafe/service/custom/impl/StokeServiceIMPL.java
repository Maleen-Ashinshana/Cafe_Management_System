package lk.ijse.cafe.service.custom.impl;

import lk.ijse.cafe.dao.custom.StokeDAO;
import lk.ijse.cafe.dao.custom.StokeDetailsDAO;
import lk.ijse.cafe.dao.exception.ConstraintViolationException;
import lk.ijse.cafe.dao.util.DAOFactory;
import lk.ijse.cafe.dao.util.DaoTypes;
import lk.ijse.cafe.db.DBConnection;
import lk.ijse.cafe.dto.StokeDTO;
import lk.ijse.cafe.dto.StokeDetailsDTO;
import lk.ijse.cafe.entity.StokeEntity;

import lk.ijse.cafe.service.ServiceFactory;
import lk.ijse.cafe.service.ServiceTypes;
import lk.ijse.cafe.service.custom.StokeItemService;
import lk.ijse.cafe.service.custom.StokeService;
import lk.ijse.cafe.service.exception.DuplicateException;
import lk.ijse.cafe.service.exception.NotFoundException;
import lk.ijse.cafe.service.util.Convertor;
import lk.ijse.cafe.to.Stock;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class StokeServiceIMPL implements StokeService {
    private final StokeDAO stokeDAO;
    private final Convertor convertor;
    private final Connection connection;
    private StokeItemService stokeItemService;
    private StokeDetailsDAO stokeDetailDAO;


    public StokeServiceIMPL() throws SQLException, ClassNotFoundException {
        connection= DBConnection.getInstance().getConnection();
        stokeDAO= DAOFactory.getInstance().getDAO(connection, DaoTypes.STOKE);
        stokeDetailDAO= DAOFactory.getInstance().getDAO(connection, DaoTypes.STOKEDETAILS);
        stokeItemService= ServiceFactory.getInstance().getService(ServiceTypes.STOKEITEMS);
        convertor=new Convertor();
    }

    @Override
    public StokeDTO saveStoke(StokeDTO stokeDTO) throws DuplicateException {
        if (stokeDAO.existByPk(stokeDTO.getStoke_id()))throw new DuplicateException("Stoke Already save");
        stokeDAO.save(convertor.toStoke(stokeDTO));
        return stokeDTO;
    }

    @Override
    public StokeDTO findById(String id) throws NotFoundException {
        Optional<StokeEntity> optional=stokeDAO.findByPk(id);
        if (!optional.isPresent())throw new NotFoundException("Stoke Not Found");

        return convertor.froStoke(optional.get());
    }

    @Override
    public StokeDTO searchStoke(String text) {
        Optional<StokeEntity> stokeEntity=stokeDAO.findByPk(text);
        if (!stokeEntity.isPresent())throw new NotFoundException("Stoke No Found");

        return convertor.froStoke(stokeEntity.get());
    }

    @Override
    public void deleteStoke(String stoke_id) throws NotFoundException {
        if (!stokeDAO.existByPk(stoke_id))throw  new NotFoundException("Stoke not Found");
        try {
            stokeDAO.delete(stoke_id);
        }catch (ConstraintViolationException e){
            throw new RuntimeException();
        }

    }

    @Override
    public List<StokeDTO> findAllStoke() {
        return stokeDAO.findAll().stream().map(stokeEntity -> convertor.froStoke(stokeEntity)).collect(Collectors.toList());
    }

    @Override
    public StokeDTO updateStoke(StokeDTO stokeDTO) throws NotFoundException {
        if (!stokeDAO.existByPk(stokeDTO.getStoke_id()))throw new NotFoundException("Stoke Not Found");
        stokeDAO.update(convertor.toStoke(stokeDTO));
        return stokeDTO;
    }
    @Override
    public  boolean placeStock(StokeDTO stokeDTO, ArrayList<StokeDetailsDTO> stokeDetailsDTOS) throws SQLException, ClassNotFoundException {
        try {
            DBConnection.getInstance().getConnection().setAutoCommit(false);

            // boolean isStockAdded=StokeModel.save(new Stock();
            //boolean isStockAdded= stokeDAO.save(new StokeEntity(stokeDTO.getStoke_id(),String.valueOf(stokeDTO.getDate()),stokeDTO.getSupplyer_id())==null);
            System.out.println(stokeDTO.getStoke_id()+"123456789");
            if (stokeDAO.save(new StokeEntity(stokeDTO.getStoke_id(),String.valueOf(stokeDTO.getDate()),stokeDTO.getSupplyer_id()))==null){
                DBConnection.getInstance().getConnection().rollback();
                return false;

            }else {
                boolean isUpdated = stokeItemService.updateQty(stokeDetailsDTOS);
                System.out.println(isUpdated+"*********");

                if (isUpdated) {
                    for (StokeDetailsDTO stokeDetailsDTO : stokeDetailsDTOS) {
                        if (stokeDetailDAO.save(convertor.stokeDetails(stokeDetailsDTO)) == null) {
                            System.out.println(stokeDetailDAO.save(convertor.stokeDetails(stokeDetailsDTO)) != null);
                            DBConnection.getInstance().getConnection().rollback();
                            return false;
                        }
                    }
                    DBConnection.getInstance().getConnection().commit();
                    return true;
                }

                DBConnection.getInstance().getConnection().rollback();
                return false;
            }
        }finally {
            DBConnection.getInstance().getConnection().setAutoCommit(true);
        }

    }
}
