// Java
package com.gardenplanner.gardenplanner.controller;

import com.gardenplanner.gardenplanner.model.DataStore;
import com.gardenplanner.gardenplanner.model.Friend;
import com.gardenplanner.gardenplanner.model.FriendManager;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import java.io.IOException;

/**
 * FriendController is a class that represents a controller for the friend page.
 */
public class FriendController {
    @FXML
    private Button backButton;
    @FXML
    private Button addFriendButton;
    @FXML
    private Button removeFriendButton;
    @FXML
    private ListView<Friend> friendList;
    @FXML
    private TextField searchFriends;
    @FXML
    private Label friendNameLabel;

    /**
     * Constructs a new FriendController
     */
    public FriendController() {}

    /**
     * Returns the user to the home page when the back button is clicked.
     *
     * @throws IOException
     */
    @FXML
    void backHome() throws IOException {
        Stage stage = (Stage) backButton.getScene().getWindow();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/gardenplanner/gardenplanner/homepage.fxml"));
        loader.setControllerFactory(type -> new HomepageController());

        stage.setScene(new Scene(loader.load(), 900, 600));
        stage.show();
    }

    /**
     * Opens the add friend page when the add friend button is clicked.
     *
     * @throws IOException
     */
    @FXML
    void addFriend() throws IOException {
        Stage stage = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/gardenplanner/gardenplanner/friendpage_addfriend.fxml"));
        loader.setControllerFactory(type -> new AddFriendController());

        stage.setScene(new Scene(loader.load()));
        stage.show();
    }

    /**
     * Handles the remove friend event.
     *
     */
    @FXML
    void removeFriend() {
        Friend selectedFriend = friendList.getSelectionModel().getSelectedItem();
        if (selectedFriend != null) {
            FriendManager.getInstance().delete(selectedFriend);
            friendNameLabel.setText("");
        }
    }

    /**
     * Initializes the friend page.
     */
    @FXML
    public void initialize() {
        friendList.setCellFactory(param -> new ListCell<>() {
            @Override
            protected void updateItem(Friend friend, boolean empty) {
                super.updateItem(friend, empty);

                if (empty || friend == null) {
                    setText(null);
                } else {
                    setText(friend.friendName());
                }
            }
        });

        friendList.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                friendNameLabel.setText(newValue.friendName());
            } else {
                friendNameLabel.setText("");
            }

        });

        populateFriends();
    }

    public void populateFriends() {
        friendList.getItems().setAll(FriendManager.getInstance().searchFriends(DataStore.getInstance().getCurrentUser().ID(), searchFriends.getText()));

    }
}
