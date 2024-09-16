package com.gardenplanner.gardenplanner;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;


public class FriendpageController {

    @FXML
    private Button exitButton;

    @FXML
    void backHome() throws IOException {
        Parent homepage = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/com/gardenplanner/gardenplanner/homepage.fxml")));
        Stage stage = (Stage) exitButton.getScene().getWindow();
        stage.setScene(new Scene(homepage, 800, 600));
        stage.show();

    }
}
