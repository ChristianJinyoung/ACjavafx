module com.example {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;
    requires javafx.graphics;

    opens com.example to javafx.fxml;
    exports com.example;
}
