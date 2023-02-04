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
import lk.ijse.cafe.db.DBConnection;
import lk.ijse.cafe.dto.ItemDTO;
import lk.ijse.cafe.model.ItemModel;
import lk.ijse.cafe.service.ServiceFactory;
import lk.ijse.cafe.service.ServiceTypes;
import lk.ijse.cafe.service.custom.ItemService;
import lk.ijse.cafe.service.exception.DuplicateException;
import lk.ijse.cafe.service.exception.NotFoundException;
import lk.ijse.cafe.tm.ItemTM;
import lk.ijse.cafe.to.Item;

import java.io.IOException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.regex.Pattern;

public class ItemFormController {
    @FXML
    private AnchorPane pana;

    @FXML
    private JFXTextField txtCode;

    @FXML
    private JFXTextField txtDescription;

    @FXML
    private JFXTextField txtUnitPrice;
    public ItemService itemService;

public ItemTM itemTM;

    @FXML
    private void btnAddOnAction(javafx.event.ActionEvent actionEvent) {





//        String code=this.txtCode.getText();
//        String description=this.txtDescription.getText();
//        double unitPrice=Double.parseDouble(this.txtUnitPrice.getText());
//         Item item=new Item(code,description,unitPrice);
//
//
//        try {
//            boolean isAdded=ItemModel.isAdded(item);
//            if (isAdded){
//                new Alert(Alert.AlertType.CONFIRMATION,"Add Success!..").show();
//            }else {
//                new Alert(Alert.AlertType.WARNING,"Something happened!").show();
//            }
//        } catch (SQLException | ClassNotFoundException e) {
//            throw new RuntimeException(e);
//        }
//        ItemDTO itemDTO=new ItemDTO(txtCode.getText(),txtDescription.getText(),Double.parseDouble(txtUnitPrice.getText()));
//        try {
//            if(itemService.saveItem(itemDTO)==null){
//                new Alert(Alert.AlertType.ERROR,"Field").show();
//                return;
//            }new Alert(Alert.AlertType.CONFIRMATION,"Added").show();
//        }catch (DuplicateException e){
//            new Alert(Alert.AlertType.ERROR,"Item Already exists").show();
//        }
    }

    @FXML
    private void btnSearchOnAction(ActionEvent actionEvent) {

        codeOnAction(actionEvent);
    }

    @FXML
    private void btnUpdaeOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
//           String code=txtCode.getText();
//           String description=txtDescription.getText();
//           double unitPrice=Double.parseDouble(txtUnitPrice.getText());
//
//           Item item=new Item(code,description,unitPrice);
//        try {
//            boolean isUpdate=ItemModel.updateItem(item);
//            if (isUpdate){
//                new Alert(Alert.AlertType.CONFIRMATION,"Updated!...").show();
//            }else{
//                new Alert(Alert.AlertType.WARNING,"Some thing went wrong").show();
//            }
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        } catch (ClassNotFoundException e) {
//            throw new RuntimeException(e);
//        }
        ItemDTO updateItem=new ItemDTO(itemTM.getCode(),txtDescription.getText(),Double.parseDouble(txtUnitPrice.getText()));
        try {
            if (itemService.updateItem(updateItem)!=null){
                new Alert(Alert.AlertType.INFORMATION,"Updated!").show();
            }else{
                new Alert(Alert.AlertType.ERROR,"Fail To Update").show();
            }
        }catch (NotFoundException e){
            new Alert(Alert.AlertType.ERROR,"Item not Found").show();
        }
        this.itemTM=itemTM;
        fillData(itemTM);
        itemService= ServiceFactory.getInstance().getService(ServiceTypes.ITEM);

    }

    @FXML
    private void btnDeleteOnAction(ActionEvent actionEvent) {
        try {
            boolean isDelete=ItemModel.deleteItem(txtCode.getText());
            if (isDelete){
                new Alert(Alert.AlertType.CONFIRMATION,"Item Deleted!...").show();
            }else{
                new Alert(Alert.AlertType.WARNING,"some thing went wrong!...").show();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    private void btnViewOnAction(ActionEvent actionEvent) throws IOException {
        Stage window=(Stage)pana.getScene().getWindow();
        window.close();
        URL resource = getClass().getResource("/lk/ijse/cafe/view/ItemView.fxml");
        Parent load = FXMLLoader.load(resource);
        Scene scene = new Scene(load);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void codeOnAction(ActionEvent actionEvent) {
         String code=txtCode.getText();
        try {
            Item item=ItemModel.searchItem(code);
            if (item != null){
                fillData(itemTM);
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private void fillData(Item item) {
        txtCode.setText(item.getCode());
        txtDescription.setText(item.getDescription());
        txtUnitPrice.setText(String.valueOf(item.getUnitPrice()));
    }

}
