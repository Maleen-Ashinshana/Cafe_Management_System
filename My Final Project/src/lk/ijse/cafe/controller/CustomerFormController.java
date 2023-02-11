package lk.ijse.cafe.controller;

import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import lk.ijse.cafe.dto.CustomerDTO;
import lk.ijse.cafe.model.CustomerModel;
import lk.ijse.cafe.service.ServiceFactory;
import lk.ijse.cafe.service.ServiceTypes;
import lk.ijse.cafe.service.custom.CustomerService;
import lk.ijse.cafe.service.exception.DuplicateException;
import lk.ijse.cafe.service.exception.NotFoundException;
import lk.ijse.cafe.tm.CustomerTM;
import lk.ijse.cafe.to.Customer;
import lk.ijse.cafe.views.tm.CustomerTm;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class CustomerFormController {
    public JFXTextField txtCustomerId;
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
    public CustomerDTO customerDTO;
    public CustomerService customerService;

    private ObservableList<CustomerTM> oblist= FXCollections.observableArrayList();

    public void  initialize() throws SQLException, ClassNotFoundException {
        CustomerView();
        this.customerService= ServiceFactory.getInstance().getService(ServiceTypes.CUSTOMER);
       list();
    }
    public void list(){
        List<CustomerTm> list=customerService.findAllCustomer().stream().map(customer->new CustomerTm(customer.getCustomer_id(),customer.getName())).collect(Collectors.toList());
        tblCustomer.setItems(FXCollections.observableArrayList(list));
    }

    @FXML
    private void cusromerNameOnAction(ActionEvent actionEvent) {
        CustomerDTO customerDTO=customerService.findById(txtCustomerId.getText());
        if (customerDTO!=null){
            fillDate(customerDTO);
        }
    }

    @FXML
    private void btnAddOnAction(ActionEvent actionEvent) {
        CustomerDTO customerDTO=new CustomerDTO(txtCustomerId.getText(),customerName.getText());
        try {
            if (customerService.saveCustomer(customerDTO)==null){
                new Alert(Alert.AlertType.ERROR,"Fail To Save Customer").show();
                return;
            }new Alert(Alert.AlertType.CONFIRMATION,"Saved!").show();
            tblCustomer.getItems().add(new CustomerTm(customerDTO.getCustomer_id(), customerDTO.getName()));
            txtCustomerId.clear();
            customerName.clear();
        }catch (DuplicateException e){
            new Alert(Alert.AlertType.ERROR,"CustomerDTO Already Exists!").show();
            txtCustomerId.selectAll();
            txtCustomerId.requestFocus();
        }
    }
    @FXML
    private void btnUpdateOnAction(ActionEvent actionEvent) {
        CustomerDTO updateCustomer=new CustomerDTO(txtCustomerId.getText(),customerName.getText());
        try {
            customerService.updateCustomer(updateCustomer);
            int selectedIndex=tblCustomer.getSelectionModel().getSelectedIndex();
            tblCustomer.getItems().remove(selectedIndex+1);
            new Alert(Alert.AlertType.INFORMATION,"update successful").show();
            txtCustomerId.clear();
            customerName.clear();
            list();
        }catch (NotFoundException e){
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        }
    }
    private void fillDate(CustomerDTO customerDTO){
        txtCustomerId.setText(customerDTO.getCustomer_id());
        customerName.setText(customerDTO.getName());
    }

    private void CustomerView(){
        colCustomerId.setCellValueFactory(new PropertyValueFactory<>("customer_id"));
        colCustomerName.setCellValueFactory(new PropertyValueFactory<>("name"));

    }

    public void btnDeleteOnAction(ActionEvent actionEvent) {
        Alert alert=new Alert(Alert.AlertType.WARNING,"are you sure to delete the customer", ButtonType.YES,ButtonType.NO);
        Optional<ButtonType> result=alert.showAndWait();
        if (result.isPresent() && result.get()==ButtonType.YES){
            try {
                customerService.deleteCustomer(txtCustomerId.getText());
                new Alert(Alert.AlertType.INFORMATION,"employe deleted").show();
                tblCustomer.getItems().removeAll(tblCustomer.getSelectionModel().getSelectedItem());
                txtCustomerId.clear();
                customerName.clear();
                list();
            }catch (NotFoundException e){
                new Alert(Alert.AlertType.WARNING,"No").show();
            }
        }

    }

    public void txtCustomerIdOnAction(ActionEvent actionEvent) {
      CustomerDTO customerDTO1=customerService.findById(txtCustomerId.getText());
      if (customerDTO1!=null){
          fillDate(customerDTO1);
      }
    }
}
