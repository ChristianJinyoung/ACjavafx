package com.example;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;


public class SecondaryController {
    @FXML
    private Label currentCapitalLabel;

    @FXML
    private GridPane gameScreen;

    private static double currentCapital = 0.00;
    private static String currentCapitalString = "$" + String.format("%.2f", currentCapital);
    private static int shopCnt = 0;
    private static int rows = 10;

    ManagerController managerController;

    private static boolean testing = true;

    ShopContainer[] shopArr = new ShopContainer[20];
    ShopContainer shop1;
    ShopContainer shop2;
    ShopContainer shop3;
    ShopContainer shop4;
    ShopContainer shop5;
    ShopContainer shop6;
    ShopContainer shop7;
    ShopContainer shop8;
    ShopContainer shop9;
    ShopContainer shop10;
    ShopContainer shop11;
    ShopContainer shop12;
    ShopContainer shop13;
    ShopContainer shop14;
    ShopContainer shop15;
    ShopContainer shop16;
    ShopContainer shop17;
    ShopContainer shop18;
    ShopContainer shop19;
    ShopContainer shop20;

    // @FXML
    public void initialize() {
        if(testing){
            updateCurrentCapital("+", 10000.00);
        }

        currentCapitalLabel.setText("Current Capital: " + currentCapitalString);

        setupShops();
    }

    public void setupShops(){
        
        String[] shopNames = {
            "Lemonade Stand", "Newspaper Stand", "Bakery", "Lumber Yard", "Thrift Store",
            "Boba Shop", "Bakery", "Sports Store", "Perfume Shop", "Seafood Restaurant",
            "Telecommunications Company", "Video Game Company", "Movie Streaming Service",
            "Electronics Store", "Car Dealership", "Real Estate Agency", "Law Firm",
            "Autonomous Robot Manufacturer", "Quantum Computer Manufacturer", "Space Exploration Ventures"
        };

        for (int col = 0; col < 2; col++) {
            ColumnConstraints colConstraints = new ColumnConstraints();
            colConstraints.setPercentWidth(50); // Each column gets 50% width
            gameScreen.getColumnConstraints().add(colConstraints);
        }

        for (int i = 0; i < shopNames.length; i++) {
            ShopContainer shop = new ShopContainer(shopNames[i], (i + 1) + ".jpg", shopCnt, this);
            shopArr[i] = shop;
            gameScreen.add(shop.getContainer(), i%2, i/2);
            GridPane.setHgrow(shop.getContainer(), Priority.ALWAYS);
            shopCnt++;
        }
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

    @FXML
    private void handleManagers(){
        managerController = new ManagerController();
        managerController.addSC(this);
        managerController.showManagerTab();
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
        currentCapitalLabel.setText("Current Capital: " + currentCapitalString);
    }

    public static void updateShopCnt(){
        shopCnt++;
    }

    public void hireManager(String shop){
        for(int i = 1; i < 21; i++){
            if(shop.equals("shop" + i)){
                if(!shopArr[i-1].getHired()){
                    shopArr[i-1].setHired();
                }
                break;
            }
        }
    }
}