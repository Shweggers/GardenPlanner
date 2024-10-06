package com.gardenplanner.gardenplanner.model;

import java.sql.*;

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
                        + "gardenName   TEXT NOT NULL, "
                        + "userid       INTEGER  FOREIGN KEY REFERENCES users(id), "
                        + ")"
        );
    }

    @Override
    public void insert(Garden garden) throws SQLException {
        PreparedStatement insertGarden = connection.prepareStatement(
                "INSERT INTO gardens (gardenName, userid) VALUES (?, ?)"
        );
        insertGarden.setString(1, garden.name());
        insertGarden.setInt(2, garden.userid());

        insertGarden.execute("INSERT INTO gardens (gardenName, userid) VALUES (?, ?)");


    }

    @Override
    public void delete(Garden garden) {
        try {
            PreparedStatement deleteGarden = connection.prepareStatement(
                    "DELETE FROM gardens WHERE gardenName = ? AND userid = ?"
            );
            deleteGarden.setString(1, garden.name());
            deleteGarden.setInt(2, garden.userid());
            deleteGarden.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public ResultSet getGardens(int userID) throws SQLException {
        return null;
    }

}
