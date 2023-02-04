package lk.ijse.cafe.controller;

import com.jfoenix.controls.JFXTextField;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.cafe.util.CashierSetDate;
import lk.ijse.cafe.util.Navigation;
import lk.ijse.cafe.util.Routs;

import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ResourceBundle;

public class CashierDachBoardController implements Initializable {
    private Label lblTotal;
    @FXML
    private Label lblTime;
    @FXML
    private AnchorPane pane;
    @FXML
    private Label lblOrderId;
    @FXML
    private Label lblCustomerId;
    @FXML
    private Label lblDate;
    @FXML
    private Label lblDescription;
    @FXML
    private Label lblUnitPrice;
    @FXML
    private JFXTextField txtQty;
    @FXML
    private TreeTableColumn colItemCode;
    @FXML
    private TreeTableColumn colDescription;
    @FXML
    private TreeTableColumn colQty;
    @FXML
    private TreeTableColumn colUnitPrice;
    @FXML
    private TreeTableColumn colTotal;
    static  Thread thread;
    public void dashBordOnAction(ActionEvent actionEvent) throws IOException {
        pane.getChildren().clear();
        Navigation.navigation(Routs.DASHBORAD,pane);

    }

    public void backOnAction(ActionEvent actionEvent) throws IOException {
        Stage window=(Stage)pane.getScene().getWindow();
        window.close();
        URL resource = getClass().getResource("/lk/ijse/cafe/view/NewForm.fxml");
        Parent load = FXMLLoader.load(resource);
        Scene scene = new Scene(load);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
    }

    public void placeOrderOnAction(ActionEvent actionEvent) throws IOException {
        Navigation.navigation(Routs.PLACE_ORDER,pane);
    }

    public void btnCustomerOnAction(ActionEvent actionEvent) throws IOException {
        URL resource = getClass().getResource("/lk/ijse/cafe/view/customerForm.fxml");
        Parent load = FXMLLoader.load(resource);
        Scene scene = new Scene(load);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
    }
    private void loadDate(Label lblDate,Label  lblTime){
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
        CashierSetDate.setDateTime(lblTime,lblDate);
    }

    public void btnOrdersOnAction(ActionEvent actionEvent) {

    }
}