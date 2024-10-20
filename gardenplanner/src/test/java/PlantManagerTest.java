import com.gardenplanner.gardenplanner.model.DAO.MockPlantDAO;
import com.gardenplanner.gardenplanner.model.Plant;
import com.gardenplanner.gardenplanner.model.PlantManager;
import com.gardenplanner.gardenplanner.model.PlantProperty;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * A class to test the PlantManager class
 */
public class PlantManagerTest {
    private final Plant[] plants = {
            new Plant(1, "Tomato"),
            new Plant(1, "Potato"),
            new Plant(1, "Carrot"),
            new Plant(1, "Baby Cucumber"),
            new Plant(1, "Cucumber"),
            new Plant(1, "Melon"),
            new Plant(2, "Carrot"),
            new Plant(2, "Tomato"),
            new Plant(2, "Blueberry"),
            new Plant(2, "Banana"),
            new Plant(2, "Pineapple"),
            new Plant(2, "Strawberry"),
    };
    private PlantManager plantManager;

    /**
     * Set up the test
     */
    @BeforeEach
    public void setUp() {
        plantManager = new PlantManager(new MockPlantDAO());
    }

    /**
     * Test searching for plants in a list with one plant
     */
    @Test
    public void testSearchInOnePlant() {
        plantManager.insert(plants[0]);

        List<Plant> plantList = plantManager.searchPlants(1, "Tomato");
        assertEquals(1, plantList.size());
        for (Plant plant : plantList) {
            assertEquals("Tomato", plant.name());
        }
    }

    /**
     * Test searching for plants in a list with multiple plants
     */
    @Test
    public void testSearchInMultiplePlants() {
        for (Plant plant : plants) {
            plantManager.insert(plant);
        }

        List<Plant> plantList = plantManager.searchPlants(1, "Cucumber");
        assertEquals(2, plantList.size());
        for (Plant plant : plantList) {
            assertTrue(plant.name().equals("Cucumber") || plant.name().equals("Baby Cucumber"));
        }
    }

    /**
     * Test searching for plants in a list with no results
     */
    @Test
    public void testSearchNoResults() {
        for (Plant plant : plants) {
            plantManager.insert(plant);
        }

        List<Plant> plantList = plantManager.searchPlants(1, "Apple");
        assertEquals(0, plantList.size());
    }

    /**
     * Test searching for plants in a list with an empty query
     */
    @Test
    public void testSearchEmptyQuery() {
        for (Plant plant : plants) {
            plantManager.insert(plant);
        }

        List<Plant> plantList = plantManager.searchPlants(1, "");
        assertEquals(6, plantList.size());
    }

    /**
     * Test searching for plants in a list with a null query
     */
    @Test
    public void testSearchNullQuery() {
        for (Plant plant : plants) {
            plantManager.insert(plant);
        }

        List<Plant> plantList = plantManager.searchPlants(2, null);
        assertEquals(6, plantList.size());
    }

    /**
     * Test searching for plants in a list with a case insensitive query
     */
    @Test
    public void testSearchCaseInsensitive() {
        for (Plant plant : plants) {
            plantManager.insert(plant);
        }

        List<Plant> plantList = plantManager.searchPlants(2, "blueberry");
        assertEquals(1, plantList.size());
        for (Plant plant : plantList) {
            assertEquals("Blueberry", plant.name());
        }
    }

    /**
     * Test searching for plants in a list with a partial query
     */
    @Test
    public void testSearchPartialQuery() {
        for (Plant plant : plants) {
            plantManager.insert(plant);
        }

        List<Plant> plantList = plantManager.searchPlants(2, "berry");
        assertEquals(2, plantList.size());
        for (Plant plant : plantList) {
            assertTrue(plant.name().equals("Blueberry") || plant.name().equals("Strawberry"));
        }
    }

    /**
     * Test searching for plants in a list with no plants
     */
    @Test
    public void testSearchEmptyPlants() {
        List<Plant> plantList = plantManager.searchPlants(1, "Tomato");
        assertEquals(0, plantList.size());
    }

    /**
     * Test deleting a plant
     */
    @Test
    public void testDeletePlants() {
        for (Plant plant : plants) {
            plantManager.insert(plant);
        }

        plantManager.delete(plants[0]);
        List<Plant> plantList = plantManager.searchPlants(1, "Tomato");
        assertEquals(0, plantList.size());
    }

    /**
     * Test getInstance which will return an empty list
     */
    @Test
    public void testGetInstanceEmptyList() {
        List<Plant> plantList = PlantManager.getInstance().searchPlants(0, "");
        assertEquals(0, plantList.size());
    }

    /**
     * Test get the properties of a plant
     */
    @Test
    public void testGetProperties() {
        Plant plant = new Plant(1, "Tomato");
        List<PlantProperty> properties = new ArrayList<>();
        properties.add(new PlantProperty("Water Depth", ""));
        properties.add(new PlantProperty("Water Volume", ""));
        properties.add(new PlantProperty("Water Amount", ""));
        properties.add(new PlantProperty("Sunlight", ""));
        properties.add(new PlantProperty("Care Level", ""));
        properties.add(new PlantProperty("Harvest Season", ""));

        assertEquals(plant.getProperties(), properties);
    }

    /**
     * Test converting plant object toString
     */
    @Test
    public void testPlantToString() {
        Plant plant = new Plant(1, "4", "Baby Cucumber", "", "", "", "", "", "", "");
        assertEquals("Plant{userid=1, plantID=4}", plant.toString());
    }
}
