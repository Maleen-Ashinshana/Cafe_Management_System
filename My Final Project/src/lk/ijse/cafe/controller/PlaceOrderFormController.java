package lk.ijse.cafe.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import lk.ijse.cafe.dao.custom.OrderDAO;
import lk.ijse.cafe.dao.custom.PaymentDAO;
import lk.ijse.cafe.dto.*;
import lk.ijse.cafe.model.*;
import lk.ijse.cafe.service.ServiceFactory;
import lk.ijse.cafe.service.ServiceTypes;
import lk.ijse.cafe.service.custom.*;
import lk.ijse.cafe.service.exception.DuplicateException;
import lk.ijse.cafe.tm.PlaceOrderTM;
import lk.ijse.cafe.util.Animations;
import lk.ijse.cafe.util.CrudUtil;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanArrayDataSource;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;

public class PlaceOrderFormController {

    public Label lblPayOrderId;
    public JFXButton btnCustomerDelete;
    @FXML
    private Label lblPayCustomerId;
    @FXML
    private JFXComboBox cmbPeyMentCustomerId;
    @FXML
    private AnchorPane payPane;
    @FXML
    private Label lblPayMeantId;
    @FXML
    private Label lblPeyMeantDate;
    @FXML
    private Label lblPeymentCustomerName;
    @FXML
    private Label lblPeyMeantTotal;
    @FXML
    private JFXTextField txtPeyMeantAmount;
    @FXML
    private JFXTextField txtPeyMantReaming;
    @FXML
    private AnchorPane customerPane;
    @FXML
    private JFXTextField txtCustomerName;
    @FXML
    private JFXTextField txtCustomerId;

    public JFXButton btnAddOnAction;
    @FXML
    private Label lblCustomerName;
    @FXML
    private JFXComboBox comCustomerId;
    @FXML
    private TableView lblOrderChart;
    @FXML
    private JFXComboBox cmbItemCode;
//    @FXML
//    private JFXComboBox cmbCashierId;
    @FXML
    private AnchorPane pane;
    @FXML
    private Label lblOrderId;
    @FXML
    private Label lblCustomerId;
    @FXML
    private Label lblDescription;
    @FXML
    private JFXTextField txtQty;
    @FXML
    private Label lblUnitPrice;
    @FXML
    private TableColumn colCode;
    @FXML
    private TableColumn colDescription;
    @FXML
    private TableColumn colQty;
    @FXML
    private TableColumn colUnitPrice;
    @FXML
    private TableColumn colTotal;
    @FXML
    private TableColumn colAction;
    @FXML
    private Label lblTotal;
    @FXML
    private Label lblOrderDate;
    private ObservableList<PlaceOrderTM> obList=FXCollections.observableArrayList();

    public CustomerService customerService;
    public PaymentService paymentService;
    public OrderDetailsService orderDetailsService;
    public OrderService orderService;
    public PaymentDAO paymentDAO;
    public ItemService itemService;
    public OrderDAO orderDAO;


    public  void initialize() throws SQLException, ClassNotFoundException {
        loadNextOrderId();
        loadPayId();
        //loadNextCustomerId();
        loadItemCode();
        loadOrderDate();
        setCellValueFactory();
        loadCustomerId();
        customerPane.setVisible(false);
        Animations.fadeOut(customerPane);
        payPane.setVisible(false);
        Animations.fadeOut(payPane);
        loadPeyMentId();
        this.customerService= ServiceFactory.getInstance().getService(ServiceTypes.CUSTOMER);
        this.paymentService=ServiceFactory.getInstance().getService(ServiceTypes.PAYMENT);
        this.itemService=ServiceFactory.getInstance().getService(ServiceTypes.ITEM);
        this.orderService=ServiceFactory.getInstance().getService(ServiceTypes.ORDER);
        //loadpayCustomerId();
    }

