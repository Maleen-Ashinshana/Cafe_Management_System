package lk.ijse.cafe.service.custom;

import lk.ijse.cafe.dto.CustomerDTO;
import lk.ijse.cafe.dto.EmployeDTO;
import lk.ijse.cafe.service.SuperSevice;
import lk.ijse.cafe.service.exception.DuplicateException;
import lk.ijse.cafe.service.exception.InUseException;
import lk.ijse.cafe.service.exception.NotFoundException;

import java.util.List;

public interface CustomerService extends SuperSevice {
    public CustomerDTO saveCustomer(CustomerDTO customerDTO)throws DuplicateException;

    public CustomerDTO updateEmploye(CustomerDTO customerDTO)throws NotFoundException;

    public void deleteEmploye(String id)throws NotFoundException, InUseException;
    public CustomerDTO findById(String id)throws NotFoundException;

}
