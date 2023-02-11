//package lk.ijse.cafe.model;
//
//import lk.ijse.cafe.db.DBConnection;
//import lk.ijse.cafe.to.PlaceStock;
//import lk.ijse.cafe.to.Stock;
//
//import java.sql.SQLException;
//import java.time.LocalDate;
//public class PlaceStockModel {
//
//
////    public static boolean placeStock(PlaceStock placeStock) throws SQLException, ClassNotFoundException {
////        try {
////            DBConnection.getInstance().getConnection().setAutoCommit(false);
////           // boolean isStockAdded=StokeModel.save(new Stock();
////            boolean isStockAdded=StockModel.save(new Stock(placeStock.getStockId(), LocalDate.now(), placeStock.getSupplierId()));
////            if (isStockAdded){
////                boolean isUpdated=StokeItemModel.updateQty(placeStock.getStockCartDeteils());
////                if (isUpdated){
////                    boolean isOrderDeteilsAdded=StockDetailsModel.saveStockDetails(placeStock.getStockCartDeteils());
////                    if (isOrderDeteilsAdded){
////                        DBConnection.getInstance().getConnection().commit();
////                    }
////                }
////            }
////            DBConnection.getInstance().getConnection().rollback();
////            return false;
////        }finally {
////            DBConnection.getInstance().getConnection().setAutoCommit(true);
////        }
////
////    }
//}
