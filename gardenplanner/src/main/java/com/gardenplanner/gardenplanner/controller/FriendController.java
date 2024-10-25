// Java
package com.gardenplanner.gardenplanner.controller;

import com.gardenplanner.gardenplanner.model.*;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * FriendController is a class that represents a controller for the friend page.
 */
public class FriendController {
    @FXML
    private Button backButton;
    @FXML
    private TextField searchFriends;
    @FXML
    private ListView<Friend> friendList;
    @FXML
    private Button addFriendButton;
    @FXML
    private Label friendNameLabel;
    @FXML
    private VBox friendGardenContainer;
    @FXML
    private ListView<Garden> friendsGardens;
    @FXML
    private ListView<Plot> friendsGardenPlots;
    @FXML
    private TableView<PlantProperty> friendGardenTable;
    @FXML
    private TableColumn<PlantProperty, String> typeColumn;
    @FXML
    private TableColumn<PlantProperty, String> detailsColumn;
    @FXML
    private ImageView plantImage;
    @FXML
    private Button removeFriendButton;




    /**
     * Constructs a new FriendController
     */
    public FriendController() {}

    /**
     * Returns the user to the home page when the back button is clicked.
     *
     * @throws IOException if an I/O error occurs
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
     * Populates the friends list.
     */
    public void populateFriends() {
        String search = searchFriends.getText();
        friendList.getItems().setAll(FriendManager.getInstance().searchFriends(
                DataStore.getInstance().getCurrentUser().ID(),
                search
        ));
    }

    /**
     * Populates the friend gardens list.
     */
    public void populateFriendGardens() {
        Friend selectedFriend = friendList.getSelectionModel().getSelectedItem();
        if (selectedFriend != null) {
            friendsGardens.getItems().setAll(GardenManager.getInstance().searchGardens(
                    selectedFriend.friendID(),
                    ""
            ));
        }
    }

    /**
     * Populates the friend garden plots list.
     */
    public void populateFriendGardenPlots() {
        Garden selectedGarden = friendsGardens.getSelectionModel().getSelectedItem();
        if (selectedGarden != null) {
            friendsGardenPlots.getItems().setAll(PlotManager.getInstance().searchPlots(
                    selectedGarden.ID(),
                    ""
            ));
        }
    }

    /**
     * Populates the friend garden table.
     */
    public void populateFriendGardenTable() {
        Plant selectedPlantFromPlot = PlantManager.getInstance().getPlantFromName(
                friendList.getSelectionModel().getSelectedItem().friendID(),
                PlotManager.getInstance().getPlotFromName(
                        friendsGardens.getSelectionModel().getSelectedItem().ID(),
                        friendsGardenPlots.getSelectionModel().getSelectedItem().name()
                ).plant()
        );

        friendGardenTable.getItems().setAll(selectedPlantFromPlot.getProperties());

        try {
            plantImage.setImage(new Image(selectedPlantFromPlot.imageURL(), 150, 150, true, true));
        } catch (IllegalArgumentException e) {
            plantImage.setImage(null);
        }
    }

    /**
     * Handles the remove friend event.
     *
     * @throws IOException if an I/O error occurs
     */
    @FXML
    void removeFriend() throws IOException {
        Friend selectedFriend = friendList.getSelectionModel().getSelectedItem();
        if (selectedFriend != null) {
            FriendManager.getInstance().delete(selectedFriend);
            friendNameLabel.setText("");
        }

        Stage stage = (Stage) friendList.getScene().getWindow();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/gardenplanner/gardenplanner/friendpage.fxml"));
        loader.setControllerFactory(type -> new FriendController());

        stage.setScene(new Scene(loader.load(), 900, 600));
        stage.show();
    }

