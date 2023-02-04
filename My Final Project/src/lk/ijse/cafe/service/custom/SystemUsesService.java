package lk.ijse.cafe.service.custom;

import lk.ijse.cafe.dto.SystemUsesDTO;
import lk.ijse.cafe.service.SuperSevice;
import lk.ijse.cafe.service.exception.DuplicateException;

public interface SystemUsesService extends SuperSevice {
    public SystemUsesDTO saveCashier(SystemUsesDTO systemUsesDTO)throws DuplicateException;
}
