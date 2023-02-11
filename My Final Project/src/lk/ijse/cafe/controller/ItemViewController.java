package lk.ijse.cafe.controller;


import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;
import lk.ijse.cafe.dto.ItemDTO;
import lk.ijse.cafe.entity.ItemEntity;
import lk.ijse.cafe.model.ItemModel;
import lk.ijse.cafe.service.ServiceFactory;
import lk.ijse.cafe.service.ServiceTypes;
import lk.ijse.cafe.service.custom.ItemService;
import lk.ijse.cafe.service.exception.DuplicateException;
import lk.ijse.cafe.service.exception.NotFoundException;
//import lk.ijse.cafe.tm.ItemTM;
import lk.ijse.cafe.views.tm.ItemsTm;
import lk.ijse.cafe.to.Item;
import lk.ijse.cafe.util.Animations;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import static javafx.collections.FXCollections.observableList;

public class ItemViewController {

    public JFXTextField txtItemCode;

    public JFXTextField txtUnitPrice;
    public JFXTextField txtxDecsription;
    public AnchorPane subPane;
    public JFXButton btnDelete;
    public JFXButton btnDeleted;
    @FXML
    private TableColumn tblUnitPrice1;
    @FXML
    private AnchorPane pane;
    @FXML
    public TableView<ItemsTm> tblItem;
    private TableView<Item> tblItems;
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

    public ItemsTm itemsTm;


    public void init(ItemsTm itemsTm) throws SQLException, ClassNotFoundException {
        fillAllFields(itemsTm);
        this.itemsTm=itemsTm;
        this.itemService=ServiceFactory.getInstance().getService(ServiceTypes.ITEM);

    }
    private void fillAllFields(ItemsTm itemsTm) throws SQLException, ClassNotFoundException {
        txtItemCode.setText(itemsTm.getCode());
        txtxDecsription.setText(itemsTm.getDescription());
        txtUnitPrice.setText(itemsTm.getUnit_price()+"");
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
         itemView();
         subPane.setVisible(false);
        Animations.fadeOut(subPane);
        codePattern= Pattern.compile("[I][0][0-9]{1,}");
        decsriptionPattern=Pattern.compile("[a-z0-9]");
        unitPricePattern=Pattern.compile("[.0-9]");
        itemService=ServiceFactory.getInstance().getService(ServiceTypes.ITEM);
        list();

    }
    public void  list(){
        List<ItemsTm> itemImList=itemService.findAll().stream().map(item->new ItemsTm(item.getCode(),item.getDescription(), item.getUnit_price())).collect(Collectors.toList());
        tblItem.setItems(FXCollections.observableList(itemImList));
    }

    private void itemView() throws SQLException, ClassNotFoundException {
        this.itemService= ServiceFactory.getInstance().getService(ServiceTypes.ITEM);

        tblItemCode.setCellValueFactory(new PropertyValueFactory<>("code"));
        tblDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
        tblUnitPrice.setCellValueFactory(new PropertyValueFactory<>("unit_price"));
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
        Alert alert=new Alert(Alert.AlertType.WARNING,"Are you sure to delete the item", ButtonType.YES,ButtonType.NO);
        Optional<ButtonType> result=alert.showAndWait();
        if (result.isPresent() && result.get()==ButtonType.YES){
            try {
                itemService.delete(txtItemCode.getText());
                new Alert(Alert.AlertType.INFORMATION,"Deleted").show();
                tblItem.getItems().removeAll(tblItem.getSelectionModel().getSelectedItem());
                txtItemCode.clear();
                txtxDecsription.clear();
                txtUnitPrice.clear();
                list();

            }catch (NotFoundException e){
                new Alert(Alert.AlertType.WARNING,"No").show();
            }
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

        ItemDTO updateItem=new ItemDTO(txtItemCode.getText(),txtxDecsription.getText(),Double.parseDouble(txtUnitPrice.getText()));
        try {
            itemService.updateItem(updateItem);
            int selectedIndex=tblItem.getSelectionModel().getSelectedIndex();
            //tblItem.getItems().add(selectedIndex,new ItemsTm(updateItem.getCode(), updateItem.getDescription(), updateItem.getUnit_price()));
            tblItem.getItems().remove(selectedIndex+1);
            new Alert(Alert.AlertType.INFORMATION,"Updated!").show();
            txtItemCode.clear();
            txtxDecsription.clear();
            txtUnitPrice.clear();
            list();

        }catch (NotFoundException e){
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        }
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

