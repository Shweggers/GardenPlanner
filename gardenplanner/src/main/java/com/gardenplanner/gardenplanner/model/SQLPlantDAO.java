package com.gardenplanner.gardenplanner.model;

import java.sql.*;

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
                        + "id       INTEGER PRIMARY KEY AUTOINCREMENT, "
                        + "userid   STRING  FOREIGN KEY REFERENCES users(id), "
                        + "plantid  STRING  NOT NULL, "
                        + "datePlanted DATE"
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
    public void insert(Plant plant) throws SQLException {
        PreparedStatement insertPlant = connection.prepareStatement(
                "INSERT INTO plants (userid, plantid)" +
                        "VALUES (?, ?)"
        );

        insertPlant.setString(1, plant.userid());
        insertPlant.setString(2, plant.plantid());

        insertPlant.execute();
    }


    /**
     * Deletes a plant from the 'plants' table.
     *
     * @param plant the plant to delete
     * @throws SQLException if a database access error occurs
     */
    @Override
    public void delete(Plant plant) throws SQLException {
        PreparedStatement deletePlant = connection.prepareStatement(
                "DELETE FROM plants WHERE userid = ? AND plantid = ?"
        );

        deletePlant.setString(1, plant.userid());
        deletePlant.setString(2, plant.plantid());

        deletePlant.execute();
    }

    /**
     * Retrieves all plants from the 'plants' table for a user.
     *
     * @return a ResultSet containing all plants
     * @throws SQLException if a database access error occurs
     */
    @Override
    public ResultSet getPlants(int userID) throws SQLException {
        PreparedStatement getPlants = connection.prepareStatement(
                "SELECT * FROM plants WHERE userid = ?"
        );
        getPlants.setString(1, String.valueOf(userID));

        return getPlants.executeQuery();
    }
}
