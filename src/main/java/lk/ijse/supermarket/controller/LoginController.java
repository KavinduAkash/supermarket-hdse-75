package lk.ijse.supermarket.controller;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import lk.ijse.supermarket.App;

public class LoginController {

    @FXML
    private TextField usernameField;
    
    @FXML
    private PasswordField passwordField;
    
    @FXML
    private void login() throws IOException {
        String realUsername = "KavinduAkash";
        String realPassword = "Admin@123";
        
        String username = usernameField.getText();
        String password = passwordField.getText();
        
        System.out.println(username + " - " + password);
        
        if(username.equals(realUsername) & password.equals(realPassword)) {
            App.setRoot("CustomerView");
        }
    }
    
}
