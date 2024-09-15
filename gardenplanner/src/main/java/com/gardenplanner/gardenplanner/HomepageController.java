package com.gardenplanner.gardenplanner;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.Region;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.Optional;
import java.util.ResourceBundle;

public class HomepageController implements Initializable {

    private User theUser;
    @FXML
    private Button gardenBookButton;

    @FXML
    private Button logOutButton;

    @FXML
    private Button friendButton;

    @FXML
    private Button gardenButton;

    @FXML
    private Label welcomeMsg;

    @FXML
    void handleButtonClick(ActionEvent event) throws IOException {
        Parent showPage;
        if (event.getSource() == gardenBookButton) {
            Parent gardenBookPage = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/com/gardenplanner/gardenplanner/loginpage.fxml")));
            Stage stage = (Stage) gardenBookButton.getScene().getWindow();
            stage.setScene(new Scene(gardenBookPage, 900, 600));
            stage.show();
        }
        else if (event.getSource() == friendButton) {
            Parent friendPage = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/com/gardenplanner/gardenplanner/loginpage.fxml")));
            Stage stage = (Stage) friendButton.getScene().getWindow();
            stage.setScene(new Scene(friendPage, 900, 600));
            stage.show();
        }
        else if (event.getSource() == gardenButton) {
            Parent gardenPage = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/com/gardenplanner/gardenplanner/loginpage.fxml")));
            Stage stage = (Stage) gardenButton.getScene().getWindow();
            stage.setScene(new Scene(gardenPage, 900, 600));
            stage.show();
        }
    }

    @FXML
    void handleLogOut() throws IOException {
        ButtonType YesButton = new ButtonType("Yes");
        ButtonType CancelButton = new ButtonType("Cancel");
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION,"Are you sure you want to logout?", YesButton, CancelButton);
        alert.setTitle("Log out");
        alert.setHeaderText("Log out");
        Optional<ButtonType> result = alert.showAndWait();
        if(result.isPresent()) {
            if(result.get() == YesButton) {
                Parent loginPage = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/com/gardenplanner/gardenplanner/loginpage.fxml")));
                Stage stage = (Stage) logOutButton.getScene().getWindow();
                stage.setScene(new Scene(loginPage, 900, 600));
                stage.show();
            }
            else {
                alert.close();
            }
        }
    }

    public void setTheUser(User user) {
        this.theUser = user;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Platform.runLater(() -> {
            welcomeMsg.setText("Welcome, " + theUser.username() + ".");
            welcomeMsg.setMinWidth(Region.USE_PREF_SIZE);
            welcomeMsg.setAlignment(Pos.CENTER);
        });
    }

}
