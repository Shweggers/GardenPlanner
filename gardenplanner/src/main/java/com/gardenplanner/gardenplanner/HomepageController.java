package com.gardenplanner.gardenplanner;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Optional;

public class HomepageController {

    private final DataStore dataStore;
    public HomepageController(DataStore dataStore) {
        this.dataStore = dataStore;
    }

    @FXML
    private Button gardenBookButton;

    @FXML
    private Button logOutButton;

    @FXML
    private Button friendButton;

    @FXML
    private Button gardenButton;

    @FXML
    private Label welcomeMsg;

    /**
     * Change scene to GardenBook page upon called
     */
    @FXML
    void handleGardenBook() throws IOException {
        Stage stage = (Stage) gardenBookButton.getScene().getWindow();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/gardenplanner/gardenplanner/gardenbook.fxml"));
        loader.setControllerFactory(type -> new GardenbookController(dataStore));

        stage.setScene(new Scene(loader.load(), 900, 600));
        stage.show();
    }

    /**
     * Change scene to Friend page upon called
     */
    @FXML
    void handleFriendpage() throws IOException{
        Stage stage = (Stage) friendButton.getScene().getWindow();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/gardenplanner/gardenplanner/friendpage.fxml"));
        loader.setControllerFactory(type -> new FriendpageController(dataStore));

        stage.setScene(new Scene(loader.load(), 900, 600));
        stage.show();
    }

    /**
     * Change scene to Garden page upon called
     */
    @FXML
    void handleGardenPageNavigation() throws IOException {
        Stage stage = (Stage) gardenButton.getScene().getWindow();  // Get the current stage

        // Load the garden page FXML
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/gardenplanner/gardenplanner/gardenpage.fxml"));
        loader.setControllerFactory(type -> new GardenbookController(dataStore));

        stage.setScene(new Scene(loader.load(), 900, 600));  // Set the garden page scene
        stage.show();  // Show the new scene
    }

    /**
     * Change scene to Login page upon called
     */
    @FXML
    void handleLogOut() throws IOException {
        Stage stage = (Stage) logOutButton.getScene().getWindow();

        ButtonType YesButton = new ButtonType("Yes");
        ButtonType CancelButton = new ButtonType("Cancel");

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION,"Are you sure you want to logout?", YesButton, CancelButton);
        alert.setTitle("Log out");
        alert.setHeaderText("Log out");
        Optional<ButtonType> result = alert.showAndWait();

        if(result.isPresent()) {
            if(result.get() == YesButton) {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/gardenplanner/gardenplanner/loginpage.fxml"));
                loader.setControllerFactory(type -> new LoginController(dataStore));

                stage.setScene(new Scene(loader.load(), 900, 600));
                stage.show();
            }
            else {
                alert.close();
            }
        }
    }

    /**
     * Initialize the user data when Homepage is called
     */
    @FXML
    void initialize() {
        welcomeMsg.setText(dataStore.getCurrentUser().username());
    }
}
