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
import lk.ijse.supermarket.dto.CustomerDTO;
import lk.ijse.supermarket.model.CustomerModel;

public class OrderController implements Initializable {

    private final CustomerModel customerModel = new CustomerModel();
    
    @FXML
    private ComboBox<Number> comboCustomerId;
    
    @FXML
    private Label lblCustomerAddressValue;

    @FXML
    private Label lblCustomerNameValue;

    @FXML
    private Label lblCustomerSalaryValue;
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        loadComboCustomerId();
        
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
    
}
