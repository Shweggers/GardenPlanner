package com.gardenplanner.gardenplanner.controller;

import com.gardenplanner.gardenplanner.model.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import javax.naming.LimitExceededException;
import java.io.IOException;
import java.util.Dictionary;

/**
 * GardenbookAddPlantController is a class that represents a controller for adding a new plant to the garden book.
 */
public class GardenbookAddPlantController {
    @FXML
    TextField addPlantSearch;
    @FXML
    ListView<PerenualItem> addPlantSearchList;
    @FXML
    Label addPlantCurrentPage;
    @FXML
    Button addPlantPreviousPage;
    @FXML
    Button addPlantNextPage;
    @FXML
    Label addPlantDepth;
    @FXML
    Label addPlantWaterRoutine;
    @FXML
    Label addPlantWaterVolume;
    @FXML
    Label addPlantSunlight;
    @FXML
    Label addPlantHarvestSeason;
    @FXML
    ImageView addPlantImage;
    @FXML
    Button addPlantConfirm;
    @FXML
    Button addPlantExit;
    @FXML
    GridPane addPlantInformationGridPane;
    private GardenbookController gardenbookController;
    private String lastSearch = "";
    private int page = 1;
    private int lastPage = 1;


    /**
     * Constructs a new GardenbookAddPlantController
     *
     * @param gardenbookController the garden book controller
     */
    public GardenbookAddPlantController(GardenbookController gardenbookController) { this.gardenbookController = gardenbookController; }

    /**
     * Populates the add plant search list with the search results
     *
     * @param event the key event
     * @throws IOException if an I/O error occurs
     * @throws InterruptedException if the thread is interrupted
     */
    @FXML
    void populateList(ActionEvent event) throws IOException, InterruptedException {
        String search = addPlantSearch.getText();

        if (search.isEmpty()) {
            addPlantSearchList.getItems().clear();
            addPlantCurrentPage.setText("");
            return;
        }

        if (search.equals(lastSearch) && page == lastPage) {
            return;
        }

        try {
            PerenualCollection perenualCollection = PerenualService.getInstance().getPlantNames(search, page);

            addPlantSearchList.getItems().setAll(perenualCollection.perenualItems);

            addPlantCurrentPage.setText(page + "/" + perenualCollection.pages);

            addPlantPreviousPage.setDisable(page == 1);
            addPlantNextPage.setDisable(page == perenualCollection.pages);

            lastSearch = search;
            lastPage = page;
        } catch (LimitExceededException e) {
            handleAPIError(e);
        }
    }

    /**
     * Goes to the previous page of search results
     *
     * @param event the action event
     * @throws IOException if an I/O error occurs
     * @throws InterruptedException if the thread is interrupted
     */
    @FXML
    void previousPage(ActionEvent event) throws IOException, InterruptedException {
        page--;

        populateList(event);

        addPlantSearchList.getSelectionModel().clearSelection();
        addPlantInformationGridPane.setVisible(false);
    }

    /**
     * Goes to the next page of search results
     *
     * @param event the action event
     * @throws IOException if an I/O error occurs
     * @throws InterruptedException if the thread is interrupted
     */
    @FXML
    void nextPage(ActionEvent event) throws IOException, InterruptedException {
        page++;

        populateList(event);

        addPlantSearchList.getSelectionModel().clearSelection();
        addPlantInformationGridPane.setVisible(false);
    }

    /**
     * Shows the information for a selected item
     *
     * @param item the selected item
     */
    @FXML
    void showItemInformation(PerenualItem item) {
        if (item == null) {
            addPlantInformationGridPane.setVisible(false);
            return;
        }

        try {
            Dictionary<String, String> itemData = item.getItemData();

            addPlantDepth.setText(itemData.get("waterDepth"));
            addPlantWaterRoutine.setText(itemData.get("waterAmount"));
            addPlantWaterVolume.setText(itemData.get("waterVolume"));
            addPlantSunlight.setText(itemData.get("sunlight"));
            addPlantHarvestSeason.setText(itemData.get("harvestSeason"));
            try {
                addPlantImage.setImage(new Image(itemData.get("imageURL"), 100, 100, true, true));
            } catch (IllegalArgumentException e) {
                addPlantImage.setImage(null);
            }

            addPlantInformationGridPane.setVisible(true);
        } catch (LimitExceededException e) {
            handleAPIError(e);
        }
    }

