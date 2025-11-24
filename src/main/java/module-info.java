module lk.ijse.supermarket {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.base;
    requires java.sql;
    
    opens lk.ijse.supermarket.controller to javafx.fxml;
    opens lk.ijse.supermarket.dto to javafx.base;
    exports lk.ijse.supermarket;
    exports lk.ijse.supermarket.controller;
    exports lk.ijse.supermarket.dto;
}