    /**
     * Initializes the friend page.
     */
    @FXML
    public void initialize() {
        friendList.setCellFactory(this::renderFriendListCell);
        friendsGardens.setCellFactory(this::renderFriendGardenListCell);
        friendsGardenPlots.setCellFactory(this::renderFriendGardenPlotListCell);

        typeColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().propertyName()));
        detailsColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().propertyValue()));

        populateFriends();
    }

    /**
     * Renders the cell for the friend list
     *
     * @param friendList the friend list
     * @return the list cell
     */
    ListCell<Friend> renderFriendListCell(ListView<Friend> friendList) {
        return new ListCell<>() {
            /**
             * Handles the mouse event when an item is selected
             *
             * @param mouseEvent the mouse event
             */
            private void onItemSelected(MouseEvent mouseEvent) {
                ListCell<Friend> clickedCell = (ListCell<Friend>) mouseEvent.getSource();
                Friend selectedFriend = clickedCell.getItem();
                if (selectedFriend != null) {
                    friendList.getSelectionModel().select(selectedFriend);
                    friendNameLabel.setText(selectedFriend.friendName());
                    friendGardenContainer.setVisible(true);
                    populateFriendGardens();
                }
                else {
                    friendList.getSelectionModel().select(null);
                    friendNameLabel.setText("");
                    friendGardenContainer.setVisible(false);
                }
            }

            /**
             * Updates the cell with the new item and sets the mouse event
             *
             * @param friend the new item
             * @param empty whether the cell is empty
             */
            @Override
            protected void updateItem(Friend friend, boolean empty) {
                super.updateItem(friend, empty);
                if (empty || friend == null) {
                    setText(null);
                    super.setOnMouseClicked(this::onItemSelected);
                } else {
                    setText(friend.friendName());
                }
            }
        };
    }

    /**
     * Renders the cell for the garden list
     *
     * @param friendsGardens the garden list
     * @return the list cell
     */
    ListCell<Garden> renderFriendGardenListCell(ListView<Garden> friendsGardens) {
        return new ListCell<>() {
            /**
             * Handles the mouse event when an item is selected
             *
             * @param mouseEvent the mouse event
             */
            private void onItemSelected(MouseEvent mouseEvent) {
                ListCell<Garden> clickedCell = (ListCell<Garden>) mouseEvent.getSource();
                Garden selectedGarden = clickedCell.getItem();
                if (selectedGarden != null) {
                    friendsGardens.getSelectionModel().select(selectedGarden);
                    populateFriendGardenPlots();
                }
            }

            /**
             * Updates the cell with the new item and sets the mouse event
             *
             * @param garden the new item
             * @param empty  whether the cell is empty
             */
            @Override
            protected void updateItem(Garden garden, boolean empty) {
                super.updateItem(garden, empty);
                if (empty || garden == null) {
                    setText(null);
                    super.setOnMouseClicked(this::onItemSelected);
                } else {
                    setText(garden.name());
                }
            }
        };
    }

    /**
     * Renders the cell for the garden plots list
     *
     * @param friendsGardens the garden list
     * @return the list cell
     */
    ListCell<Plot> renderFriendGardenPlotListCell(ListView<Plot> friendsGardens) {
        return new ListCell<>() {
            /**
             * Handles the mouse event when an item is selected
             *
             * @param mouseEvent the mouse event
             */
            private void onItemSelected(MouseEvent mouseEvent) {
                ListCell<Plot> clickedCell = (ListCell<Plot>) mouseEvent.getSource();
                Plot selectedPlot = clickedCell.getItem();
                if (selectedPlot != null) {
                    friendsGardenPlots.getSelectionModel().select(selectedPlot);
                    populateFriendGardenTable();
                }
            }

            /**
             * Updates the cell with the new item and sets the mouse event
             *
             * @param plot the new item
             * @param empty  whether the cell is empty
             */
            @Override
            protected void updateItem(Plot plot, boolean empty) {
                super.updateItem(plot, empty);
                if (empty || plot == null) {
                    setText(null);
                    super.setOnMouseClicked(this::onItemSelected);
                } else {
                    setText(plot.name());
                }
            }
        };
    }
}
