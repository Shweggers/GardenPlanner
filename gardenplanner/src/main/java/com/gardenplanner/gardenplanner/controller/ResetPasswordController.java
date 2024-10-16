package com.gardenplanner.gardenplanner.controller;

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
 * ResetPasswordController is a class that represents a controller for the reset password page.
 */
public class ResetPasswordController {

    /**
     * Constructs a new ResetPasswordController
     */
    public ResetPasswordController() {
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
     * The password confirmation field
     */
    @FXML
    private PasswordField confirmPasswordField;

    /**
     * Reset the user's password with the entered information
     */
    @FXML
    private void handleResetPassword() {
        // Retrieve the entered values from the input fields
        String username = usernameField.getText();
        String email = emailField.getText();
        String password = passwordField.getText();
        String confirmPassword = confirmPasswordField.getText();

        // Validate that none of the fields are empty
        if (username.isEmpty() || email.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
            new Alert(AlertType.ERROR, "All fields must be filled in!").showAndWait();
            return;
        }

        // Ensure the two password fields match
        if (!password.equals(confirmPassword)) {
            new Alert(AlertType.ERROR, "Passwords do not match!").showAndWait();
            return;
        }

        try {
            // Retrieve the user from the database using the UserDAO
            UserManager userManager = UserManager.getInstance();
            User user = userManager.getUser(username);

            // Check if the user exists in the database
            if (user == null) {
                new Alert(AlertType.ERROR, "User not found!").showAndWait();
                return;
            }

            // Verify that the entered email matches the one stored for this user
            if (!user.email().equals(email)) {
                new Alert(AlertType.ERROR, "Email does not match our records!").showAndWait();
                return;
            }

            // Hash the new password using BCrypt
            String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt());

            // Update the user's password in the database using the UserDAO
            userManager.updatePassword(username, hashedPassword);

            // Confirmation message after successful reset
            new Alert(AlertType.INFORMATION, "Password reset successful!").showAndWait();

            // TODO: navigate to login
        } catch (Exception e) {
            new Alert(AlertType.ERROR, "Password reset failed!").showAndWait();
        }
    }

    /**
     * Handle the login button click event
     * 
     * @throws IOException
     */
    @FXML
    private void handleLogin() throws IOException {
        Stage stage = (Stage) usernameField.getScene().getWindow();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/gardenplanner/gardenplanner/loginpage.fxml"));
        loader.setControllerFactory(type -> new LoginController());

        stage.setScene(new Scene(loader.load(), 900, 600));
        stage.show();
    }
}
