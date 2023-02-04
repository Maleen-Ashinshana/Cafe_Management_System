package lk.ijse.cafe.dao.util;

import lk.ijse.cafe.dao.custom.impl.*;

import java.sql.Connection;

public class DAOFactory {
    private static DAOFactory daoFactory;

    public DAOFactory() {
    }
    public static DAOFactory getInstance(){
        return  daoFactory==null?(daoFactory=new DAOFactory()):daoFactory;
    }

   public <T extends  SuperDAO> T getDAO(Connection connection,DaoTypes daoTypes){
        switch (daoTypes){
            case ITEM :
                return (T) new ItemDAOIMPL(connection);
            case EMPLOYE:
                return (T) new EmployeDAOIMPL(connection);
            case CUSTOMER:
                return (T) new CustomerDAOIMPL(connection);
            case STOKEDETAILS:
                return (T) new StokeDetailsDAOIMPL(connection);
            case ORDERDETAILS:
                return (T) new OrderDetailsDAOIMPL(connection);
            case STOKEITEMS:
                return (T) new StokeItemsDAOIMPL(connection);
            case PAYMENT:
                return (T) new PaymentDAOIMPL(connection);
//            case MANAGER:
//                return (T) new ManagerDAOIMPL(connection);
            case STOKE:
                return (T) new StokeDAOIMPL(connection);
            case ORDER:
                return (T) new OrderDAOIMPL(connection);
            case SUPPLOYER:
                return (T) new SupployeDAOIMPL(connection);

            case SYSTEMUSERS:
                return (T) new SystemUsesDAOIMPL(connection);
            default:
                return null;
        }
   }
}
