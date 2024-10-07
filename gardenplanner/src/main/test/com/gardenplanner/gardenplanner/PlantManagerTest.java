package com.gardenplanner.gardenplanner;

import com.gardenplanner.gardenplanner.model.*;
import com.gardenplanner.gardenplanner.model.DAO.MockPlantDAO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PlantManagerTest {
    private PlantManager plantManager;

    LocalDate plantedTime = LocalDate.now();
    private final Plant[] plants = {
            new Plant(1, "Tomato", plantedTime),
            new Plant(1, "Potato", plantedTime),
            new Plant(1, "Carrot", plantedTime),
            new Plant(1, "Baby Cucumber", plantedTime),
            new Plant(1, "Cucumber", plantedTime),
            new Plant(1, "Melon", plantedTime),
            new Plant(2, "Carrot", plantedTime),
            new Plant(2, "Tomato", plantedTime),
            new Plant(2, "Blueberry", plantedTime),
            new Plant(2, "Banana", plantedTime),
            new Plant(2, "Pineapple", plantedTime),
            new Plant(2, "Strawberry", plantedTime),
    };

    @BeforeEach
    public void setUp() {
        plantManager = new PlantManager(new MockPlantDAO());
    }

    @Test
    public void testSearchInOnePlant() {
        plantManager.insert(plants[0]);

        List<Plant> plantList = plantManager.searchPlants(1, "Tomato");
        assertEquals(1, plantList.size());
        for (Plant plant : plantList) {
            assertEquals("Tomato", plant.plantID());
        }
    }

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

    @Test
    public void testSearchNoResults() {
        for (Plant plant : plants) {
            plantManager.insert(plant);
        }

        List<Plant> plantList = plantManager.searchPlants(1, "Apple");
        assertEquals(0, plantList.size());
    }

    @Test
    public void testSearchEmptyQuery() {
        for (Plant plant : plants) {
            plantManager.insert(plant);
        }

        List<Plant> plantList = plantManager.searchPlants(1, "");
        assertEquals(6, plantList.size());
    }

    @Test
    public void testSearchNullQuery() {
        for (Plant plant : plants) {
            plantManager.insert(plant);
        }

        List<Plant> plantList = plantManager.searchPlants(2, null);
        assertEquals(6, plantList.size());
    }

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

    @Test
    public void testSearchEmptyPlants() {
        List<Plant> plantList = plantManager.searchPlants(1, "Tomato");
        assertEquals(0, plantList.size());
    }
}
