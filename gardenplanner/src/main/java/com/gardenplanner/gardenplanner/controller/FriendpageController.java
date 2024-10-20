package com.gardenplanner.gardenplanner.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * FriendpageController is a class that represents a controller for the friend page.
 */
public class FriendpageController {
    @FXML
    private Button backButton;
    @FXML
    private  Button addFriendButton;

    /**
     * Constructs a new FriendpageController
     */
    public FriendpageController() {}

    /**
     * Returns the user to the home page when the back button is clicked.
     *
     * @throws IOException
     */
    @FXML
    void backHome() throws IOException {
        Stage stage = (Stage) backButton.getScene().getWindow();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/gardenplanner/gardenplanner/homepage.fxml"));
        loader.setControllerFactory(type -> new HomepageController());

        stage.setScene(new Scene(loader.load(), 900, 600));
        stage.show();
    }

    /**
     * Adds a new friend to the friend list when the add friend button is clicked.
     *
     * @throws IOException if an I/O error occurs
     */
    @FXML
    void addFriend() throws IOException {
        Stage stage = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/gardenplanner/gardenplanner/friendpage_addfriend.fxml"));
        loader.setControllerFactory(type -> new FriendAddFriendController());

        stage.setScene(new Scene(loader.load()));
        stage.show();
    }
}
