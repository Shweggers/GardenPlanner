package com.gardenplanner.gardenplanner.controller;

import com.gardenplanner.gardenplanner.model.Plant;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * GardenbookController is a class that represents a controller for the garden book page.
 */
public class GardenbookController {
    @FXML
    private Button addNewPlantButton;
    @FXML
    private TableView<Plant> plantTable;
    @FXML
    private TableColumn<Plant, String> plantColumn1;
    @FXML
    private TableColumn<Plant, String> plantColumn2;
    @FXML
    private TextField plantSearch;
    @FXML
    private Button backButton;

    /**
     * Constructs a new GardenbookController
     */
    public GardenbookController() {}

    /**
     * Adds a new plant to the garden book when the add new plant button is clicked.
     *
     * @param event the event details
     * @throws IOException if an I/O error occurs
     */
    @FXML
    void addNewPlant(ActionEvent event) throws IOException {
        Stage stage = new Stage();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/gardenplanner/gardenplanner/gardenbook_addplantpage.fxml"));
        loader.setControllerFactory(type -> new GardenbookAddPlantController());

        stage.setScene(new Scene(loader.load()));
        stage.show();
    }

    /**
     * Returns the user to the homepage when the back button is clicked.
     *
     * @param event
     * @throws IOException
     */
    @FXML
    void backButtonClicked(ActionEvent event) throws IOException {
        Stage stage = (Stage) backButton.getScene().getWindow();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/gardenplanner/gardenplanner/homepage.fxml"));
        loader.setControllerFactory(type -> new HomepageController());

        stage.setScene(new Scene(loader.load()));
        stage.show();
    }


    ListCell<Plant> renderCell(ListView<Plant> addPlantSearchList) {
        return new ListCell<>() {
            /**
             * Handles the mouse event when an item is selected
             *
             * @param mouseEvent the mouse event
             */
            private void onItemSelected(MouseEvent mouseEvent) {
                ListCell<Plant> clickedCell = (ListCell<Plant>) mouseEvent.getSource();
                Plant selectedItem = clickedCell.getItem();
                if (selectedItem != null) {
                    addPlantSearchList.getSelectionModel().select(selectedItem);
                    // showItemInformation(selectedItem);
                }
            }

            /**
             * Updates the cell with the new item and sets the mouse event
             *
             * @param perenualItem the new item
             * @param empty        whether the cell is empty
             */
            @Override
            protected void updateItem(Plant perenualItem, boolean empty) {
                super.updateItem(perenualItem, empty);
                if (empty || perenualItem == null) {
                    setText(null);
                    super.setOnMouseClicked(this::onItemSelected);
                } else {
                    setText(perenualItem.name());
                }
            }
        };
    }
}
