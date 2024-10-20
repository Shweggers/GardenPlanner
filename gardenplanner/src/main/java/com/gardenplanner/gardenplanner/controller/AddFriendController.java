// Java
package com.gardenplanner.gardenplanner.controller;

import com.gardenplanner.gardenplanner.model.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * AddFriendController is a class that represents a controller for adding a new friend.
 */
public class AddFriendController {
    @FXML
    private TextField addFriendName;
    @FXML
    private Button confirmButton;
    @FXML
    private Button cancelButton;

    /**
     * Constructs a new AddFriendController
     */
    public AddFriendController() {
    }

    /**
     * Handles the confirm button click event.
     *
     * @param event the event details
     */
    @FXML
    public void confirmButtonClicked(ActionEvent event) {
        String friendName = addFriendName.getText();
        if (friendName != null && !friendName.trim().isEmpty()) {
            User user = DataStore.getInstance().getCurrentUser();
            User friend = UserManager.getInstance().getUser(friendName);

            // Check if the friend exists in the database
            if (friend == null) {
                new Alert(Alert.AlertType.ERROR, "Friend not found!").showAndWait();
                return;
            }

            // Create a new Friend object
            Friend newFriend = new Friend(user.ID(), friend.ID(), friendName);
            FriendManager.getInstance().insert(newFriend);

            // Close the current window
            Stage stage = (Stage) confirmButton.getScene().getWindow();
            stage.close();
        } else {
            // Handle the case where friendName is null or empty
            new Alert(Alert.AlertType.ERROR, "Friend name cannot be empty.").showAndWait();
        }
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