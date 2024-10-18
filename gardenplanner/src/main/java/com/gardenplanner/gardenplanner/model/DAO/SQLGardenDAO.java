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
                            + "userID       INTEGER NOT NULL, "
                            + "gardenName   TEXT    NOT NULL, "
                            + "FOREIGN KEY(userID)  REFERENCES users(id), "
                            + "UNIQUE(userID, gardenName)"
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
                    "INSERT INTO gardens (userID, gardenName) VALUES (?, ?)"
            );
            insertGarden.setInt(1, garden.userID());
            insertGarden.setString(2, garden.name());

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
                    "DELETE FROM gardens WHERE userID = ? AND gardenName = ?"
            );
            deleteGarden.setInt(1, garden.userID());
            deleteGarden.setString(2, garden.name());


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
