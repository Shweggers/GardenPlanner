package com.gardenplanner.gardenplanner.controller;

import com.gardenplanner.gardenplanner.model.PerenualCollection;
import com.gardenplanner.gardenplanner.model.PerenualItem;
import com.gardenplanner.gardenplanner.model.PerenualService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Dictionary;

public class GardenbookAddplantController {
    /**
     * Constructs a new GardenbookAddplantController
     */
    public GardenbookAddplantController() {
        perenualService = new PerenualService();
    }


    private String lastSearch = "";


    private int page = 1;


    private int lastPage = 1;


    @FXML
    TextField addPlantSearch;


    @FXML
    ListView<PerenualItem> addPlantSearchList;


    @FXML
    Label addPlantCurrentPage;

    /**
     * Populates the add plant search list with the search results
     * Executed when a key is pressed
     *
     * @param event the key event
     * @throws IOException if an I/O error occurs
     */
    @FXML
    void populateList(ActionEvent event) throws IOException, InterruptedException {
        String search = addPlantSearch.getText();

        if (search.isEmpty()) {
            addPlantSearchList.getItems().clear();
            addPlantInformationGridPane.setVisible(false);
            addPlantCurrentPage.setText("");
            return;
        }

        if (search.equals(lastSearch) && page == lastPage) {
            return;
        }

        PerenualCollection perenualCollection = perenualService.getPlantNames(search, page);
        addPlantSearchList.getItems().setAll(perenualCollection.perenualItems);

        addPlantCurrentPage.setText(page + "/" + perenualCollection.pages);

        addPlantPreviousPage.setDisable(page == 1);
        addPlantNextPage.setDisable(page == perenualCollection.pages);

        addPlantInformationGridPane.setVisible(true);

        lastSearch = search;
        lastPage = page;
    }


    @FXML
    Button addPlantPreviousPage;


    @FXML
    Button addPlantNextPage;


    @FXML
    void previousPage(ActionEvent event) throws IOException, InterruptedException {
        page--;
        populateList(event);

    }


    @FXML
    void nextPage(ActionEvent event) throws IOException, InterruptedException {
        page++;
        populateList(event);
    }


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
    void showItemInformation(PerenualItem item) {
        Dictionary<String, String> itemData = item.getItemData();
        addPlantDepth.setText(itemData.get("depthWaterRequirement"));
        addPlantWaterRoutine.setText(itemData.get("wateringGeneralBenchmark"));
        addPlantWaterVolume.setText(itemData.get("volumeWaterRequirement"));
        addPlantSunlight.setText(itemData.get("sunRequirement"));
        addPlantHarvestSeason.setText(itemData.get("harvestSeason"));
        try {
            addPlantImage.setImage(new Image(itemData.get("imageURL"), 100, 100, true, true));
        } catch (IllegalArgumentException e) {
            addPlantImage.setImage(null);
        }
    }


    @FXML
    Button addPlantConfirm;


    @FXML
    void confirmAddPlant(ActionEvent event) throws IOException {
        PerenualItem selectedItem = addPlantSearchList.getSelectionModel().getSelectedItem();
        if (selectedItem != null) {
            // dataStore.addPlant(selectedItem);
            exitButtonClicked(event);
        }
    }

    /**
     * The exit button
     */
    @FXML
    Button addPlantExit;


    @FXML
    void exitButtonClicked(ActionEvent event) throws IOException {
        Stage stage = (Stage) addPlantExit.getScene().getWindow();

        stage.close();
    }

    @FXML
    GridPane addPlantInformationGridPane;


    @FXML
    void initialize() {
        page = 1;
        lastPage = 1;

        addPlantSearchList.setCellFactory(this::renderCell);

        addPlantInformationGridPane.setVisible(false);

        addPlantPreviousPage.setDisable(true);
        addPlantCurrentPage.setText("");
    }


    ListCell<PerenualItem> renderCell(ListView<PerenualItem> addPlantSearchList) {
        return new ListCell<>() {

            private void onItemSelected(MouseEvent mouseEvent) {
                ListCell<PerenualItem> clickedCell = (ListCell<PerenualItem>) mouseEvent.getSource();
                PerenualItem selectedItem = clickedCell.getItem();
                if (selectedItem != null) {
                    addPlantSearchList.getSelectionModel().select(selectedItem);
                    showItemInformation(selectedItem);
                }
            }


            @Override
            protected void updateItem(PerenualItem perenualItem, boolean empty) {
                super.updateItem(perenualItem, empty);
                if (empty || perenualItem == null) {
                    setText(null);
                    super.setOnMouseClicked(this::onItemSelected);
                } else {
                    setText(perenualItem.getCommonName());
                }
            }
        };
    }
}
