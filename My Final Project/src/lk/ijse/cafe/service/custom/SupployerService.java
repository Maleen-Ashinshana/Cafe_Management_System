package lk.ijse.cafe.service.custom;

import lk.ijse.cafe.dto.ItemDTO;
import lk.ijse.cafe.dto.SupployerDTO;
import lk.ijse.cafe.service.SuperSevice;
import lk.ijse.cafe.service.exception.DuplicateException;
import lk.ijse.cafe.service.exception.NotFoundException;

import java.util.ArrayList;
import java.util.List;

public interface SupployerService extends SuperSevice {
    public SupployerDTO saveSupployer(SupployerDTO supployerDTO)throws DuplicateException;
    public SupployerDTO searchSypployer(String id)throws NotFoundException;
    public List<SupployerDTO> findAll();
    public  SupployerDTO findByid(String id) throws  NotFoundException;
    public SupployerDTO updateSupployer(SupployerDTO supployerDTO)throws NotFoundException;
    public void delete(String code)throws NotFoundException;

    ArrayList<String> loadSupplerId();
}
