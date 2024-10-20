package com.gardenplanner.gardenplanner.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * GardenAddGardenController is a class that represents a controller for adding a new garden.
 */
public class FriendAddFriendController {
    @FXML
    private TextField AddFriendName;
    @FXML
    private Button confirmButton;
    @FXML
    private Button cancelButton;

    /**
     * Constructs a new GardenAddGardenController
     */
    public FriendAddFriendController() {}

    /**
     * Handles the save button click event.
     *
     * @param event the event details
     */
    @FXML
    public void confirmButtonClicked(ActionEvent event) {
        String gardenName = AddFriendName.getText();
        // Add logic to save the garden name to the data store
        // dataStore.addGarden(new Garden(gardenName));

        // Close the current window
        Stage stage = (Stage) confirmButton.getScene().getWindow();
        stage.close();
    }

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

}