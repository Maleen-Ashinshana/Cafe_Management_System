package lk.ijse.cafe.controller;

import com.jfoenix.controls.JFXTextField;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import lk.ijse.cafe.model.SupplyeerModel;
import lk.ijse.cafe.to.Supplyer;

import java.sql.SQLException;

public class SupplerFormController {
    @FXML
    private AnchorPane pane;
    @FXML
    private JFXTextField txtId;
    @FXML
    private JFXTextField txtName;
    @FXML
    private JFXTextField txtAddress;
    @FXML
    private TableView tblSupplier;
    @FXML
    private TableColumn colId;
    @FXML
    private TableColumn colName;
    @FXML
    private TableColumn colAddress;

    @FXML
    private void txtIdOnAction(ActionEvent actionEvent) {
        String id=txtId.getText();
        try {
            Supplyer supplyer=SupplyeerModel.search(id);
            if (supplyer!=null){
                fillDate(supplyer);
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    private void btnAddOnAction(ActionEvent actionEvent) {
        String id=txtId.getText();
        String name=txtName.getText();
        String address=txtAddress.getText();

        Supplyer supplyer=new Supplyer(id,name,address);

        try {
            boolean isAdded= SupplyeerModel.isAdded(supplyer);
            if (isAdded){
                new Alert(Alert.AlertType.CONFIRMATION,"Added!...").show();
            }else{
                new Alert(Alert.AlertType.WARNING,"NO!...").show();
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    private void btnDeleteOnAction(ActionEvent actionEvent) {
        try {
            boolean isDelete=SupplyeerModel.deleteSuppler(txtId.getText());
            if (isDelete){
                new Alert(Alert.AlertType.CONFIRMATION,"DELETED!...").show();
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
        String id=txtId.getText();
        String name=txtName.getText();
        String address=txtAddress.getText();
        Supplyer supplyer=new Supplyer(id,name,address);
        try {
            boolean isUpdate=SupplyeerModel.update(supplyer);
            if (isUpdate){
                new Alert(Alert.AlertType.CONFIRMATION,"Updated!...").show();
            }else{
                new Alert(Alert.AlertType.WARNING,"NO!...").show();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    private void btnReloadOnAction(ActionEvent actionEvent) {
        try {
            ObservableList<Supplyer> observableList=SupplyeerModel.getAll();
            tblSupplier.setItems(observableList);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    private void fillDate(Supplyer supplyer){
        txtId.setText(supplyer.getId());
        txtName.setText(supplyer.getName());
        txtAddress.setText(supplyer.getAddress());
    }
    private void SupplierView(){
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
    }
    public void initialize(){
        SupplierView();
    }
}
