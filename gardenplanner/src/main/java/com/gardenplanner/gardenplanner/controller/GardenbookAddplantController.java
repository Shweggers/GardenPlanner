package com.gardenplanner.gardenplanner.controller;

import com.gardenplanner.gardenplanner.model.DataStore;
import com.gardenplanner.gardenplanner.model.PerenualService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class GardenbookAddplantController {
    /**
     * The data store
     */
    private final DataStore dataStore;

    /**
     * The Perenual service
     */
    private final PerenualService perenualService;

    /**
     * Constructs a new GardenbookAddplantController with the specified data store and initialises the perenualService.
     *
     * @param dataStore the data store
     */
    public GardenbookAddplantController(DataStore dataStore) {
        this.dataStore = dataStore;
        perenualService = new PerenualService();
    }

    @FXML
    TextField addPlantSearch;

    @FXML
    ListView<String> addPlantSearchList;

    private String lastSearch = "";

    /**
     * Populates the add plant search list with the search results
     * Executed when a key is pressed
     *
     * @param event the key event
     * @throws IOException if an I/O error occurs
     */
    @FXML
    void populateAddPlantSearchList(ActionEvent event) throws IOException, InterruptedException {
        String search = addPlantSearch.getText();
        if (search.isEmpty()) {
            addPlantSearchList.getItems().clear();
            return;
        }

        if (search.equals(lastSearch)) {
            return;
        }

        addPlantSearchList.getItems().setAll(perenualService.getPlantNames(search));

        lastSearch = search;
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
    void initialize() {
    }
}
