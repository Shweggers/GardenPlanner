package com.gardenplanner.gardenplanner.controller;

import com.gardenplanner.gardenplanner.model.DataStore;
import com.gardenplanner.gardenplanner.model.Garden;
import com.gardenplanner.gardenplanner.model.GardenManager;
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
    private TextField addGardenName;
    @FXML
    private Button cancelButton;
    @FXML
    private Button addGardenConfirmButton;
    private GardenController gardenController;

    /**
     * Constructs a new GardenAddGardenController
     *
     * @param gardenController the garden controller
     */
    public GardenAddGardenController(GardenController gardenController) { this.gardenController = gardenController; }

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
        Garden garden = new Garden(DataStore.getInstance().getCurrentUser().ID(), addGardenName.getText());
        GardenManager.getInstance().insert(garden);

        gardenController.populateList();

        // Close the current window
        cancelButtonClicked(event);
    }
}
