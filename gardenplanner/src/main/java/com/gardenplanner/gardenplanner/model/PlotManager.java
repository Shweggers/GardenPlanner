package com.gardenplanner.gardenplanner.model;

import com.gardenplanner.gardenplanner.model.DAO.IPlotDAO;

import java.util.List;

/**
 * PlotManager is a class that manages the Plot objects.
 */
public class PlotManager {
    /**
     * The plot DAO
     */
    private final IPlotDAO plotDAO;

    /**
     * Create a new PlotManager object.
     *
     * @param plotDAO the plot DAO
     */
    public PlotManager(IPlotDAO plotDAO) {
        this.plotDAO = plotDAO;
    }

    /**
     * Insert a plot object into the database.
     *
     * @param plot the plot object to insert
     */
    public void insert(Plot plot) {
        plotDAO.insert(plot);
    }

    /**
     * Delete a plot object from the database.
     *
     * @param plot the plot object to delete
     */
    public void delete(Plot plot) {
        plotDAO.delete(plot);
    }

    /**
     * Get a list of plots for a user.
     *
     * @param userID the user ID
     * @param query the query
     * @return a list of plots
     */
    public List<Plot> searchPlots(int userID, String query) {
        return plotDAO.getPlots(userID)
                .stream()
                .filter(plot -> isPlotMatched(plot, query))
                .toList();
    }

    /**
     * Search for plots by a query.
     *
     * @param plot the plot object
     * @param query the query
     * @return true if the plot matches the query, false otherwise
     */
    private boolean isPlotMatched(Plot plot, String query) {
        query = query == null ? "" : query.toLowerCase();
        return query.isEmpty() || plot.plant().toLowerCase().contains(query);
    }
}
