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

    private final DataStore dataStore;
    public LoginController(DataStore dataStore) {
        this.dataStore = dataStore;
    }

    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private void handleLogin() {
        Stage stage = (Stage) usernameField.getScene().getWindow();

        String username = usernameField.getText();
        String password = passwordField.getText();

        if (username.isEmpty() || password.isEmpty()) {
            new Alert(AlertType.ERROR, "Please fill in all fields").showAndWait();
            return;
        }

        try {
            UserDAO userDAO = new UserDAO();
            User user = userDAO.getUser(username);

            if (user == null) {
                new Alert(AlertType.ERROR, "User not found!").showAndWait();
                return;
            }

            if (BCrypt.checkpw(password, user.hashedPassword())) {
                dataStore.setCurrentUser(user);

                FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/gardenplanner/gardenplanner/homepage.fxml"));
                loader.setControllerFactory(type -> new HomepageController(dataStore));

                stage.setScene(new Scene(loader.load()));
                stage.show();
            } else {
                new Alert(AlertType.ERROR, "Invalid password").showAndWait();
            }
        } catch (Exception e) {
            new Alert(AlertType.ERROR, "Login failed!").showAndWait();
        }
    }

    @FXML
    private void handleRegister() throws IOException {
        Stage stage = (Stage) usernameField.getScene().getWindow();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/gardenplanner/gardenplanner/registerpage.fxml"));
        loader.setControllerFactory(type -> new RegisterController(dataStore));

        stage.setScene(new Scene(loader.load(), 900, 600));
        stage.show();
    }

    @FXML
    private void handleResetPassword() throws IOException {
        Stage stage = (Stage) usernameField.getScene().getWindow();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/gardenplanner/gardenplanner/resetpasswordpage.fxml"));
        loader.setControllerFactory(type -> new ResetPasswordController(dataStore));

        stage.setScene(new Scene(loader.load(), 900, 600));
        stage.show();
    }
}
