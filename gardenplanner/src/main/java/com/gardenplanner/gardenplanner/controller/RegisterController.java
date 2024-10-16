package com.gardenplanner.gardenplanner.controller;

import com.gardenplanner.gardenplanner.model.User;
import com.gardenplanner.gardenplanner.model.UserManager;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.mindrot.jbcrypt.BCrypt;

import java.io.IOException;

/**
 * RegisterController is a class that represents a controller for the registration page.
 */
public class RegisterController {

    /**
     * Constructs a new RegisterController with the specified data store.
     */
    public RegisterController() {
    }

    /**
     * The username field
     */
    @FXML
    private TextField usernameField;

    /**
     * The email field
     */
    @FXML
    private TextField emailField;

    /**
     * The password field
     */
    @FXML
    private PasswordField passwordField;

    /**
     * Register the user with the entered information
     */
    @FXML
    private void handleRegister() {
        Stage stage = (Stage) usernameField.getScene().getWindow();

        // Retrieve the entered values from the input fields
        String username = usernameField.getText();
        String email = emailField.getText();
        String password = passwordField.getText();

        // Validate that none of the fields are empty
        if (username.isEmpty() || email.isEmpty() || password.isEmpty()) {
            new Alert(Alert.AlertType.ERROR, "All fields must be filled in!").showAndWait();
            return;
        }

        // Hash the password using BCrypt for secure storage
        String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt());

        // Create a new User object with the entered username, email, and hashed password
        User user = new User(username, email, hashedPassword);

        try {
            // Insert the new user into the database using UserDAO
            UserManager userManager = UserManager.getInstance();
            userManager.insert(user);

            // Confirmation message that registration went through
            new Alert(Alert.AlertType.INFORMATION, "Registration successful!").showAndWait();

            // Load the login page FXML and switch to the login scene
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/gardenplanner/gardenplanner/loginpage.fxml"));
            loader.setControllerFactory(type -> new LoginController());

            stage.setScene(new Scene(loader.load()));
            stage.show();
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, "Registration failed!").showAndWait();
        }
    }

    /**
     * Redirect the user to the login page
     * 
     * @throws IOException
     */
    @FXML
    private void handleLogin() throws IOException {
        Stage stage = (Stage) usernameField.getScene().getWindow();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/gardenplanner/gardenplanner/loginpage.fxml"));
        loader.setControllerFactory(type -> new LoginController());

        stage.setScene(new Scene(loader.load()));
        stage.show();
    }
}


