package com.gardenplanner.gardenplanner.controller;

import com.gardenplanner.gardenplanner.model.*;

import com.gardenplanner.gardenplanner.model.DAO.SQLGardenDAO;
import com.gardenplanner.gardenplanner.model.DAO.SQLUserDAO;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Optional;

/**
 * HomepageController is a class that represents a controller for the homepage.
 */
public class HomepageController {

    /**
     * Constructs a new HomepageController
     */
    public HomepageController() {
    }

    /**
     * The garden book button
     */
    @FXML
    private Button gardenBookButton;

    /**
     * The logout button
     */
    @FXML
    private Button logOutButton;

    /**
     * The friend button
     */
    @FXML
    private Button friendButton;

    /**
     * The garden button
     */
    @FXML
    private Button gardenButton;

    /**
     * The welcome message
     */
    @FXML
    private Label welcomeMsg;


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
    void handleFriendpage() throws IOException{
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
        loader.setControllerFactory(type -> new GardenbookController());

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

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION,"Are you sure you want to logout?", YesButton, CancelButton);
        alert.setTitle("Log out");
        alert.setHeaderText("Log out");
        Optional<ButtonType> result = alert.showAndWait();

        if(result.isPresent()) {
            if(result.get() == YesButton) {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/gardenplanner/gardenplanner/loginpage.fxml"));
                loader.setControllerFactory(type -> new LoginController());

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
     *
     * @throws SQLException
     */
    @FXML
    void initialize() throws SQLException {
        SQLGardenDAO SQLGardenDAO = new SQLGardenDAO();
        SQLUserDAO SQLUserDAO = new SQLUserDAO();

        User currentUser = DataStore.getInstance().getCurrentUser();

        welcomeMsg.setText("Welcome " + currentUser.username());

        /*if (SQLGardenDAO.getGardens(SQLUserDAO.returnID(currentUser.username())) == null) {
            SQLGardenDAO.insert(new Garden("My Garden", SQLUserDAO.returnID(currentUser.username())));
        }*/
    }
}
