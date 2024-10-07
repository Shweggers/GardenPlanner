package com.gardenplanner.gardenplanner;

import com.gardenplanner.gardenplanner.model.*;
import com.gardenplanner.gardenplanner.model.DAO.MockPlotDAO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PlotManagerTest {
    private PlotManager plotManager;

    private final Plot[] plots = {
            new Plot(1, "Tomato"),
            new Plot(1, "Potato"),
            new Plot(1, "Potato"),
            new Plot(1, "Raspberry"),
            new Plot(1, "Blueberry"),
            new Plot(1, "Blackberry"),
    };

    @BeforeEach
    public void setUp() {
        plotManager = new PlotManager(new MockPlotDAO());
    }

    @Test
    public void testSearchInOnePlot() {
        plotManager.insert(plots[0]);

        List<Plot> plotList = plotManager.searchPlots(1, "Tomato");
        assertEquals(1, plotList.size());
        for (Plot plot : plotList) {
            assertEquals("Tomato", plot.plant());
        }
    }

    @Test
    public void testSearchInMultiplePlots() {
        for (Plot plot : plots) {
            plotManager.insert(plot);
        }

        List<Plot> plotList = plotManager.searchPlots(1, "Potato");
        assertEquals(2, plotList.size());
        for (Plot plot : plotList) {
            assertEquals("Potato", plot.plant());
        }
    }

    @Test
    public void testSearchNoResults() {
        for (Plot plot : plots) {
            plotManager.insert(plot);
        }

        List<Plot> plotList = plotManager.searchPlots(1, "Strawberry");
        assertEquals(0, plotList.size());
    }

    @Test
    public void testSearchEmptyQuery() {
        for (Plot plot : plots) {
            plotManager.insert(plot);
        }

        List<Plot> plotList = plotManager.searchPlots(1, "");
        assertEquals(6, plotList.size());
    }

    @Test
    public void testSearchNullQuery() {
        for (Plot plot : plots) {
            plotManager.insert(plot);
        }

        List<Plot> plotList = plotManager.searchPlots(1, null);
        assertEquals(6, plotList.size());
    }

    @Test
    public void testSearchCaseInsensitive() {
        for (Plot plot : plots) {
            plotManager.insert(plot);
        }

        List<Plot> plotList = plotManager.searchPlots(1, "tomato");
        assertEquals(1, plotList.size());
        for (Plot plot : plotList) {
            assertEquals("Tomato", plot.plant());
        }
    }

    @Test
    public void testSearchPartialQuery() {
        for (Plot plot : plots) {
            plotManager.insert(plot);
        }

        List<Plot> plotList = plotManager.searchPlots(1, "berry");
        assertEquals(3, plotList.size());
        for (Plot plot : plotList) {
            assertTrue(plot.plant().equals("Blueberry") || plot.plant().equals("Blackberry") || plot.plant().equals("Raspberry"));
        }
    }

    @Test
    public void testSearchEmptyPlots() {
        List<Plot> plotList = plotManager.searchPlots(1, "Tomato");
        assertEquals(0, plotList.size());
    }
}
