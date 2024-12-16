package com.example;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class ManagerController {

    private Button[] hireButtons = new Button[20];

    @FXML
    private ScrollPane managerPane;
    @FXML
    private Button closeManagerButton;
    @FXML
    private VBox managerBox;

    private SecondaryController sc;
    private ShopContainer[] shops;

    private String[] suffixes = {
        "", "K", "M", "B", "T", "Quad", "Quint", "S", 
        "Sept", "Octi", "Non", "Dec", "Un", "Duo", "Tre", 
        "Quat", "Quin", "SD", "Septen", "Octo", "Novem", "Vig"
    };

    @FXML
    private void handleManagerClose() {
        Stage stage = (Stage) closeManagerButton.getScene().getWindow();
        stage.close();
    }

    @FXML
    public void initialize() {
        // Get the screen height and set the ScrollPane height
        double screenHeight = Screen.getPrimary().getVisualBounds().getHeight();
        managerPane.setPrefHeight(screenHeight*0.75);
    }

    public void showManagerTab() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("manager.fxml"));
            Parent root = loader.load();  // Load the settings FXML

            ManagerController managerController = loader.getController();
            managerController.addSC(sc);
            managerController.setShops(sc.getShopArr());

            Stage stage = new Stage();  // Create a new Stage for the settings window
            stage.setTitle("Managers");
            stage.setScene(new Scene(root));

            // stage.getScene().addEventFilter(javafx.scene.input.MouseEvent.MOUSE_PRESSED, event -> {
            //     if (!root.isHover()) {
            //         stage.close();
            //     }
            // });

            stage.focusedProperty().addListener((observable, oldValue, newValue) -> {
                if (!newValue) {
                    stage.close();
                }
            });


            stage.setOnCloseRequest(event -> {
                handleManagerClose();
            });

            // stage.getScene().setOnMouseClicked(event -> {
            //     if (event.getTarget() != stage.getScene()) {
            //         // Close the window when the mouse click happens outside the window's controls
            //         stage.close();
            //     }
            // });

            stage.show();  // Show the settings window
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setupHireButtons(){
        if (managerBox == null) {
            // System.err.println("Error: managerBox is null!");
            return;
        }

        String[] shopNames = {
            "Lemonade Stand", "Newspaper Stand", "Potato Farm", "Lumber Yard", "Thrift Store",
            "Boba Shop", "Bakery", "Sports Store", "Perfume Shop", "Seafood Restaurant",
            "Movie Streaming Service", "Telecommunications Company", "Video Game Company",
            "Electronics Store", "Car Dealership", "Real Estate Agency", "Law Firm",
            "Autonomous Robot Manufacturer", "Quantum Computer Manufacturer", "Space Exploration Ventures"
        };
        
        for(int i = 0; i < 20; i++){
            Button button = new Button(getManagerString(shops[i]));

            hireButtons[i] = button;

            final int index = i;
            button.setOnAction(e -> handleHire(index));

            managerBox.getChildren().add(hireButtons[i]);

            if(!shops[i].getInvested() || shops[i].getHired()){
                hireButtons[i].setVisible(false);
            }
            if(shops[i].getInvested() && !shops[i].getHired()){
                // System.out.println("DEBUG1 " + sc.getCurrentCapital() + " " + shops[i].getManagerWage());
                if(sc.getCurrentCapital() < shops[i].getManagerWage()){
                    hireButtons[i].setDisable(true);
                    // System.out.println("DEBUG2");
                }else{
                    hireButtons[i].setDisable(false);
                    // System.out.println("DEBUG3");
                }
            }
        }
    }

    public void addSC(SecondaryController sc){
        this.sc = sc;
    }

    public void setShops(ShopContainer[] shopArr){
        this.shops = shopArr;
        setupHireButtons();
    }

    @FXML
    private void handleHire(int index){
        if(sc.hireManager("shop" + (index + 1))){
            hireButtons[index].setVisible(false);
            managerBox.getChildren().remove(hireButtons[index]);
        }
        // System.out.println("Hiring manager for shop " + (index + 1));
    }

    public String getManagerString(ShopContainer shop){
        // Convert the initial value to a scaled-down version
        double rem = shop.getManagerWage();
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
            // result = "Current Capital: $" + String.format("%.2f", rem) + prefix;
            result = "Hire " + shop.getName() + " Manager for $" + String.format("%.2f", rem) + prefix;
        }else{
            result = "Hire " + shop.getName() + " Manager for $" + String.format("%.2f", rem) + prefix;
        }
    
        // Debug output (remove in production)
        // System.out.println("Upgrade: " + shop.getName() 
        //     + ", upgradeRem: " + rem 
        //     + ", upgradeMetric: " + metric 
        //     + ", upgradePrefix: " + prefix);
        // System.out.println("Result: " + result);
    
        return result;
    }

    public void showHire(int index){
        hireButtons[index].setDisable(false);
    }

    public void hideHire(int index){
        hireButtons[index].setDisable(true);
    }
}