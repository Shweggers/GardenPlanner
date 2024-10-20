package com.gardenplanner.gardenplanner.controller;

import com.gardenplanner.gardenplanner.model.Plant;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

/**
 * GardenTabController is a class that represents a controller for the garden tab.
 */
public class GardenTabController {
    @FXML
    TableView<Plant> plotTable;
    @FXML
    TableColumn<Plant, String> typeColumn;
    @FXML
    TableColumn<Plant, String> detailsColumn;
    @FXML
    ListView scheduleList;
    @FXML
    ProgressBar scheduleProgress;

    @FXML
    void initialize() {
        System.out.println("GardenTabController initialized");
    }
}
