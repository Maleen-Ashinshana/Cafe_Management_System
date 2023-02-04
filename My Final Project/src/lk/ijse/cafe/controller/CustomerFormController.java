package lk.ijse.cafe.controller;

import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import lk.ijse.cafe.model.CustomerModel;
import lk.ijse.cafe.tm.CustomerTM;
import lk.ijse.cafe.to.Customer;

import java.sql.SQLException;

public class CustomerFormController {
    @FXML
    private AnchorPane pane;
    @FXML
    private TableView tblCustomer;
    @FXML
    private TableColumn colCustomerId;
    @FXML
    private TableColumn colCustomerName;
    @FXML
    private JFXTextField customerId;
    @FXML
    private JFXTextField customerName;

    private ObservableList<CustomerTM> oblist= FXCollections.observableArrayList();

    @FXML
    private void cusromerNameOnAction(ActionEvent actionEvent) {
        String id=customerId.getText();
        try {
            Customer customer=CustomerModel.searchCustomer(id);
            if (customer!=null){
                fillDate(customer);
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    private void btnAddOnAction(ActionEvent actionEvent) {
        String id=customerId.getText();
        String name=customerName.getText();

        Customer customer=new Customer(id,name);
//        if (!oblist.isEmpty()){
//            L1:
//            for (int i=0;i<tblCustomer.getItems().size();i++){
//                if (colCustomerId.getCellData(i).equals(id)){
//                    oblist.get(i).getName();
//                    return;
//                }
//            }
//        }


        try {
            boolean isAdded= CustomerModel.addCustomer(customer);
            if (isAdded){
                new Alert(Alert.AlertType.CONFIRMATION,"Added!...").show();
            }else{
                new Alert(Alert.AlertType.WARNING,"No!...").show();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    private void btnUpdateOnAction(ActionEvent actionEvent) {
    }
    private void fillDate(Customer customer){
        colCustomerId.setText(customer.getId());
        colCustomerName.setText(customer.getName());
    }
    private void CustomerView(){
        colCustomerId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colCustomerName.setCellValueFactory(new PropertyValueFactory<>("name"));

    }

    public void btnRefreshOnAction(ActionEvent actionEvent) {
        try {
            ObservableList<Customer> customersList=CustomerModel.getAllCustomer();
            tblCustomer.setItems(customersList);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    public void  initialize(){
        CustomerView();
    }
}
