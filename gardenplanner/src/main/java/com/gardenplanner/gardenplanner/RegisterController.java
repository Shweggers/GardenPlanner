package com.gardenplanner.gardenplanner;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.mindrot.jbcrypt.BCrypt;

import java.io.IOException;

public class RegisterController {

    private final DataStore dataStore;
    public RegisterController(DataStore dataStore) {
        this.dataStore = dataStore;
    }

    @FXML
    private TextField usernameField;

    @FXML
    private TextField emailField;

    @FXML
    private PasswordField passwordField;

    /**
     * Handle the register button click event
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
            UserDAO userDAO = new UserDAO();
            userDAO.insert(user);

            // Confirmation message that registration went through
            new Alert(Alert.AlertType.INFORMATION, "Registration successful!").showAndWait();

            // Load the login page FXML and switch to the login scene
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/gardenplanner/gardenplanner/loginpage.fxml"));
            loader.setControllerFactory(type -> new LoginController(dataStore));

            stage.setScene(new Scene(loader.load()));
            stage.show();
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, "Registration failed!").showAndWait();
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
        loader.setControllerFactory(type -> new LoginController(dataStore));

        stage.setScene(new Scene(loader.load()));
        stage.show();
    }
}


