package com.gardenplanner.gardenplanner.model;

import com.gardenplanner.gardenplanner.model.DAO.IPlotDAO;
import com.gardenplanner.gardenplanner.model.DAO.SQLPlotDAO;

import java.util.List;

/**
 * PlotManager is a class that manages the Plot objects.
 */
public class PlotManager {
    private static PlotManager instance;
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
     * Get the instance of the PlotManager.
     *
     * @return the instance of the PlotManager
     */
    public static PlotManager getInstance() {
        if (instance == null) {
            instance = new PlotManager(new SQLPlotDAO());
        }
        return instance;
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
     * @param gardenID the user ID
     * @param query  the query
     * @return a list of plots
     */
    public List<Plot> searchPlots(int gardenID, String query) {
        return plotDAO.getPlots(gardenID)
                .stream()
                .filter(plot -> isPlotMatched(plot, query))
                .toList();
    }

    /**
     * Get a plot from a garden by name.
     *
     * @param gardenID the garden ID
     * @param name     the name of the plot
     * @return the plot object
     */
    public Plot getPlotFromName(int gardenID, String name) {
        return plotDAO.getPlots(gardenID)
                .stream()
                .filter(plot -> plot.name().equals(name))
                .findFirst().orElse(null);
    }

    /**
     * Search for plots by a query.
     *
     * @param plot  the plot object
     * @param query the query
     * @return true if the plot matches the query, false otherwise
     */
    private boolean isPlotMatched(Plot plot, String query) {
        query = query == null ? "" : query.toLowerCase();
        return query.isEmpty() || plot.name().toLowerCase().contains(query);
    }
}
