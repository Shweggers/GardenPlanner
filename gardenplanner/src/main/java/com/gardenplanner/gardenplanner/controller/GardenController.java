package com.gardenplanner.gardenplanner.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class GardenController {
    @FXML
    private Button backButton;
    @FXML
    private Button addGardenButton;

    @FXML
    public void backButtonClicked(ActionEvent event) throws IOException {
        Stage stage = (Stage) backButton.getScene().getWindow();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/gardenplanner/gardenplanner/homepage.fxml"));
        loader.setControllerFactory(type -> new HomepageController());

        stage.setScene(new Scene(loader.load()));
        stage.show();
    }

    @FXML
    void addGardenButtonClicked(ActionEvent event) throws IOException {
        Stage stage = (Stage) addGardenButton.getScene().getWindow();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/gardenplanner/gardenplanner/addgarden.fxml"));
        loader.setControllerFactory(type -> new GardenAddGardenController());

        stage.setScene(new Scene(loader.load()));
        stage.show();
    }
}