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
    private static String currentCapitalString;
    private static int shopCnt = 0;
    private static int rows = 10;
    private int capitalMetric;

    ManagerController managerController;

    private static boolean testing = true;

    ShopContainer[] shopArr = new ShopContainer[20];

    private String[] suffixes = {
        "", "K", "M", "B", "T", "Quad", "Quint", "S", 
        "Sept", "Octi", "Non", "Dec", "Un", "Duo", "Tre", 
        "Quat", "Quin", "SD", "Septen", "Octo", "Novem", "Vig"
    };

    // @FXML
    public void initialize() {
        if(testing){
            initialCapital(10000000000.00);
        }else{
            initialCapital(0.0);
        }

        currentCapitalLabel.setText(currentCapitalString);

        setupShops();
    }

    public void setupShops(){
        
        String[] shopNames = {
            "Lemonade Stand", "Newspaper Stand", "Potato Farm", "Lumber Yard", "Thrift Store",
            "Boba Shop", "Bakery", "Sports Store", "Perfume Shop", "Seafood Restaurant",
            "Movie Streaming Service", "Telecommunications Company", "Video Game Company",
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

        showUpgrade();
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

    public void initialCapital(double val){
        currentCapital = val;
        currentCapitalString = setCapitalString();
        currentCapitalLabel.setText(currentCapitalString);
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

        currentCapitalString = setCapitalString();
        currentCapitalLabel.setText(currentCapitalString);

        showUpgrade();
    }

    public static void updateShopCnt(){
        shopCnt++;
    }

    public boolean hireManager(String shop){
        for(int i = 1; i < 21; i++){
            if(shop.equals("shop" + i)){
                if(!shopArr[i-1].getHired() && currentCapital >= shopArr[i-1].getManagerWage()){
                    updateCurrentCapital("-", shopArr[i-1].getManagerWage());
                    shopArr[i-1].setHired();
                    return true;
                }
                return false;
            }
        }
        return false;
    }

    public ShopContainer[] getShopArr(){
        return shopArr;
    }

    public String setCapitalString(){
        // Convert the initial value to a scaled-down version
        double rem = currentCapital;
        int metric = 0;
    
        while (rem >= 1000 && metric < suffixes.length - 1) {
            rem /= 1000;
            metric++;
        }
    
        // Get the appropriate suffix
        String prefix = suffixes[metric];
    
        // Format the result
        // String result = "\nInvest in " + shopName + "?   $" + String.format("%.3f", investRem) + investPrefix;
        String result;
        if(metric == 0){
            result = "Current Capital: $" + String.format("%.2f", rem) + prefix;
        }
        else{
            result = "Current Capital: $" + String.format("%.3f", rem) + prefix;
            // result = "Current Capital: $" + String.format("%." + (metric * 3) + "f", rem) + prefix;
        }

        
    
        // Debug output (remove in production)
        System.out.println("Upgrade: " + currentCapital 
            + ", upgradeRem: " + rem 
            + ", upgradeMetric: " + metric 
            + ", upgradePrefix: " + prefix);
        System.out.println("Result: " + result);

        capitalMetric = metric;
    
        return result;
    }

    public void showUpgrade(){
        for (int i = 0; i < shopArr.length; i++) {
            if (shopArr[i] != null && shopArr[i].getInvested()) { 
                if (currentCapital >= shopArr[i].getUpgradeVal()) {
                    shopArr[i].showUpgrade();
                } else {
                    shopArr[i].hideUpgrade();
                }
            }
        }
    }

    public void showInitial(){
        for(int i = 0; i < shopArr.length; i++){
            if(shopArr[i] != null && !shopArr[i].getInvested()){
                if(currentCapital >= shopArr[i].getInitialVal()){
                    shopArr[i].showInitial();
                }else{
                    shopArr[i].hideInitial();
                }
            }
        }
    }
}