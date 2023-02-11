package lk.ijse.cafe.controller;

import com.jfoenix.controls.JFXPasswordField;
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
import lk.ijse.cafe.util.CrudUtil;
import lk.ijse.cafe.util.Navigation;
import lk.ijse.cafe.util.Routs;

import java.io.IOException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.regex.Pattern;

public class CachierLoginController {
    public JFXPasswordField Password;
    private Pattern txtPasswordPatten;
    public JFXTextField txtName;
    private Pattern  txtUserNamePatten;

    private JFXTextField txtUserName;
    @FXML
    private AnchorPane pane;
    private JFXPasswordField txtPassword;

    public void btnLogingOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException, IOException {
        ResultSet rst = CrudUtil.execute("select * from system_uses where system_user_id =?", txtName.getText());
        boolean uniq=false;
        String pass="";
        while (rst.next()){
            uniq=true;
            pass=rst.getString("password");

        }
        if (uniq){
            if (pass.equals(Password.getText())){
                Stage window=(Stage)pane.getScene().getWindow();
                window.close();
                URL resource = getClass().getResource("/lk/ijse/cafe/view/CashierDachBoard.fxml");
                Parent load = FXMLLoader.load(resource);
                Scene scene = new Scene(load);
                Stage stage = new Stage();
                stage.setScene(scene);
                stage.show();

            }else {
                new Alert(Alert.AlertType.CONFIRMATION,"Password is incorect").show();
            }
        }else {
            new Alert(Alert.AlertType.WARNING,"Place Enter The User Name And Password!..").show();
        }
        boolean isUserNameMatched=txtUserNamePatten.matcher(txtName.getText()).matches();
        boolean isPasswordMatched=txtPasswordPatten.matcher(Password.getText()).matches();
        if (isUserNameMatched){
            if (isPasswordMatched) {
                System.out.println("Loging Success");
            }else{
                Password.setFocusColor(Paint.valueOf("Red"));
                Password.requestFocus();
            }
        }else{
            txtName.setFocusColor(Paint.valueOf("Red"));
            txtName.requestFocus();
        }
    }

    public void btnCreateOnAction(ActionEvent actionEvent) throws IOException {
        URL resource = getClass().getResource("/lk/ijse/cafe/view/CashierCreateAcountForm.fxml");
        Parent load = FXMLLoader.load(resource);
        Scene scene=new Scene(load);
        Stage stage=new Stage();
        stage.setScene(scene);
        stage.show();
    }
    public void initialize(){
//      txtUserNamePatten=Pattern.compile("^[a-zA-Z0-9]{4,}$");
//      txtPasswordPatten=Pattern.compile("^[0-9]{3,}$");
    }

    public void btnBackOnAction(ActionEvent actionEvent) throws IOException {
        Navigation.navigation(Routs.NEW,pane);
    }
}
