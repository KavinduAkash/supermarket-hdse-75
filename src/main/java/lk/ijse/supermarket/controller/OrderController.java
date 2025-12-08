package lk.ijse.supermarket.controller;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import lk.ijse.supermarket.dto.CustomerDTO;
import lk.ijse.supermarket.model.CustomerModel;

public class OrderController implements Initializable {

    private final CustomerModel customerModel = new CustomerModel();
    
    @FXML
    private ComboBox<Number> comboCustomerId;
    
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
    
}
