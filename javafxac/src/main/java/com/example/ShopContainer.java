package com.example;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Screen;
import javafx.util.Duration;

public class ShopContainer {
    private VBox container;
    private HBox header;
    private HBox buttons;

    private String shopName;
    private String imagePath;
    private int shopCnt;

    private Label shopLabel;

    private ProgressBar progressBar;

    private Button upgradeButton;
    private Button profitButton;
    private Button initialButton;
    private double profit;
    private double upgradeVal;
    private double initialInvest;
    private double investVal;
    private double initialVal;
    private double profitIncrease;
    private String investString;
    private String upgradeString;
    private String profitString;
    private String initialString;
    private int upgradeCnt = 0;
    private String shopString = shopName + " " + upgradeCnt + "x";
    private Timeline upgradeRepeatTimeline;

    private int progressSpeed = 1;
    private double progressSpeedDouble = 1.00;
    private Timeline progressTimer;

    private boolean hired = false;
    private double managerWage = 100.00;
    private boolean invested = false;

    private SecondaryController sc;

    private String[] suffixes = {
        "", "K", "M", "B", "T", "Quad", "Quint", "S", 
        "Sept", "Octi", "Non", "Dec", "Un", "Duo", "Tre", 
        "Quat", "Quin", "SD", "Septen", "Octo", "Novem", "Vig"
    };

    public ShopContainer(String name, String iconPath, int num, SecondaryController sc){
        this.sc = sc;
        container = new VBox(10);
        container.setAlignment(Pos.CENTER);
        container.setStyle("-fx-background-color: lightblue; -fx-border-color: black; -fx-border-width: 1;");
        container.setPadding(new Insets(10));

        // Get screen size
        Rectangle2D screenBounds = Screen.getPrimary().getBounds();
        double screenWidth = screenBounds.getWidth();
        double screenHeight = screenBounds.getHeight();
        // container.setPrefSize(screenWidth*0.48/2, 125);
        container.setMaxWidth(Double.MAX_VALUE);
        // container.setPrefHeight(500);
        header = new HBox(5);
        buttons = new HBox(10);

        imagePath = iconPath;
        shopName = name;
        shopCnt = num;

        double power = 10;
        for(int i = 0; i < num; i++){
            power *= 10;
        }
        initialVal = num*(power);
        // initialString = setInitialString();

        // //Set up container header
        setupHeader();

        //Set up container progress bar
        setupProgressBar();

        //Set up container upgrade and profit buttons
        setupButtons();

        sc.updateShopCnt();
    }

    public void setupHeader(){
        //Set up container header
        shopString = shopName + " " + upgradeCnt + "x";
        shopLabel = new Label(shopString);
        // header.getChildren().add(shopLabel);
        // Image image = new Image(getClass().getResourceAsStream(iconPath));
        // InputStream inputStream = getClass().getResourceAsStream(iconPath);
        // if (inputStream == null) {
        //     System.out.println("Image resource not found!");
        // }
        // Image image = new Image(inputStream);
        // ImageView icon = new ImageView(new Image(getClass().getResourceAsStream(iconPath)));
        // icon.setFitWidth(50);
        // icon.setFitHeight(50);
        // header.getChildren().add(icon);
        header.setAlignment(Pos.CENTER);
        header.getChildren().add(shopLabel);
        container.getChildren().add(header);
    }

    public void setupProgressBar(){
        progressBar = new ProgressBar(0);
        progressBar.setMaxWidth(Double.MAX_VALUE);
        container.setAlignment(Pos.CENTER);
        container.getChildren().add(progressBar);
    }

