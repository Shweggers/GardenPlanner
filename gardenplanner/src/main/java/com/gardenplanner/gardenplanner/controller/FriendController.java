// Java
package com.gardenplanner.gardenplanner.controller;

import com.gardenplanner.gardenplanner.model.DataStore;
import com.gardenplanner.gardenplanner.model.Friend;
import com.gardenplanner.gardenplanner.model.FriendManager;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
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
    private ListView<Friend> friendList;
    @FXML
    private TextField searchFriends;

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
     * Populates the friend list based on the search query.
     */
    void populateFriends() {
        String search = searchFriends.getText();
        friendList.getItems().setAll(FriendManager.getInstance().searchFriends(
                DataStore.getInstance().getCurrentUser().ID(), search));
    }

    /**
     * Initializes the controller after the root element has been completely processed.
     */
    @FXML
    void initialize() {
        friendList.setCellFactory(this::renderListCell);
        populateFriends();
    }

    /**
     * Renders each friend in the ListView.
     *
     * @param list the ListView of friends
     * @return a ListCell for each friend
     */
    ListCell<Friend> renderListCell(ListView<Friend> list) {
        return new ListCell<>() {
            @Override
            protected void updateItem(Friend friend, boolean empty) {
                super.updateItem(friend, empty);
                if (empty || friend == null) {
                    setText(null);
                } else {
                    setText(friend.friendName());
                }
            }
        };
    }
}