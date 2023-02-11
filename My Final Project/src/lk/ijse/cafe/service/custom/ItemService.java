package lk.ijse.cafe.service.custom;

import lk.ijse.cafe.dto.ItemDTO;
import lk.ijse.cafe.service.exception.DuplicateException;
import lk.ijse.cafe.service.exception.InUseException;
import lk.ijse.cafe.service.exception.NotFoundException;
import lk.ijse.cafe.service.SuperSevice;

import java.util.List;

public interface ItemService extends SuperSevice {
    public ItemDTO saveItem(ItemDTO itemDTO)throws DuplicateException;
    public ItemDTO updateItem(ItemDTO itemDTO)throws NotFoundException;
    //public  void deleteItem(String code)throws  NotFoundException, InUseException;
    public List<ItemDTO> findAll();
    public  ItemDTO findItemByCode(String code) throws  NotFoundException;
    //public List<ItemDTO> searchByText(String text);
    public ItemDTO searchItem(String text);
    public void delete(String code)throws NotFoundException;

}
