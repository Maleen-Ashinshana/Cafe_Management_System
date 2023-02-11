package lk.ijse.cafe.controller;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;

import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.cafe.util.Navigation;
import lk.ijse.cafe.util.Routs;
import lk.ijse.cafe.util.SetDate;



import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ResourceBundle;

public class ManagerDashBoardFomController implements Initializable {
    @FXML
    private AnchorPane pane;
    @FXML
    private AnchorPane MainPane;
    @FXML
    private Label lblDate;
    @FXML
    private Label lblTime;
    static Thread thread;

    public void stokeOnAction(ActionEvent actionEvent) throws IOException {
        Navigation.navigation(Routs.STOKE,pane);
    }

    public void ItemsOnAction(ActionEvent actionEvent) throws IOException {
        Navigation.navigation(Routs.LIST_ITEM,pane);
    }

    public void repotOnAction(ActionEvent actionEvent) {
    }


    public void employeOnAction(ActionEvent actionEvent) throws IOException {
        //Navigation.navigation(Routs.EMPLOY_VIEW,pane);
        Stage window = (Stage) MainPane.getScene().getWindow();
        window.close();
        URL resource = getClass().getResource("/lk/ijse/cafe/view/EmployeViewForm.fxml");
        Parent load = FXMLLoader.load(resource);
        Scene scene = new Scene(load);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();


    }

    public void placeStokeOnaction(ActionEvent actionEvent) throws IOException {
        Navigation.navigation(Routs.PLACE_STOKE,pane);
    }

    public void backOnAction(ActionEvent actionEvent) throws IOException {
        //Navigation.navigation(Routs.MANAGER,pane);
        Stage window = (Stage) MainPane.getScene().getWindow();
        window.close();
        URL resource = getClass().getResource("/lk/ijse/cafe/view/ManagerLogingForm.fxml");
        Parent load = FXMLLoader.load(resource);
        Scene scene = new Scene(load);
        Stage stage = new Stage();

        stage.setScene(scene);
        stage.show();

    }
    private void loadDate(Label lblDate1,Label  lblTime1){
        thread=new Thread(()->{
            while (true){
                SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy:MM:dd");
                SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                final
                String date=dateFormat.format(new java.util.Date());
                String time=timeFormat.format(new java.util.Date());
                Platform.runLater(()->{
                    lblDate.setText(date);
                    lblTime.setText(time);
                });
            }}
        );
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        loadDate(lblDate,lblTime);
        SetDate.setDateTime(lblTime,lblDate);

    }

    public void btnCreateAccount(ActionEvent actionEvent) throws IOException {
        Stage window = (Stage) MainPane.getScene().getWindow();
        window.close();
        URL resource = getClass().getResource("/lk/ijse/cafe/view/CashierCreateAcountForm.fxml");
        Parent load = FXMLLoader.load(resource);
        Scene scene = new Scene(load);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();

    }

    public void btnSupplierOnAction(ActionEvent actionEvent) throws IOException {
   Navigation.navigation(Routs.SUPPLIER,pane);
    }

    public void btnStokeItemOnAction(ActionEvent actionEvent) throws IOException {
     Navigation.navigation(Routs.STOKEITEMS,pane);
    }
}