    private void loadPeyMentId() {
                try {
            String payMentId=getNextPayMentId();
            lblPayMeantId.setText(payMentId);
        } catch (SQLException e) {
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }
    public void loadPayId(/*PaymentDTOS paymentDTOS*/){
       //PaymentDTOS paymentDTOS1=paymentService.generateNextId("P001","P100");
//        try {
//        }
//        String id= paymentService.generateNextId("0",null);/*.generateNextId("000000000","999999999");*/
//        lblPayMeantId.setText(id);
//        String id=paymentDAO.generateNextId("0", null);
//        lblPayMeantId.setText(id);
    }
    public static String getNextOrderId() throws SQLException, ClassNotFoundException {
        ResultSet result= CrudUtil.execute("SELECT order_id FROM orders ORDER BY order_id DESC LIMIT 1");
        if (result.next()){
            return generateNextOrderId("O",result.getString(1));

        }
        return generateNextOrderId("O",null);
    }
    private static String  generateNextOrderId(String PrefId,String LsatId) {

        if (LsatId!=null){
            int newId=Integer.parseInt(LsatId.replace(PrefId,""))+1;
            return String.format(PrefId+"%03d",newId);

        }else {
            return PrefId+000;
        }
    }

    private void loadNextOrderId(){

        try {
            String orderId= getNextOrderId();
            lblOrderId.setText(orderId);
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }

//    private void loadNextCustomerId(){
//        try {
//            String customerId=PlaceOrderModel.getNextCustomerId();
//            lblCustomerId.setText(customerId);
//        } catch (SQLException | ClassNotFoundException e) {
//            throw new RuntimeException(e);
//        }
//    }
    private  void loadCustomerId(){
        ObservableList<String> observableList=FXCollections.observableArrayList();
        try {
            ArrayList<String> idList= CustomerModel.loadCustomerId();
            for (String id:idList) {
                observableList.add(id);
            }
            comCustomerId.setItems(observableList);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }
    private  void loadItemCode(){
        ObservableList<String>observableList=FXCollections.observableArrayList();
        try {
            ArrayList<String> itemList= ItemModel.LoadItemCode();
            for (String code:itemList) {
                observableList.add(code);

            }
            cmbItemCode.setItems(observableList);
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    public static String getNextPayMentId() throws SQLException, ClassNotFoundException {
        ResultSet resultSet= CrudUtil.execute("SELECT id FROM payment ORDER BY id DESC LIMIT 1");
        if (resultSet.next()){
            return genarateNextPeymentId("P",resultSet.getString(1));
        }
        return genarateNextPeymentId("P",null);
    }
    private static String genarateNextPeymentId(String PrefId,String LsatId){
        if (LsatId!=null){
            int newId=Integer.parseInt(LsatId.replace(PrefId,""))+1;
            return String.format(PrefId+"%03d",newId);

        }else {
            return PrefId+000;
        }
    }
    private void setCellValueFactory(){
        colCode.setCellValueFactory(new PropertyValueFactory<>("code"));
        colDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
        colQty.setCellValueFactory(new PropertyValueFactory<>("qty"));
        colUnitPrice.setCellValueFactory(new PropertyValueFactory<>("unitPrice"));
        colTotal.setCellValueFactory(new PropertyValueFactory<>("total"));
    }
    public void btnAddToChartOnAction(ActionEvent actionEvent) {
        String code=String.valueOf(cmbItemCode.getValue());
        int qty=Integer.parseInt(txtQty.getText());
        String desc=lblDescription.getText();
        double unitPrice=Double.parseDouble(lblUnitPrice.getText());
        double total=unitPrice*qty;
        Button btnDelete=new Button("Delete");

        txtQty.setText("");

        if (!obList.isEmpty()){
            L1:
            for (int i=0;i<lblOrderChart.getItems().size();i++){
                if(colCode.getCellData(i).equals(code)) {
                    qty += (int) colQty.getCellData(i);
                    total = unitPrice * qty;

                    obList.get(i).setQty(qty);
                    obList.get(i).setTotal(total);
                    lblOrderChart.refresh();
                    setTotal();
                    return;
                }
            }

        }

        obList.add(new PlaceOrderTM(code,desc,qty,unitPrice,total,btnDelete));
        lblOrderChart.setItems(obList);

        setTotal();
    }

    private void setTotal() {
        double total=0.0;
        for (PlaceOrderTM tm:obList) {
            total+=tm.getTotal();
        }
        lblTotal.setText(String.valueOf(total));
        lblPeyMeantTotal.setText(String.valueOf(total));

    }


    public void btnPlaceOrderOnAction(ActionEvent actionEvent) {
        String orderId=lblOrderId.getText();
        String customerId=String.valueOf(comCustomerId.getValue());//String.valueOf(lblCustomerId.getText());
        String date=lblOrderDate.getText();//String.valueOf(lblOrderDate.getText());
        double netTotal=0.0;

        ArrayList<OrderDetailsDTO> orderDetailsDTOS=new ArrayList<>();

        for (int i=0;i<lblOrderChart.getItems().size();i++){
            PlaceOrderTM tm=obList.get(i);
            orderDetailsDTOS.add(new OrderDetailsDTO(lblOrderId.getText(),tm.getCode(),tm.getQty(),tm.getUnitPrice(), lblOrderDate.getText()));
            netTotal+=tm.getTotal();
            System.out.println(tm.getCode());
        }
        //PlaceOrder placeOrder = new PlaceOrder(customerId, lblOrderId.getText(),date);
        try {
            for (OrderDetailsDTO orderDetailsDTO : orderDetailsDTOS) {
                System.out.println(orderDetailsDTO.getItem_code());
            }
            boolean isPlaced=orderService.placeOrder(lblOrderId.getText(),lblOrderDate.getText(),customerId,orderDetailsDTOS);
            Object selectedItem = comCustomerId.getSelectionModel().getSelectedItem();
            lblPayCustomerId.setText((String) selectedItem);
            lblOrderId.getText();
            lblPayOrderId.setText(lblOrderId.getText());

            if (isPlaced){
                //obList.clear();
                loadNextOrderId();
                new Alert(Alert.AlertType.CONFIRMATION,"Order Placed!...").show();
                payPane.setVisible(true);
                Animations.fadeInUp(payPane);

            }else{
                new Alert(Alert.AlertType.ERROR,"Order Not Found!...").show();

            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }



//        OrderDTO orderDTO=new OrderDTO(lblOrderId.getText(),lblOrderDate.getText(),lblPayCustomerId.getText());
//        double netTotal=0.0;
//        ArrayList<OrderDetailsDTO> orderDetailsDTOS=new ArrayList<>();
//        for (int i=0;i<lblOrderChart.getItems().size();i++) {
//            PlaceOrderTM tm = obList.get(i);
//            orderDetailsDTOS.add(new OrderDetailsDTO(lblOrderId.getText(), tm.getCode(), tm.getQty(), tm.getUnitPrice(), lblOrderDate.getText()));
//            netTotal += tm.getTotal();
//        }
//        PlaceOrderDTO  placeOrderDTO=new PlaceOrderDTO(orderDTO.getCustomer_id(),orderDTO.getOrder_id(),orderDTO.getDate());
//        try {
//
//        }


    }
    @FXML
    private void cmbCodeOncAction(ActionEvent actionEvent) {
        ItemDTO itemDTO=itemService.findItemByCode(String.valueOf(cmbItemCode.getValue()));
        if (itemDTO!=null){
            fillItemFields(itemDTO);
        }
    }
    private void fillItemFields(ItemDTO item){
        lblDescription.setText(item.getDescription());
        lblUnitPrice.setText(String.valueOf(item.getUnit_price()));

    }

    @FXML
    private void txtQtyOnAction(ActionEvent actionEvent) {
        btnAddToChartOnAction(actionEvent);

    }
    private  void loadOrderDate(){

        lblOrderDate.setText(String.valueOf(LocalDate.now()));
        lblPeyMeantDate.setText(String.valueOf(LocalDate.now()));
    }

    @FXML
    private void btnAddOnAction(ActionEvent actionEvent) throws IOException {
        customerPane.setVisible(true);
        Animations.fadeInUp(customerPane);

    }

    @FXML
    private void cmbCustomerIdOnAction(ActionEvent actionEvent) {
//        String id=String.valueOf(comCustomerId.getValue());
//        try {
//            Customer customer=CustomerModel.searchCustomer(id);
//            fillCustomerName(customer);
//            //loadCustomerId();
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        } catch (ClassNotFoundException e) {
//            throw new RuntimeException(e);
//        }
        CustomerDTO customerDTO=customerService.findById(String.valueOf(comCustomerId.getValue()));
        if (customerDTO!=null){
            fillCustomerName(customerDTO);
        }

    }
    private void fillCustomerName(CustomerDTO customer){

        lblCustomerName.setText(customer.getName());
        lblPeymentCustomerName.setText(customer.getName());
    }
//    private void calculateNetTotal(){
//        //DefaultTableModel dtm=(DefaultTableModel) lblOrderChart.getMode
//        double netTotal=0;
//        for (:) {
//
//        }
//    }

    @FXML
    private void btnAddOnACtion(ActionEvent actionEvent) {

        CustomerDTO customerDTO=new CustomerDTO(txtCustomerId.getText(),txtCustomerName.getText());
        try {
            if (customerService.saveCustomer(customerDTO)==null){
                new Alert(Alert.AlertType.ERROR,"Fail To Save Customer").show();
                return;
            }new Alert(Alert.AlertType.CONFIRMATION,"Saved!").show();
            txtCustomerId.clear();
            txtCustomerName.clear();
        }catch (DuplicateException e){
            new Alert(Alert.AlertType.ERROR,"CustomerDTO Already Exists!").show();
            txtCustomerId.selectAll();
            txtCustomerId.requestFocus();
        }
        loadCustomerId();
    }

    @FXML
    private void btnCencleOnACtion(ActionEvent actionEvent) {
        customerPane.setVisible(false);
        Animations.fadeOut(customerPane);
    }

    @FXML
    private void btnRemoveOnAction(ActionEvent actionEvent) {

        int selectId=lblOrderChart.getSelectionModel().getSelectedIndex();
        lblOrderChart.getItems().remove(selectId);
        setTotal();
    }
    public void netTotal() {
        TableView<Integer> tableView = lblOrderChart;
        int netTotal = 0;
        for (Object a : lblOrderChart.getItems()) {

        }
    }


 //    private void loadpayCustomerId(){
//        ObservableList<String>observableList=FXCollections.observableArrayList();
//        try {
//            ArrayList<String> idList=CustomerModel.loadCustomerId();
//            for (String id:idList) {
//                observableList.add(id);
//            }
//            cmbPeyMentCustomerId.setItems(observableList);
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        } catch (ClassNotFoundException e) {
//            throw new RuntimeException(e);
//        }
//    }



    @FXML
    private void btnPayOnAction(ActionEvent actionEvent) {
        loadPeyMentId();
        PaymentDTOS paymentDTOS= new PaymentDTOS(lblPayMeantId.getText(),lblPeyMeantDate.getText(),Double.parseDouble(lblPeyMeantTotal.getText()),lblPayOrderId.getText(),lblPayCustomerId.getText(),lblPeymentCustomerName.getText());

        System.out.println(lblPayMeantId.getText());
        try {
            System.out.println(lblPayMeantId.getText());
            if (paymentService.savePayment(paymentDTOS) == null) {
                new Alert(Alert.AlertType.ERROR, "Payment Failed!").show();
            }
            new Alert(Alert.AlertType.CONFIRMATION, "Sucssess!").show();

            String id1 = lblPayMeantId.getText();
            double prices = Double.parseDouble(lblPeyMeantTotal.getText());
            double amounts=Double.parseDouble(txtPeyMeantAmount.getText());
            double remains=Double.parseDouble(txtPeyMantReaming.getText());

            HashMap hashMap=new HashMap<>();
            hashMap.put("billId",id1);
            hashMap.put("total",prices);
            hashMap.put("Amount",amounts);
            hashMap.put("Remaining",remains);

            try {
                JasperDesign load = JRXmlLoader.load(this.getClass().getResourceAsStream("/lk/ijse/cafe/view/repots/New.jrxml"));
                JasperReport jasperReport = JasperCompileManager.compileReport(load);
                ObservableList<PlaceOrderTM> items = lblOrderChart.getItems();
                JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport,hashMap,new JRBeanArrayDataSource(lblOrderChart.getItems().toArray()));
                JasperViewer.viewReport(jasperPrint,false);
            } catch (JRException e1) {
                throw new RuntimeException(e1);
            }
        }catch (DuplicateException e){
            new Alert(Alert.AlertType.ERROR,"paymentDTO Already Exists!").show();

        }
    }

    @FXML
    private void btnCencleOnAction(ActionEvent actionEvent) {
        payPane.setVisible(false);
        Animations.fadeOut(payPane);

    }

    @FXML
    private void cmbPeymenyCustomerIDOnAction(ActionEvent actionEvent) {
//        String id=String.valueOf(cmbPeyMentCustomerId.getValue());
//       // PayMent payMent=PAyMentModel.search();
//        try {
//            Customer customer=CustomerModel.searchCustomer(id);
//            fillCustomerName(customer);
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        } catch (ClassNotFoundException e) {
//            throw new RuntimeException(e);
//        }
    }
    @FXML
    private void txtAmountOnAction(ActionEvent actionEvent) {
        lblPeyMeantTotal.getText();
        txtPeyMeantAmount.getText();
        txtPeyMantReaming.setText(String.valueOf(Double.parseDouble(txtPeyMeantAmount.getText())-Double.parseDouble(lblPeyMeantTotal.getText())));
    }
    public void txtCusIdOnAction(ActionEvent actionEvent) {
        CustomerDTO customerDTO=customerService.findById(txtCustomerId.getText());
        if (customerDTO!=null){
            fillDate(customerDTO);
        }
    }
    private void fillDate(CustomerDTO customerDTO){
        txtCustomerId.setText(customerDTO.getCustomer_id());
        txtCustomerName.setText(customerDTO.getName());
    }
}
