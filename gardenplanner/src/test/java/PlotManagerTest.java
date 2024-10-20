import com.gardenplanner.gardenplanner.model.DAO.MockPlotDAO;
import com.gardenplanner.gardenplanner.model.Plot;
import com.gardenplanner.gardenplanner.model.PlotManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * A class to test the PlotManager class
 */
public class PlotManagerTest {
    private final Plot[] plots = {
            new Plot(1, 1, "Tomato"),
            new Plot(1, 1, "Potato"),
            new Plot(1, 1, "Potato"),
            new Plot(1, 1, "Raspberry"),
            new Plot(1, 1, "Blueberry"),
            new Plot(1, 1, "Blackberry"),
            new Plot(2, 1, "Tomato"),
            new Plot(2, 1, "Raspberry"),
            new Plot(2, 1, "Blueberry"),
    };
    private PlotManager plotManager;

    /**
     * Set up the test
     */
    @BeforeEach
    public void setUp() {
        plotManager = new PlotManager(new MockPlotDAO());
    }

    /**
     * Test searching for plots in a list with one plot
     */
    @Test
    public void testSearchInOnePlot() {
        plotManager.insert(plots[0]);

        List<Plot> plotList = plotManager.searchPlots(1, "Tomato");
        assertEquals(1, plotList.size());
        for (Plot plot : plotList) {
            assertEquals("Tomato", plot.plant());
        }
    }

    /**
     * Test searching for plots in a list with multiple plots
     */
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

    /**
     * Test searching for plots in a list with no results
     */
    @Test
    public void testSearchNoResults() {
        for (Plot plot : plots) {
            plotManager.insert(plot);
        }

        List<Plot> plotList = plotManager.searchPlots(1, "Strawberry");
        assertEquals(0, plotList.size());
    }

    /**
     * Test searching for plots in a list with no results
     */
    @Test
    public void testSearchEmptyQuery() {
        for (Plot plot : plots) {
            plotManager.insert(plot);
        }

        List<Plot> plotList = plotManager.searchPlots(1, "");
        assertEquals(6, plotList.size());
    }

    /**
     * Test searching for plots in a list with no results
     */
    @Test
    public void testSearchNullQuery() {
        for (Plot plot : plots) {
            plotManager.insert(plot);
        }

        List<Plot> plotList = plotManager.searchPlots(1, null);
        assertEquals(6, plotList.size());
    }

    /**
     * Test searching for plots in a list with no results
     */
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

    /**
     * Test searching for plots in a list with no results
     */
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

    /**
     * Test searching for plots in a list with no results
     */
    @Test
    public void testSearchEmptyPlots() {
        List<Plot> plotList = plotManager.searchPlots(1, "Tomato");
        assertEquals(0, plotList.size());
    }

    /**
     * Test deleting a plot
     */
    @Test
    public void testDeletePlot() {
        for (Plot plot : plots) {
            plotManager.insert(plot);
        }

        plotManager.delete(plots[0]);
        List<Plot> plotList = plotManager.searchPlots(1, "Tomato");
        assertEquals(0, plotList.size());
    }

    /**
     * Test converting plot object toString
     */
    @Test
    public void testPlotToString() {
        Plot plot = new Plot(1, 1, "Tomato");
        assertEquals("Plot{userID=1, gardenID=1, plant=Tomato}", plot.toString());
    }
}
