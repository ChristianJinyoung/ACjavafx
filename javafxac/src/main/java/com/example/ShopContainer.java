package com.example;

// import java.time.Duration;

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
    
    private Label shopLabel;
    private String shopName;
    private String imagePath;

    private Button upgradeButton;
    private Button profitButton;
    private double profit;
    private double upgradeVal;
    private double initialInvest;
    private double investVal;
    private double initialVal;
    private double profitIncrease;
    private String investString = "$" + String.format("%.2f", investVal);
    private String upgradeString = "$" + String.format("%.2f", upgradeVal);
    private String profitString = "$" + String.format("%.2f", profit);
    private String initialString = "Invest in " + shopName + "?   $" + String.format("%.2f", initialVal);
    private int upgradeCnt = 0;
    private String shopString = shopName + " " + upgradeCnt + "x";
    private Timeline upgradeRepeatTimeline;

    private int progressSpeed = 1;
    private double progressSpeedDouble = 1.00;
    private Timeline progressTimer;

    private boolean hired = false;

    private SecondaryController sc;

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
        container.setPrefSize(screenWidth*0.48/2, 125);
        container.setMaxWidth(screenWidth*0.48/2);
        header = new HBox(5);
        header.setAlignment(Pos.CENTER);
        buttons = new HBox(10);

        //Set up container header
        imagePath = iconPath;
        // shopName = name;
        shopName = name;
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
        header.getChildren().add(shopLabel);
        container.getChildren().add(header);

        //Set up container progress bar
        ProgressBar progressBar = new ProgressBar(0);
        progressBar.setMaxWidth(Double.MAX_VALUE);
        container.setAlignment(Pos.TOP_LEFT);
        container.getChildren().add(progressBar);

        double power = 10;
        for(int i = 0; i < num; i++){
            power *= 10;
        }
        initialVal = num*(power);
        initialString = "Invest in " + shopName + "?   $" + String.format("%.2f", initialVal);

        //Set up container buttons
        if(name.equals("Lemonade Stand")){
            profit = 1.00;
            upgradeVal = 2.00;
            investVal = upgradeVal/100.0;
            initialInvest = investVal;
            progressSpeed = 2;
            progressSpeedDouble = 2.00;
            profitIncrease = 1.00;
        } else {
            progressSpeed = 1;
            progressSpeedDouble = 1.00;
            profit = initialVal/2.0;
            upgradeVal = initialVal/(num*num*10);
            investVal = upgradeVal/(num*10.0);
            initialInvest = investVal;
            profitIncrease = initialVal;
        }
        profitString = "$" + String.format("%.2f", profit);
        upgradeString = "$" + String.format("%.2f", upgradeVal);
        investString = "$" + String.format("%.2f", investVal);
        System.out.println("shopName: " + shopName + ", openVal: " + initialString);
        System.out.println("Profit: " + profitString + ", Upgrade: " + upgradeString + ", Invest: " + investString + "\n");

        upgradeButton = new Button("Upgrade for " + upgradeString);
        profitButton = new Button("Profit for " + profitString);
        upgradeButton.setStyle("-fx-alignment: center;");
        profitButton.setStyle("-fx-alignment: center;");
        buttons.getChildren().addAll(upgradeButton, profitButton);
        container.getChildren().add(buttons);
        upgradeButton.setPrefSize(200, 25);
        profitButton.setPrefSize(200, 25);

        if(!name.equals("Lemonade Stand")){
            header.setVisible(false);
            progressBar.setVisible(false);
            buttons.setVisible(false);

            header.setManaged(false);
            progressBar.setManaged(false);
            buttons.setManaged(false);

            Button initialButton = new Button(initialString);
            initialButton.setPrefSize(400, 100);
            initialButton.setAlignment(Pos.CENTER);
            System.out.println(shopName + " open cost: " + initialString);

            container.setVgrow(initialButton, Priority.ALWAYS);
            container.getChildren().add(initialButton);

            initialButton.setOnAction(e -> {
                /* Make components visible */
                if (SecondaryController.getCurrentCapital() >= initialVal) {
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
                progressBar.setProgress(currentValue + progressSpeed / 100.0); // Adjust for 100% scale
            } else {
                // Stop the timer once progress is complete
                sc.updateCurrentCapital("+", profit);
                System.out.println("capital: " + SecondaryController.getCurrentCapital() + ", capitalString: " + SecondaryController.getCapitalString() + ", capitalLabel: " + sc.getCapitalLabel().getText());
                progressBar.setProgress(0); // Reset progress
                if(!hired){
                    progressTimer.stop(); // Stop the timeline
                }
            }
        }));

        profitButton.setOnAction(e -> {
            progressTimer.setCycleCount(Timeline.INDEFINITE);
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

        sc.updateShopCnt();
    }

    private void upgradeRepeat(){
        if(SecondaryController.getCurrentCapital() >= upgradeVal){
            System.out.println(shopName + " capital: Profit: " + SecondaryController.getCapitalString() + ", Upgrade: " + upgradeString + ", Invest: " + investString);
            sc.updateCurrentCapital("-", upgradeVal);
            upgrade();
        }
    }
    
    public VBox getContainer(){
        return container;
    }

    public void upgrade(){
        if(sc.getCurrentCapital() >= upgradeVal){
            upgradeCnt++;
            shopLabel.setText(shopName + " " + upgradeCnt + "x");
            System.out.println(shopLabel.getText() + " upgrade Method start: Profit: " + profitString + ", Upgrade: " + upgradeString + ", Invest: " + investString + ", Speed: " + progressSpeed + ", speedDouble: " + progressSpeedDouble);
            updateProfit("+", profitIncrease);
            updateUpgrade("+", investVal);
            progressSpeedDouble += progressSpeedDouble * 0.01;
            progressSpeed = (int) Math.floor(progressSpeedDouble);
            System.out.println(shopLabel.getText() + " upgrade Method end:  Profit: " + profitString + ", Upgrade: " + upgradeString + ", Invest: " + investString + ", Speed: " + progressSpeed + ", speedDouble: " + progressSpeedDouble);
            if(upgradeCnt%10 == 0){
                investVal *= 2.0;
                investString = "$" + String.format("%.2f", investVal);
            }
            if(upgradeCnt%25 == 0){
                investVal *= 2.0;
                investString = "$" + String.format("%.2f", investVal);
            }
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
        upgradeString = "$" + String.format("%.2f", upgradeVal);
        upgradeButton.setText("Upgrade for " + upgradeString);
    }

    public void setHired(){
        hired = true;
    }
}
