package com.gardenplanner.gardenplanner.model.DAO;

import com.gardenplanner.gardenplanner.model.Plot;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SQLPlotDAO implements IPlotDAO {
    public void createTable() {
        // + "datePlanted  DATE"
    }

    @Override
    public void insert(Plot plot) {

        // insertPlant.setDate(3, Date.valueOf(plant.datePlanted()));
    }

    @Override
    public void delete(Plot plot) {

    }

    @Override
    public List<Plot> getPlots(int userID) {

        return null;
        // rs.getDate("datePlanted").toLocalDate()
    }
}