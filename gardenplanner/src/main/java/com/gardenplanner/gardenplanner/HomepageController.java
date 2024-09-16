package com.gardenplanner.gardenplanner;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;
import java.util.Optional;

public class HomepageController {

    @FXML
    private Button gardenBookButton;

    @FXML
    private Button logOutButton;

    @FXML
    private Button friendButton;

    @FXML
    private Button gardenButton;

    @FXML
    void handleButtonClick() {

    }

    @FXML
    void handleFriendpage() throws IOException{
        Parent friendPage = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/com/gardenplanner/gardenplanner/friendpage.fxml")));
        Stage stage = (Stage) friendButton.getScene().getWindow();
        stage.setScene(new Scene(friendPage, 1000, 750));
        stage.show();

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



}
