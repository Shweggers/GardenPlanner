package com.gardenplanner.gardenplanner;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.mindrot.jbcrypt.BCrypt;

import java.io.IOException;
import java.util.Objects;

public class LoginController {

    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private void handleLogin() {
        String username = usernameField.getText();
        String password = passwordField.getText();

        if (username.isEmpty() || password.isEmpty()) {
            new Alert(AlertType.ERROR, "Please fill in all fields").showAndWait();
            return;
        }

        try {
            UserDAO userDAO = new UserDAO();
            User user = userDAO.getUserByUsername(username);

            if (user == null) {
                new Alert(AlertType.ERROR, "User not found!").showAndWait();
                return;
            }

            if (BCrypt.checkpw(password, user.hashedPassword())) {
                new Alert(AlertType.INFORMATION, "Login successful!").showAndWait();
            } else {
                new Alert(AlertType.ERROR, "Invalid password").showAndWait();
            }
        } catch (Exception e) {
            new Alert(AlertType.ERROR, "Login failed!").showAndWait();
        }
    }

    @FXML
    private void handleRegister() throws IOException {
        Parent loginPage = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/com/gardenplanner/gardenplanner/registerpage.fxml")));
        Stage stage = (Stage) usernameField.getScene().getWindow();
        stage.setScene(new Scene(loginPage, 800, 600));
        stage.show();
    }
}
