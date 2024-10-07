package com.gardenplanner.gardenplanner.model.DAO;

import com.gardenplanner.gardenplanner.model.Plot;

import java.util.ArrayList;
import java.util.List;

/**
 * Mock class for the PlotDAO class
 */
public class MockPlotDAO implements IPlotDAO {
    /**
     * List of plots
     */
    private final ArrayList<Plot> plots = new ArrayList<Plot>();

    /**
     * Auto-incremented ID
     */
    private int autoIncrementedId = 0;

    /**
     * Insert a plot into the database
     *
     * @param plot the plot to insert
     */
    @Override
    public void insert(Plot plot) {
        plot.setID(autoIncrementedId);
        autoIncrementedId++;

        plots.add(plot);
    }

    /**
     * Delete a plot from the database
     *
     * @param plot the plot to delete
     */
    @Override
    public void delete(Plot plot) {
        plots.remove(plot);
    }

    /**
     * Get a list of plots for a user
     *
     * @param userID the user ID
     * @return a list of plots
     */
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
