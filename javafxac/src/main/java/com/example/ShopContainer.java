package com.example;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class ShopContainer {
    private VBox vbox;
    private HBox header;
    private HBox buttons;
    
    private Label shopName;

    // public ShopContainer(String name, String iconPath){
    public ShopContainer(String name){
        vbox = new VBox(10);
        vbox.setAlignment(Pos.CENTER);
        header = new HBox(5);
        buttons = new HBox(10);

        shopName = new Label(name);
        header.getChildren().add(shopName);

        // ImageView icon = new ImageView(new Image(getClass().getResourceAsStream(iconPath)));
        // icon.setFitWidth(50);
        // icon.setFitHeight(50);
        // header.getChildren().add(icon);

        vbox.getChildren().add(header);

        ProgressBar progressBar = new ProgressBar(0);
        vbox.getChildren().add(progressBar);

        Button upgradeButton = new Button("Upgrade");
        Button runButton = new Button("Run");
        buttons.getChildren().addAll(upgradeButton, runButton);
        vbox.getChildren().add(buttons);
    }
    
    public VBox getContainer(){
        return vbox;
    }
}
