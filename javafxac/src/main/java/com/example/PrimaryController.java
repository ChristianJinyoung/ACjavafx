package com.example;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;

public class PrimaryController {

    @FXML
    private void handleLogin() {
        // Display a login dialog or action
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Login");
        alert.setHeaderText(null);
        alert.setContentText("Login functionality is not implemented yet.");
        alert.showAndWait();
    }

    @FXML
    private void handleEnter() throws IOException {
        // Switch to the game screen
        App.setRoot("secondary");
    }

    @FXML
    private void handleExit() {
        // Exit the application
        System.exit(0);
    }
}
