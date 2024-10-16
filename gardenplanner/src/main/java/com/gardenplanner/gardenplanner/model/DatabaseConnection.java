package com.gardenplanner.gardenplanner.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * A class to manage the database connection
 */
public class DatabaseConnection {
    private static Connection instance = null;

    /**
     * Create a new database connection
     */
    private DatabaseConnection() {
        String url = "jdbc:sqlite:database.db";
        try {
            instance = DriverManager.getConnection(url);
        } catch (SQLException sqlEx) {
            System.err.println(sqlEx);
        }
    }

    /**
     * Get the instance of the database connection
     *
     * @return the instance of the database connection
     */
    public static Connection getInstance() {
        if (instance == null) {
            new DatabaseConnection();
        }
        return instance;
    }
}