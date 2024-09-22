package com.gardenplanner.gardenplanner;

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

public class LoginController {

    // Used to hold the logged-in user's session data
    private final DataStore dataStore;

    // Constructor for the LoginController
    public LoginController(DataStore dataStore) {
        this.dataStore = dataStore;
    }

    @FXML
    protected TextField usernameField;

    @FXML
    protected PasswordField passwordField;

    /**
     * Handle the login button click event
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
            // Create a new instance of UserDAO to retrieve the users data
            UserDAO userDAO = new UserDAO();
            User user = userDAO.getUser(username);

            // If the user does not exist, show an error alert and stop the login process
            if (user == null) {
                new Alert(AlertType.ERROR, "User not found!").showAndWait();
                return;
            }

            // Check if the entered password matches the stored hashed password
            if (BCrypt.checkpw(password, user.hashedPassword())) {
                // If the password is correct, set the current user in the DataStore
                dataStore.setCurrentUser(user);

                // Load the homepage FXML file and switch the scene to the homepage
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/gardenplanner/gardenplanner/homepage.fxml"));
                loader.setControllerFactory(type -> new HomepageController(dataStore));

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
     * Handle the register button click event
     * @throws IOException
     */
    @FXML
    private void handleRegister() throws IOException {
        Stage stage = (Stage) usernameField.getScene().getWindow();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/gardenplanner/gardenplanner/registerpage.fxml"));
        loader.setControllerFactory(type -> new RegisterController(dataStore));

        stage.setScene(new Scene(loader.load(), 900, 600));
        stage.show();
    }

    /**
     * Handle the reset password button click event
     * @throws IOException
     */
    @FXML
    private void handleResetPassword() throws IOException {
        Stage stage = (Stage) usernameField.getScene().getWindow();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/gardenplanner/gardenplanner/resetpasswordpage.fxml"));
        loader.setControllerFactory(type -> new ResetPasswordController(dataStore));

        stage.setScene(new Scene(loader.load(), 900, 600));
        stage.show();
    }
}
