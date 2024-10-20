import com.gardenplanner.gardenplanner.model.DAO.MockGardenDAO;
import com.gardenplanner.gardenplanner.model.FriendManager;
import com.gardenplanner.gardenplanner.model.Garden;
import com.gardenplanner.gardenplanner.model.GardenManager;
import com.gardenplanner.gardenplanner.model.User;
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
            new Garden(1, "Tomatoes 1"),
            new Garden(1, "Tomatoes 2"),
            new Garden(1, "New Potatoes 1"),
            new Garden(1, "Garden 1"),
            new Garden(1, "Garden 2"),
            new Garden(1, "Carrots"),
            new Garden(2, "Garden 1"),
            new Garden(2, "Garden 2"),
            new Garden(2, "Garden 3"),
            new Garden(2, "Tomatoes"),
            new Garden(2, "Potatoes"),
            new Garden(2, "Potatoes 1")
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
            assertEquals(gardens[0].toString(), garden.toString());
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
     * Test getInstance which will return an empty garden list
     */
    @Test
    public void testGetInstanceEmptyList() {
        List<Garden> gardenList = GardenManager.getInstance().searchGardens(0, "");
        assertEquals(0, gardenList.size());
    }

    /**
     * Test getting a user list with userID
     */
    @Test
    public void testGetUsers() {
        for (Garden garden : gardens) {
            gardenManager.insert(garden);
        }

        List<User> userlist = gardenManager.getUsers(1);
        assertEquals(6, userlist.size());
    }

    /**
     * Test converting garden object toString
     */
    @Test
    public void testGardenToString() {
        Garden garden = new Garden(1, "Tomatoes 1");
        assertEquals("Garden{name=Tomatoes 1, userID=1}", garden.toString());
    }
}
