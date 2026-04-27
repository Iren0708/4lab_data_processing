module com.example.lab_4 {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.example.lab_4.gui to javafx.graphics;
    exports com.example.lab_4.core;
    exports com.example.lab_4.models;
    exports com.example.lab_4.console;
    exports com.example.lab_4.gui;
}