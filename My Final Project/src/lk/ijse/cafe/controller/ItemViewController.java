package lk.ijse.cafe.controller;


import com.jfoenix.controls.JFXTextField;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;
import lk.ijse.cafe.dto.ItemDTO;
import lk.ijse.cafe.model.ItemModel;
import lk.ijse.cafe.service.ServiceFactory;
import lk.ijse.cafe.service.ServiceTypes;
import lk.ijse.cafe.service.custom.ItemService;
import lk.ijse.cafe.service.exception.DuplicateException;
import lk.ijse.cafe.tm.ItemTM;
import lk.ijse.cafe.views.tm.ItemsTm;
import lk.ijse.cafe.to.Item;
import lk.ijse.cafe.util.Animations;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.regex.Pattern;

public class ItemViewController {

    public JFXTextField txtItemCode;

    public JFXTextField txtUnitPrice;
    public JFXTextField txtxDecsription;
    public AnchorPane subPane;
    @FXML
    private TableColumn tblUnitPrice1;
    @FXML
    private AnchorPane pane;
    @FXML
    private TableView<Item> tblItem;
    @FXML
    private TableColumn tblItemCode;
    @FXML
    private TableColumn tblDescription;
    @FXML
    private TableColumn tblUnitPrice;
    private Pattern codePattern;
    private  Pattern decsriptionPattern;
    private  Pattern unitPricePattern;
    public ItemService itemService;
    public ItemTM itemTM;
    public ItemViewController itemViewController;

    public void init(ItemTM itemTM) throws SQLException, ClassNotFoundException {
        this.itemTM=itemTM;
        //this.itemViewController=itemViewController;
        //fillData(itemTM);
        //fillAllFields(itemTM);
        itemService=ServiceFactory.getInstance().getService(ServiceTypes.ITEM);


    }
    private void fillAllFields(ItemsTm itemTM) throws SQLException, ClassNotFoundException {
        //this.itemService= ServiceFactory.getInstance().getService(ServiceTypes.ITEM);
        System.out.println();
        txtItemCode.setText(itemTM.getCode());
        txtxDecsription.setText(itemTM.getDescription());
        txtUnitPrice.setText(String.valueOf(itemTM.getUnit_price()));
       // txtUnitPrice.setText(String.valueOf(item.getUnitPrice()));
    }
    public void btnBackOnAction(ActionEvent actionEvent) throws IOException {
        Stage window=(Stage)pane.getScene().getWindow();
        window.close();
        URL resource = getClass().getResource("/lk/ijse/cafe/view/ManagerDashBoardFom.fxml");
        Parent load = FXMLLoader.load(resource);
        Scene scene = new Scene(load);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
    }

    public void initialize() throws SQLException, ClassNotFoundException {
        //init(i);
        init(itemTM);
         itemView();
         subPane.setVisible(false);
        Animations.fadeOut(subPane);
        codePattern= Pattern.compile("[I][0][0-9]{1,}");
        decsriptionPattern=Pattern.compile("[a-z0-9]");
        unitPricePattern=Pattern.compile("[.0-9]");
    }

    private void itemView() throws SQLException, ClassNotFoundException {
        //this.itemService= ServiceFactory.getInstance().getService(ServiceTypes.ITEM);

        tblItemCode.setCellValueFactory(new PropertyValueFactory<>("code"));
        tblDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
        tblUnitPrice.setCellValueFactory(new PropertyValueFactory<>("unitPrice"));

    }

