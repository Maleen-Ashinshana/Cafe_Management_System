package lk.ijse.cafe.service.custom;

import lk.ijse.cafe.dto.EmployeDTO;
import lk.ijse.cafe.dto.StokeItemsDTO;
import lk.ijse.cafe.service.SuperSevice;
import lk.ijse.cafe.service.exception.DuplicateException;
import lk.ijse.cafe.service.exception.InUseException;
import lk.ijse.cafe.service.exception.NotFoundException;

import java.util.List;

public interface StokeItemService extends SuperSevice {
    public StokeItemsDTO saveStokeItem(StokeItemsDTO stokeItemsDTO)throws DuplicateException;

    public StokeItemsDTO updateStokeItem(StokeItemsDTO StokeItemsDTO)throws NotFoundException;

    public void deleteStokeItem(String id)throws NotFoundException, InUseException;

    public List<StokeItemsDTO> findAllStokeItems();

    public StokeItemsDTO searchStokeItem(String text);
}