    /**
     * Confirms the addition of a plant
     *
     * @param event the action event
     */
    @FXML
    void confirmAddPlant(ActionEvent event) {
        PerenualItem selectedItem = addPlantSearchList.getSelectionModel().getSelectedItem();
        if (selectedItem != null) {
            try {
                Dictionary<String, String> selectedItemData = selectedItem.getItemData();

                Plant plant = new Plant(
                        DataStore.getInstance().getCurrentUser().ID(),
                        selectedItem.getID(),
                        selectedItem.getName(),
                        selectedItemData.get("waterDepth"),
                        selectedItemData.get("waterVolume"),
                        selectedItemData.get("waterAmount"),
                        selectedItemData.get("sunlight"),
                        selectedItemData.get("careLevel"),
                        selectedItemData.get("harvestSeason"),
                        selectedItemData.get("imageURL")
                );
                PlantManager.getInstance().insert(plant);

                gardenbookController.populateList();

                exitButtonClicked(event);
            } catch (LimitExceededException e) {
                handleAPIError(e);
            }
        }
    }

    /**
     * Exits the add plant page
     *
     * @param event the action event
     */
    @FXML
    void exitButtonClicked(ActionEvent event) {
        Stage stage = (Stage) addPlantExit.getScene().getWindow();
        stage.close();
    }

    /**
     * Initializes the controller
     */
    @FXML
    void initialize() {
        page = 1;
        lastPage = 1;

        addPlantSearchList.setCellFactory(this::renderCell);

        addPlantInformationGridPane.setVisible(false);

        addPlantPreviousPage.setDisable(true);
        addPlantCurrentPage.setText("");
    }


    /**
     * Renders the cell for the list view
     *
     * @param addPlantSearchList the list view
     * @return the list cell
     */
    ListCell<PerenualItem> renderCell(ListView<PerenualItem> addPlantSearchList) {
        return new ListCell<>() {
            /**
             * Handles the mouse event when an item is selected
             *
             * @param mouseEvent the mouse event
             */
            private void onItemSelected(MouseEvent mouseEvent) {
                ListCell<PerenualItem> clickedCell = (ListCell<PerenualItem>) mouseEvent.getSource();
                PerenualItem selectedItem = clickedCell.getItem();
                if (selectedItem != null) {
                    addPlantSearchList.getSelectionModel().select(selectedItem);
                    showItemInformation(selectedItem);
                }
            }

            /**
             * Updates the cell with the new item and sets the mouse event
             *
             * @param perenualItem the new item
             * @param empty        whether the cell is empty
             */
            @Override
            protected void updateItem(PerenualItem perenualItem, boolean empty) {
                super.updateItem(perenualItem, empty);
                if (empty || perenualItem == null) {
                    setText(null);
                    super.setOnMouseClicked(this::onItemSelected);
                } else {
                    setText(perenualItem.getName());
                }
            }
        };
    }

    /**
     * Handles an API error
     *
     * @param e the exception
     */
    void handleAPIError(Exception e) {
        addPlantSearchList.getSelectionModel().clearSelection();
        addPlantSearchList.getItems().clear();
        addPlantCurrentPage.setText("");

        addPlantPreviousPage.setDisable(true);
        addPlantNextPage.setDisable(true);

        addPlantInformationGridPane.setVisible(false);

        new Alert(Alert.AlertType.ERROR, e.getMessage()).showAndWait();
    }
}
