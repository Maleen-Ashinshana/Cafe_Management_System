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
import lk.ijse.cafe.dto.SupployerDTO;
//import lk.ijse.cafe.model.SupplyeerModel;
import lk.ijse.cafe.service.ServiceFactory;
import lk.ijse.cafe.service.ServiceTypes;
import lk.ijse.cafe.service.SuperSevice;
import lk.ijse.cafe.service.custom.SupployerService;
import lk.ijse.cafe.service.exception.DuplicateException;
import lk.ijse.cafe.service.exception.NotFoundException;
import lk.ijse.cafe.to.Supplyer;
import lk.ijse.cafe.views.tm.SupployerTm;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class SupplerFormController {
    public TableColumn tblSupplierId;
    @FXML
    private AnchorPane pane;
    @FXML
    private JFXTextField txtId;
    @FXML
    private JFXTextField txtName;
    @FXML
    private JFXTextField txtAddress;
    @FXML
    private TableView<SupployerTm> tblSupplier;
    @FXML
    private TableColumn colId;
    @FXML
    private TableColumn colName;
    @FXML
    private TableColumn colAddress;
    public SupployerDTO supployerDTO;
    public SupployerService supployerService;

    public void initialize() throws SQLException, ClassNotFoundException {
        SupplierView();
        this.supployerService= ServiceFactory.getInstance().getService(ServiceTypes.SUPPLOYER);
        list();
    }
    public void list(){
        List<SupployerTm> supployerTmList=supployerService.findAll().stream().map(supployer->new SupployerTm(supployer.getSupplyer_id(),supployer.getName(),supployer.getAddress())).collect(Collectors.toList());
        tblSupplier.setItems(FXCollections.observableArrayList(supployerTmList));
    }

    private void SupplierView() throws SQLException, ClassNotFoundException {
        this.supployerService= ServiceFactory.getInstance().getService(ServiceTypes.SUPPLOYER);
        tblSupplierId.setCellValueFactory(new PropertyValueFactory<>("supplyer_id"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
    }

    @FXML
    private void txtIdOnAction(ActionEvent actionEvent) {
        SupployerDTO supployerDTO1=supployerService.findByid(txtId.getText());
        if (supployerDTO1!=null){
            fillDate(supployerDTO1);
        }
    }

    @FXML
    private void btnAddOnAction(ActionEvent actionEvent) {
        SupployerDTO supployerDTO=new SupployerDTO(txtId.getText(),txtName.getText(),txtAddress.getText());
        try {
            if (supployerService.saveSupployer(supployerDTO)==null){
                new Alert(Alert.AlertType.ERROR,"fail to save supplier").show();
                return;
            }new Alert(Alert.AlertType.CONFIRMATION,"saved").show();
            tblSupplier.getItems().add(new SupployerTm(supployerDTO.getSupplyer_id(),supployerDTO.getName(),supployerDTO.getAddress()));
            txtId.clear();
            txtName.clear();
            txtAddress.clear();
        }catch (DuplicateException e){
            new Alert(Alert.AlertType.ERROR,"Supplier already exists").show();
            txtId.selectAll();
            txtId.requestFocus();
        }
    }

    @FXML
    private void btnDeleteOnAction(ActionEvent actionEvent) {
        Alert alert=new Alert(Alert.AlertType.WARNING,"are you sure to delete supplier", ButtonType.YES,ButtonType.NO);
        Optional<ButtonType> result=alert.showAndWait();
        if (result.isPresent() && result.get()==ButtonType.YES){
            try {
                supployerService.delete(txtId.getText());
                new Alert(Alert.AlertType.INFORMATION,"supplier delete").show();
                tblSupplier.getItems().removeAll(tblSupplier.getSelectionModel().getSelectedItem());
                txtId.clear();
                txtName.clear();
                txtAddress.clear();
                list();
            }catch (NotFoundException e){
                new Alert(Alert.AlertType.WARNING,"No").show();
            }
        }
    }
    @FXML
    private void btnUpdateOnAction(ActionEvent actionEvent) {
//        String id=txtId.getText();
//        String name=txtName.getText();
//        String address=txtAddress.getText();
//        Supplyer supplyer=new Supplyer(id,name,address);
//        try {
//            boolean isUpdate=SupplyeerModel.update(supplyer);
//            if (isUpdate){
//                new Alert(Alert.AlertType.CONFIRMATION,"Updated!...").show();
//            }else{
//                new Alert(Alert.AlertType.WARNING,"NO!...").show();
//            }
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        } catch (ClassNotFoundException e) {
//            throw new RuntimeException(e);
//        }
        SupployerDTO updateSupployer=new SupployerDTO(txtId.getText(),txtName.getText(),txtAddress.getText());
        try {
            supployerService.updateSupployer(updateSupployer);
            int  selectedIndex=tblSupplier.getSelectionModel().getSelectedIndex();
            tblSupplier.getItems().remove(selectedIndex+1);
            new Alert(Alert.AlertType.INFORMATION,"update successful").show();
            txtId.clear();
            txtName.clear();
            txtAddress.clear();
            list();
        }catch (NotFoundException e){
            new Alert(Alert.AlertType.ERROR,e.getMessage());
        }
    }
    @FXML
    private void btnReloadOnAction(ActionEvent actionEvent) {
//        try {
//            ObservableList<Supplyer> observableList=SupplyeerModel.getAll();
//            tblSupplier.setItems(observableList);
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        } catch (ClassNotFoundException e) {
//            throw new RuntimeException(e);
//        }
    }
    private void fillDate(SupployerDTO supplyer){
        txtId.setText(supplyer.getSupplyer_id());
        txtName.setText(supplyer.getName());
        txtAddress.setText(supplyer.getAddress());
    }
}
