package lk.ijse.cafe.service.custom;

import lk.ijse.cafe.dto.ItemDTO;
import lk.ijse.cafe.dto.StokeDTO;
import lk.ijse.cafe.dto.StokeDetailsDTO;
import lk.ijse.cafe.service.SuperSevice;
import lk.ijse.cafe.service.exception.DuplicateException;
import lk.ijse.cafe.service.exception.NotFoundException;
import lk.ijse.cafe.to.PlaceStock;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface StokeService extends SuperSevice {
    public StokeDTO saveStoke(StokeDTO stokeDTO)throws DuplicateException;
    public StokeDTO findById(String id)throws NotFoundException;
    public StokeDTO searchStoke(String text);
    public void deleteStoke(String stoke_id)throws NotFoundException;
    public List<StokeDTO>findAllStoke();
    public StokeDTO updateStoke(StokeDTO stokeDTO)throws NotFoundException;

    //boolean placeStock(PlaceStock placeStock, ArrayList<StokeDetailsDTO> stokeDetailsDTOS) throws SQLException, ClassNotFoundException;

    boolean placeStock(StokeDTO stokeDTO, ArrayList<StokeDetailsDTO> stokeDetailsDTOS) throws SQLException, ClassNotFoundException;
}
