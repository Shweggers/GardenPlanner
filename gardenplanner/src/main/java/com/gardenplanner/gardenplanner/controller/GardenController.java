package com.gardenplanner.gardenplanner.controller;

import com.gardenplanner.gardenplanner.model.DataStore;
import com.gardenplanner.gardenplanner.model.Garden;
import com.gardenplanner.gardenplanner.model.GardenManager;
import com.gardenplanner.gardenplanner.model.PlotManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * GardenController is a class that represents a controller for the garden page.
 */
public class GardenController {
    @FXML
    private TextField gardenSearchField;
    @FXML
    ListView<Garden> gardenList;
    @FXML
    private Button addGardenButton;
    @FXML
    private Button addPlotButton;
    @FXML
    private Button editPlotButton;
    @FXML
    private Button backButton;
    @FXML
    private TabPane plotsTabPane;
    @FXML
    private Tab currentTab;
    @FXML
    private Button membersButton;
    @FXML
    private Button replantButton;
    @FXML
    private Button leaveGarden;

    /**
     * handle the back button click event
     *
     * @param event the event details
     * @throws IOException if an I/O error occurs
     */
    @FXML
    public void backButtonClicked(ActionEvent event) throws IOException {
        Stage stage = (Stage) backButton.getScene().getWindow();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/gardenplanner/gardenplanner/homepage.fxml"));
        loader.setControllerFactory(type -> new HomepageController());

        stage.setScene(new Scene(loader.load()));
        stage.show();
    }

    /**
     * Adds a new garden when the add garden button is clicked.
     */
    @FXML
    public void populateList() {
        String search = gardenSearchField.getText();

        gardenList.getItems().setAll(GardenManager.getInstance().searchGardens(
                DataStore.getInstance().getCurrentUser().ID(),
                search
        ));
    }

    /**
     * handle the add garden button click event
     *
     * @param event the event details
     * @throws IOException if an I/O error occurs
     */
    @FXML
    void addGardenButtonClicked(ActionEvent event) throws IOException {
        Stage stage = new Stage();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/gardenplanner/gardenplanner/gardenpage_addgarden.fxml"));
        loader.setControllerFactory(type -> new GardenAddGardenController(this));

        stage.setScene(new Scene(loader.load()));
        stage.show();
    }

    /**
     * handle the add plot button click event
     *
     * @param event the event details
     * @throws IOException if an I/O error occurs
     */
    @FXML
    void addPlotButtonClicked(ActionEvent event) throws IOException {
        Stage stage = new Stage();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/gardenplanner/gardenplanner/gardenpage_addplot.fxml"));
        loader.setControllerFactory(type -> new GardenAddPlotController(this));

        stage.setScene(new Scene(loader.load()));
        stage.show();
    }


    /**
     * handle the edit plot button click event
     *
     * @throws IOException if an I/O error occurs
     */
    @FXML
    public void populateTabs() throws IOException {
        Garden selectedGarden = gardenList.getSelectionModel().getSelectedItem();
        if (selectedGarden == null) {
            return;
        }

        plotsTabPane.getTabs().clear();
        PlotManager.getInstance().searchPlots(selectedGarden.ID(), "").forEach(plot -> {
            Tab tab = new Tab(plot.name());
            plotsTabPane.getTabs().add(tab);
        });
    }

    /**
     * handle the members button click event
     *
     * @param event the event details
     * @throws IOException if an I/O error occurs
     */
    @FXML
    void membersButtonClicked(ActionEvent event) throws IOException {
        Stage stage = new Stage();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/gardenplanner/gardenplanner/gardenpage_memberlist.fxml"));
        loader.setControllerFactory(type -> new GardenMemberController());

        stage.setScene(new Scene(loader.load()));
        stage.show();
    }

    /**
     * handle the leave garden button click event
     *
     * @param event the event details
     */
    @FXML
    void leaveGardenButtonClicked(ActionEvent event) {
        GardenManager.getInstance().delete(gardenList.getSelectionModel().getSelectedItem());

        gardenList.getSelectionModel().select(null);
    }

    /**
     * Initializes the controller
     */
    @FXML
    void initialize() {
        gardenSearchField.textProperty().addListener((observable, oldValue, newValue) -> populateList());
        populateList();

        gardenList.setCellFactory(this::renderListCell);

        plotsTabPane.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            try {
                Tab selectedTab = plotsTabPane.getSelectionModel().getSelectedItem();
                if (selectedTab == null) {
                    return;
                }
                plotsTabPane.getSelectionModel().getSelectedItem().setContent(
                        new FXMLLoader(getClass().getResource("/com/gardenplanner/gardenplanner/gardenpage_tab.fxml")).load());
            } catch (IOException e) {
                e.printStackTrace();
            }
            currentTab = newValue;
        });
    }

    /**
     * Renders the cell for the plant list
     *
     * @param gardenList the plant list
     * @return the list cell
     */
    ListCell<Garden> renderListCell(ListView<Garden> gardenList) {
        return new ListCell<>() {
            /**
             * Handles the mouse event when an item is selected
             *
             * @param mouseEvent the mouse event
             */
            private void onItemSelected(MouseEvent mouseEvent) {
                ListCell<Garden> clickedCell = (ListCell<Garden>) mouseEvent.getSource();
                Garden selectedGarden = clickedCell.getItem();
                if (selectedGarden != null) {
                    gardenList.getSelectionModel().select(selectedGarden);
                    try {
                        populateTabs();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            /**
             * Updates the cell with the new item and sets the mouse event
             *
             * @param plant the new item
             * @param empty whether the cell is empty
             */
            @Override
            protected void updateItem(Garden plant, boolean empty) {
                super.updateItem(plant, empty);
                if (empty || plant == null) {
                    setText(null);
                    super.setOnMouseClicked(this::onItemSelected);
                } else {
                    setText(plant.name());
                }
            }
        };
    }
}
