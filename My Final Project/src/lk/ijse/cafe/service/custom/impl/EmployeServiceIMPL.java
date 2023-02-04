package lk.ijse.cafe.service.custom.impl;

import lk.ijse.cafe.dao.custom.EmployeDAO;
import lk.ijse.cafe.dao.util.DAOFactory;
import lk.ijse.cafe.dao.util.DaoTypes;
import lk.ijse.cafe.db.DBConnection;
import lk.ijse.cafe.dto.EmployeDTO;
import lk.ijse.cafe.entity.EmployeEntity;
import lk.ijse.cafe.service.custom.EmployeService;
import lk.ijse.cafe.service.exception.DuplicateException;
import lk.ijse.cafe.service.exception.InUseException;
import lk.ijse.cafe.service.exception.NotFoundException;
import lk.ijse.cafe.service.util.Convertor;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class EmployeServiceIMPL implements EmployeService {
    private final EmployeDAO employeDAO;
    private final Convertor convertor;
    private final Connection connection;

    public EmployeServiceIMPL() throws SQLException, ClassNotFoundException {
        connection= DBConnection.getInstance().getConnection();
        employeDAO= DAOFactory.getInstance().getDAO(connection, DaoTypes.EMPLOYE);
        convertor=new Convertor();
    }

    @Override
    public EmployeDTO saveEmploye(EmployeDTO employeDTO) throws DuplicateException {
        if (employeDAO.existByPk(employeDTO.getEmploye_id()))throw new DuplicateException("Employe Already Saved");
        employeDAO.save(convertor.toEmploy(employeDTO));

        return employeDTO;
    }

    @Override
    public EmployeDTO updateEmploye(EmployeDTO employeDTO) throws NotFoundException {
        if (!employeDAO.existByPk(employeDTO.getEmploye_id())) throw  new NotFoundException("Employe Not Found!");
        employeDAO.update(convertor.toEmploy(employeDTO));
        return employeDTO;
    }

    @Override
    public void deleteEmploye(String id) throws NotFoundException, InUseException {
        if (!employeDAO.existByPk(id))throw  new NotFoundException("Employe Not Found!");
        try {
            employeDAO.deleteByPk(id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public List<EmployeDTO> findAllEmploye() {
        return employeDAO.findAll().stream().map(employeEntity -> convertor.fromEmploy(employeEntity)).collect(Collectors.toList());
    }

    @Override
    public EmployeDTO findById(String id) throws NotFoundException {
        Optional<EmployeEntity> optionalEmploye=employeDAO.findByPk(id);
        if (!optionalEmploye.isPresent())throw new NotFoundException("Employe Not Found!");

        return convertor.fromEmploy(optionalEmploye.get());
    }


}
