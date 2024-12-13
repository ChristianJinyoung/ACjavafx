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
    private static int rows = 10;

    private static boolean testing = true;

    // @FXML
    public void initialize() {
        if(testing){
            updateCurrentCapital("+", 10000.00);
        }

        currentCapitalLabel.setText(currentCapitalString);

        //Column 1
        ShopContainer lemonade = new ShopContainer("Lemonade Stand", "javafxac/src/main/resources/Icons/Lemonade.jpg", shopCnt, this);
        gameScreen.add(lemonade.getContainer(), (shopCnt - 1)/rows, (shopCnt - 1)%rows);
        
        ShopContainer newspaper = new ShopContainer("Newspaper Stand", "javafxac/src/main/resources/Icons/Lemonade.jpg", shopCnt, this);
        gameScreen.add(newspaper.getContainer(), (shopCnt - 1)/rows, (shopCnt - 1)%rows);

        ShopContainer bakery = new ShopContainer("Bakery", "javafxac/src/main/resources/Icons/Lemonade.jpg", shopCnt, this);
        gameScreen.add(bakery.getContainer(), (shopCnt - 1)/rows, (shopCnt - 1)%rows);

        ShopContainer lumber = new ShopContainer("Lumber Yard", "javafxac/src/main/resources/Icons/Lemonade.jpg", shopCnt, this);
        gameScreen.add(lumber.getContainer(), (shopCnt - 1)/rows, (shopCnt - 1)%rows);

        ShopContainer test1 = new ShopContainer("test1", "javafxac/src/main/resources/Icons/Lemonade.jpg", shopCnt, this);
        gameScreen.add(test1.getContainer(), (shopCnt - 1)/rows, (shopCnt - 1)%rows);

        ShopContainer test2 = new ShopContainer("test2", "javafxac/src/main/resources/Icons/Lemonade.jpg", shopCnt, this);
        gameScreen.add(test2.getContainer(), (shopCnt - 1)/rows, (shopCnt - 1)%rows);

        ShopContainer test3 = new ShopContainer("test3", "javafxac/src/main/resources/Icons/Lemonade.jpg", shopCnt, this);
        gameScreen.add(test3.getContainer(), (shopCnt - 1)/rows, (shopCnt - 1)%rows);

        ShopContainer test4 = new ShopContainer("test4", "javafxac/src/main/resources/Icons/Lemonade.jpg", shopCnt, this);
        gameScreen.add(test4.getContainer(), (shopCnt - 1)/rows, (shopCnt - 1)%rows);

        ShopContainer test5 = new ShopContainer("test5", "javafxac/src/main/resources/Icons/Lemonade.jpg", shopCnt, this);
        gameScreen.add(test5.getContainer(), (shopCnt - 1)/rows, (shopCnt - 1)%rows);

        ShopContainer test6 = new ShopContainer("test6", "javafxac/src/main/resources/Icons/Lemonade.jpg", shopCnt, this);
        gameScreen.add(test6.getContainer(), (shopCnt - 1)/rows, (shopCnt - 1)%rows);

        //Column 2
        ShopContainer tech = new ShopContainer("Tech Store", "javafxac/src/main/resources/Icons/Lemonade.jpg", shopCnt, this);
        gameScreen.add(tech.getContainer(), (shopCnt - 1)/rows, (shopCnt - 1)%rows);
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