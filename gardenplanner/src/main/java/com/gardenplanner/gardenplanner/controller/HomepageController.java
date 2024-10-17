package com.gardenplanner.gardenplanner.controller;

import com.gardenplanner.gardenplanner.model.DataStore;
import com.gardenplanner.gardenplanner.model.User;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Optional;

/**
 * HomepageController is a class that represents a controller for the homepage.
 */
public class HomepageController {
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
     * Constructs a new HomepageController
     */
    public HomepageController() {}

    /**
     * Change scene to Garden Book page upon called
     *
     * @throws IOException
     */
    @FXML
    void handleGardenBook() throws IOException {
        Stage stage = (Stage) gardenBookButton.getScene().getWindow();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/gardenplanner/gardenplanner/gardenbookpage.fxml"));
        loader.setControllerFactory(type -> new GardenbookController());

        stage.setScene(new Scene(loader.load(), 900, 600));
        stage.show();
    }

    /**
     * Change scene to Friend page upon called
     *
     * @throws IOException
     */
    @FXML
    void handleFriendpage() throws IOException {
        Stage stage = (Stage) friendButton.getScene().getWindow();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/gardenplanner/gardenplanner/friendpage.fxml"));
        loader.setControllerFactory(type -> new FriendpageController());

        stage.setScene(new Scene(loader.load(), 900, 600));
        stage.show();
    }

    /**
     * Change scene to Garden page upon called
     *
     * @throws IOException
     */
    @FXML
    void handleGardenPageNavigation() throws IOException {
        Stage stage = (Stage) gardenButton.getScene().getWindow();  // Get the current stage

        // Load the garden page FXML
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/gardenplanner/gardenplanner/gardenpage.fxml"));
        loader.setControllerFactory(type -> new GardenController());

        stage.setScene(new Scene(loader.load(), 900, 600));  // Set the garden page scene
        stage.show();  // Show the new scene
    }

    /**
     * Change scene to Login page upon called
     *
     * @throws IOException
     */
    @FXML
    void handleLogOut() throws IOException {
        Stage stage = (Stage) logOutButton.getScene().getWindow();

        ButtonType YesButton = new ButtonType("Yes");
        ButtonType CancelButton = new ButtonType("Cancel");

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to logout?", YesButton, CancelButton);
        alert.setTitle("Log out");
        alert.setHeaderText("Log out");
        Optional<ButtonType> result = alert.showAndWait();

        if (result.isPresent()) {
            if (result.get() == YesButton) {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/gardenplanner/gardenplanner/loginpage.fxml"));
                loader.setControllerFactory(type -> new LoginController());

                stage.setScene(new Scene(loader.load(), 900, 600));
                stage.show();
            } else {
                alert.close();
            }
        }
    }

    /**
     * Initialize the user data when Homepage is called
     */
    @FXML
    void initialize() {
        User currentUser = DataStore.getInstance().getCurrentUser();

        welcomeMsg.setText("Welcome " + currentUser.username());

        /*if (SQLGardenDAO.getGardens(SQLUserDAO.returnID(currentUser.username())) == null) {
            SQLGardenDAO.insert(new Garden("My Garden", SQLUserDAO.returnID(currentUser.username())));
        }*/
    }
}
