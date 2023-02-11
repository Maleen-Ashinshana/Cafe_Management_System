package lk.ijse.cafe.controller;

import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import lk.ijse.cafe.dto.StokeItemsDTO;
import lk.ijse.cafe.service.ServiceFactory;
import lk.ijse.cafe.service.ServiceTypes;
import lk.ijse.cafe.service.custom.StokeItemService;
import lk.ijse.cafe.service.exception.DuplicateException;
import lk.ijse.cafe.service.exception.NotFoundException;
import lk.ijse.cafe.util.Animations;
import lk.ijse.cafe.views.tm.StokeItemsTm;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class StokeItemsFormController {
    public TableView<StokeItemsTm> tblStokeItems;
    public TableColumn coId;
    public TableColumn colDescription;
    public TableColumn colUnitPrice;
    public TableColumn colQty;
    public JFXTextField txtId;
    public JFXTextField txtDescription;
    public JFXTextField txtUnitPrice;
    public JFXTextField txtQty;
    public AnchorPane subPane;
    public StokeItemService itemService;

    public void initialize() throws SQLException, ClassNotFoundException {
        subPane.setVisible(false);
        Animations.fadeOut(subPane);
        itemView();
        this.itemService= ServiceFactory.getInstance().getService(ServiceTypes.STOKEITEMS);
        list();

    }
    public void list(){
        List<StokeItemsTm> stokeItemsTmList= itemService.findAllStokeItems().stream().map(stoke->new StokeItemsTm(stoke.getId(),stoke.getDescription(),stoke.getUnitPrice(),stoke.getQty())).collect(Collectors.toList());
                //itemService.findAllStokeItems().stream().map(stokeItemsDTO -> new StokeItemsTm(stokeItemsDTO.getId(),stokeItemsDTO.getDescription(),stokeItemsDTO.getUnitPrice(),stokeItemsDTO.getQty()));
        tblStokeItems.setItems(FXCollections.observableList(stokeItemsTmList));
    }
    public void itemView(){
        coId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
        colUnitPrice.setCellValueFactory(new PropertyValueFactory<>("unitPrice"));
        colQty.setCellValueFactory(new PropertyValueFactory<>("qty"));
    }
    public void btnEditeOnAction(ActionEvent actionEvent) {
    subPane.setVisible(true);
        Animations.fadeInUp(subPane);
    }

    public void txtIdOnAction(ActionEvent actionEvent) {
        StokeItemsDTO stokeItemsDTO=itemService.searchStokeItem(txtId.getText());
        if (stokeItemsDTO!=null){
            fillData(stokeItemsDTO);
        }

    }

    private void fillData(StokeItemsDTO stokeItemsDTO) {
        txtId.setText(stokeItemsDTO.getId());
        txtDescription.setText(stokeItemsDTO.getDescription());
        txtUnitPrice.setText(String.valueOf(stokeItemsDTO.getUnitPrice()));
        txtQty.setText(String.valueOf(stokeItemsDTO.getQty()));

    }

    public void btnSearchOnAction(ActionEvent actionEvent) {
        txtIdOnAction(actionEvent);
    }

    public void btnUpdateOnAction(ActionEvent actionEvent) {
        StokeItemsDTO updateItems=new StokeItemsDTO(txtId.getText(),txtDescription.getText(),Double.parseDouble(txtUnitPrice.getText()),Integer.parseInt(txtQty.getText()));
        try {
            itemService.updateStokeItem(updateItems);
            int selectedIndex=tblStokeItems.getSelectionModel().getSelectedIndex();
            tblStokeItems.getItems().remove(selectedIndex+1);
            new Alert(Alert.AlertType.INFORMATION,"update successful").show();
            txtId.clear();
            txtDescription.clear();
            txtUnitPrice.clear();
            txtQty.clear();
            list();
        }catch (NotFoundException e){
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    public void btnDeleteOnAction(ActionEvent actionEvent) {
        Alert alert=new Alert(Alert.AlertType.WARNING,"are you sure to delete the stoke item?", ButtonType.YES,ButtonType.NO);
        Optional<ButtonType> result=alert.showAndWait();
        if (result.isPresent() && result.get()==ButtonType.YES){
            try {
                itemService.deleteStokeItem(txtId.getText());
                new Alert(Alert.AlertType.INFORMATION,"delete successful").show();
                tblStokeItems.getItems().removeAll(tblStokeItems.getSelectionModel().getSelectedItem());
                txtId.clear();
                txtDescription.clear();
                txtUnitPrice.clear();
                txtQty.clear();
                list();
            }catch (NotFoundException e){
                new Alert(Alert.AlertType.WARNING,"No").show();
            }
        }
    }

    public void btnCencleOnAction(ActionEvent actionEvent) {
        subPane.setVisible(false);
        Animations.fadeOut(subPane);
    }

    public void btnAddOnAction(ActionEvent actionEvent) {
        StokeItemsDTO stokeItemsDTO=new StokeItemsDTO(txtId.getText(),txtDescription.getText(),Double.parseDouble(txtUnitPrice.getText()),Integer.parseInt(txtQty.getText()));
        try {
            if (itemService.saveStokeItem(stokeItemsDTO)==null){
                new Alert(Alert.AlertType.ERROR,"filed").show();
                return;
            }
            new Alert(Alert.AlertType.CONFIRMATION,"Added").show();
            tblStokeItems.getItems().add(new StokeItemsTm(stokeItemsDTO.getId(),stokeItemsDTO.getDescription(),stokeItemsDTO.getUnitPrice(),stokeItemsDTO.getQty()));
            txtId.clear();
            txtDescription.clear();
            txtUnitPrice.clear();
            txtQty.clear();
        }catch (DuplicateException e){
            new Alert(Alert.AlertType.ERROR,"stoke item dto already exists").show();
            txtId.selectAll();
            txtId.requestFocus();
        }
    }
}