    public void btnRealoadOnAction(ActionEvent actionEvent) {
        try {
            ObservableList<Item> itemList= ItemModel.getAllItems();
            tblItem.setItems(itemList);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void btnAddOnAction(ActionEvent actionEvent) {
        boolean isCodeMached=codePattern.matcher(txtItemCode.getText()).matches();
        boolean isdescriptionMached=decsriptionPattern.matcher(txtxDecsription.getText()).matches();
        boolean isunitPriceMached=unitPricePattern.matcher(txtUnitPrice.getText()).matches();

        if (isCodeMached){
            if (isdescriptionMached) {
                if (isunitPriceMached){
                    System.out.println("Start");
                }else{
                    txtUnitPrice.setFocusColor(Paint.valueOf("Red"));
                    txtUnitPrice.requestFocus();
                }
            }else{
                //txtxDecsription.setFocusColor(Paint.valueOf("Red"));
                //txtxDecsription.requestFocus();
            }
        }else{
            txtItemCode.setFocusColor(Paint.valueOf("Red"));
            txtItemCode.requestFocus();
        }
        ItemDTO itemDTO=new ItemDTO(txtItemCode.getText(),txtxDecsription.getText(),Double.parseDouble(txtUnitPrice.getText()));
        try {
            if(itemService.saveItem(itemDTO)==null){
                new Alert(Alert.AlertType.ERROR,"Field").show();
                return;
            }new Alert(Alert.AlertType.CONFIRMATION,"Added").show();
        }catch (DuplicateException e){
            new Alert(Alert.AlertType.ERROR,"Item Already exists").show();
        }
    }

    public void btnDeleteOnAction(ActionEvent actionEvent) {
        try {
            boolean isDelete=ItemModel.deleteItem(txtItemCode.getText());
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

    public void btnUpadteOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {

        boolean isCodeMached=codePattern.matcher(txtItemCode.getText()).matches();
        boolean isdescriptionMached=decsriptionPattern.matcher(txtxDecsription.getText()).matches();
        boolean isunitPriceMached=unitPricePattern.matcher(txtUnitPrice.getText()).matches();

        if (isCodeMached){
            if (isdescriptionMached) {
                if (isunitPriceMached){
                    System.out.println("Start");
                }else{
                    txtUnitPrice.setFocusColor(Paint.valueOf("Red"));
                    txtUnitPrice.requestFocus();
                }
            }else{
                //txtxDecsription.setFocusColor(Paint.valueOf("Red"));
                //txtxDecsription.requestFocus();
            }
        }else{
            txtItemCode.setFocusColor(Paint.valueOf("Red"));
            txtItemCode.requestFocus();
        }

//        String code=txtItemCode.getText();
//        String description=txtxDecsription.getText();
//        double unitPrice=Double.parseDouble(txtUnitPrice.getText());

//        Item item=new Item(code,description,unitPrice);
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
//        ItemDTO updateItem=new ItemDTO(txtItemCode.getText(),txtxDecsription.getText(),Double.parseDouble(txtUnitPrice.getText()));
//        try {
//            if (itemService.updateItem(updateItem)!=null){
//                int selectedIndex=tblItem.getSelectionModel().getSelectedIndex();
//                tblItem.getItems().add(selectedIndex,new ItemTM(updateItem.getCode(), updateItem.getDescription(), updateItem.getUnit_price()));
//
//                new Alert(Alert.AlertType.INFORMATION,"Updated!").show();
//            }else{
//                new Alert(Alert.AlertType.ERROR,"Fail To Update").show();
//            }
//        }catch (NotFoundException e){
//            new Alert(Alert.AlertType.ERROR,"Item not Found").show();
//        }
//        this.itemTM=itemTM;
//        fillData(itemTM);
//        itemService=ServiceFactory.getInstance().getService(ServiceTypes.ITEM);
    }

    public void btnCencleOnAction(ActionEvent actionEvent) {
        subPane.setVisible(false);
        Animations.fadeOut(subPane);
    }

    public void btnEditOnAction(ActionEvent actionEvent) {
          subPane.setVisible(true);
          Animations.fadeInUp(subPane);
    }
    public void txtCodeOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        ItemDTO itemDTO=itemService.findItemByCode(txtItemCode.getText());
        if (itemDTO!=null){
            fillData(itemDTO);
        }
    }
    private void fillData(ItemDTO itemDTO) throws SQLException, ClassNotFoundException {
        this.itemService= ServiceFactory.getInstance().getService(ServiceTypes.ITEM);
        txtItemCode.setText(itemDTO.getCode());
        txtxDecsription.setText(itemDTO.getDescription());
        txtUnitPrice.setText(String.valueOf(itemDTO.getUnit_price()));
    }

    public void btnSearchOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        txtCodeOnAction(actionEvent);
    }
}

