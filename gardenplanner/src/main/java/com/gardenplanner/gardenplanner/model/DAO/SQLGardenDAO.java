package com.gardenplanner.gardenplanner.model.DAO;

import com.gardenplanner.gardenplanner.model.DatabaseConnection;
import com.gardenplanner.gardenplanner.model.Garden;

import java.sql.*;

import java.util.List;

public class SQLGardenDAO implements IGardenDAO {
    private final Connection connection;

    public SQLGardenDAO() {
        connection = DatabaseConnection.getInstance();
    }

    public void createTable() throws SQLException {
        Statement createTable = connection.createStatement();
        createTable.execute(
                "CREATE TABLE IF NOT EXISTS gardens ("
                        + "id           INTEGER PRIMARY KEY AUTOINCREMENT, "
                        + "gardenName   TEXT    NOT NULL, "
                        + "userID       INTEGER FOREIGN KEY REFERENCES users(id), "
                        + "UNIQUE(gardenName, userID)"
                        + ")"
        );
    }

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

    @Override
    public List<Garden> getGardens(int userID) {
        return null;
    }
}
