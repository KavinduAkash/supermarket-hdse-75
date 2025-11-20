module lk.ijse.supermarket {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.base;
    requires java.sql;
    
    opens lk.ijse.supermarket to javafx.fxml;
    exports lk.ijse.supermarket;
}
