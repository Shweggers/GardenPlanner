package com.gardenplanner.gardenplanner.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

/**
 * AddFriendSuccessController is a class that represents a controller for the add friend success page.
 */
public class AddFriendSuccessController {
    @FXML
    private Button okButton;

    /**
     * handle the ok button click event
     *
     * @param event the event details
     */
    @FXML
    public void okButtonClicked (ActionEvent event) {
        Stage stage = (Stage) okButton.getScene().getWindow();
        stage.close();
    }
}

