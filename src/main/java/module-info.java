module lk.ijse.supermarket {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.base;

    opens lk.ijse.supermarket to javafx.fxml;
    exports lk.ijse.supermarket;
}
