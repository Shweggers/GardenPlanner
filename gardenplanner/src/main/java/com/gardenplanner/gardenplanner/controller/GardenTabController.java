package com.gardenplanner.gardenplanner.controller;

import com.gardenplanner.gardenplanner.model.*;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;

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
    HBox scheduleBox;
    private GardenController gardenController;

    /**
     * Constructs a new GardenTabController
     *
     * @param gardenController the garden controller
     */
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

        ArrayList<String> days = new ArrayList<>(Arrays.asList("Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"));

        LocalDate currentDate = LocalDate.now();
        int currentDayInWeek = currentDate.getDayOfWeek().getValue() % 7;
        int currentDayInYear = currentDate.getDayOfYear();

        ArrayList<String> daysFromCurrentDay = new ArrayList<>();
        daysFromCurrentDay.addAll(days.subList(currentDayInWeek, days.size()));
        daysFromCurrentDay.addAll(days.subList(0, currentDayInWeek));

        String[] frequencyRange = selectedPlantFromPlot.waterAmount().split("-");
        int frequency = Average(Integer.parseInt(frequencyRange[0]), Integer.parseInt(frequencyRange[1].split(" ")[0]));

        ArrayList<VBox> dayInformation = new ArrayList<>();

        daysFromCurrentDay.forEach(day -> {
            VBox dayBox = new VBox();
            dayBox.setAlignment(javafx.geometry.Pos.CENTER);

            if ((currentDayInYear + daysFromCurrentDay.indexOf(day)) % frequency == 0) {
                dayBox.getChildren().addAll(
                        new Label(day),
                        new Label("Water")
                );
            }
            else {
                dayBox.getChildren().add(new Label(day));
            }
            dayInformation.add(dayBox);
        });

        scheduleBox.getChildren().clear();
        scheduleBox.getChildren().addAll(dayInformation);
    }

    private int Average(int a, int b) {
        return (a + b) / 2;
    }
}
