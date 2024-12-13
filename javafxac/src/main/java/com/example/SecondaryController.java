package com.example;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;


public class SecondaryController {
    @FXML
    private Label currentCapitalLabel;

    @FXML
    private GridPane gameScreen;

    private static double currentCapital = 0.00;
    private static String currentCapitalString = "$" + String.format("%.2f", currentCapital);
    private static int shopCnt = 0;

    private static boolean testing = true;

    // @FXML
    public void initialize() {
        if(testing){
            updateCurrentCapital("+", 10000.00);
        }

        currentCapitalLabel.setText(currentCapitalString);
        ShopContainer lemonadeStand = new ShopContainer("Lemonade", "javafxac/src/main/resources/Icons/Lemonade.jpg", shopCnt, this);
        ShopContainer newspaperStand = new ShopContainer("Newspaper", "javafxac/src/main/resources/Icons/Lemonade.jpg", shopCnt, this);

        gameScreen.add(lemonadeStand.getContainer(), 0, 0);
        gameScreen.add(newspaperStand.getContainer(), 0, 1);
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

    public static double getCurrentCapital(){
        return currentCapital;
    }

    public static String getCapitalString(){
        return currentCapitalString;
    }

    public Label getCapitalLabel(){
        return currentCapitalLabel;
    }

    public void updateCurrentCapital(String operation, double val){
        if(operation.equals("+")){
            currentCapital += val;
        } else if(operation.equals("-")){
            currentCapital -= val;
        } else if(operation.equals("*")){
            currentCapital *= val;
        } else if(operation.equals("/")){
            currentCapital /= val;
        }
        currentCapitalString = "$" + String.format("%.2f", currentCapital);
        currentCapitalLabel.setText(currentCapitalString);
    }

    public static void updateShopCnt(){
        shopCnt++;
    }
}