    public void setupButtons(){
        //Set up container buttons
        if(shopName.equals("Lemonade Stand")){
            profit = 1.00;
            upgradeVal = 2.00;
            investVal = upgradeVal/10.0;
            initialInvest = initialVal;
            progressSpeed = 2;
            progressSpeedDouble = 2.00;
            profitIncrease = 1.00;
            managerWage = 100.00;
            invested = true;
        } else {
            progressSpeed = 1;
            progressSpeedDouble = 1.00;
            profit = initialVal/2.0;
            upgradeVal = initialVal/(shopCnt*shopCnt*10);
            investVal = upgradeVal/(shopCnt*10.0);
            initialInvest = initialVal;
            profitIncrease = upgradeVal;
            managerWage = initialVal*50.0;
            invested = false;
        }
        // profitString = "$" + String.format("%.2f", profit);
        profitString = setProfitString();
        // upgradeString = "$" + String.format("%.2f", upgradeVal);
        upgradeString = setUpgradeString();
        investString = "$" + String.format("%.2f", investVal);
        System.out.println("\nshopName: " + shopName);
        initialString = setInitialString();
        System.out.println("setup initialString: " + initialString + ", initial Val: " + initialVal);
        System.out.println("Profit: " + profitString + ", Upgrade: " + upgradeString + ", Invest: " + investString  + ", InvestValue: " + investVal);

        upgradeButton = new Button(upgradeString);
        profitButton = new Button(profitString);
        upgradeButton.setStyle("-fx-alignment: center;");
        profitButton.setStyle("-fx-alignment: center;");
        buttons.getChildren().addAll(upgradeButton, profitButton);
        container.getChildren().add(buttons);
        upgradeButton.setPrefSize(200, 25);
        profitButton.setPrefSize(200, 25);
        buttons.setAlignment(Pos.CENTER);

        initialButton = new Button(initialString);
        initialButton.setPrefSize(400, 100);
        initialButton.setAlignment(Pos.CENTER);

        if(sc.getCurrentCapital() < upgradeVal){
            upgradeButton.setDisable(true);
        } else {
            upgradeButton.setDisable(false);
        }

        if(sc.getCurrentCapital() < initialVal){
            initialButton.setDisable(true);
        }else{
            initialButton.setDisable(false);
        }

        if(!shopName.equals("Lemonade Stand")){
            header.setVisible(false);
            progressBar.setVisible(false);
            buttons.setVisible(false);

            header.setManaged(false);
            progressBar.setManaged(false);
            buttons.setManaged(false);

            
            // System.out.println(shopName + " open cost: " + initialString);

            container.setVgrow(initialButton, Priority.ALWAYS);
            container.getChildren().add(initialButton);

            initialButton.setOnAction(e -> {
                /* Make components visible */
                if (SecondaryController.getCurrentCapital() >= initialVal) {
                    invested = true;
                    sc.updateCurrentCapital("-", initialVal);
                    header.setVisible(true);
                    progressBar.setVisible(true);
                    buttons.setVisible(true);

                    header.setManaged(true);
                    progressBar.setManaged(true);
                    buttons.setManaged(true);


                    // container.getChildren().add(header);
                    // container.getChildren().add(progressBar);
                    // container.getChildren().add(buttons);
            
                    /* Remove the "openShop" button */
                    initialButton.setVisible(false);
                    initialButton.setManaged(false);
                    container.getChildren().remove(initialButton);
                    container.requestLayout();
                    //revalidate();
                    //repaint();
                }
            });
        }

        progressTimer = new Timeline(new KeyFrame(Duration.millis(100), e -> {
            // Increase the progress bar value by progressSpeed every 100 ms
            double currentValue = progressBar.getProgress(); // Get progress as a fraction (0.0 to 1.0)
            if (currentValue < 1.0) {
                progressBar.setProgress(currentValue + progressSpeed / (100.0*(shopCnt + 1))); // Adjust for 100% scale
            } else {
                // Stop the timer once progress is complete
                sc.updateCurrentCapital("+", profit);
                // System.out.println("capital: " + SecondaryController.getCurrentCapital() + ", capitalString: " + SecondaryController.getCapitalString() + ", capitalLabel: " + sc.getCapitalLabel().getText());
                progressBar.setProgress(0); // Reset progress
                if(!hired){
                    progressTimer.stop(); // Stop the timeline
                }
            }
        }));
        progressTimer.setCycleCount(Timeline.INDEFINITE);

        profitButton.setOnAction(e -> {
            progressTimer.play();
        });

        upgradeRepeatTimeline = new Timeline(new KeyFrame(Duration.millis(100), e -> upgradeRepeat()));
        upgradeRepeatTimeline.setCycleCount(Timeline.INDEFINITE);

        upgradeButton.setOnAction(e -> {
            upgrade();
        });

        upgradeButton.setOnMousePressed(e -> {
            upgradeRepeatTimeline.play();
        });

        upgradeButton.setOnMouseReleased(e -> upgradeRepeatTimeline.stop());
    }

    private void upgradeRepeat(){
        if(SecondaryController.getCurrentCapital() >= upgradeVal){
            // System.out.println("upgradeRepeat");
            // System.out.println(shopName + " capital: Profit: " + SecondaryController.getCapitalString() + ", Upgrade: " + upgradeString + ", Invest: " + investString);
            upgrade();
        }
    }
    
    public VBox getContainer(){
        return container;
    }

    public void upgrade(){
        if(sc.getCurrentCapital() >= upgradeVal){
            System.out.println("update");
            sc.updateCurrentCapital("-", upgradeVal);
            upgradeCnt++;
            shopLabel.setText(shopName + " " + upgradeCnt + "x");
            // System.out.println(shopLabel.getText() + " upgrade Method start: Profit: " + profitString + ", Upgrade: " + upgradeString + ", Invest: " + investString + ", Speed: " + progressSpeed + ", speedDouble: " + progressSpeedDouble);
            updateProfit("+", profitIncrease);
            updateUpgrade("+", investVal);
            progressSpeedDouble += progressSpeedDouble * 0.05;
            progressSpeed = (int) Math.floor(progressSpeedDouble);
            // System.out.println(shopLabel.getText() + " upgrade Method end:  Profit: " + profitString + ", Upgrade: " + upgradeString + ", Invest: " + investString + ",InvestValue: " + investVal + ", Speed: " + progressSpeed + ", speedDouble: " + progressSpeedDouble);
            if(upgradeCnt%10 == 0){
                investVal *= 2.0;
                investString = "$" + String.format("%.2f", investVal);
            }
            // if(upgradeCnt%25 == 0){
            //     investVal *= 2.0;
            //     investString = "$" + String.format("%.2f", investVal);
            // }
        }
    }

