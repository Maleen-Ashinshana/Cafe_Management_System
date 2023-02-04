package lk.ijse.cafe.service.custom.impl;

import lk.ijse.cafe.dao.custom.CustomerDAO;
import lk.ijse.cafe.dao.util.DAOFactory;
import lk.ijse.cafe.dao.util.DaoTypes;
import lk.ijse.cafe.db.DBConnection;
import lk.ijse.cafe.dto.CustomerDTO;
import lk.ijse.cafe.entity.CustomerEntity;
import lk.ijse.cafe.service.custom.CustomerService;
import lk.ijse.cafe.service.exception.DuplicateException;
import lk.ijse.cafe.service.exception.InUseException;
import lk.ijse.cafe.service.exception.NotFoundException;
import lk.ijse.cafe.service.util.Convertor;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class CustomerServiceIMPL implements CustomerService {
    private final CustomerDAO customerDAO;
    private final Convertor convertor;
    private final Connection connection;

    public CustomerServiceIMPL() throws SQLException, ClassNotFoundException {
        connection= DBConnection.getInstance().getConnection();
        customerDAO= DAOFactory.getInstance().getDAO(connection, DaoTypes.CUSTOMER);
        convertor=new Convertor();
    }

    @Override
    public CustomerDTO saveCustomer(CustomerDTO customerDTO) throws DuplicateException {
        if (customerDAO.existByPk(customerDTO.getCustomer_id())) throw new DuplicateException("Customer Already Saved!");
        customerDAO.save(convertor.toCustomer(customerDTO));
        return customerDTO;
    }

    @Override
    public CustomerDTO updateEmploye(CustomerDTO customerDTO) throws NotFoundException {
        return null;
    }

    @Override
    public void deleteEmploye(String id) throws NotFoundException, InUseException {

    }

    @Override
    public CustomerDTO findById(String id) throws NotFoundException {
        Optional<CustomerEntity> customerEntity=customerDAO.findByPk(id);
        if (!customerEntity.isPresent())throw new NotFoundException("Customer Not Found");

        return convertor.fromCustomer(customerEntity.get());
    }


}
