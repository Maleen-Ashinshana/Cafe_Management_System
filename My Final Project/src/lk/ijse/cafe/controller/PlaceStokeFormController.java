package lk.ijse.cafe.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import lk.ijse.cafe.dto.StokeDTO;
import lk.ijse.cafe.dto.StokeItemsDTO;
import lk.ijse.cafe.dto.SupployerDTO;
import lk.ijse.cafe.model.PlaceStockModel;
import lk.ijse.cafe.model.StockModel;
import lk.ijse.cafe.model.StokeItemModel;
import lk.ijse.cafe.model.SupplyeerModel;
import lk.ijse.cafe.service.ServiceFactory;
import lk.ijse.cafe.service.ServiceTypes;
import lk.ijse.cafe.service.custom.StokeItemService;
import lk.ijse.cafe.service.custom.StokeService;
import lk.ijse.cafe.service.custom.SupployerService;
import lk.ijse.cafe.service.exception.DuplicateException;
import lk.ijse.cafe.tm.PlaceStockTM;
import lk.ijse.cafe.to.PlaceStock;
import lk.ijse.cafe.to.StockItems;
import lk.ijse.cafe.to.StockCartDeteils;
import lk.ijse.cafe.to.Supplyer;
import lk.ijse.cafe.util.Animations;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Optional;

public class PlaceStokeFormController {
    @FXML
    private AnchorPane SupplierPane;
    @FXML
    private JFXTextField txtId;
    @FXML
    private JFXTextField txtName;
    @FXML
    private JFXTextField txtAddress;
    @FXML
    private TableView tblOrderChart;
    @FXML
    private JFXButton btnAddToChart;
    @FXML
    private Label lblSupplerName;
    @FXML
    private Label lblId;
    @FXML
    private TableColumn tblAction;
    @FXML
    private Label lblDate;
    @FXML
    private JFXComboBox comSupplerId;
    @FXML
    private JFXComboBox comCode;
    @FXML
    private Label lblDescription;
    @FXML
    private Label lblUnitPrice;
    @FXML
    private JFXTextField txtQty;
    @FXML
    private TableColumn tblCode;
    @FXML
    private TableColumn tblDescription;
    @FXML
    private TableColumn tblUnitPrice;
    @FXML
    private TableColumn tblQty;
    public SupployerService supployerService;
    public StokeService stokeService;
    public StokeItemService itemService;

    private ObservableList<PlaceStockTM> obList=FXCollections.observableArrayList();

