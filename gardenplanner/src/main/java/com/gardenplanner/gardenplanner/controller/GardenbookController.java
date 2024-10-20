package com.gardenplanner.gardenplanner.controller;

import com.gardenplanner.gardenplanner.model.DataStore;
import com.gardenplanner.gardenplanner.model.Plant;
import com.gardenplanner.gardenplanner.model.PlantManager;
import com.gardenplanner.gardenplanner.model.PlantProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
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
 * GardenbookController is a class that represents a controller for the garden book page.
 */
public class GardenbookController {
    @FXML
    private TextField plantSearch;
    @FXML
    private ListView<Plant> plantList;
    @FXML
    private Button addNewPlantButton;
    @FXML
    private VBox plantInformationPane;
    @FXML
    private Label plantName;
    @FXML
    private TableView<PlantProperty> plantTable;
    @FXML
    private TableColumn<PlantProperty, String> plantInfo;
    @FXML
    private TableColumn<PlantProperty, String> plantDetails;
    @FXML
    private Button backButton;
    @FXML
    private ImageView plantImage;

    /**
     * Constructs a new GardenbookController
     */
    public GardenbookController() {}

    /**
     * Populates the plant list with the search results
     */
    void populateList() {
        String search = plantSearch.getText();

        plantList.getItems().setAll(PlantManager.getInstance().searchPlants(
                DataStore.getInstance().getCurrentUser().ID(),
                search
        ));
    }

    /**
     * Shows the plant information when a plant is selected
     *
     * @param plant the selected plant
     */
    public void showPlantInformation(Plant plant) {
        plantInformationPane.setVisible(true);
        plantImage.setVisible(true);

        plantName.setText(plant.name());
        try {
            plantImage.setImage(new Image(plant.imageURL(), 100, 100, true, true));
        } catch (IllegalArgumentException e) {
            plantImage.setImage(null);
        }

        plantTable.getItems().setAll(plant.getProperties());
    }

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
        loader.setControllerFactory(type -> new GardenbookAddPlantController(this));

        stage.setScene(new Scene(loader.load()));
        stage.show();
    }

    /**
     * Returns the user to the homepage when the back button is clicked.
     *
     * @param event the event details
     * @throws IOException if an I/O error occurs
     */
    @FXML
    void backButtonClicked(ActionEvent event) throws IOException {
        Stage stage = (Stage) backButton.getScene().getWindow();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/gardenplanner/gardenplanner/homepage.fxml"));
        loader.setControllerFactory(type -> new HomepageController());

        stage.setScene(new Scene(loader.load()));
        stage.show();
    }

    /**
     * Initializes the controller
     */
    @FXML
    void initialize() {
        plantSearch.textProperty().addListener((observable, oldValue, newValue) -> populateList());
        populateList();

        plantList.setCellFactory(this::renderListCell);

        plantInfo.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().propertyName()));
        plantDetails.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().propertyValue()));

        plantInformationPane.setVisible(false);
        plantImage.setVisible(false);
    }

    /**
     * Renders the cell for the plant list
     *
     * @param plantList the plant list
     * @return the list cell
     */
    ListCell<Plant> renderListCell(ListView<Plant> plantList) {
        return new ListCell<>() {
            /**
             * Handles the mouse event when an item is selected
             *
             * @param mouseEvent the mouse event
             */
            private void onItemSelected(MouseEvent mouseEvent) {
                ListCell<Plant> clickedCell = (ListCell<Plant>) mouseEvent.getSource();
                Plant selectedPlant = clickedCell.getItem();
                if (selectedPlant != null) {
                    plantList.getSelectionModel().select(selectedPlant);
                    showPlantInformation(selectedPlant);
                }
            }

            /**
             * Updates the cell with the new item and sets the mouse event
             *
             * @param plant the new item
             * @param empty whether the cell is empty
             */
            @Override
            protected void updateItem(Plant plant, boolean empty) {
                super.updateItem(plant, empty);
                if (empty || plant == null) {
                    setText(null);
                    super.setOnMouseClicked(this::onItemSelected);
                } else {
                    setText(plant.name());
                }
            }
        };
    }
}
