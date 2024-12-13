package com.example;

// import java.time.Duration;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

public class ShopContainer {
    private VBox container;
    private HBox header;
    private HBox buttons;
    
    private Label shopLabel;
    private String shopName;
    private String imagePath;

    private Button upgradeButton;
    private Button profitButton;
    private double profit;
    private double upgradeVal;
    private double investVal;
    private double initialVal;
    private String investString = "$" + String.format("%.2f", investVal);
    private String upgradeString = "$" + String.format("%.2f", upgradeVal);
    private String profitString = "$" + String.format("%.2f", profit);
    private String initialString = "Invest in " + shopName + "?   $" + String.format("%.2f", initialVal);
    private int shopCnt = 0;

    private int progressSpeed = 1;
    private double progressSpeedDouble = 1.00;
    private Timeline progressTimer;

    public ShopContainer(String name, String iconPath, int num, SecondaryController sc){
    // public ShopContainer(String name){
        container = new VBox(10);
        container.setAlignment(Pos.CENTER);
        header = new HBox(5);
        buttons = new HBox(10);

        //Set up container header
        imagePath = iconPath;
        // shopName = name;
        setupShopName(name);
        shopLabel = new Label(shopName);
        header.getChildren().add(shopLabel);
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
        container.getChildren().add(header);

        //Set up container progress bar
        ProgressBar progressBar = new ProgressBar(0);
        container.getChildren().add(progressBar);

        double power = 10;
        for(int i = 0; i < num; i++){
            power *= 10;
        }
        initialVal = num*(power);
        initialString = "Invest in " + shopName + "?   $" + String.format("%.2f", initialVal);

        //Set up container buttons
        if(name.equals("Lemonade")){
            profit = 1.00;
            upgradeVal = 2.00;
            investVal = upgradeVal/100.0;
            progressSpeed = 2;
            progressSpeedDouble = 2.00;
        } else {
            progressSpeed = 1;
            progressSpeedDouble = 1.00;
            profit = initialVal/2.0;
            upgradeVal = initialVal/10;
            investVal = upgradeVal/100.0;
        }
        profitString = "$" + String.format("%.2f", profit);
        upgradeString = "$" + String.format("%.2f", upgradeVal);
        investString = "$" + String.format("%.2f", investVal);
        System.out.println("shopName: " + shopName + ", openVal: " + initialString);
        System.out.println("Profit: " + profitString + ", Upgrade: " + upgradeString + ", Invest: " + investString + "\n");

        upgradeButton = new Button("Upgrade for " + upgradeString);
        profitButton = new Button("Profit for " + profitString);
        buttons.getChildren().addAll(upgradeButton, profitButton);
        container.getChildren().add(buttons);

        if(!name.equals("Lemonade")){
            header.setVisible(false);
            progressBar.setVisible(false);
            buttons.setVisible(false);

            container.getChildren().remove(header);
            container.getChildren().remove(progressBar);
            container.getChildren().remove(buttons);

            Button initialButton = new Button(initialString);
            System.out.println(shopName + " open cost: " + initialString);
            container.getChildren().add(initialButton);

            initialButton.setOnAction(e -> {
                /* Make components visible */
                if (SecondaryController.getCurrentCapital() >= initialVal) {
                    header.setVisible(true);
                    progressBar.setVisible(true);
                    buttons.setVisible(true);
    
                    container.getChildren().add(header);
                    container.getChildren().add(progressBar);
                    container.getChildren().add(buttons);
            
                    /* Remove the "openShop" button */
                    container.getChildren().remove(initialButton);
                    //revalidate();
                    //repaint();
                }
            });
        }

        progressTimer = new Timeline(new KeyFrame(Duration.millis(100), e -> {
            // Increase the progress bar value by progressSpeed every 100 ms
            double currentValue = progressBar.getProgress(); // Get progress as a fraction (0.0 to 1.0)
            if (currentValue < 1.0) {
                progressBar.setProgress(currentValue + progressSpeed / 100.0); // Adjust for 100% scale
            } else {
                // Stop the timer once progress is complete
                sc.updateCurrentCapital("+", profit);
                System.out.println("capital: " + SecondaryController.getCurrentCapital() + ", capitalString: " + SecondaryController.getCapitalString() + ", capitalLabel: " + sc.getCapitalLabel().getText());
                progressBar.setProgress(0); // Reset progress
                progressTimer.stop(); // Stop the timeline
            }
        }));

        profitButton.setOnAction(e -> {
            progressTimer.setCycleCount(Timeline.INDEFINITE);
            progressTimer.play();
        });

        upgradeButton.setOnAction(e -> {
            System.out.println(shopName + " capital: Profit: " + SecondaryController.getCapitalString() + ", Upgrade: " + upgradeString + ", Invest: " + investString);
            if(SecondaryController.getCurrentCapital() >= upgradeVal){
                sc.updateCurrentCapital("-", upgradeVal);
                upgrade();
            }
        });
    }
    
    public VBox getContainer(){
        return container;
    }

    public void setupShopName(String name){
        if(name.equals("Lemonade") || name.equals("Newspaper")){
            shopName = name + " Stand";
        } else {
            shopName = name + " Store";
        }
    }

    public void upgrade(){
        shopCnt++;
        shopLabel.setText(shopName + " " + shopCnt + "x");
        System.out.println(shopLabel.getText() + " upgrade Method start: Profit: " + profitString + ", Upgrade: " + upgradeString + ", Invest: " + investString + ", Speed: " + progressSpeed + ", speedDouble: " + progressSpeedDouble);
        updateProfit("+", (1.00));
        updateUpgrade("+", investVal);
        progressSpeedDouble += progressSpeedDouble * 0.01;
        progressSpeed = (int) Math.floor(progressSpeedDouble);
        System.out.println(shopLabel.getText() + " upgrade Method end:  Profit: " + profitString + ", Upgrade: " + upgradeString + ", Invest: " + investString + ", Speed: " + progressSpeed + ", speedDouble: " + progressSpeedDouble);
        if(shopCnt%10 == 0 || shopCnt%25 == 0){
            investVal = investVal * 2.0;
            investString = "$" + String.format("%.2f", investVal);
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
        profitString = "$" + String.format("%.2f", profit);
        profitButton.setText(profitString);
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
        upgradeString = "$" + String.format("%.2f", upgradeVal);
        upgradeButton.setText(upgradeString);
    }
}
