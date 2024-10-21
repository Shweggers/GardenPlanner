package com.gardenplanner.gardenplanner.controller;

import com.gardenplanner.gardenplanner.model.GardenManager;
import com.gardenplanner.gardenplanner.model.User;
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
    ListView<String> memberList;
    @FXML
    Button confirmButton;
    private GardenController gardenController;

    /**
     * Constructs a new GardenAddGardenController
     *
     * @param gardenController the garden controller
     */
    public GardenMemberController(GardenController gardenController) {
        this.gardenController = gardenController;
    }


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

    /**
     * Initializes the controller.
     */
    @FXML
    void initialize() {
        memberList.getItems().setAll(
                GardenManager.getInstance()
                        .getUsers(gardenController.gardenList.getSelectionModel().getSelectedItem().ID())
                        .stream()
                        .map(User::username)
                        .toList()
        );
    }
}
