package com.gardenplanner.gardenplanner;

import com.gardenplanner.gardenplanner.model.DAO.MockPlantDAO;
import com.gardenplanner.gardenplanner.model.Plant;
import com.gardenplanner.gardenplanner.model.PlantManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * A class to test the PlantManager class
 */
public class PlantManagerTest {
    /**
     * The PlantManager object to test
     */
    private PlantManager plantManager;

    /**
     * The Plant objects to use in the tests
     */
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
            assertEquals("Tomato", plant.plantID());
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
            assertTrue(plant.plantID().equals("Cucumber") || plant.plantID().equals("Baby Cucumber"));
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
            assertEquals("Blueberry", plant.plantID());
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
            assertTrue(plant.plantID().equals("Blueberry") || plant.plantID().equals("Strawberry"));
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
}
