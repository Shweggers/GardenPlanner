package com.gardenplanner.gardenplanner.model.DAO;

import com.gardenplanner.gardenplanner.model.DatabaseConnection;
import com.gardenplanner.gardenplanner.model.Plant;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SQLPlantDAO implements IPlantDAO {
    private final Connection connection;

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
                        + "userid       INTEGER FOREIGN KEY REFERENCES users(id), "
                        + "plantid      STRING  NOT NULL, "
                        + "datePlanted  DATE"
                        + ")"
        );
    }

    /**
     * Inserts a plant into the 'plants' table.
     *
     * @param plant the plant to insert
     * @throws SQLException if a database access error occurs
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
            insertPlant.setDate(3, Date.valueOf(plant.datePlanted()));

            insertPlant.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    /**
     * Deletes a plant from the 'plants' table.
     *
     * @param plant the plant to delete
     * @throws SQLException if a database access error occurs
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
     * @return a ResultSet containing all plants
     * @throws SQLException if a database access error occurs
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
                        rs.getDate("datePlanted").toLocalDate()
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return plants;
    }
}
