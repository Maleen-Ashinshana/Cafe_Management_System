package lk.ijse.cafe.controller;

import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.cafe.dto.EmployeDTO;
import lk.ijse.cafe.model.EmployeeModel;
import lk.ijse.cafe.model.ItemModel;
import lk.ijse.cafe.service.custom.EmployeService;
import lk.ijse.cafe.service.exception.DuplicateException;
import lk.ijse.cafe.to.Employee;
import lk.ijse.cafe.to.Item;
import lk.ijse.cafe.util.Navigation;
import lk.ijse.cafe.util.Routs;
import lk.ijse.cafe.views.tm.EmployeTm;

import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;

public class EmployeFormController {

    @FXML
    private AnchorPane pane;
    @FXML
    private TextField txtId;
    @FXML
    private TextField txtName;
    @FXML
    private TextField txtAddress;
    @FXML
    private TextField txtEmail;
    @FXML
    private TextField txtTel;
    @FXML
    private TextField txtGender;
    @FXML
    private TextField txtType;
    @FXML
    private JFXTextField txtSalary;
public EmployeService employeService;
//    @FXML
//    private void idOnAction(ActionEvent actionEvent) {
//        String id=txtId.getText();
//        try {
//            Employee employee=EmployeeModel.searchEmployee(id);
//            if (employee!=null){
//                fillDate(employee);
//            }
//        } catch (SQLException | ClassNotFoundException e) {
//            throw new RuntimeException(e);
//        }
//
//    }
//
//    @FXML
//    private void btnAddOnACtion(ActionEvent actionEvent) {
//
////        String id=txtId.getText();
////        String name=txtName.getText();
////        String address=txtAddress.getText();
////        String email=txtEmail.getText();
////        int tel=Integer.parseInt(txtTel.getText());
////        String gender=txtGender.getText();
////        String type=txtType.getText();
////        double salary=Double.parseDouble(txtSalary.getText());
////        Employee employee=new Employee(id,name,address,email,tel,gender,type,salary);
////        try {
////            boolean isAdded=EmployeeModel.add(employee);
////            if (isAdded){
////                new Alert(Alert.AlertType.CONFIRMATION,"Employee Added!...").show();
////            }else{
////                new Alert(Alert.AlertType.WARNING,"Some thing went wrong!...").show();
////            }
////        } catch (SQLException | ClassNotFoundException e) {
////            throw new RuntimeException(e);
////        }
////    }
//        EmployeDTO employeDTO=new EmployeDTO(txtId.getText(),txtName.getText(),txtAddress.getText(),txtEmail.getText(),Integer.parseInt(txtTel.getText()),txtGender.getText(),txtType.getText(),Double.parseDouble(txtSalary.getText()));
//        try {
//            if (employeService.saveEmploye(employeDTO)==null){
//                new Alert(Alert.AlertType.ERROR,"Fail To Save Employe!").show();
//                return;
//            }
//            new Alert(Alert.AlertType.CONFIRMATION,"Successfully Saved!").show();
//            //tblEmployee.getItems().add(new EmployeTm(employeDTO.getEmploye_id(),employeDTO.getName(),employeDTO.getAddress(),employeDTO.getEmail(),employeDTO.getContact_num(),employeDTO.getGender(),employeDTO.getType(),employeDTO.getSalary()));
////            txtId.clear();
////            txtName.clear();
////            txtAddress.clear();
////            txtEmail.clear();
////            txtTel.clear();
////            txtGender.clear();
////            txtType.clear();
////            txtSalary.clear();
//        }catch (DuplicateException e){
//            new Alert(Alert.AlertType.ERROR,"EmployeDTO Already Exists!").show();
////            txtId.selectAll();
////            txtId.requestFocus();
//        }
//    }
//
//    @FXML
//    private void btnSearchOnAction(ActionEvent actionEvent) {
//
//        idOnAction(actionEvent);
//    }
//
//    @FXML
//    private void btnUpdateOnAction(ActionEvent actionEvent) {
//        String id=txtId.getText();
//        String name=txtName.getText();
//        String address=txtAddress.getText();
//        String email=txtEmail.getText();
//        int tel=Integer.parseInt(txtTel.getText());
//        String gender=txtGender.getText();
//        String type=txtType.getText();
//        double salary=Double.parseDouble(txtSalary.getText());
//
//        Employee employee=new Employee(id,name,address,email,tel,gender,type,salary);
//
//        try {
//            boolean isUpdate=EmployeeModel.updateEmploye(employee);
//            if (isUpdate){
//                new Alert(Alert.AlertType.CONFIRMATION,"Updated!...").show();
//            }else{
//                new Alert(Alert.AlertType.WARNING,"Some Thing Went Wrong!...").show();
//            }
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        } catch (ClassNotFoundException e) {
//            throw new RuntimeException(e);
//        }
//    }
//
//    @FXML
//    private void btnDeleteOnAction(ActionEvent actionEvent) {
//        try {
//            boolean isDelete=EmployeeModel.deleteEmployee(txtId.getText());
//            if (isDelete){
//                new Alert(Alert.AlertType.CONFIRMATION,"Deleted!...").show();
//            }else{
//                new Alert(Alert.AlertType.WARNING,"Some Thing Went Wrong!...");
//            }
//        } catch (SQLException | ClassNotFoundException e) {
//            throw new RuntimeException(e);
//        }
//    }
//
//    @FXML
//    private void btnViewOnAction(ActionEvent actionEvent) throws IOException {
//        //Navigation.navigation(Routs.LIST_EMPLOYEE,pane);
//        Stage window=(Stage)pane.getScene().getWindow();
//        window.close();
//        URL resource = getClass().getResource("/lk/ijse/cafe/view/EmployeViewForm.fxml");
//        Parent load = FXMLLoader.load(resource);
//        Scene scene = new Scene(load);
//        Stage stage = new Stage();
//        stage.setScene(scene);
//        stage.show();
//    }
//    private void fillDate(Employee employee){
//        txtId.setText(employee.getId());
//        txtName.setText(employee.getName());
//        txtAddress.setText(employee.getAddress());
//        txtEmail.setText(employee.getEmail());
//        txtTel.setText(String.valueOf(employee.getTel()));
//        txtGender.setText(employee.getGender());
//        txtType.setText(employee.getType());
//        txtSalary.setText(String.valueOf(employee.getSalary()));
//
//    }
}
