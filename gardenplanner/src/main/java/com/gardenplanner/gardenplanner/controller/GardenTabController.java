package com.gardenplanner.gardenplanner.controller;

import com.gardenplanner.gardenplanner.model.*;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * GardenTabController is a class that represents a controller for the garden tab.
 */
public class GardenTabController {

    @FXML
    TableView<PlantProperty> plotTable;
    @FXML
    TableColumn<PlantProperty, String> typeColumn;
    @FXML
    TableColumn<PlantProperty, String> detailsColumn;
    @FXML
    ImageView plotImage;
    @FXML
    ListView scheduleList;
    @FXML
    ProgressBar scheduleProgress;
    private GardenController gardenController;

    public GardenTabController(GardenController gardenController) {
        this.gardenController = gardenController;
    }


    @FXML
    void initialize() {
        Plant selectedPlantFromPlot = PlantManager.getInstance().getPlantFromName(
                DataStore.getInstance().getCurrentUser().ID(),
                PlotManager.getInstance().getPlotFromName(
                        gardenController.gardenList.getSelectionModel().getSelectedItem().ID(),
                        gardenController.currentTab.getText()
                ).plant()
        );

        plotTable.getItems().setAll(selectedPlantFromPlot.getProperties());

        typeColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().propertyName()));
        detailsColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().propertyValue()));

        try {
            plotImage.setImage(new Image(selectedPlantFromPlot.imageURL(), 177, 177, true, true));
        } catch (IllegalArgumentException e) {
            plotImage.setImage(null);
        }
    }
}
