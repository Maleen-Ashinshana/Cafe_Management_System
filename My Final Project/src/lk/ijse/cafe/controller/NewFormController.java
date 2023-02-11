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
        Navigation.navigation(Routs.MANAGER,pane);

    }

    public void loginCachierOnActuon(ActionEvent actionEvent) throws IOException {
        Navigation.navigation(Routs.CASHIER,pane);

    }
}
