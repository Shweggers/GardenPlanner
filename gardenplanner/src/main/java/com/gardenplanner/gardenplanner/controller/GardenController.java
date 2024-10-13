package com.gardenplanner.gardenplanner.controller;

import com.gardenplanner.gardenplanner.model.DataStore;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * GardenController is a class that represents a controller for the garden page.
 */
public class GardenController {
    /**
     * The data store
     */
    private final DataStore dataStore;

    /**
     * Constructs a new GardenController with the specified data store.
     *
     * @param dataStore the data store
     */
    public GardenController(DataStore dataStore) {
        this.dataStore = dataStore;
    }

    /**
     * The back button
     */
    @FXML
    private Button backButton;

    @FXML
    private Button addGardenButton;

    /**
     * Returns the user to the home page when the back button is clicked.
     * 
     * @param event event details
     * @throws IOException if an I/O error occurs
     */
    @FXML
    public void backButtonClicked(ActionEvent event) throws IOException {
        Stage stage = (Stage) backButton.getScene().getWindow();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/gardenplanner/gardenplanner/homepage.fxml"));
        loader.setControllerFactory(type -> new HomepageController(dataStore));

        stage.setScene(new Scene(loader.load()));
        stage.show();
    }

    @FXML
    public void addGardenButtonClicked(ActionEvent event) throws IOException {
        Stage stage = (Stage) addGardenButton.getScene().getWindow();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/gardenplanner/gardenplanner/addgarden.fxml"));
        loader.setControllerFactory(type -> new GardenAddGardenController(dataStore));

        stage.setScene(new Scene(loader.load()));
        stage.show();
    }

}
