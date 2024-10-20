// Java
package com.gardenplanner.gardenplanner.controller;

import com.gardenplanner.gardenplanner.model.DataStore;
import com.gardenplanner.gardenplanner.model.UserManager;
import com.gardenplanner.gardenplanner.model.DAO.IUserDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import javax.sql.RowSet;

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
        friendManager = FriendManager.getInstance();
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
            UserManager userManager = UserManager.getInstance();
            RowSet currentUser;
            int userId = userManager.returnID(currentUser.getUsername());
            int friendId = userManager.returnID(friendName);

            // Check if the friend exists in the database
            User friend = userManager.getUser(friendName);
            if (friend == null) {
                new Alert(Alert.AlertType.ERROR, "Friend not found!").showAndWait();
                return;
            }

            // Create a new Friend object
            Friend newFriend = new Friend(userId, friendId);
            friendManager.addFriend(newFriend);

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

    public void setAddFriendName(String name) {
        addFriendName.setText(name);
    }

    public String getAddFriendName() {
        return addFriendName.getText();
    }
}