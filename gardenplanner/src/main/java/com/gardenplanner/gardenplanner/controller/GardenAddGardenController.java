package com.gardenplanner.gardenplanner.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * GardenAddGardenController is a class that represents a controller for adding a new garden.
 */
public class GardenAddGardenController {
    @FXML
    private TextField gardenNameField;
    @FXML
    private Button saveButton;

    /**
     * Constructs a new GardenAddGardenController
     */
    public GardenAddGardenController() {}

    /**
     * Handles the save button click event.
     *
     * @param event the event details
     */
    @FXML
    public void saveButtonClicked(ActionEvent event) {
        String gardenName = gardenNameField.getText();
        // Add logic to save the garden name to the data store
        // dataStore.addGarden(new Garden(gardenName));

        // Close the current window
        Stage stage = (Stage) saveButton.getScene().getWindow();
        stage.close();
    }
}