package lk.ijse.cafe.service.custom.impl;

import lk.ijse.cafe.dao.custom.StokeDetailsDAO;
import lk.ijse.cafe.dao.util.DAOFactory;
import lk.ijse.cafe.dao.util.DaoTypes;
import lk.ijse.cafe.db.DBConnection;
import lk.ijse.cafe.dto.StokeDetailsDTO;
import lk.ijse.cafe.service.custom.StokeDetailsService;
import lk.ijse.cafe.service.util.Convertor;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

public class StokeDetailsServiceIMPL implements StokeDetailsService {
    private final StokeDetailsDAO stokeDetailsDAO;
    private final Convertor convertor;
    private final Connection connection;

    public StokeDetailsServiceIMPL() throws SQLException, ClassNotFoundException {
        connection= DBConnection.getInstance().getConnection();
        stokeDetailsDAO= DAOFactory.getInstance().getDAO(connection,DaoTypes.STOKEDETAILS);
        convertor=new Convertor();
    }
@Override
    public  ArrayList<StokeDetailsDTO> saveStockDetails(ArrayList<StokeDetailsDTO> stockCartDeteils) throws SQLException, ClassNotFoundException {
        for (StokeDetailsDTO cartDeteils:stockCartDeteils){
            stokeDetailsDAO.save(convertor.stokeDetails(cartDeteils));
//            if (!save(cartDeteils)){
//                return false;
//            }
        }
        return stockCartDeteils;


    }
}
