package com.gardenplanner.gardenplanner;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class FriendpageController {

    private final DataStore dataStore;
    public FriendpageController(DataStore dataStore) {
        this.dataStore = dataStore;
    }

    @FXML
    private Button exitButton;

    /**
     * Handle the back button click event
     * @throws IOException
     */
    @FXML
    void backHome() throws IOException {
        Stage stage = (Stage) exitButton.getScene().getWindow();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/gardenplanner/gardenplanner/homepage.fxml"));
        loader.setControllerFactory(type -> new HomepageController(dataStore));

        stage.setScene(new Scene(loader.load(), 900, 600));
        stage.show();

    }
}
