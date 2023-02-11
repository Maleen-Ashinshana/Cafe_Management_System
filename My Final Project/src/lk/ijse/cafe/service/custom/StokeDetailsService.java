package lk.ijse.cafe.service.custom;

import lk.ijse.cafe.dto.StokeDetailsDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface StokeDetailsService {
    ArrayList<StokeDetailsDTO> saveStockDetails(ArrayList<StokeDetailsDTO> stockCartDeteils) throws SQLException, ClassNotFoundException;
}
