package com.gardenplanner.gardenplanner.model.DAO;

import com.gardenplanner.gardenplanner.model.DatabaseConnection;
import com.gardenplanner.gardenplanner.model.Garden;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

/**
 * SQL class for the GardenDAO class
 */
public class SQLGardenDAO implements IGardenDAO {
    private final Connection connection;

    /**
     * Constructor for the SQLGardenDAO class
     */
    public SQLGardenDAO() {
        connection = DatabaseConnection.getInstance();
        createTable();
    }

    /**
     * Create the gardens table
     */
    public void createTable() {
        try {
            Statement createTable = connection.createStatement();
            createTable.execute(
                    "CREATE TABLE IF NOT EXISTS gardens ("
                            + "id           INTEGER PRIMARY KEY AUTOINCREMENT, "
                            + "gardenName   TEXT    NOT NULL, "
                            + "userID       INTEGER NOT NULL, "
                            + "FOREIGN KEY(userID)  REFERENCES users(id), "
                            + "UNIQUE(gardenName, userID)"
                            + ")"
            );
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Insert a garden into the database
     *
     * @param garden the garden to insert
     */
    @Override
    public void insert(Garden garden) {
        try {
            PreparedStatement insertGarden = connection.prepareStatement(
                    "INSERT INTO gardens (gardenName, userID) VALUES (?, ?)"
            );
            insertGarden.setString(1, garden.name());
            insertGarden.setInt(2, garden.userID());

            insertGarden.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Delete a garden from the database
     *
     * @param garden the garden to delete
     */
    @Override
    public void delete(Garden garden) {
        try {
            PreparedStatement deleteGarden = connection.prepareStatement(
                    "DELETE FROM gardens WHERE gardenName = ? AND userID = ?"
            );
            deleteGarden.setString(1, garden.name());
            deleteGarden.setInt(2, garden.userID());

            deleteGarden.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Get a list of gardens for a user
     *
     * @param userID the user ID
     * @return a list of gardens
     */
    @Override
    public List<Garden> getGardens(int userID) {
        return null;
    }
}
