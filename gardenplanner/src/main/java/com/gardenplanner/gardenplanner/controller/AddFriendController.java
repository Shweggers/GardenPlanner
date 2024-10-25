// Java
package com.gardenplanner.gardenplanner.controller;

import com.gardenplanner.gardenplanner.model.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

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
    public void confirmButtonClicked(ActionEvent event) throws IOException {
        String friendName = addFriendName.getText();
        if (friendName != null && !friendName.trim().isEmpty()) {
            User user = DataStore.getInstance().getCurrentUser();
            User friend = UserManager.getInstance().getUser(friendName);

            // Check if the friend exists in the database
            if (friend == null) {
                new Alert(Alert.AlertType.ERROR, "Friend not found!").showAndWait();
                return;
            }
            System.out.println("Friend found: " + friend);
            // Create a new Friend object
            FriendManager.getInstance().insert(new Friend(user.ID(), friend.ID(), addFriendName.getText()));
            new Alert(Alert.AlertType.ERROR, friendName + " has been added!").showAndWait();


            // Close the current window
            Stage stage = (Stage) confirmButton.getScene().getWindow();
            stage.close();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/gardenplanner/gardenplanner/friendpage.fxml"));
            loader.setControllerFactory(type -> new FriendController());

            stage.setScene(new Scene(loader.load(), 900, 600));
            stage.show();
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