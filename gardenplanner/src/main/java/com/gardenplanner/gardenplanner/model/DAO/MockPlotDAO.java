package com.gardenplanner.gardenplanner.model.DAO;

import com.gardenplanner.gardenplanner.model.Plot;

import java.util.ArrayList;
import java.util.List;

/**
 * Mock class for the PlotDAO class
 */
public class MockPlotDAO implements IPlotDAO {
    private final ArrayList<Plot> plots = new ArrayList<Plot>();
    private int autoIncrementID = 1;

    /**
     * Insert a plot into the database
     *
     * @param plot the plot to insert
     */
    @Override
    public void insert(Plot plot) {
        Plot newPlot = plot.withID(autoIncrementID++);
        plots.add(newPlot);
    }

    /**
     * Delete a plot from the database
     *
     * @param plot the plot to delete
     */
    @Override
    public void delete(Plot plot) {
        Plot testPlot = plot.withID(0);
        for (Plot p : plots) {
            if (p.withID(0).equals(testPlot)) {
                plots.remove(p);
                break;
            }
        }
    }

    /**
     * Get a list of plots for a user
     *
     * @param gardenID the user ID
     * @return a list of plots
     */
    @Override
    public List<Plot> getPlots(int gardenID) {
        ArrayList<Plot> plotsList = new ArrayList<Plot>();
        for (Plot plot : plots) {
            if (plot.gardenID() == gardenID) {
                plotsList.add(plot);
            }
        }
        return plotsList;
    }
}
