package com.gardenplanner.gardenplanner.model.DAO;

import com.gardenplanner.gardenplanner.model.Plot;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface IPlotDAO {

    void insert(Plot plot) throws SQLException;

    void delete(Plot plot) throws SQLException;

    ResultSet getPlots(int userID) throws SQLException;
}
