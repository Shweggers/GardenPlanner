package com.gardenplanner.gardenplanner.model;

import com.gardenplanner.gardenplanner.model.DAO.IPlotDAO;

import java.util.List;

public class PlotManager {
    private final IPlotDAO plotDAO;
    public PlotManager(IPlotDAO plotDAO) {
        this.plotDAO = plotDAO;
    }

    public void insert(Plot plot) {
        plotDAO.insert(plot);
    }

    public void delete(Plot plot) {
        plotDAO.delete(plot);
    }

    public List<Plot> searchPlots(int userID, String query) {
        return plotDAO.getPlots(userID)
                .stream()
                .filter(plot -> isPlotMatched(plot, query))
                .toList();
    }

    private boolean isPlotMatched(Plot plot, String query) {
        query = query == null ? "" : query.toLowerCase();
        return query.isEmpty() || plot.plant().toLowerCase().contains(query);
    }
}
