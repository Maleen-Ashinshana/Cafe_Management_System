package lk.ijse.cafe.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import lk.ijse.cafe.util.Navigation;
import lk.ijse.cafe.util.Routs;

import java.io.IOException;

public class DashBordFormController {
    public AnchorPane pane2;
    @FXML
    private AnchorPane pane;
    @FXML
    private AnchorPane pane1;

    public void manOnAction(ActionEvent actionEvent) throws IOException {
        Navigation.navigation(Routs.MAIN,pane2);
    }

    public void dessertsOnAction(ActionEvent actionEvent) throws IOException {
        Navigation.navigation(Routs.DESSERT,pane2);
    }

    public void beveragesOnAction(ActionEvent actionEvent) throws IOException {
        Navigation.navigation(Routs.BEVERAGE,pane2);
    }

    public void nigthOnAction(ActionEvent actionEvent) throws IOException {
        Navigation.navigation(Routs.NIGHT_MAIL,pane2);
    }

    public void saladOnAction(ActionEvent actionEvent) throws IOException {
        Navigation.navigation(Routs.SALAD,pane2);
    }

    public void breakFastOnAction(ActionEvent actionEvent) {
    }
}
