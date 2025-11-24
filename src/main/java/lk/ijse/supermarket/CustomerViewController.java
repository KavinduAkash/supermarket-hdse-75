package lk.ijse.supermarket;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

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
       
            Connection conn = DBConnection.getInstance().getConnection();
       
            String sql = "INSERT INTO customer (name, address, salary) VALUES (?,?,?)";
                
            PreparedStatement pstm = conn.prepareStatement(sql);
                
            pstm.setString(1, name);
            pstm.setString(2, address);
            pstm.setDouble(3, Double.parseDouble(salary));
                
            int result = pstm.executeUpdate();

            if(result > 0) {
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
       
            Connection conn = DBConnection.getInstance().getConnection();
       
            String sql = "UPDATE customer SET name=?, address=?, salary=? WHERE id=?";
                
            PreparedStatement pstm = conn.prepareStatement(sql);
                
            pstm.setString(1, name);
            pstm.setString(2, address);
            pstm.setDouble(3, Double.parseDouble(salary));
            pstm.setInt(4, Integer.parseInt(id));
                
            int result = pstm.executeUpdate();

            if(result > 0) {
                
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
    
    
    private void cleanFields() {
    
        idField.setText("");
        customerName.setText("");
        customerAddress.setText("");
        customerSalary.setText("");
        
    }
    
}
