// Java
package com.gardenplanner.gardenplanner.controller;

import com.gardenplanner.gardenplanner.model.Friend;
import com.gardenplanner.gardenplanner.model.FriendManager;
import com.gardenplanner.gardenplanner.model.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
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

    private final FriendManager friendManager;

    /**
     * Constructs a new AddFriendController
     */
    public AddFriendController() {
        this.friendManager = FriendManager.getInstance();
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

            // Get the friend's user ID // THIS NEED TO BE CHANGED TO THE FRIEND'S USER ID
            int userId = getCurrentUser().getID();

            // NEED LOGIC TO CHECK IF USE EXISTS IN DATABASE


            // Create a new Friend object
            Friend newFriend = new Friend(userId, friendName);
            friendManager.insert(newFriend);

            // Close the current window
            Stage stage = (Stage) confirmButton.getScene().getWindow();
            stage.close();
        } else {
            // Handle the case where friendName is null or empty
            System.out.println("Friend name cannot be empty.");
        }
    }

    private User getCurrentUser() {
        return User.getCurrentUser();
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

    public void setAddFriendName(String name) {
        addFriendName.setText(name);
    }

    public String getAddFriendName() {
        return addFriendName.getText();
    }
}