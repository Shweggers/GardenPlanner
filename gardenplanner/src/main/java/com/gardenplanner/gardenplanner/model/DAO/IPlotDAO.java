package com.gardenplanner.gardenplanner.model.DAO;

import com.gardenplanner.gardenplanner.model.Plot;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public interface IPlotDAO {

    void insert(Plot plot);

    void delete(Plot plot);

    List<Plot> getPlots(int userID);
}
