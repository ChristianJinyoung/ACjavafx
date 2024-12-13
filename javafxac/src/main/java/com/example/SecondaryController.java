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

    private static boolean testing = false;

    // @FXML
    public void initialize() {
        if(testing){
            updateCurrentCapital("+", 10000.00);
        }

        currentCapitalLabel.setText("Current Capital: " + currentCapitalString);

        setupShops();
    }

    public void setupShops(){
        //Column 1
        ShopContainer shop1 = new ShopContainer("Lemonade Stand", "javafxac/src/main/resources/Icons/Lemonade.jpg", shopCnt, this);
        gameScreen.add(shop1.getContainer(), (shopCnt - 1)/rows, (shopCnt - 1)%rows);
        
        ShopContainer shop2 = new ShopContainer("Newspaper Stand", "javafxac/src/main/resources/Icons/Lemonade.jpg", shopCnt, this);
        gameScreen.add(shop2.getContainer(), (shopCnt - 1)/rows, (shopCnt - 1)%rows);

        ShopContainer shop3 = new ShopContainer("Bakery", "javafxac/src/main/resources/Icons/Lemonade.jpg", shopCnt, this);
        gameScreen.add(shop3.getContainer(), (shopCnt - 1)/rows, (shopCnt - 1)%rows);

        ShopContainer shop4 = new ShopContainer("Lumber Yard", "javafxac/src/main/resources/Icons/Lemonade.jpg", shopCnt, this);
        gameScreen.add(shop4.getContainer(), (shopCnt - 1)/rows, (shopCnt - 1)%rows);

        ShopContainer shop5 = new ShopContainer("Thrift Store", "javafxac/src/main/resources/Icons/Lemonade.jpg", shopCnt, this);
        gameScreen.add(shop5.getContainer(), (shopCnt - 1)/rows, (shopCnt - 1)%rows);

        ShopContainer shop6 = new ShopContainer("Boba Shop", "javafxac/src/main/resources/Icons/Lemonade.jpg", shopCnt, this);
        gameScreen.add(shop6.getContainer(), (shopCnt - 1)/rows, (shopCnt - 1)%rows);

        ShopContainer shop7 = new ShopContainer("Bakery", "javafxac/src/main/resources/Icons/Lemonade.jpg", shopCnt, this);
        gameScreen.add(shop7.getContainer(), (shopCnt - 1)/rows, (shopCnt - 1)%rows);

        ShopContainer shop8 = new ShopContainer("Sports Store", "javafxac/src/main/resources/Icons/Lemonade.jpg", shopCnt, this);
        gameScreen.add(shop8.getContainer(), (shopCnt - 1)/rows, (shopCnt - 1)%rows);

        ShopContainer shop9 = new ShopContainer("Perfume Shop", "javafxac/src/main/resources/Icons/Lemonade.jpg", shopCnt, this);
        gameScreen.add(shop9.getContainer(), (shopCnt - 1)/rows, (shopCnt - 1)%rows);

        ShopContainer shop10 = new ShopContainer("Seafood Restaurant", "javafxac/src/main/resources/Icons/Lemonade.jpg", shopCnt, this);
        gameScreen.add(shop10.getContainer(), (shopCnt - 1)/rows, (shopCnt - 1)%rows);

        //Column 2
        ShopContainer shop11 = new ShopContainer("Telecommunications Company", "javafxac/src/main/resources/Icons/Lemonade.jpg", shopCnt, this);
        gameScreen.add(shop11.getContainer(), (shopCnt - 1)/rows, (shopCnt - 1)%rows);

        ShopContainer shop12 = new ShopContainer("Video Game Company", "javafxac/src/main/resources/Icons/Lemonade.jpg", shopCnt, this);
        gameScreen.add(shop12.getContainer(), (shopCnt - 1)/rows, (shopCnt - 1)%rows);

        ShopContainer shop13 = new ShopContainer("Movie Streaming Service", "javafxac/src/main/resources/Icons/Lemonade.jpg", shopCnt, this);
        gameScreen.add(shop13.getContainer(), (shopCnt - 1)/rows, (shopCnt - 1)%rows);

        ShopContainer shop14 = new ShopContainer("Electronics Store", "javafxac/src/main/resources/Icons/Lemonade.jpg", shopCnt, this);
        gameScreen.add(shop14.getContainer(), (shopCnt - 1)/rows, (shopCnt - 1)%rows);

        ShopContainer shop15 = new ShopContainer("Car Dealership", "javafxac/src/main/resources/Icons/Lemonade.jpg", shopCnt, this);
        gameScreen.add(shop15.getContainer(), (shopCnt - 1)/rows, (shopCnt - 1)%rows);

        ShopContainer shop16 = new ShopContainer("Real Estate Agency", "javafxac/src/main/resources/Icons/Lemonade.jpg", shopCnt, this);
        gameScreen.add(shop16.getContainer(), (shopCnt - 1)/rows, (shopCnt - 1)%rows);

        ShopContainer shop17 = new ShopContainer("Law Firm", "javafxac/src/main/resources/Icons/Lemonade.jpg", shopCnt, this);
        gameScreen.add(shop17.getContainer(), (shopCnt - 1)/rows, (shopCnt - 1)%rows);

        ShopContainer shop18 = new ShopContainer("Autonomous Robot Manufacturer", "javafxac/src/main/resources/Icons/Lemonade.jpg", shopCnt, this);
        gameScreen.add(shop18.getContainer(), (shopCnt - 1)/rows, (shopCnt - 1)%rows);

        ShopContainer shop19 = new ShopContainer("Quantum Computer Manufacturer", "javafxac/src/main/resources/Icons/Lemonade.jpg", shopCnt, this);
        gameScreen.add(shop19.getContainer(), (shopCnt - 1)/rows, (shopCnt - 1)%rows);

        ShopContainer shop20 = new ShopContainer("Space Exploration Tech Corp", "javafxac/src/main/resources/Icons/Lemonade.jpg", shopCnt, this);
        gameScreen.add(shop20.getContainer(), (shopCnt - 1)/rows, (shopCnt - 1)%rows);
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
        currentCapitalLabel.setText("Current Capital: " + currentCapitalString);
    }

    public static void updateShopCnt(){
        shopCnt++;
    }
}