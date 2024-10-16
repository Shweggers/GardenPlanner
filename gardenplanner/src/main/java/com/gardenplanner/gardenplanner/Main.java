package com.gardenplanner.gardenplanner;

import com.gardenplanner.gardenplanner.controller.LoginController;
import com.gardenplanner.gardenplanner.model.DAO.*;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.sql.SQLException;

/**
 * The main class
 */
public class Main extends Application {
    /**
     * Start the application
     *
     * @param primaryStage the primary stage
     * @throws Exception if an exception occurs
     */
    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/gardenplanner/gardenplanner/loginpage.fxml"));
        loader.setControllerFactory(type -> new LoginController());

        primaryStage.setScene(new Scene(loader.load(), 900, 600));
        primaryStage.show();
    }

    /**
     * Main method
     *
     * @param args the arguments
     * @throws SQLException if an SQL exception occurs
     */
    public static void main(String[] args) throws SQLException {
        SQLUserDAO SQLUserDAO = new SQLUserDAO();
        SQLUserDAO.createTable();

        SQLFriendDAO SQLFriendDAO = new SQLFriendDAO();
        SQLFriendDAO.createTable();

        SQLGardenDAO SQLGardenDAO = new SQLGardenDAO();
        SQLGardenDAO.createTable();

        SQLPlotDAO SQLPlotDAO = new SQLPlotDAO();
        SQLPlotDAO.createTable();

        SQLPlantDAO SQLPlantDAO = new SQLPlantDAO();
        SQLPlantDAO.createTable();

        launch(args);
    }
}