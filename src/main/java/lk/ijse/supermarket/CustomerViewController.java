package lk.ijse.supermarket;

import javafx.fxml.FXML;
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
        
        System.out.println(name + " - " + address + " - " + salary);
    }
    
}
