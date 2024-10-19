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
    private Button addPlotButton;
    @FXML
    private Button editPlotButton;
    @FXML
    private Button membersButton;
    @FXML
    private Button replantButton;
    @FXML
    private Button leaveGarden;

    /**
     * handle the back button click event
     *
     * @param event the event details
     */
    @FXML
    public void backButtonClicked(ActionEvent event) throws IOException {
        Stage stage = (Stage) backButton.getScene().getWindow();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/gardenplanner/gardenplanner/homepage.fxml"));
        loader.setControllerFactory(type -> new HomepageController());

        stage.setScene(new Scene(loader.load()));
        stage.show();
    }

    /**
     * handle the add garden button click event
     *
     * @param event the event details
     */
    @FXML
    void addGardenButtonClicked(ActionEvent event) throws IOException {
        Stage stage = new Stage();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/gardenplanner/gardenplanner/addgarden.fxml"));
        loader.setControllerFactory(type -> new GardenAddGardenController());

        stage.setScene(new Scene(loader.load()));
        stage.show();
    }

    /**
     * handle the members button click event
     *
     * @param event the event details
     */
    @FXML
    void membersButtonClicked(ActionEvent event) {
        // Handle the members button click event
        System.out.println("Members button clicked");
        // Add your logic here
    }

    /**
     * handle the replant button click event
     *
     * @param event the event details
     */
    @FXML
    void replantButtonClicked(ActionEvent event) {
        // Handle the replant button click event
        System.out.println("Replant button clicked");
        // Add your logic here
    }

    /**
     * handle the leave garden button click event
     *
     * @param event the event details
     */
    @FXML
    void leaveGardenButtonClicked(ActionEvent event) {
        // Handle the leave garden button click event
        System.out.println("Leave Garden button clicked");
        // Add your logic here
    }
}