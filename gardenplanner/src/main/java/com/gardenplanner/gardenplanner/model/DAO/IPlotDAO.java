package com.gardenplanner.gardenplanner.model.DAO;

import com.gardenplanner.gardenplanner.model.Plot;

import java.util.List;

/**
 * Interface for the PlotDAO class
 */
public interface IPlotDAO {

    /**
     * Insert a plot into the database
     *
     * @param plot the plot to insert
     */
    void insert(Plot plot);

    /**
     * Delete a plot from the database
     *
     * @param plot the plot to delete
     */
    void delete(Plot plot);

    /**
     * Get a list of plots for a user
     *
     * @param userID the user ID
     * @return a list of plots
     */
    List<Plot> getPlots(int userID);
}
