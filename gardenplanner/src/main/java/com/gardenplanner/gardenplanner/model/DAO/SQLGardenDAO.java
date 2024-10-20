package com.gardenplanner.gardenplanner.model.DAO;

import com.gardenplanner.gardenplanner.model.DatabaseConnection;
import com.gardenplanner.gardenplanner.model.Garden;
import com.gardenplanner.gardenplanner.model.User;
import com.gardenplanner.gardenplanner.model.UserManager;

import java.sql.*;
import java.util.ArrayList;
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
        List<Garden> gardens = new ArrayList<>();
        try {
            PreparedStatement getGardens = connection.prepareStatement(
                    "SELECT * FROM gardens WHERE userID = ?"
            );
            getGardens.setInt(1, userID);

            ResultSet rs = getGardens.executeQuery();
            while (rs.next()) {
                gardens.add(new Garden(rs.getInt("id"), rs.getInt("userID"), rs.getString("gardenName")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return gardens;
    }

    /**
     * Search for users in a garden
     *
     * @param gardenID the garden ID
     * @return a list of users
     */
    public List<User> getUsers(int gardenID) {
        List<User> users = new ArrayList<>();
        try {
            PreparedStatement searchGardens = connection.prepareStatement(
                    "SELECT * FROM gardens WHERE id = ?"
            );
            searchGardens.setInt(1, gardenID);

            ResultSet rs = searchGardens.executeQuery();
            while (rs.next()) {
                users.add(UserManager.getInstance().getUserFromID(rs.getInt("userID")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }
}
