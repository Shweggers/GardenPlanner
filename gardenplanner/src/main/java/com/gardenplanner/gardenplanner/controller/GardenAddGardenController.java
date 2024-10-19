package com.gardenplanner.gardenplanner.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

/**
 * GardenAddGardenController is a class that represents a controller for adding a new garden.
 */
public class GardenAddGardenController {

    @FXML
    private Button cancelButton;
    @FXML
    private Button addGardenConfirmButton;

    /**
     * Constructs a new GardenAddGardenController
     */
    public GardenAddGardenController() {}

    /**
     * Handles the save button click event.
     *
     * @param event the event details
     */

    /**
     * Handles the cancel button click event.
     *
     * @param event the event details
     */
    @FXML
    public void cancelButtonClicked(ActionEvent event) {
        // Close the current window
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }

    /**
     * Handles the add garden button click event.
     *
     * @param event the event details
     */
    @FXML
    public void setAddGardenConfirmButton(ActionEvent event) {
        // Add logic to save the garden name to the data store
        // dataStore.addGarden(new Garden(gardenName));

        // Close the current window
        cancelButtonClicked(event);
    }
}