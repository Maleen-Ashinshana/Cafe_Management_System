package lk.ijse.cafe.service.custom.impl;

import lk.ijse.cafe.dao.custom.CustomerDAO;
import lk.ijse.cafe.dao.exception.ConstraintViolationException;
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
import java.util.stream.Collectors;

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
    public CustomerDTO updateCustomer(CustomerDTO customerDTO) throws NotFoundException {
        if (!customerDAO.existByPk(customerDTO.getCustomer_id()))throw new NotFoundException("Customer Not Found");
        customerDAO.update(convertor.toCustomer(customerDTO));
        return customerDTO;
    }

    @Override
    public List<CustomerDTO> findAllCustomer() {
        return customerDAO.findAll().stream().map(customer->convertor.fromCustomer(customer)).collect(Collectors.toList());
    }

    @Override
    public void deleteCustomer(String id) throws NotFoundException, InUseException {
        if (!customerDAO.existByPk(id))throw  new NotFoundException("Customer Not Found");
        try {
            customerDAO.delete(id);
        }catch (ConstraintViolationException e){
            throw new RuntimeException();
        }

    }

    @Override
    public CustomerDTO findById(String id) throws NotFoundException {
        Optional<CustomerEntity> customerEntity=customerDAO.findByPk(id);
        if (!customerEntity.isPresent())throw new NotFoundException("Customer Not Found");

        return convertor.fromCustomer(customerEntity.get());
    }


}
