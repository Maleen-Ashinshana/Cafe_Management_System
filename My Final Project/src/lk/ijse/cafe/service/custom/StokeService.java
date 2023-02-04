package lk.ijse.cafe.service.custom;

import lk.ijse.cafe.dto.StokeDTO;
import lk.ijse.cafe.service.SuperSevice;
import lk.ijse.cafe.service.exception.NotFoundException;

public interface StokeService extends SuperSevice {
    public StokeDTO findById(String id)throws NotFoundException;

}
