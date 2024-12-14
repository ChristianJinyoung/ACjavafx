package com.example;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class ManagerController {

    @FXML
    private Button closeManagerButton;
    @FXML
    private Button lemonadeHireButton;
    @FXML
    private Button newspaperHireButton;

    private SecondaryController sc;

    @FXML
    private void handleManagerClose() {
        Stage stage = (Stage) closeManagerButton.getScene().getWindow();
        stage.close();
    }

    public void showManagerTab() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("manager.fxml"));
            Parent root = loader.load();  // Load the settings FXML

            ManagerController managerController = loader.getController();
            managerController.addSC(sc);

            Stage stage = new Stage();  // Create a new Stage for the settings window
            stage.setTitle("Managers");
            stage.setScene(new Scene(root));
            stage.show();  // Show the settings window
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void handleLemonadeHire(){
        sc.hireManager("shop1");
    }

    @FXML
    private void handleNewspaperHire(){
        sc.hireManager("shop2");
    }

    public void addSC(SecondaryController sc){
        this.sc = sc;
    }
}