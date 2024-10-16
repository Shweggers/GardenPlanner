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

    /**
     * Constructs a new FriendpageController with the specified data store.
     */
    public FriendpageController() {
    }

    /**
     * The back button
     */
    @FXML
    private Button backButton;

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
}
