package lk.ijse.cafe.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableArray;
import javafx.collections.ObservableList;
import lk.ijse.cafe.to.CartDetail;
import lk.ijse.cafe.to.Item;
import lk.ijse.cafe.to.ItemDetails;
import lk.ijse.cafe.util.CrudUtil;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ItemModel {
    public ItemModel() {

    }
//    public static boolean isAdded(Item item) throws SQLException, ClassNotFoundException {
//        return CrudUtil.execute("INSERT INTO items VALUES(?,?,?)",
//        item.getCode(),
//        item.getDescription(),
//        item.getUnitPrice()
//
//        );
//    }
    public static  Item searchItem(String code) throws SQLException, ClassNotFoundException {
      //CrudUtil.execute("Select * from items where code?=");
//        ResultSet resultSet=CrudUtil.execute("Select * from items where code?=");
//
//        if (resultSet.next()){
//            Item item=new Item(resultSet.getString("code"),resultSet.getString("description"),resultSet.getDouble("unitPrice"));
//            return  item;
//        }
//        return null;
       String sql="SELECT  * FROM items WHERE item_code = ?";
       ResultSet result= CrudUtil.execute(sql,code);
       if (result.next()){
           return  new Item(
                   result.getString(1),
                   result.getString(2),
                   result.getDouble(3)
           );
       }
       return  null;
    }
//    public static boolean updateItem(ArrayList<Item> items) throws SQLException, ClassNotFoundException {
//        for (Item item:items) {
//            if (!updateItem(new Item(item.getCode(),item.getDescription(),item.getUnitPrice()))){
//                return  false;
//            }
//        }
//        return  true;
//    }

    public static boolean updateItem(ArrayList<ItemDetails>itemDetails) throws SQLException, ClassNotFoundException {
        for (ItemDetails cItemDetails:itemDetails) {
            if (!updateItem(new Item(cItemDetails.getCode(), cItemDetails.getDescription(), cItemDetails.getUnitPrice()))){
                return  false;
            }
        }
        return  true;
    }
//    public static boolean updateItem(Item item) throws SQLException, ClassNotFoundException {
//       return  CrudUtil.execute("Update items set description=?,unit_price=? where item_code=?",
//               item.getDescription(),
//               item.getUnitPrice(),
//               item.getCode()
//       );
//
//    }

    public static boolean deleteItem(String code) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("Delete From items where item_code= '"+code+"'");
    }

    public static ObservableList<Item> getAllItems() throws SQLException, ClassNotFoundException {
        String sql="select * From items";
        ResultSet resul=CrudUtil.execute(sql);
        ObservableList<Item> itemsList= FXCollections.observableArrayList() ;
        while (resul.next()){
            Item item=new Item(resul.getString("item_code"),resul.getString("description"),resul.getDouble("unit_price"));
            itemsList.add(item);
        }
        return itemsList;
    }

    public static ArrayList<String> LoadItemCode() throws SQLException, ClassNotFoundException {
        String sql="SELECT item_code FROM items";
        ResultSet resultSet=CrudUtil.execute(sql);
        ArrayList<String> codeList=new ArrayList<>();
        while (resultSet.next()){
            codeList.add(resultSet.getString(1));

        }
        return  codeList;
    }

    public static boolean updateQty(ArrayList<CartDetail>cartDetails) throws SQLException, ClassNotFoundException {
        for (CartDetail cartDetail:cartDetails) {
            if (!updateQty(new Item(cartDetail.getCode(), cartDetail.getDescription(), cartDetail.getUnitPrice()))){
                   return  false;

            }
        }
        return  true;
    }
    private  static boolean updateQty(Item item) throws SQLException, ClassNotFoundException {
        String sql="UPDATE items SET unit_price=unit_price-? WHERE item_code";
        return CrudUtil.execute(sql,item.getUnitPrice());
    }
}