    public void updateProfit(String operation, double val){
        if(operation.equals("+")){
            profit += val;
        } else if(operation.equals("-")){
            profit -= val;
        } else if(operation.equals("*")){
            profit *= val;
        } else if(operation.equals("/")){
            profit /= val;
        }
        // profitString = "$" + String.format("%.2f", profit);
        profitString = setProfitString();
        profitButton.setText("Profit for " + profitString);
    }

    public void updateUpgrade(String operation, double val){
        if(operation.equals("+")){
            upgradeVal += val;
        } else if(operation.equals("-")){
            upgradeVal -= val;
        } else if(operation.equals("*")){
            upgradeVal *= val;
        } else if(operation.equals("/")){
            upgradeVal /= val;
        }
        // upgradeString = "$" + String.format("%.2f", upgradeVal);
        upgradeString = setUpgradeString();
        upgradeButton.setText("Upgrade for " + upgradeString);
    }

    public void setHired(){
        if(!hired){
            System.out.println(this.shopName + " manager hired!");
            hired = true;

            if (progressTimer.getStatus() != Animation.Status.RUNNING) {
                progressTimer.play();
            }
        }
    }

    public boolean getHired(){
        return hired;
    }

    public double getManagerWage(){
        return managerWage;
    }

    public boolean getInvested(){
        return invested;
    }

    public String getName(){
        return shopName;
    }

    public Double getUpgradeVal(){
        return upgradeVal;
    }

    public Double getInitialVal(){
        return initialVal;
    }

    /*private String investString = "$" + String.format("%.2f", investVal);
    private String upgradeString = "$" + String.format("%.2f", upgradeVal);
    private String profitString = "$" + String.format("%.2f", profit); */
    public String setInitialString(){
   
        // Convert the initial value to a scaled-down version
        double investRem = initialVal;
        int investMetric = 0;
    
        while (investRem >= 1000 && investMetric < suffixes.length - 1) {
            investRem /= 1000;
            investMetric++;
        }
    
        // Get the appropriate suffix
        String investPrefix = suffixes[investMetric];
    
        // Format the result
        String result = "\nInvest in " + shopName + "?   $" + String.format("%.3f", investRem) + investPrefix;
    
        // Debug output (remove in production)
        // System.out.println("initialVal: " + initialVal 
        //     + ", investRem: " + investRem 
        //     + ", investMetric: " + investMetric 
        //     + ", investPrefix: " + investPrefix);
        // System.out.println("Result: " + result);
    
        return result;
    }

    public String setProfitString(){
        // Convert the initial value to a scaled-down version
        double profitRem = profit;
        int profitMetric = 0;
    
        while (profitRem >= 1000 && profitMetric < suffixes.length - 1) {
            profitRem /= 1000;
            profitMetric++;
        }
    
        // Get the appropriate suffix
        String profitPrefix = suffixes[profitMetric];
    
        // Format the result
        // String result = "\nInvest in " + shopName + "?   $" + String.format("%.3f", investRem) + investPrefix;
        
        String result;
        if(profitMetric == 0){
            result = "Profit for $" + String.format("%.2f", profitRem) + profitPrefix;
        } else {
            result = "Profit for $" + String.format("%.3f", profitRem) + profitPrefix;
        }
    
        // Debug output (remove in production)
        // System.out.println("Profit: " + profit 
        //     + ", profitRem: " + profitRem 
        //     + ", profitMetric: " + profitMetric 
        //     + ", profitPrefix: " + profitPrefix);
        // System.out.println("Result: " + result);
    
        return result;
    }

    public String setUpgradeString(){
        // Convert the initial value to a scaled-down version
        double rem = upgradeVal;
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
            result = "Upgrade for $" + String.format("%.2f", rem) + prefix;
        }else{
            result = "Upgrade for $" + String.format("%.3f", rem) + prefix;
        }
    
        // Debug output (remove in production)
        // System.out.println("Upgrade: " + upgradeVal 
        //     + ", upgradeRem: " + rem 
        //     + ", upgradeMetric: " + metric 
        //     + ", upgradePrefix: " + prefix);
        // System.out.println("Result: " + result);
    
        return result;
    }

    public void showUpgrade(){
        upgradeButton.setDisable(false);
    }

    public void hideUpgrade(){
        upgradeButton.setDisable(true);
    }

    public void showInitial(){
        initialButton.setDisable(false);
    }

    public void hideInitial(){
        initialButton.setDisable(true);
    }
}
