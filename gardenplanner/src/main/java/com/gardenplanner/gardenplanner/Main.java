package com.gardenplanner.gardenplanner;

import com.gardenplanner.gardenplanner.controller.LoginController;
import com.gardenplanner.gardenplanner.model.SQLFriendDAO;
import com.gardenplanner.gardenplanner.model.SQLUserDAO;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.sql.SQLException;

/**
 * The main class
 */
public class Main extends Application {
    DataStore dataStore = new DataStore();

    /**
     * Start the application
     *
     * @param primaryStage the primary stage
     * @throws Exception
     */
    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/gardenplanner/gardenplanner/loginpage.fxml"));
        loader.setControllerFactory(type -> new LoginController(dataStore));

        primaryStage.setScene(new Scene(loader.load(), 900, 600));
        primaryStage.show();
    }

    /**
     * Main method
     *
     * @param args the arguments
     * @throws SQLException
     */
    public static void main(String[] args) throws SQLException {
        SQLUserDAO SQLUserDAO = new SQLUserDAO();
        SQLUserDAO.createTable();

        SQLFriendDAO SQLFriendDAO = new SQLFriendDAO();
        SQLFriendDAO.createTable();

        launch(args);
    }
}