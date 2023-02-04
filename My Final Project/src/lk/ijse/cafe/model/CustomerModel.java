package lk.ijse.cafe.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lk.ijse.cafe.to.Customer;
import lk.ijse.cafe.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CustomerModel {
    public static boolean addCustomer(Customer customer) throws SQLException, ClassNotFoundException {
        String sql="INSERT INTO customer VALUES(?,?)";
        return CrudUtil.execute(sql,customer.getId(),customer.getName());
    }

    public static Customer searchCustomer(String id) throws SQLException, ClassNotFoundException {
        String sql="SELECT * FROM customer WHERE customer_id=?";
        ResultSet resultSet=CrudUtil.execute(sql,id);
        if (resultSet.next()){
            return  new Customer(
                    resultSet.getString(1),
                    resultSet.getString(2)
            );
        }
            return null;
    }

    public static ObservableList<Customer> getAllCustomer() throws SQLException, ClassNotFoundException {
        String sql="SELECT * FROM customer";
        ResultSet resultSet=CrudUtil.execute(sql);
        ObservableList<Customer> customerObservableList= FXCollections.observableArrayList();
        while (resultSet.next()){
            Customer customer=new Customer(resultSet.getString("customer_id"),resultSet.getString("name"));
            customerObservableList.add(customer);
        }
        return customerObservableList;
    }

    public static ArrayList<String> loadCustomerId() throws SQLException, ClassNotFoundException {
        String sql="SELECT * FROM customer";
        ResultSet resultSet=CrudUtil.execute(sql);
        ArrayList<String> idList=new ArrayList<>();
        while (resultSet.next()){
            idList.add(resultSet.getString(1));

        }
        return idList;
    }
}
