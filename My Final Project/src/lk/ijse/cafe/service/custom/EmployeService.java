package lk.ijse.cafe.service.custom;

import lk.ijse.cafe.dto.EmployeDTO;
import lk.ijse.cafe.entity.EmployeEntity;
import lk.ijse.cafe.service.SuperSevice;
import lk.ijse.cafe.service.exception.DuplicateException;
import lk.ijse.cafe.service.exception.InUseException;
import lk.ijse.cafe.service.exception.NotFoundException;

import java.util.List;

public interface EmployeService extends SuperSevice {
    public EmployeDTO saveEmploye(EmployeDTO employeDTO)throws DuplicateException;

    public EmployeDTO updateEmploye(EmployeDTO employeDTO)throws NotFoundException;

    public void deleteEmploye(String id)throws NotFoundException, InUseException;

    public List<EmployeDTO> findAllEmploye();

    public EmployeDTO findById(String id)throws NotFoundException;

}
