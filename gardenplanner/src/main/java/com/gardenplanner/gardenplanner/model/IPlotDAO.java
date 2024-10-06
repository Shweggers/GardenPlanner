package com.gardenplanner.gardenplanner.model;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface IPlotDAO {
    void createTable() throws SQLException;

    void insert(Plot plot) throws SQLException;

    void delete(Plot plot) throws SQLException;

    ResultSet getPlots(int userID) throws SQLException;
}
