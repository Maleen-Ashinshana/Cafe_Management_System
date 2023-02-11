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
import lk.ijse.cafe.dto.StokeDetailsDTO;
import lk.ijse.cafe.dto.StokeItemsDTO;
import lk.ijse.cafe.dto.SupployerDTO;

import lk.ijse.cafe.model.StokeItemModel;

import lk.ijse.cafe.model.SupplyeerModel;
import lk.ijse.cafe.service.ServiceFactory;
import lk.ijse.cafe.service.ServiceTypes;
import lk.ijse.cafe.service.custom.StokeItemService;
import lk.ijse.cafe.service.custom.StokeService;
import lk.ijse.cafe.service.custom.SupployerService;
import lk.ijse.cafe.service.exception.DuplicateException;
import lk.ijse.cafe.tm.PlaceStockTM;
import lk.ijse.cafe.util.Animations;
import lk.ijse.cafe.util.CrudUtil;
import lk.ijse.cafe.views.tm.StokeItemsTm;

import java.io.IOException;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

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
    public StokeItemService stokeitemService;
    //public StokeItemService itemService;

    private ObservableList<StokeItemsTm> obList=FXCollections.observableArrayList();

     public  void initialize() throws SQLException, ClassNotFoundException {
         loadStockDate();
         loadNextOrderId();
         loadSupployeerId();
         LoadId();
         setCellValueFactory();
         SupplierPane.setVisible(false);
         Animations.fadeOut(SupplierPane);
         this.supployerService= ServiceFactory.getInstance().getService(ServiceTypes.SUPPLOYER);
         this.stokeitemService=ServiceFactory.getInstance().getService(ServiceTypes.STOKEITEMS);
         this.stokeService=ServiceFactory.getInstance().getService(ServiceTypes.STOKE);

     }
    public static String getNextId() throws SQLException, ClassNotFoundException {
        ResultSet result= CrudUtil.execute("SELECT stok_id FROM stok ORDER BY stok_id DESC LIMIT 1");
        if (result.next()){
            return generateNextId("E",result.getString(1));

        }
        return generateNextId("E",null);

    }
    private static String generateNextId(String PrefId,String LsatId){
        if (LsatId!=null){
            int newId=Integer.parseInt(LsatId.replace(PrefId,""))+1;
            return String.format(PrefId+"%03d",newId);

        }else {
            return PrefId+000;
        }
    }
   private void loadNextOrderId(){
       try {
           String id= getNextId();
           lblId.setText(id);
       } catch (SQLException | ClassNotFoundException e) {
           throw new RuntimeException(e);
       }
   }
   private  void loadSupployeerId() throws SQLException, ClassNotFoundException {
       ObservableList<String> observableList= FXCollections.observableArrayList();
       //System.out.println(comSupplerId.getSelectionModel().getSelectedIndex()+"***************");
       ArrayList<String> supplyeer= SupplyeerModel.loadSupplyerId();
       for (String id:supplyeer) {
           observableList.add(id);
       }
       comSupplerId.setItems(observableList);
   }
    public void btnPlaceStokeOnAction(ActionEvent actionEvent) {
        String stockId=lblId.getText();
        System.out.println(stockId);
        String SupplierId=String.valueOf(comSupplerId.getValue());

         ArrayList<StokeDetailsDTO> stokeItems=new ArrayList<>();
        for (StokeItemsTm itemsTm : obList) {
            stokeItems.add(new StokeDetailsDTO(stockId,itemsTm.getId(), itemsTm.getUnitPrice(), itemsTm.getQty()));

        }

//         for (int i=0;i<tblOrderChart.getItems().size();i++){
//             StokeItemsTm tm=obList.get(i);
//             stockCartDeteils.add(new StokeDetailsDTO(comCode.getSelectionModel().getSelectedItem().toString(),tm.getId(),tm.getUnitPrice(),tm.getQty()));
//             System.out.println(stockId);
//         }
        //StokeDTO placeStock=new StokeDTO(comCode.getSelectionModel().getSelectedItem().toString(),lblDate.getText(),String.valueOf(comSupplerId.getSelectionModel().getSelectedItem()));
        StokeDTO placeStock=new StokeDTO(lblId.getText(),lblDate.getText(),String.valueOf(comSupplerId.getSelectionModel().getSelectedItem()));
        try {
            System.out.println(placeStock.getStoke_id());
            boolean isPlaced= stokeService.placeStock(placeStock,stokeItems);
            if (isPlaced) {
            //System.out.println("Place:"+placeStock);

                obList.clear();
                loadNextOrderId();
                new Alert(Alert.AlertType.CONFIRMATION,"Stock Placed!...").show();

            }else{
                new Alert(Alert.AlertType.ERROR,"Stock Not Placed!...").show();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void btnAddToChartOnAction(ActionEvent actionEvent) {
         String id=comCode.getSelectionModel().getSelectedItem().toString();
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
         obList.add(new StokeItemsTm(id,desc,unitPrice,qty));
         tblOrderChart.setItems(obList);
    }
    private  void loadStockDate(){
        lblDate.setText(String.valueOf(LocalDate.now()));
    }

    @FXML
    private void supplyeerIdOnAction(ActionEvent actionEvent) {

        SupployerDTO supployerDTO=supployerService.findByid(String.valueOf(comSupplerId.getValue()));

        if (supployerDTO!=null){
            fillSupplyeerFiels(supployerDTO);
        }
    }
    private  void fillSupplyeerFiels(SupployerDTO supplyer){
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
         StokeItemsDTO stokeItemsDTO=stokeitemService.searchStokeItem(String.valueOf(comCode.getValue()));
        //StokeItemsDTO  stokeItemsDTO=itemService.searchStokeItem(String.valueOf(comCode.getValue()));
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
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
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