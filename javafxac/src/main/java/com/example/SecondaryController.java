package com.example;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

public class SecondaryController {
    @FXML
    private Label currentCapitalLabel;

    @FXML
    private GridPane gameScreen;

    private double currentCapital = 0.00;
    private String currentCapitalString = "$" + String.format("%.2f", currentCapital);

    // @FXML
    public void initialize() {
        currentCapitalLabel.setText(currentCapitalString);
        // ShopContainer lemonadeStand = new ShopContainer("Lemonade Stand", "javafxac/src/main/resources/Icons/Lemonade.jpg");
        ShopContainer lemonadeStand = new ShopContainer("Lemonade Stand");

        gameScreen.add(lemonadeStand.getContainer(), 0, 0);
    }

    @FXML
    private void handleGameExit() {
        // Exit the application
        System.exit(0);
    }

    @FXML
    private void handleSettings() {
        SettingsController settingsController = new SettingsController();
        settingsController.showSettingsWindow();  // Show the settings window when the button is clicked
    }

    private String getCurrentCapital(){
        return currentCapitalString;
    }
}