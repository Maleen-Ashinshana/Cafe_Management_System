package lk.ijse.cafe.service.custom;

import lk.ijse.cafe.dto.SupployerDTO;
import lk.ijse.cafe.service.SuperSevice;
import lk.ijse.cafe.service.exception.DuplicateException;
import lk.ijse.cafe.service.exception.NotFoundException;

public interface SupployerService extends SuperSevice {
    public SupployerDTO saveSupployer(SupployerDTO supployerDTO)throws DuplicateException;
    public SupployerDTO searchSypployer(String id)throws NotFoundException;

}
