package lk.ijse.cafe.controller;

import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;
import lk.ijse.cafe.util.CrudUtil;
import lk.ijse.cafe.util.Navigation;
import lk.ijse.cafe.util.Routs;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.regex.Pattern;

public class ManagerLogingFormController extends Component {
    @FXML
    private Label lblUserName;
    @FXML
    private Label lblPassword;
    @FXML
    private  AnchorPane pane;
    @FXML
    private JFXTextField txtUserName;
     private Pattern txtUserNamePattern;

    @FXML
    private JFXPasswordField txtPassword;
    private  Pattern txtPasswordPattern;
    private  JFXTextField txtId;

    @FXML
    private void logingOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException, IOException {
        ResultSet rst=CrudUtil.execute("SELECT * FROM system_uses WHERE name=?",txtUserName.getText());
        boolean uniq=false;
        String pass="";
        while (rst.next()){
            uniq=true;
            pass=rst.getString("password");
        }
        if (uniq){
            if (pass.equals(txtPassword.getText())){
                Stage window=(Stage)pane.getScene().getWindow();
                window.close();
                URL resource = getClass().getResource("/lk/ijse/cafe/view/ManagerDashBoardFom.fxml");
                Parent load = FXMLLoader.load(resource);
                Scene scene=new Scene(load);
                Stage stage=new Stage();
                stage.setScene(scene);
                stage.show();
            }else{
                new Alert(Alert.AlertType.WARNING,"Your Password Is Incorrect").show();
            }
        }else{
            new Alert(Alert.AlertType.WARNING,"Place Enter The UserName And Password").show();
        }
        boolean isUserNameMatched=txtUserNamePattern.matcher(txtUserName.getText()).matches();
        boolean isPasswordMatched=txtPasswordPattern.matcher(txtPassword.getText()).matches();
        if (isUserNameMatched){
            if (isPasswordMatched){
                System.out.println("Login Success");

            }else{
                txtPassword.setFocusColor(Paint.valueOf("Red"));
                //lblPassword.setText("Invalid Password");
                txtPassword.requestFocus();
            }
        }else{
            txtUserName.setFocusColor(Paint.valueOf("Red"));
            //lblUserName.setText("Invalid User Name");
            txtUserName.requestFocus();
        }

    }


    @FXML
    private void createAccountOnAction(ActionEvent actionEvent) throws IOException {

//        URL resource = getClass().getResource("/lk/ijse/cafe/view/ManagerCreateAccountForm.fxml");
//        Parent load = FXMLLoader.load(resource);
//        Scene scene = new Scene(load);
//        Stage stage = new Stage();
//        stage.setScene(scene);
//        stage.show();
        Parent load = FXMLLoader.load(getClass().getResource("/lk/ijse/cafe/view/ManagerCreateAccountForm.fxml"));
         pane.getChildren().clear();
         pane.getChildren().add(load);

         //Navigation.navigation(Routs.MANAGER,pane);
    }

    private void idOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
//        String id=txtId.getText();
//        ResultSet rst=CrudUtil.
//        if (rst.next()) {
//            txtUserName.setText(rst.getString("name"));
//        }else {
//            JOptionPane.showMessageDialog(this,"Manager not found!...");
//        }
//    }
//        try {
//
//
//            String id = txtId.getId();
//
//            String SQL = "Select * from system_uses where system_user_id=?";
//            Class.forName("com.mysql.cj.jdbc.Driver");
//            Connection connection = DriverManager.getConnection("jdbc:mysql://localHost/cafe", "root", "1234");
//            PreparedStatement stm = connection.prepareStatement(SQL);
//            stm.setObject(1, id);
//
//            ResultSet rst = stm.executeQuery();
//            if (rst.next()) {
//                txtUserName.setText(rst.getString("name"));
//
//            } else {
//                //JOptionPane.showMessageDialog(this, "Manager Not Found!.,..");
//                System.out.println("Manager not found1!...");
//                //JOptionPane.showMessageDialog("Manager Not Founds!...");
//            }
//        }catch (ClassNotFoundException|SQLException ex){
//            JOptionPane.showMessageDialog(this,ex.getMessage());
//        }
//    }

    }
    public void initialize(){
        txtUserNamePattern= Pattern.compile("^[a-zA-Z0-9]{4,}$");
        txtPasswordPattern=Pattern.compile("^[0-9]{3,}$");

    }

    @FXML
    private void btnBackOnAction(ActionEvent actionEvent) throws IOException {
        Navigation.navigation(Routs.NEW,pane);
    }
}
