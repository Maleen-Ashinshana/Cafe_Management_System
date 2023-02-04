package lk.ijse.cafe.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
//import lk.ijse.cafe.util.Navigation;
import javafx.stage.Stage;
//import lk.ijse.cafe.util.Navigation;
import lk.ijse.cafe.util.Navigation;
import lk.ijse.cafe.util.Routs;

import java.io.IOException;
import java.net.URL;

public class NewFormController {
    @FXML
    private AnchorPane pane;

    @FXML
    private void ManagerOnAction(ActionEvent actionEvent) throws IOException {
// pane.getChildren().clear();
 //pane.getChildren().removeAll();
      //Navigation.navigation(Routs.MANAGER,pane);
//      Navigation.navigation(Routs.MANAGER,"view/ManagerLogingForm.fxml");


//        URL resource = getClass().getResource("/view/ManagerLogingForm.fxml");
//        Parent load = FXMLLoader.load(resource);
//        Scene scene=new Scene(load);
//        Stage stage=new Stage();
//        stage.setScene(scene);
//        stage.setTitle("Manager Loging Inter Face");
//        stage.show();
//        URL resource = getClass().getResource("/lk/ijse/cafe/view/ManagerLogingForm.fxml");
//        Parent load = FXMLLoader.load(resource);
//        Scene scene = new Scene(load);
//        Stage stage = new Stage();
//        stage.setScene(scene);
//        stage.show();
        Navigation.navigation(Routs.MANAGER,pane);

    }

    public void loginCachierOnActuon(ActionEvent actionEvent) throws IOException {
//        pane.getChildren().remove(1,2,3,4,5);
        //pane.getChildren().removeAll();
        //pane.getChildren().isEmpty();
//        URL resource = getClass().getResource("/lk/ijse/cafe/view/CachierLogin.fxml");
//        Parent load = FXMLLoader.load(resource);
//        Scene scene = new Scene(load);
//        Stage stage = new Stage();
//        stage.setScene(scene);
//        stage.show();
        Navigation.navigation(Routs.CASHIER,pane);

    }
}
