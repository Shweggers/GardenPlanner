package com.gardenplanner.gardenplanner.controller;

import com.gardenplanner.gardenplanner.model.Plant;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
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
        loader.setControllerFactory(type -> new GardenbookAddplantController());

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
}
