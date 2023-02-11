package lk.ijse.cafe.util;

import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.cafe.controller.ManagerLogingFormController;

import java.io.IOException;

public class Navigation {
    private static AnchorPane pane;

    public static void navigation(Routs routs, AnchorPane pane) throws IOException {
        Navigation.pane=pane;
        Navigation.pane.getChildren().clear();
        Stage window=(Stage) Navigation.pane.getScene().getWindow();

        switch (routs){
            case MANAGER:
                window.setTitle("Manager Login");
                iniUI("ManagerLogingForm.fxml");
                break;
            case STOKE:
                window.setTitle("Stoke Manage");
                iniUI("StokeForm.fxml");
                break;
            case DASHBORAD:
                window.setTitle("Dash board");
                iniUI("DashBordForm.fxml");
                break;
            case PLACE_ORDER:
                window.setTitle("Place order");
                iniUI("PlaceOrderForm.fxml");
                break;
            case MAIN:
                window.setTitle("Main Food Form");
                iniUI("MainFoodForm.fxml");
                break;
            case SALAD:
                window.setTitle("Salad Form");
                iniUI("SaladForm.fxml");
                break;
            case DESSERT:
                window.setTitle("Dessert Form");
                iniUI("DessartForm.fxml");
                break;
            case BEVERAGE:
                iniUI("BeveregesForm.fxml");
                break;
            case NIGHT_MAIL:
                iniUI("NigthMenuForm.fxml");
                break;
//            case BREAKFAST:
//                iniUI();
            case PLACE_STOKE:
                iniUI("placeStokeForm.fxml");
                break;
            case NEW:
                iniUI("NewForm.fxml");
                break;
            case LIST_ITEM:
                iniUI("ItemView.fxml");
                break;
//            case LIST_EMPLOYEE:
//                iniUI("EmployeViewForm.fxml");
//                break;
            case CASHIER:
                iniUI("CachierLogin.fxml");
                break;
            case MANGER_CREATE:
                iniUI("ManagerLogingForm.fxml");
                break;
            case EMPLOY_VIEW:
                iniUI("EmployeViewForm.fxml");
                 break;
            case SUPPLIER:
                iniUI("supplerForm.fxml");
                break;
            case STOKEITEMS:
                iniUI("StokeItemsForm.fxml");
                break;
            default:
                new Alert(Alert.AlertType.ERROR,"UI Not Found!..");
        }
    }
    private static void iniUI(String location) throws IOException {
        Navigation.pane.getChildren().add(FXMLLoader.load(Navigation.class.getResource("/lk/ijse/cafe/view/" +location)));
    }
}
