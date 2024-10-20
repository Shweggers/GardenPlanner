package com.gardenplanner.gardenplanner.controller;

import com.gardenplanner.gardenplanner.model.DataStore;
import com.gardenplanner.gardenplanner.model.Plot;
import com.gardenplanner.gardenplanner.model.PlotManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * GardenAddGardenController is a class that represents a controller for adding a new garden.
 */
public class GardenAddPlotController {
    @FXML
    private TextField addPlotName;
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
     */
    @FXML
    public void setAddGardenConfirmButton(ActionEvent event) {
        Plot plot = new Plot(
                DataStore.getInstance().getCurrentUser().ID(),
                gardenController.gardenList.getSelectionModel().getSelectedItem().ID(),
                addPlotName.getText()
        );
        PlotManager.getInstance().insert(plot);

        gardenController.populateList();

        // Close the current window
        cancelButtonClicked(event);
    }
}
