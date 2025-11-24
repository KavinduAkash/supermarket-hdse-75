module lk.ijse.supermarket {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.base;
    requires java.sql;
    
    opens lk.ijse.supermarket.controller to javafx.fxml;
    exports lk.ijse.supermarket;
    exports lk.ijse.supermarket.controller;
}
