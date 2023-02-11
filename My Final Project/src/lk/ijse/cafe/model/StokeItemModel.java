package lk.ijse.cafe.model;

import lk.ijse.cafe.to.StockCartDeteils;
import lk.ijse.cafe.to.StockItems;
import lk.ijse.cafe.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class StokeItemModel {

//    public static String getNextId() throws SQLException, ClassNotFoundException {
//        //String sql=CrudUtil.execute("SELECT id FROM stoke ORDER BY id DESC LIMIT 1 ");
//        ResultSet result= CrudUtil.execute("SELECT stok_id FROM stok ORDER BY id DESC LIMIT 1 ");
//        if (result.next()){
//            return generateNextId(result.getString(1));
//        }
//        return generateNextId(null);
//    }
//    private static String generateNextId(String currentId){
//        if (currentId != null){
//            String[] ids=currentId.split("S00");
//            int id=Integer.parseInt(ids[1]);
//            id+=1;
//            return "S000" + id;
//        }
//        return "S0001";
//    }

    public static StockItems search(String id) throws SQLException, ClassNotFoundException {
        String sql="SELECT * FROM stoke_items WHERE id= ?";
        ResultSet result=CrudUtil.execute(sql,id);
        if (result.next()){
            return new StockItems(
                    result.getString(1),
                    result.getString(2),
                    result.getDouble(3),
                    result.getInt(4)

            );

        }
      return  null;
    }



    public static ArrayList<String> loadId() throws SQLException, ClassNotFoundException {
        String sql="SELECT id FROM stoke_items";
        ResultSet result=CrudUtil.execute(sql);

        ArrayList<String> idList=new ArrayList<>();
        while (result.next()){
            idList.add(result.getString(1));

        }
        return idList;

    }

//    public static boolean updateQty(ArrayList<StockCartDeteils>stockCartDeteils) throws SQLException, ClassNotFoundException {
//        for (StockCartDeteils stok:stockCartDeteils) {
//            if (!updateQty(new StockItems(stok.getCode(),stok.getDescription(),stok.getUnitPrice(),stok.getQty()))){
//                return false;
//
//            }
//        }
//        return true;
//
//    }
    private static  boolean updateQty(StockItems stockItems) throws SQLException, ClassNotFoundException {
        String sql="UPDATE stoke_items SET qty = qty - ? WHERE id = ?";
        return  CrudUtil.execute(sql,stockItems.getQty(),stockItems.getId());
    }
//
//    public static boolean save(StockItems stock) throws SQLException, ClassNotFoundException {
//        String sql="INSERT INTO stok VALUES(?,?,?,?)";
//        return CrudUtil.execute(sql,stock.getId(),stock.getDescription(),stock.getUnitPrice(),stock.getQty());
//
//
//    }

//    public static StockItems search(String code) {
//    }
}