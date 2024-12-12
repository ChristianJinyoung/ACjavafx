package com.example;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class SettingsController {

    @FXML
    private Button closeSettingsButton;

    @FXML
    private void handleSettingsClose() {
        Stage stage = (Stage) closeSettingsButton.getScene().getWindow();  // Get the current stage
        stage.close();  // Close the settings window
    }

    public void showSettingsWindow() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("settings.fxml"));
            Parent root = loader.load();  // Load the settings FXML

            Stage stage = new Stage();  // Create a new Stage for the settings window
            stage.setTitle("Settings");
            stage.setScene(new Scene(root));
            stage.show();  // Show the settings window
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
