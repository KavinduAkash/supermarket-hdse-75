package lk.ijse.supermarket.controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import lk.ijse.supermarket.db.DBConnection;
import lk.ijse.supermarket.dto.CustomerDTO;
import lk.ijse.supermarket.model.CustomerModel;

public class CustomerViewController{  
    
    @FXML
    private TextField idField;
    
    @FXML
    private TextField customerName;
    
    @FXML
    private TextArea customerAddress;
    
    @FXML
    private TextField customerSalary;
    
    @FXML
    private void saveCustomer() {
        String name = customerName.getText();
        String address = customerAddress.getText();
        String salary = customerSalary.getText();
        
        try {
       
            CustomerModel customerModel = new CustomerModel();
            
            CustomerDTO cusDTO = new CustomerDTO(name, address, Double.parseDouble(salary));
            
            boolean isSaved = customerModel.saveCustomer(cusDTO);
            

            if(isSaved) {
                System.out.println("Customer saved successfully!");
                
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Customer Saved!");
                alert.setHeaderText("Customer saved successfully!");
                alert.show();
                
                cleanFields();
                
            } else {
                System.out.println("Sorry! Something went wrong!");
                
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Customer Saved!");
                alert.setHeaderText("Sorry! Something went wrong!");
                alert.show();
            }
            
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
    
    
    @FXML
    private void handleSearchCustomer(KeyEvent event) {
    
        if(event.getCode() == KeyCode.ENTER) {
            
            String id = idField.getText();
            
            try {
            
                Connection conn = DBConnection.getInstance().getConnection();
                
                String sql = "SELECT * FROM customer WHERE id=?";
                
                PreparedStatement ptsm = conn.prepareStatement(sql);
                ptsm.setInt(1, Integer.parseInt(id));
                
                ResultSet rs = ptsm.executeQuery();
                
                if(rs.next()) {
                    
                    int cusId = rs.getInt("id");
                    String cusName = rs.getString("name");
                    String cusAddress = rs.getString("address");
                    double cusSalary = rs.getDouble("salary");
                    
                    customerName.setText(cusName);
                    customerAddress.setText(cusAddress);
                    customerSalary.setText(String.valueOf(cusSalary));
                    
                } else {
                    new Alert(Alert.AlertType.ERROR, "Customer not found!").show();
                }
                
            } catch(Exception e) {
                e.printStackTrace();
            }
            
        }
        
    }
    
    @FXML
    private void handleCustomerUpdate() {
    
        String id = idField.getText();
        String name = customerName.getText();
        String address = customerAddress.getText();
        String salary = customerSalary.getText();
        
        try {
       
            CustomerModel customerModel = new CustomerModel();
            
            CustomerDTO cusDTO = new CustomerDTO(Integer.parseInt(id), name, address, Double.parseDouble(salary));
            
            boolean isUpdated = customerModel.updateCustomer(cusDTO);

            if(isUpdated) {
                
                new Alert(Alert.AlertType.INFORMATION, "Customer updated successfully!").show();
                
                cleanFields();
                
            } else {
                
                new Alert(Alert.AlertType.ERROR, "Something went wrong").show();
            
            }
            
        } catch(Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Something went wrong").show();
        }
        
    }
    
    
    @FXML
    private void handleCustomerDelete() {
    
        String id = idField.getText();
        
        try {
       
            Connection conn = DBConnection.getInstance().getConnection();
       
            String sql = "DELETE FROM customer WHERE id=?";
                
            PreparedStatement pstm = conn.prepareStatement(sql);
          
            pstm.setInt(1, Integer.parseInt(id));
                
            int result = pstm.executeUpdate();

            if(result > 0) {
                
                new Alert(Alert.AlertType.INFORMATION, "Customer deleted successfully!").show();
                
                cleanFields();
                
            } else {
                
                new Alert(Alert.AlertType.ERROR, "Something went wrong").show();
            
            }
            
        } catch(Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Something went wrong").show();
        }
        
    }
    
    
    @FXML
    private void handleCustomerReset() {
    
        cleanFields();
        
    }
    
    
    private void cleanFields() {
    
        idField.setText("");
        customerName.setText("");
        customerAddress.setText("");
        customerSalary.setText("");
        
    }
    
}