     public  void initialize() throws SQLException, ClassNotFoundException {
         loadStockDate();
         loadNextOrderId();
         loadSupployeerId();
         LoadId();
         setCellValueFactory();
         SupplierPane.setVisible(false);
         Animations.fadeOut(SupplierPane);
         this.supployerService= ServiceFactory.getInstance().getService(ServiceTypes.SUPPLOYER);

     }
   private void loadNextOrderId(){
       try {
           String id= StockModel.getNextId();
           lblId.setText(id);
       } catch (SQLException | ClassNotFoundException e) {
           throw new RuntimeException(e);
       }
   }
   private  void loadSupployeerId(){
       ObservableList<String> observableList= FXCollections.observableArrayList();
       try {
           ArrayList<String> supplyeer= SupplyeerModel.loadSupplyerId();
           for (String id:supplyeer) {
               observableList.add(id);
           }
           comSupplerId.setItems(observableList);
       } catch (SQLException | ClassNotFoundException e) {
           throw new RuntimeException(e);
       }
   }
    public void btnPlaceStokeOnAction(ActionEvent actionEvent) {
        String stockId=lblId.getText();
        String SupplierId=String.valueOf(comSupplerId.getValue());

         ArrayList<StockCartDeteils>stockCartDeteils=new ArrayList<>();

         for (int i=0;i<tblOrderChart.getItems().size();i++){
             PlaceStockTM tm=obList.get(i);
             stockCartDeteils.add(new StockCartDeteils(stockId,tm.getId(),tm.getDescription(),tm.getUnitPrice(),tm.getQty(),lblDate.getText()));
         }
        PlaceStock placeStock=new PlaceStock(SupplierId,stockId,stockCartDeteils);
        try {
            boolean isPlaced= PlaceStockModel.placeStock(placeStock);
            if (isPlaced) {
            //System.out.println("Place:"+placeStock);
                new Alert(Alert.AlertType.ERROR,"Stock Not Placed!...").show();

            }else{
                obList.clear();
                loadNextOrderId();
                new Alert(Alert.AlertType.CONFIRMATION,"Stock Placed!...").show();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void btnAddToChartOnAction(ActionEvent actionEvent) {
         String id=String.valueOf(comCode.getValue());
         int qty=Integer.parseInt(txtQty.getText());
         String desc =lblDescription.getText();
         double unitPrice=Double.parseDouble(lblUnitPrice.getText());
         txtQty.setText("");
         if (!obList.isEmpty()) {
             for (int i = 0; i < tblOrderChart.getItems().size(); i++) {
                 if (tblCode.getCellData(i).equals(id)) {
                     qty += (int) tblQty.getCellData(i);


                     obList.get(i).setQty(qty);
                     tblOrderChart.refresh();
                     return;
                 }
             }
         }
         obList.add(new PlaceStockTM(id,desc,unitPrice,qty));
         tblOrderChart.setItems(obList);
    }
    private  void loadStockDate(){
        lblDate.setText(String.valueOf(LocalDate.now()));
    }

    @FXML
    private void supplyeerIdOnAction(ActionEvent actionEvent) {
        String id=String.valueOf(comSupplerId.getValue());
        try {
            Supplyer supplyer=SupplyeerModel.search(id);
            fillSupplyeerFiels(supplyer);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    private  void fillSupplyeerFiels(Supplyer supplyer){
         lblSupplerName.setText(supplyer.getName());
    }
    private void LoadId(){
         ObservableList<String> observableList=FXCollections.observableArrayList();
        try {
            ArrayList<String> stockList= StokeItemModel.loadId();
            for (String id:stockList) {
                observableList.add(id);
            }
            comCode.setItems(observableList);
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    private void stockCodeOnAction(ActionEvent actionEvent) {
//        String code=String.valueOf(comCode.getValue());
//        try {
//            StockItems stock= StockModel.search(code);
//            fillStockFeilds(stock);
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        } catch (ClassNotFoundException e) {
//            throw new RuntimeException(e);
//        }
        StokeItemsDTO  stokeItemsDTO=itemService.searchStokeItem(txtId.getText());
        if (stokeItemsDTO!=null){
            fillStockFeilds(stokeItemsDTO);
        }
    }
    private  void fillStockFeilds(StokeItemsDTO stock){
         lblDescription.setText(stock.getDescription());
         lblUnitPrice.setText(String.valueOf(stock.getUnitPrice()));
    }
    private void setCellValueFactory(){
         tblCode.setCellValueFactory(new PropertyValueFactory<>("id"));
         tblDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
         tblUnitPrice.setCellValueFactory(new PropertyValueFactory<>("unitPrice"));
         tblQty.setCellValueFactory(new PropertyValueFactory<>("qty"));
         //tblAction.setCellValueFactory(new PropertyValueFactory<>("btnDelete"));
    }

    @FXML
    private void btnNewOnAction(ActionEvent actionEvent) throws IOException {
        SupplierPane.setVisible(true);
        Animations.fadeInUp(SupplierPane);
    }

    @FXML
    private void btnAddOnAction(ActionEvent actionEvent) {
        SupployerDTO supployerDTO=new SupployerDTO(txtId.getText(),txtName.getText(),txtAddress.getText());
        try {
            if (supployerService.saveSupployer(supployerDTO)==null){
                new Alert(Alert.AlertType.ERROR,"Fail To Save!").show();
                return;
            }
            new Alert(Alert.AlertType.CONFIRMATION,"Saved!").show();
            txtId.clear();
            txtAddress.clear();
            txtName.clear();
            loadSupployeerId();
        }catch (DuplicateException e){
            new Alert(Alert.AlertType.ERROR,"Supployer Already Saved!").show();
            txtId.selectAll();
            txtId.requestFocus();
        }
    }

    @FXML
    private void btnCencleOnAction(ActionEvent actionEvent) {
         SupplierPane.setVisible(false);
         Animations.fadeOut(SupplierPane);
    }

    @FXML
    private void btnARemoveChartOnAction(ActionEvent actionEvent) {
        int selectId=tblOrderChart.getSelectionModel().getSelectedIndex();
        tblOrderChart.getItems().remove(selectId);
    }

    public void txtSupIdOnAction(ActionEvent actionEvent) {
         SupployerDTO supployerDTO=supployerService.searchSypployer(txtId.getText());
         if (supployerDTO!=null){
             fillData(supployerDTO);
         }
    }
    private void fillData(SupployerDTO supployerDTO){
         txtId.setText(supployerDTO.getSupplyer_id());
         txtName.setText(supployerDTO.getName());
         txtAddress.setText(supployerDTO.getAddress());
    }
}