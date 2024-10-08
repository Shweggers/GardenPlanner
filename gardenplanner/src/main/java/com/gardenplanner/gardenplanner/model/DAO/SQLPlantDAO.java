package com.gardenplanner.gardenplanner.model.DAO;

import com.gardenplanner.gardenplanner.model.DatabaseConnection;
import com.gardenplanner.gardenplanner.model.Plant;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * SQL implementation of the PlantDAO class
 */
public class SQLPlantDAO implements IPlantDAO {
    /**
     * Singleton instance of the SQLPlantDAO class
     */
    private static IPlantDAO instance;

    /**
     * Get the instance of the SQLPlantDAO class
     *
     * @return the instance of the SQLPlantDAO class
     */
    public static IPlantDAO getInstance() {
        if (instance == null) {
            instance = new SQLPlantDAO();
        }
        return instance;
    }

    /**
     * Connection to the database
     */
    private final Connection connection;

    /**
     * Constructor for the SQLPlantDAO class
     */
    public SQLPlantDAO() {
        connection = DatabaseConnection.getInstance();
    }

    /**
     * Creates the 'plants' table in the database if it does not already exist.
     *
     * @throws SQLException if a database access error occurs
     */
    public void createTable() throws SQLException {
        Statement createTable = connection.createStatement();
        createTable.execute(
                "CREATE TABLE IF NOT EXISTS plants ("
                        + "id           INTEGER PRIMARY KEY AUTOINCREMENT, "
                        + "userid       INTEGER NOT NULL, "
                        + "plantid      STRING  NOT NULL, "
                        + "FOREIGN KEY(userid) REFERENCES users(id)"
                        + ")"
        );
    }

    /**
     * Inserts a plant into the 'plants' table.
     *
     * @param plant the plant to insert
     */
    @Override
    public void insert(Plant plant) {
        try {
            PreparedStatement insertPlant = connection.prepareStatement(
                    "INSERT INTO plants (userID, plantID, datePlanted)" +
                            "VALUES (?, ?, ?)"
            );

            insertPlant.setInt(1, plant.userID());
            insertPlant.setString(2, plant.plantID());

            insertPlant.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    /**
     * Deletes a plant from the 'plants' table.
     *
     * @param plant the plant to delete
     */
    @Override
    public void delete(Plant plant) {
        try {
            PreparedStatement deletePlant = connection.prepareStatement(
                    "DELETE FROM plants WHERE userid = ? AND plantid = ?"
            );

            deletePlant.setInt(1, plant.userID());
            deletePlant.setString(2, plant.plantID());

            deletePlant.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Retrieves all plants from the 'plants' table for a user.
     *
     * @param userID the user ID to search for
     * @return a List containing all plants
     */
    @Override
    public List<Plant> getPlants(int userID) {
        List<Plant> plants = new ArrayList<>();
        try {
            PreparedStatement getPlants = connection.prepareStatement(
                    "SELECT * FROM plants WHERE userid = ?"
            );
            getPlants.setInt(1, userID);

            ResultSet rs = getPlants.executeQuery();
            while (rs.next()) {
                plants.add(new Plant(
                        rs.getInt("userID"),
                        rs.getString("plantID")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return plants;
    }
}
