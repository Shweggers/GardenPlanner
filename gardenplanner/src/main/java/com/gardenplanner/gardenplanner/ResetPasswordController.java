package com.gardenplanner.gardenplanner;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import org.mindrot.jbcrypt.BCrypt;

import java.io.IOException;
import java.util.Objects;

public class ResetPasswordController {

    private final DataStore dataStore;
    public ResetPasswordController(DataStore dataStore) {
        this.dataStore = dataStore;
    }

    @FXML
    private TextField usernameField;

    @FXML
    private TextField emailField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private PasswordField confirmPasswordField;

    @FXML
    private void handleResetPassword() {
        String username = usernameField.getText();
        String email = emailField.getText();
        String password = passwordField.getText();
        String confirmPassword = confirmPasswordField.getText();

        if (username.isEmpty() || email.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
            new Alert(AlertType.ERROR, "All fields must be filled in!").showAndWait();
            return;
        }

        if (!password.equals(confirmPassword)) {
            new Alert(AlertType.ERROR, "Passwords do not match!").showAndWait();
            return;
        }

        try {
            UserDAO userDAO = new UserDAO();
            User user = userDAO.getUser(username);

            if (user == null) {
                new Alert(AlertType.ERROR, "User not found!").showAndWait();
                return;
            }

            if (!user.email().equals(email)) {
                new Alert(AlertType.ERROR, "Email does not match our records!").showAndWait();
                return;
            }

            String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt());

            userDAO.updatePassword(username, hashedPassword);

            new Alert(AlertType.INFORMATION, "Password reset successful!").showAndWait();

            // TODO: navigate to login
        } catch (Exception e) {
            new Alert(AlertType.ERROR, "Password reset failed!").showAndWait();
        }
    }

    @FXML
    private void handleLogin() throws IOException {
        Stage stage = (Stage) usernameField.getScene().getWindow();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/gardenplanner/gardenplanner/loginpage.fxml"));
        loader.setControllerFactory(type -> new LoginController(dataStore));

        stage.setScene(new Scene(loader.load(), 900, 600));
        stage.show();
    }
}
