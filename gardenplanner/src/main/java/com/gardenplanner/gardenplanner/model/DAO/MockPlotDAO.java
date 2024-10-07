package com.gardenplanner.gardenplanner.model.DAO;

import com.gardenplanner.gardenplanner.model.Plot;

import java.util.ArrayList;
import java.util.List;

public class MockPlotDAO implements IPlotDAO {
    private final ArrayList<Plot> plots = new ArrayList<Plot>();
    private int autoIncrementedId = 0;

    @Override
    public void insert(Plot plot) {
        plot.setID(autoIncrementedId);
        autoIncrementedId++;

        plots.add(plot);
    }

    @Override
    public void delete(Plot plot) {
        plots.remove(plot);
    }

    @Override
    public List<Plot> getPlots(int userID) {
        ArrayList<Plot> plotsList = new ArrayList<Plot>();
        for (Plot plot : plots) {
            if (plot.userID() == userID) {
                plotsList.add(plot);
            }
        }
        return plotsList;
    }
}
