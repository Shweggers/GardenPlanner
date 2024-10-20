package com.gardenplanner.gardenplanner.controller;

import com.gardenplanner.gardenplanner.model.DataStore;
import com.gardenplanner.gardenplanner.model.User;
import com.gardenplanner.gardenplanner.model.UserManager;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.mindrot.jbcrypt.BCrypt;

import java.io.IOException;

/**
 * The LoginController class is a controller for the login page
 */
public class LoginController {
    /**
     * The username field
     */
    @FXML
    protected TextField usernameField;

    /**
     * The password field
     */
    @FXML
    protected PasswordField passwordField;

    /**
     * Constructor for the LoginController
     */
    public LoginController() {}

    /**
     * Handle the login process
     */
    @FXML
    protected void handleLogin() {
        // Get the current stage (window) where the login form is displayed
        Stage stage = (Stage) usernameField.getScene().getWindow();

        // Retrieve the entered username and password from the respective fields
        String username = usernameField.getText();
        String password = passwordField.getText();

        // If either field is empty, show an error alert and stop the login process
        if (username.isEmpty() || password.isEmpty()) {
            new Alert(AlertType.ERROR, "Please fill in all fields").showAndWait();
            return;
        }

        try {
            // get the User Manager instance to retrieve the users data
            UserManager userManager = UserManager.getInstance();
            User user = userManager.getUser(username);

            // If the user does not exist, show an error alert and stop the login process
            if (user == null) {
                new Alert(AlertType.ERROR, "User not found!").showAndWait();
                return;
            }

            // Check if the entered password matches the stored hashed password
            if (BCrypt.checkpw(password, user.hashedPassword())) {

                // If the password is correct, set the current user in the DataStore
                DataStore.getInstance().setCurrentUser(user);

                // Load the homepage FXML file and switch the scene to the homepage
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/gardenplanner/gardenplanner/homepage.fxml"));
                loader.setControllerFactory(type -> new HomepageController());

                stage.setScene(new Scene(loader.load()));
                stage.show();
            } else {
                // If the password is incorrect, show an error alert
                new Alert(AlertType.ERROR, "Invalid password").showAndWait();
            }
        } catch (Exception e) {
            // If there is an exception during login, show an error alert
            new Alert(AlertType.ERROR, "Login failed!").showAndWait();
        }
    }

    /**
     * Redirect the user to the register page
     *
     * @throws IOException
     */
    @FXML
    private void handleRegister() throws IOException {
        Stage stage = (Stage) usernameField.getScene().getWindow();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/gardenplanner/gardenplanner/registerpage.fxml"));
        loader.setControllerFactory(type -> new RegisterController());

        stage.setScene(new Scene(loader.load(), 900, 600));
        stage.show();
    }

    /**
     * Redirect the user to the reset password page
     *
     * @throws IOException
     */
    @FXML
    private void handleResetPassword() throws IOException {
        Stage stage = (Stage) usernameField.getScene().getWindow();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/gardenplanner/gardenplanner/resetpasswordpage.fxml"));
        loader.setControllerFactory(type -> new ResetPasswordController());

        stage.setScene(new Scene(loader.load(), 900, 600));
        stage.show();
    }
}
