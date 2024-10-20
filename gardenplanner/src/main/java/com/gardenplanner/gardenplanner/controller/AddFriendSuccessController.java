package com.gardenplanner.gardenplanner.controller;

import com.gardenplanner.gardenplanner.model.Friend;
import com.gardenplanner.gardenplanner.model.FriendManager;
import com.gardenplanner.gardenplanner.model.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;


public class AddFriendSuccessController {
    @FXML
    private Button okButton;

    @FXML
    public void okButtonClicked (ActionEvent event) {
        Stage stage = (Stage) okButton.getScene().getWindow();
        stage.close();
    }
}

