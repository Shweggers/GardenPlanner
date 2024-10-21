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
    private final Connection connection;

    /**
     * Constructor for the SQLPlantDAO class
     */
    public SQLPlantDAO() {
        connection = DatabaseConnection.getInstance();
        createTable();
    }

    /**
     * Creates the 'plants' table in the database if it does not already exist.
     */
    public void createTable() {
        try {
            Statement createTable = connection.createStatement();
            createTable.execute(
                    "CREATE TABLE IF NOT EXISTS plants ("
                            + "id               INTEGER PRIMARY KEY AUTOINCREMENT, "
                            + "userID           INTEGER NOT NULL, "
                            + "plantID          STRING  NOT NULL, "
                            + "name             STRING  NOT NULL, "
                            + "waterDepth       STRING  NOT NULL, "
                            + "waterAmount      STRING  NOT NULL, "
                            + "waterVolume      STRING  NOT NULL, "
                            + "sunlight         STRING  NOT NULL, "
                            + "careLevel        STRING  NOT NULL, "
                            + "harvestSeason    STRING  NOT NULL, "
                            + "imageURL         STRING  NOT NULL, "
                            + "FOREIGN KEY(userID) REFERENCES users(id)"
                            + ")"
            );
        } catch (SQLException e) {
            e.printStackTrace();
        }
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
                    "INSERT INTO plants (userID, plantID, name, waterDepth, waterAmount, waterVolume, sunlight, careLevel, harvestSeason, imageURL)" +
                            "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)"
            );

            insertPlant.setInt(1, plant.userID());
            insertPlant.setString(2, plant.plantID());
            insertPlant.setString(3, plant.name());
            insertPlant.setString(4, plant.waterDepth());
            insertPlant.setString(5, plant.waterAmount());
            insertPlant.setString(6, plant.waterVolume());
            insertPlant.setString(7, plant.sunlight());
            insertPlant.setString(8, plant.careLevel());
            insertPlant.setString(9, plant.harvestSeason());
            insertPlant.setString(10, plant.imageURL());

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
                        rs.getString("plantID"),
                        rs.getString("name"),
                        rs.getString("waterDepth"),
                        rs.getString("waterVolume"),
                        rs.getString("waterAmount"),
                        rs.getString("sunlight"),
                        rs.getString("careLevel"),
                        rs.getString("harvestSeason"),
                        rs.getString("imageURL")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return plants;
    }
}
