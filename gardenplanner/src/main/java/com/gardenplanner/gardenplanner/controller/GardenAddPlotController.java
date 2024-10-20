package com.gardenplanner.gardenplanner.controller;

import com.gardenplanner.gardenplanner.model.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * GardenAddGardenController is a class that represents a controller for adding a new garden.
 */
public class GardenAddPlotController {
    @FXML
    private TextField addPlotName;
    @FXML
    private ChoiceBox<Plant> addPlotPlant;
    @FXML
    private Button cancelButton;
    @FXML
    private Button addPlotConfirmButton;
    private final GardenController gardenController;

    /**
     * Constructs a new GardenAddGardenController
     *
     * @param gardenController the garden controller
     */
    public GardenAddPlotController(GardenController gardenController) {this.gardenController = gardenController;}

    /**
     * Handles the save button click event.
     *
     * @param event the event details
     */

    /**
     * Handles the cancel button click event.
     *
     * @param event the event details
     */
    @FXML
    public void cancelButtonClicked(ActionEvent event) {
        // Close the current window
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }

    /**
     * Handles the add garden button click event.
     *
     * @param event the event details
     * @throws IOException if an I/O error occurs
     */
    @FXML
    public void setAddPlotConfirmButton(ActionEvent event) throws IOException {
        if (addPlotName.getText().trim().isEmpty()) {
            return;
        }
        Plot plot = new Plot(
                DataStore.getInstance().getCurrentUser().ID(),
                gardenController.gardenList.getSelectionModel().getSelectedItem().ID(),
                addPlotName.getText(),
                addPlotPlant.getSelectionModel().getSelectedItem().name()
        );
        PlotManager.getInstance().insert(plot);

        gardenController.populateTabs();

        // Close the current window
        cancelButtonClicked(event);
    }

    @FXML
    void initialize() {
        addPlotPlant.getItems().setAll(PlantManager.getInstance().searchPlants(DataStore.getInstance().getCurrentUser().ID(), ""));
    }
}
