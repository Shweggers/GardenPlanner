package com.gardenplanner.gardenplanner;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

import java.io.IOException;

public class GardenbookController {

    private final DataStore dataStore;
    public GardenbookController(DataStore dataStore) {
        this.dataStore = dataStore;
    }

    @FXML
    private Button addNewPlantButton;

    @FXML
    private TableView plantTable;

    @FXML
    private TableColumn plantColumn1;

    @FXML
    private TableColumn plantColumn2;

    @FXML
    private TextField plantSearch;

    @FXML
    private Button backButton;

    /**
     * Handle the back button click event
     * 
     * @param event
     * @throws IOException
     */
    @FXML
    void backButtonClicked(ActionEvent event) throws IOException {
        Stage stage = (Stage) backButton.getScene().getWindow();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/gardenplanner/gardenplanner/homepage.fxml"));
        loader.setControllerFactory(type -> new HomepageController(dataStore));

        stage.setScene(new Scene(loader.load()));
        stage.show();
    }
}
