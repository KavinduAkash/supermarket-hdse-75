package lk.ijse.supermarket.controller;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import lk.ijse.supermarket.dto.CustomerDTO;
import lk.ijse.supermarket.dto.ItemDTO;
import lk.ijse.supermarket.dto.OrderItemTM;
import lk.ijse.supermarket.model.CustomerModel;
import lk.ijse.supermarket.model.ItemModel;

public class OrderController implements Initializable {

    private final CustomerModel customerModel = new CustomerModel();
    private final ItemModel itemModel = new ItemModel();
    
    @FXML
    private ComboBox<Number> comboCustomerId;
    
    @FXML
    private ComboBox<Number> comboItemId;
    
    @FXML
    private Label lblCustomerAddressValue;

    @FXML
    private Label lblCustomerNameValue;

    @FXML
    private Label lblCustomerSalaryValue;
    
    @FXML
    private Label lblItemNameValue;

    @FXML
    private Label lblItemPriceValue;

    @FXML
    private Label lblItemQtyValue;
    
    @FXML
    private TextField qtyField;
    
    
    @FXML
    private TableColumn<OrderItemTM, String> colItemName;

    @FXML
    private TableColumn<OrderItemTM, Integer> colQty;

    @FXML
    private TableColumn<OrderItemTM, Double> colTotalPrice;

    @FXML
    private TableColumn<OrderItemTM, Double> colUnitPrice;
    
    @FXML
    private TableView<OrderItemTM> tblOrderItem;
    
    
    @FXML
    private Label lblOrderTotal;
    
    
    private final ObservableList<OrderItemTM> orderItemObList = FXCollections.observableArrayList();
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        colItemName.setCellValueFactory(new PropertyValueFactory<>("itemName"));
        colQty.setCellValueFactory(new PropertyValueFactory<>("qty"));
        colUnitPrice.setCellValueFactory(new PropertyValueFactory<>("itemPrice"));
        colTotalPrice.setCellValueFactory(new PropertyValueFactory<>("totalPrice"));
        
        loadComboCustomerId();
        loadComboItemId();
        
    }    
    
    private void loadComboCustomerId() {
        try {
        
            List<CustomerDTO> customerList = customerModel.getAllCustomers();
            
            ObservableList<Number> customerIdObList =  FXCollections.observableArrayList();
            
            for (CustomerDTO customerDTO : customerList) {
                customerIdObList.add(customerDTO.getId());
            }
            
            comboCustomerId.setItems(customerIdObList);
            
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
    
    private void loadComboItemId() {
        try {
        
            List<ItemDTO> itemList = itemModel.getAllItems();
            
            System.out.println(itemList.size());
            
            ObservableList<Number> itemIdObList =  FXCollections.observableArrayList();
            
            for (ItemDTO itemDTO : itemList) {
                itemIdObList.add(itemDTO.getId());
            }
            
            comboItemId.setItems(itemIdObList);
            
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
    
    @FXML
    void handleSelectComboCustomerId(ActionEvent event) {

        try {
        
            Number selectedId = comboCustomerId.getSelectionModel().getSelectedItem();
            int selecteCustomerId = selectedId.intValue();

            CustomerDTO customerDTO = customerModel.searchCustomer(String.valueOf(selecteCustomerId));
            
            if(customerDTO!=null) {
            
                lblCustomerNameValue.setText(customerDTO.getName());
                lblCustomerAddressValue.setText(customerDTO.getAddress());
                lblCustomerSalaryValue.setText(String.valueOf(customerDTO.getSalary()));
                
            } else {
                new Alert(Alert.AlertType.ERROR, "Customer not found!").show();
            }
            
        } catch(Exception e) {
            e.printStackTrace();
        }
        
    }
    
    @FXML
    private void handleSelectComboItemId(ActionEvent event) {

        try {
        
            Number selectedId = comboItemId.getSelectionModel().getSelectedItem();
            int selecteitemId = selectedId.intValue();

            ItemDTO itemDTO = itemModel.searchItem(selecteitemId);
            
            if(itemDTO!=null) {
            
                lblItemNameValue.setText(itemDTO.getName());
                lblItemPriceValue.setText(String.valueOf(itemDTO.getUnitPrice()));
                lblItemQtyValue.setText(String.valueOf(itemDTO.getQty()));
                
            } else {
                new Alert(Alert.AlertType.ERROR, "Item not found!").show();
            }
            
        } catch(Exception e) {
            e.printStackTrace();
        }
        
    }
    
    @FXML
    private void handleAddToCart(ActionEvent event) {

        Number selectedId = comboItemId.getSelectionModel().getSelectedItem();
        
        int itemId = selectedId.intValue();
        String itemName = lblItemNameValue.getText();
        String itemPrice = lblItemPriceValue.getText();
        String qty = qtyField.getText();
        double totalItemPrice = Double.parseDouble(itemPrice) * Integer.parseInt(qty);
        
        OrderItemTM orderItemTM = new OrderItemTM(itemId, itemName, Double.parseDouble(itemPrice), Integer.parseInt(qty), totalItemPrice);
        
        orderItemObList.add(orderItemTM);
        
        loadOrderItemTbl();
        
    }
    
    private void loadOrderItemTbl() {
    
        tblOrderItem.setItems(orderItemObList);
        calcOrderTotal();
        
    }
    
    private void calcOrderTotal() {
        
        double total = 0.0;
        
        for (OrderItemTM orderItemTM : orderItemObList) {
            total+=orderItemTM.getTotalPrice();
        }
        
        lblOrderTotal.setText(String.valueOf(total));
        
    }
    
}
