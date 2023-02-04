package lk.ijse.cafe.controller;

import com.jfoenix.controls.JFXTextField;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;
import lk.ijse.cafe.dto.EmployeDTO;
import lk.ijse.cafe.entity.EmployeEntity;
import lk.ijse.cafe.model.EmployeeModel;
import lk.ijse.cafe.service.ServiceFactory;
import lk.ijse.cafe.service.ServiceTypes;
import lk.ijse.cafe.service.custom.EmployeService;
import lk.ijse.cafe.service.exception.DuplicateException;
import lk.ijse.cafe.service.exception.NotFoundException;
import lk.ijse.cafe.to.Employee;
import lk.ijse.cafe.util.Animations;
import lk.ijse.cafe.views.tm.EmployeTm;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Optional;
import java.util.regex.Pattern;

public class EmployeViewFormController {
    public AnchorPane subPane;
    public JFXTextField txtId;
    public JFXTextField txtName;
    public JFXTextField txtAddress;
    public JFXTextField txtEmail;
    public JFXTextField txtTel;
    public JFXTextField txtGender;
    public JFXTextField txtType;
    public JFXTextField txtSalary;
    @FXML
    private TableColumn tblAction;
    @FXML
    private TableColumn tblSalary;
    @FXML
    private TableColumn tblType1;
    @FXML
    private AnchorPane pane;
    @FXML
    private TableView tblEmployee;
    @FXML
    private TableColumn tblId;
    @FXML
    private TableColumn tblName;
    @FXML
    private TableColumn tblAddress;
    @FXML
    private TableColumn tblTel;
    @FXML
    private TableColumn tblEmail;
    @FXML
    private TableColumn tblGender;
    @FXML
    private TableColumn tblType;

    private Pattern idPattern;
    private  Pattern namePattern;
    private  Pattern AddressPattern;
    private  Pattern emailPattern;
    private Pattern telPattern;
    private  Pattern gendrePattern;
    private  Pattern tyePettern;
    private Pattern salaryPattern;
    public Button btnDelete;
public EmployeTm  employeTm;
public EmployeService employeService;

public void init(EmployeTm employeTm) throws SQLException, ClassNotFoundException {
    //this.tblEmployee=tblEmployee;
    this.employeTm=employeTm;
    //fillAllFields(employeTm);
    this.employeService= ServiceFactory.getInstance().getService(ServiceTypes.EMPLOYE);
}


    public void btnBackOnAction(ActionEvent actionEvent) throws IOException {
        Stage window=(Stage)pane.getScene().getWindow();
        window.close();
        URL resource = getClass().getResource("/lk/ijse/cafe/view/ManagerDashBoardFom.fxml");
        Parent load = FXMLLoader.load(resource);
        Scene scene = new Scene(load);
        Stage stage = new Stage();
        //stage.close();
        stage.setScene(scene);
        stage.show();
    }

    public  void initialize() throws SQLException, ClassNotFoundException {
    this.employeService=ServiceFactory.getInstance().getService(ServiceTypes.EMPLOYE);
    init(employeTm);
    employeeView();
        subPane.setVisible(false);
        Animations.fadeOut(subPane);
        idPattern=Pattern.compile("[E][0][0-9]{1,}");
        namePattern=Pattern.compile("[a-z0-9]{4,}");
        AddressPattern=Pattern.compile("[A-Za-z0-9]{1,}");
        emailPattern=Pattern.compile("([a-z|0-9]{3,})[@]([a-z]{2,})\\.(com|lk)");
        telPattern=Pattern.compile("(076|074|073|075|078|072|091)([0-9]{7})");
        gendrePattern=Pattern.compile("(male|female)");
        tyePettern=Pattern.compile("(waiter|supperwiser|chef)");
        salaryPattern=Pattern.compile("[.0-9]");


    }

    private void employeeView() throws SQLException, ClassNotFoundException {
        this.employeService=ServiceFactory.getInstance().getService(ServiceTypes.EMPLOYE);

        tblId.setCellValueFactory(new PropertyValueFactory<>("id"));
        tblName.setCellValueFactory(new PropertyValueFactory<>("name"));
        tblAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        tblEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        tblTel.setCellValueFactory(new PropertyValueFactory<>("tel"));
        tblGender.setCellValueFactory(new PropertyValueFactory<>("gender"));
        tblType.setCellValueFactory(new PropertyValueFactory<>("type"));
        tblSalary.setCellValueFactory(new PropertyValueFactory<>("salary"));
    }

