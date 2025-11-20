package lk.ijse.supermarket;

import java.sql.Connection;
import java.sql.PreparedStatement;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class CustomerViewController{  
    
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
                
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Customer Saved!");
                alert.setHeaderText("Customer saved successfully!");
                alert.show();
                
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
    
}
