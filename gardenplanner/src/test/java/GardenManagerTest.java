import com.gardenplanner.gardenplanner.model.DAO.MockGardenDAO;
import com.gardenplanner.gardenplanner.model.Garden;
import com.gardenplanner.gardenplanner.model.GardenManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Tests for the GardenManager class
 */
public class GardenManagerTest {
    private final Garden[] gardens = {
            new Garden("Tomatoes 1", 1),
            new Garden("Tomatoes 2", 1),
            new Garden("New Potatoes 1", 1),
            new Garden("Garden 1", 1),
            new Garden("Garden 2", 1),
            new Garden("Carrots", 1),
            new Garden("Garden 1", 2),
            new Garden("Garden 2", 2),
            new Garden("Garden 3", 2),
            new Garden("Tomatoes", 2),
            new Garden("Potatoes", 2),
            new Garden("Potatoes 1", 2)
    };
    private GardenManager gardenManager;

    /**
     * Set up the test garden manager
     */
    @BeforeEach
    public void setUp() {
        gardenManager = new GardenManager(new MockGardenDAO());
    }

    /**
     * Test searching for gardens in a list with one garden
     */
    @Test
    public void testSearchInOneGarden() {
        gardenManager.insert(gardens[0]);

        List<Garden> gardenList = gardenManager.searchGardens(1, "Tomatoes 1");
        assertEquals(1, gardenList.size());
        for (Garden garden : gardenList) {
            assertEquals(gardens[0], garden);
        }
    }

    /**
     * Test searching for gardens in a list with multiple gardens
     */
    @Test
    public void testSearchInMultipleGardens() {
        for (Garden garden : gardens) {
            gardenManager.insert(garden);
        }

        List<Garden> gardenList = gardenManager.searchGardens(1, "Tomatoes");
        assertEquals(2, gardenList.size());
        for (Garden garden : gardenList) {
            assertEquals(1, garden.userID());
            assertTrue(garden.name().equals("Tomatoes 1") || garden.name().equals("Tomatoes 2"));
        }
    }

    /**
     * Test searching for gardens in a list with multiple gardens with no results
     */
    @Test
    public void testSearchNoResults() {
        for (Garden garden : gardens) {
            gardenManager.insert(garden);
        }

        List<Garden> gardenList = gardenManager.searchGardens(2, "Carrots");
        assertEquals(0, gardenList.size());
    }

    /**
     * Test searching for gardens in a list with multiple gardens with an empty query
     */
    @Test
    public void testSearchEmptyQuery() {
        for (Garden garden : gardens) {
            gardenManager.insert(garden);
        }

        List<Garden> gardenList = gardenManager.searchGardens(1, "");
        assertEquals(6, gardenList.size());
    }

    /**
     * Test searching for gardens in a list with multiple gardens with a null query
     */
    @Test
    public void testSearchNullQuery() {
        for (Garden garden : gardens) {
            gardenManager.insert(garden);
        }

        List<Garden> gardenList = gardenManager.searchGardens(1, null);
        assertEquals(6, gardenList.size());
    }

    /**
     * Test searching for gardens in a list with multiple gardens with a case-insensitive query
     */
    @Test
    public void testSearchCaseInsensitive() {
        for (Garden garden : gardens) {
            gardenManager.insert(garden);
        }

        List<Garden> gardenList = gardenManager.searchGardens(2, "potatoes");
        assertEquals(2, gardenList.size());
        for (Garden garden : gardenList) {
            assertTrue(garden.name().equalsIgnoreCase("Potatoes") || garden.name().equalsIgnoreCase("Potatoes 1"));
        }
    }

    /**
     * Test searching for gardens in a list with multiple gardens with a partial query
     */
    @Test
    public void testSearchPartialQuery() {
        for (Garden garden : gardens) {
            gardenManager.insert(garden);
        }

        List<Garden> gardenList = gardenManager.searchGardens(2, "Gar");
        assertEquals(3, gardenList.size());
        for (Garden garden : gardenList) {
            assertTrue(garden.name().equals("Garden 1") || garden.name().equals("Garden 2") || garden.name().equals("Garden 3"));
        }
    }

    /**
     * Test searching for gardens in an empty list
     */
    @Test
    public void testSearchEmptyGardens() {
        List<Garden> gardenList = gardenManager.searchGardens(1, "Tomatoes");
        assertEquals(0, gardenList.size());
    }

    /**
     * Test deleting a garden
     */
    @Test
    public void testDeleteGarden() {
        for (Garden garden : gardens) {
            gardenManager.insert(garden);
        }

        gardenManager.delete(gardens[2]);
        List<Garden> gardenList = gardenManager.searchGardens(1, "New Tomatoes 1");
        assertEquals(0, gardenList.size());

    }

    /**
     * Test converting garden object toString
     */
    @Test
    public void testGardenToString() {
        Garden garden = new Garden("Tomatoes 1", 1);
        assertEquals("Garden{name='Tomatoes 1', userID=1}", garden.toString());
    }
}
