package lk.ijse.cafe.controller;

import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;
import lk.ijse.cafe.dto.SystemUsesDTO;
import lk.ijse.cafe.service.ServiceFactory;
import lk.ijse.cafe.service.ServiceTypes;
import lk.ijse.cafe.service.custom.SystemUsesService;
import lk.ijse.cafe.service.exception.DuplicateException;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.regex.Pattern;

public class CashierCreateAcountFormController {
    @FXML
    private JFXTextField txtId;
    @FXML
    private JFXTextField txtName;
    @FXML
    private JFXTextField txtAddress;
    @FXML
    private JFXTextField txtCon;
    @FXML
    private JFXTextField txtType;
    @FXML
    private JFXTextField txtEmail;
    @FXML
    private JFXTextField txtPassword;
    @FXML
    private AnchorPane pane;
    private Pattern idPatten;
    private  Pattern namePatten;
    private Pattern addressPatten;
    private  Pattern telPatten;
    private  Pattern tyPatten;
    private  Pattern emailPatten;
    private  Pattern passwordPatten;

    public SystemUsesService systemUsesService;

    public void  initialize() throws SQLException, ClassNotFoundException {
        //this.cashierService= ServiceFactory.getInstance().getService(ServiceTypes.CASHIER);
        this.systemUsesService=ServiceFactory.getInstance().getService(ServiceTypes.SYSTEMUSERS);
        idPatten=Pattern.compile("^[S][0][0-9]{1,}$");
        namePatten=Pattern.compile("^[a-z0-9]{4,}$");
        addressPatten=Pattern.compile("^[A-Za-z0-9]{1,}$");
        telPatten=Pattern.compile("^(076|074|073|075|078|072|091)([0-9]{7})$");
        tyPatten=Pattern.compile("^(manager|cashier)$");
        emailPatten=Pattern.compile("^([a-z|0-9]{3,})[@]([a-z]{2,})\\.(com|lk)$");
        passwordPatten=Pattern.compile("^[a-zA-Z0-9_]{8,}$");
    }

    public void createOnAction(ActionEvent actionEvent) throws IOException {
        //pane.getChildren().clear();
        //Navigation.navigation(Routs.CASHIER,pane);
        //Navigation.navigation(Routs.CASHIER,pane);
        boolean isIdMatched=idPatten.matcher(txtId.getText()).matches();
        boolean isNameMatched=namePatten.matcher(txtName.getText()).matches();
        boolean isAddressMatched=addressPatten.matcher(txtAddress.getText()).matches();
        boolean isContMatched=telPatten.matcher(txtCon.getText()).matches();
        boolean isTypeMatched=tyPatten.matcher(txtType.getText()).matches();
        boolean isEmailMatched=emailPatten.matcher(txtEmail.getText()).matches();
        boolean isPasswordMatched=passwordPatten.matcher(txtPassword.getText()).matches();

        if (isIdMatched){
            if (isNameMatched){
                if (isAddressMatched){
                    if (isContMatched){
                        if (isTypeMatched){
                            if (isEmailMatched){
                                if (isPasswordMatched){
                                    System.out.println("Account is Create Start");
                                    //new Alert(Alert.AlertType.CONFIRMATION,"Account Create Success");
                                }else {
                                    txtPassword.setFocusColor(Paint.valueOf("Red"));
                                    txtPassword.requestFocus();
                                }
                            }else{
                                txtEmail.setFocusColor(Paint.valueOf("Red"));
                                txtEmail.requestFocus();
                            }
                        }else{
                            txtType.setFocusColor(Paint.valueOf("Red"));
                            txtType.requestFocus();
                        }
                    }else{
                        txtCon.setFocusColor(Paint.valueOf("Red"));
                        txtCon.requestFocus();
                    }
                }else{
                    txtAddress.setFocusColor(Paint.valueOf("Red"));
                    txtAddress.requestFocus();
                }
            }else{
                txtName.setFocusColor(Paint.valueOf("Red"));
                txtName.requestFocus();
            }
        }else{
            txtId.setFocusColor(Paint.valueOf("Red"));
            txtId.requestFocus();
        }


        SystemUsesDTO systemUsesDTO=new SystemUsesDTO(txtId.getText(),txtName.getText(),txtAddress.getText(),Integer.parseInt(txtCon.getText()),txtType.getText(),txtEmail.getText(),txtPassword.getText());
        try {
            if (systemUsesService.saveCashier(systemUsesDTO)==null){
                new Alert(Alert.AlertType.ERROR,"Fail To Register!").show();
                return;
            }
            new Alert(Alert.AlertType.CONFIRMATION,"Account Has Created!").show();
            txtId.clear();
            txtName.clear();
            txtAddress.clear();
            txtCon.clear();
            txtType.clear();
            txtEmail.clear();
            txtPassword.clear();
        }catch (DuplicateException e){
            new Alert(Alert.AlertType.ERROR,"Cashier Already Register!").show();
            txtId.selectAll();
            txtId.requestFocus();
        }

    }


    public void btnBackOnAction(ActionEvent actionEvent) throws IOException {
        Stage window = (Stage) pane.getScene().getWindow();
        window.close();
        URL resource = getClass().getResource("/lk/ijse/cafe/view/ManagerDashBoardFom.fxml");
        Parent load = FXMLLoader.load(resource);
        Scene scene = new Scene(load);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();

    }
}
