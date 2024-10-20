package com.gardenplanner.gardenplanner.model.DAO;

import com.gardenplanner.gardenplanner.model.DatabaseConnection;
import com.gardenplanner.gardenplanner.model.Plot;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * SQL implementation of the PlotDAO class
 */
public class SQLPlotDAO implements IPlotDAO {
    private final Connection connection;

    /**
     * Constructor for the SQLGardenDAO class
     */
    public SQLPlotDAO() {
        connection = DatabaseConnection.getInstance();
        createTable();
    }

    /**
     * Create the plots table
     */
    public void createTable() {
        try {
            Statement createTable = connection.createStatement();
            createTable.execute(
                    "CREATE TABLE IF NOT EXISTS plots ("
                            + "id           INTEGER PRIMARY KEY AUTOINCREMENT, "
                            + "userID       INTEGER NOT NULL, "
                            + "gardenID     INTEGER NOT NULL, "
                            + "name         TEXT    NOT NULL, "
                            + "plant        TEXT    NOT NULL, "
                            + "FOREIGN KEY(userID)      REFERENCES users(id), "
                            + "FOREIGN KEY(gardenID)    REFERENCES gardens(id), "
                            + "FOREIGN KEY(plant)       REFERENCES plants(name)"
                            + ")"
            );
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Insert a plot into the database
     *
     * @param plot the plot to insert
     */
    @Override
    public void insert(Plot plot) {
        try {
            PreparedStatement insertGarden = connection.prepareStatement(
                    "INSERT INTO plots (userID, gardenID, name, plant) VALUES (?, ?, ?, ?)"
            );
            insertGarden.setInt(1, plot.userID());
            insertGarden.setInt(2, plot.gardenID());
            insertGarden.setString(3, plot.name());
            insertGarden.setString(4, plot.plant());

            insertGarden.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Delete a plot from the database
     *
     * @param plot the plot to delete
     */
    @Override
    public void delete(Plot plot) {
        try {
            PreparedStatement deleteGarden = connection.prepareStatement(
                    "DELETE FROM plots WHERE userID = ? AND gardenID = ?"
            );
            deleteGarden.setInt(1, plot.userID());
            deleteGarden.setInt(2, plot.gardenID());

            deleteGarden.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Get a list of plots for a user
     *
     * @param gardenID the user ID
     * @return a list of plots
     */
    @Override
    public List<Plot> getPlots(int gardenID) {
        List<Plot> plots = new ArrayList<>();
        try {
            PreparedStatement getGardens = connection.prepareStatement(
                    "SELECT * FROM plots WHERE gardenID = ?"
            );
            getGardens.setInt(1, gardenID);

            ResultSet rs = getGardens.executeQuery();
            while (rs.next()) {
                plots.add(new Plot(rs.getInt("userID"), rs.getInt("gardenID"), rs.getString("name"), rs.getString("plant")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return plots;
    }
}