    public void btnRealodOnActio0n(ActionEvent actionEvent) {
        try {
            ObservableList<Employee> employeeList= EmployeeModel.getAllEmployee();
            tblEmployee.setItems(employeeList);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void btnEditOnACtion(ActionEvent actionEvent) {
        subPane.setVisible(true);
        Animations.fadeInUp(subPane);

    }

    public void btnAddOnACtion(ActionEvent actionEvent) {
        boolean isIdMatched=idPattern.matcher(txtId.getText()).matches();
        boolean isNameMatched=namePattern.matcher(txtName.getText()).matches();
        boolean isAddressMatched=AddressPattern.matcher(txtAddress.getText()).matches();
        boolean isEmailMatched=emailPattern.matcher(txtEmail.getText()).matches();
        boolean isTelMatched=telPattern.matcher(txtTel.getText()).matches();
        boolean isGenderMatched=gendrePattern.matcher(txtGender.getText()).matches();
        boolean isTypeMatched=tyePettern.matcher(txtType.getText()).matches();
        boolean salaryMatched=salaryPattern.matcher(txtSalary.getText()).matches();

        if (isIdMatched){
            if (isNameMatched){
                if (isAddressMatched){
                    if (isEmailMatched){
                        if (isTelMatched){
                            if (isGenderMatched){
                                if (isTypeMatched){
                                    if (salaryMatched){
                                        System.out.println("Start");
                                    }else{
                                        txtSalary.setFocusColor(Paint.valueOf("Red"));
                                        txtSalary.requestFocus();
                                    }
                                }else{
                                    txtType.setFocusColor(Paint.valueOf("Red"));
                                    txtType.requestFocus();
                                }
                            }else{
                                txtGender.setFocusColor(Paint.valueOf("Red"));
                                txtGender.requestFocus();
                            }
                        }else{
                            txtTel.setFocusColor(Paint.valueOf("Red"));
                            txtTel.requestFocus();
                        }
                    }else{
                        txtEmail.setFocusColor(Paint.valueOf("Red"));
                        txtEmail.requestFocus();
                    }
                }else{
                    txtAddress.setFocusColor(Paint.valueOf("Red"));
                    txtAddress.requestFocus();
                }
            }else{
                txtId.setFocusColor(Paint.valueOf("Red"));
                txtId.requestFocus();
            }
        }
        EmployeDTO employeDTO=new EmployeDTO(txtId.getText(),txtName.getText(),txtAddress.getText(),txtEmail.getText(),Integer.parseInt(txtTel.getText()),txtGender.getText(),txtType.getText(),Double.parseDouble(txtSalary.getText()));

        try {
            if (employeService.saveEmploye(employeDTO)==null){
                new Alert(Alert.AlertType.ERROR,"Fail To Save Employe!").show();
                return;
            }
            new Alert(Alert.AlertType.CONFIRMATION,"Successfully Saved!").show();
           // tblEmployee.getItems().add(new EmployeTm(employeDTO.getEmploye_id(),employeDTO.getName(),employeDTO.getAddress(),employeDTO.getEmail(),employeDTO.getContact_num(),employeDTO.getGender(),employeDTO.getType(),employeDTO.getSalary()));
            txtId.clear();
            txtName.clear();
            txtAddress.clear();
            txtEmail.clear();
            txtTel.clear();
            txtGender.clear();
            txtType.clear();
            txtSalary.clear();
        }catch (DuplicateException e){
            new Alert(Alert.AlertType.ERROR,"EmployeDTO Already Exists!").show();
            txtId.selectAll();
            txtId.requestFocus();
        }

    }

    public void btnDeleteOnAction(ActionEvent actionEvent) {
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



        Alert alert=new Alert(Alert.AlertType.WARNING,"are you sure to delete the employe", ButtonType.YES,ButtonType.NO);
        Optional<ButtonType> result=alert.showAndWait();
        if (result.isPresent() && result.get()==ButtonType.YES){
            try {
                employeService.deleteEmploye(employeTm.getEmploye_id());
                new Alert(Alert.AlertType.INFORMATION,"Employe Deleted!").show();
                tblEmployee.getItems().removeAll(tblEmployee.getSelectionModel().getSelectedItem());;
            btnDelete.getScene().getWindow().hide();
            }catch (NotFoundException e){
                new Alert(Alert.AlertType.WARNING,"No Employe!").show();
            }
        }
    }

    public void btnUpdateOnAction(ActionEvent actionEvent) {
        boolean isIdMatched=idPattern.matcher(txtId.getText()).matches();
        boolean isNameMatched=namePattern.matcher(txtName.getText()).matches();
        boolean isAddressMatched=AddressPattern.matcher(txtAddress.getText()).matches();
        boolean isEmailMatched=emailPattern.matcher(txtEmail.getText()).matches();
        boolean isTelMatched=telPattern.matcher(txtTel.getText()).matches();
        boolean isGenderMatched=gendrePattern.matcher(txtGender.getText()).matches();
        boolean isTypeMatched=tyePettern.matcher(txtType.getText()).matches();
        boolean salaryMatched=salaryPattern.matcher(txtSalary.getText()).matches();

        if (isIdMatched){
            if (isNameMatched){
                if (isAddressMatched){
                    if (isEmailMatched){
                        if (isTelMatched){
                            if (isGenderMatched){
                                if (isTypeMatched){
                                    if (salaryMatched){
                                        System.out.println("Start");
                                    }else{
                                        txtSalary.setFocusColor(Paint.valueOf("Red"));
                                        txtSalary.requestFocus();
                                    }
                                }else{
                                    txtType.setFocusColor(Paint.valueOf("Red"));
                                    txtType.requestFocus();
                                }
                            }else{
                                txtGender.setFocusColor(Paint.valueOf("Red"));
                                txtGender.requestFocus();
                            }
                        }else{
                            txtTel.setFocusColor(Paint.valueOf("Red"));
                            txtTel.requestFocus();
                        }
                    }else{
                        txtEmail.setFocusColor(Paint.valueOf("Red"));
                        txtEmail.requestFocus();
                    }
                }else{
                    txtAddress.setFocusColor(Paint.valueOf("Red"));
                    txtAddress.requestFocus();
                }
            }else{
                txtId.setFocusColor(Paint.valueOf("Red"));
                txtId.requestFocus();
            }
        }

        String id=txtId.getText();
        String name=txtName.getText();
        String address=txtAddress.getText();
        String email=txtEmail.getText();
        int tel=Integer.parseInt(txtTel.getText());
        String gender=txtGender.getText();
        String type=txtType.getText();
        double salary=Double.parseDouble(txtSalary.getText());

        Employee employee=new Employee(id,name,address,email,tel,gender,type,salary);

        try {
            boolean isUpdate=EmployeeModel.updateEmploye(employee);
            if (isUpdate){
                new Alert(Alert.AlertType.CONFIRMATION,"Updated!...").show();
            }else{
                new Alert(Alert.AlertType.WARNING,"Some Thing Went Wrong!...").show();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void btnCencleOnACtion(ActionEvent actionEvent) {
        subPane.setVisible(false);
        Animations.fadeOut(subPane);
    }

    public void txtIdOnACtion(ActionEvent actionEvent) {
        String ids=txtId.getText();
        EmployeDTO employeDTO=employeService.findById(ids);
        if (employeDTO!=null){
            fillData(employeDTO);
        }

    }
    private void fillData(EmployeDTO employeDTO){
        txtId.setText(employeDTO.getEmploye_id());
        txtName.setText(employeDTO.getName());
        txtAddress.setText(employeDTO.getAddress());
        txtEmail.setText(employeDTO.getEmail());
        txtTel.setText(String.valueOf(employeDTO.getContact_num()));
        txtGender.setText(employeDTO.getGender());
        txtType.setText(employeDTO.getType());
        txtSalary.setText(String.valueOf(employeDTO.getSalary()));

    }
    //System.out.println(txtId.getText());
//    private void fillAllFields(EmployeTm employeTm){
//        System.out.println(txtId.getText());
//    txtId.setText(employeTm.getEmploye_id());
//    txtName.setText(employeTm.getName());
//    txtAddress.setText(employeTm.getAddress());
//    txtEmail.setText(employeTm.getEmail());
//    txtTel.setText(String.valueOf(employeTm.getContact_num()));
//    txtGender.setText(employeTm.getGender());
//    txtType.setText(employeTm.getType());
//    txtSalary.setText(String.valueOf(employeTm.getSalary()));
//    }

}
