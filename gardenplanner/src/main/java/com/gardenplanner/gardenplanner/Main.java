package com.gardenplanner.gardenplanner;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.sql.SQLException;
import java.util.Objects;

public class Main extends Application {
    DataStore dataStore = new DataStore();

    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/gardenplanner/gardenplanner/loginpage.fxml"));
        loader.setControllerFactory(type -> new LoginController(dataStore));

        primaryStage.setScene(new Scene(loader.load(), 900, 600));
        primaryStage.show();
    }

    public static void main(String[] args) throws SQLException {
            UserDAO userDAO = new UserDAO();
            userDAO.createTable();

            launch(args);
    }
}
