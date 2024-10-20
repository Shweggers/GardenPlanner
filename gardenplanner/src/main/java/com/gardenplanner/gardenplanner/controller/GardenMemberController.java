package com.gardenplanner.gardenplanner.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

/**
 * GardenAddGardenController is a class that represents a controller for adding a new garden.
 */
public class GardenMemberController {
    @FXML
    ListView memberList;
    @FXML
    Button confirmButton;

    /**
     * Constructs a new GardenAddGardenController
     */
    public GardenMemberController() {}


    /**
     * Handles the confirm button click event.
     *
     * @param event the event details
     */
    @FXML
    void onConfirmButton(ActionEvent event) {
        // Close the current window
        Stage stage = (Stage) confirmButton.getScene().getWindow();
        stage.close();
    }
}
