package com.gardenplanner.gardenplanner;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import org.mindrot.jbcrypt.BCrypt;

public class RegisterController {

    @FXML
    private TextField usernameField;

    @FXML
    private TextField emailField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private void handleRegister() {
        String username = usernameField.getText();
        String email = emailField.getText();
        String password = passwordField.getText();

        if (username.isEmpty() || email.isEmpty() || password.isEmpty()) {
            new Alert(Alert.AlertType.ERROR, "All fields must be filled in!").showAndWait();
            return;
        }

        String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt());

        User user = new User(username, email, hashedPassword);

        try {
            UserDAO userDAO = new UserDAO();
            userDAO.insert(user);
            new Alert(Alert.AlertType.INFORMATION, "Registration successful!").showAndWait();
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, "Registration failed!").showAndWait();
        }
    }
}

