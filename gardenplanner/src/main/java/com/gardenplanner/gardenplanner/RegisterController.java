package com.gardenplanner.gardenplanner;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.mindrot.jbcrypt.BCrypt;

import java.io.IOException;
import java.util.Objects;

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

    @FXML
    private void handleRegister() {
        Stage stage = (Stage) usernameField.getScene().getWindow();

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

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/gardenplanner/gardenplanner/loginpage.fxml"));
            loader.setControllerFactory(type -> new LoginController(dataStore));

            stage.setScene(new Scene(loader.load()));
            stage.show();
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, "Registration failed!").showAndWait();
        }
    }

    @FXML
    private void handleLogin() throws IOException {
        Stage stage = (Stage) usernameField.getScene().getWindow();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/gardenplanner/gardenplanner/loginpage.fxml"));
        loader.setControllerFactory(type -> new LoginController(dataStore));

        stage.setScene(new Scene(loader.load()));
        stage.show();
    }
}


