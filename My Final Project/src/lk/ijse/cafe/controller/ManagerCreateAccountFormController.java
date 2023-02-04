package lk.ijse.cafe.controller;

import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Paint;
import lk.ijse.cafe.model.ManagerModel;
import lk.ijse.cafe.to.Manager;
import lk.ijse.cafe.util.Navigation;
import lk.ijse.cafe.util.Routs;

import java.io.IOException;
import java.sql.SQLException;
import java.util.regex.Pattern;

public class ManagerCreateAccountFormController {
    @FXML
    private AnchorPane pane;
    @FXML
    private Label lblInvalidId;
    @FXML
    private Label lblInvalidName;
    @FXML
    private Label lblInvalidAddress;
    @FXML
    private Label lblinvalidType;
    @FXML
    private Label lblInvalidEmail;
    @FXML
    private Label lblInvalidPassword;
    @FXML
    private Label lblInvalidTel;
    @FXML
    private JFXTextField txtId;

    @FXML
    private JFXTextField txtName;

    @FXML
    private JFXTextField txtAddress;

    @FXML
    private JFXTextField txtCont;

    @FXML
    private JFXTextField txtType;

    @FXML
    private JFXTextField txtEmail;

    @FXML
    private JFXTextField txtPassword;

    private Pattern IdPatten;
    private Pattern NamePatten;
    private Pattern AddressPatten;
    private Pattern ContPatten;
    private Pattern TypePatten;
    private Pattern EmailPatten;
    private  Pattern PasswordPatten;

    public void CreateOnAction(ActionEvent actionEvent) throws IOException {
        boolean isIdMatched=IdPatten.matcher(txtId.getText()).matches();
        boolean isNameMatched=NamePatten.matcher(txtName.getText()).matches();
        boolean isAddressMatched=AddressPatten.matcher(txtAddress.getText()).matches();
        boolean isContMatched=ContPatten.matcher(txtCont.getText()).matches();
        boolean isTypeMatched=TypePatten.matcher(txtType.getText()).matches();
        boolean isEmailMatched=EmailPatten.matcher(txtEmail.getText()).matches();
        boolean isPasswordMatched=PasswordPatten.matcher(txtPassword.getText()).matches();

        if (isIdMatched){
            if (isNameMatched) {
                if (isAddressMatched) {
                    if (isContMatched) {
                        if (isTypeMatched) {
                            if (isEmailMatched) {
                                if (isPasswordMatched) {
                                    System.out.println("Account is Create Start");
                                }else {
                                    txtPassword.setFocusColor(Paint.valueOf("Red"));
                                    txtPassword.requestFocus();
                                }
                            }else {
                                txtEmail.setFocusColor(Paint.valueOf("Red"));
                                txtEmail.requestFocus();
                            }
                            }else{
                            txtType.setFocusColor(Paint.valueOf("Red"));
                            txtType.requestFocus();
                        }
                    }else{
                        txtCont.setFocusColor(Paint.valueOf("Red"));
                        txtCont.requestFocus();
                    }
                }else{
                    txtAddress.setFocusColor(Paint.valueOf("Red"));
                    txtAddress.requestFocus();
                }
            }else {
                txtName.setFocusColor(Paint.valueOf("Red"));
                txtName.requestFocus();
            }
        }else {
            txtId.setFocusColor(Paint.valueOf("Red"));
            txtId.requestFocus();

        }
    // new Alert(Alert.AlertType.CONFIRMATION,"Account Created");
        String id=txtId.getText();
        String name=txtName.getText();
        String address=txtAddress.getText();
        int tel=Integer.parseInt(txtCont.getText());
        String type=txtType.getText();
        String email=txtEmail.getText();
        String password=txtPassword.getText();

        Manager manager=new Manager(id,name,address,tel,type,email,password);
        try {
            boolean isCreated= ManagerModel.save(manager);
            if (isCreated){
                new Alert(Alert.AlertType.INFORMATION,"Account Has Created!...");
                 pane.getChildren().clear();
                 Navigation.navigation(Routs.MANAGER,pane);
            }else{
                new Alert(Alert.AlertType.WARNING,"Some Thing Went Wrong");
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        pane.getChildren().clear();
        Navigation.navigation(Routs.MANAGER,pane);

    }
    public void initialize(){
        IdPatten=Pattern.compile("^([S0])[0-9]{1,}$");
        NamePatten=Pattern.compile("^[a-z0-9]{4,}$");
        AddressPatten=Pattern.compile("^[A-Za-z0-9]{1,}$");
        ContPatten=Pattern.compile("^(076|074|073|075|078|072|091)([0-9]{7})$");
        TypePatten=Pattern.compile("^([manager|cashier])$");
        EmailPatten=Pattern.compile("^([a-z|0-9]{3,})[@]([a-z]{2,})\\.(com|lk)$");
        PasswordPatten=Pattern.compile("^[a-zA-Z0-9_]{8,}$");
    }

    public void btnBackOnAction(ActionEvent actionEvent) throws IOException {
        pane.getChildren().clear();
        Navigation.navigation(Routs.MANAGER,pane);
        //Navigation.navigation(Routs.MANAGER,);
    }
}
