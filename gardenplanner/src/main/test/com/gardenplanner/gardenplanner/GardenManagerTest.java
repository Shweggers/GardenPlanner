package com.gardenplanner.gardenplanner;

import com.gardenplanner.gardenplanner.model.DAO.MockGardenDAO;

import com.gardenplanner.gardenplanner.model.Garden;
import com.gardenplanner.gardenplanner.model.GardenManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class GardenManagerTest {
    private GardenManager gardenManager;
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

    @BeforeEach
    public void setUp() {
        gardenManager = new GardenManager(new MockGardenDAO());
    }

    @Test
    public void testSearchInOneGarden() {
        gardenManager.insert(gardens[0]);

        List<Garden> gardenList = gardenManager.searchGardens(1, "Tomatoes 1");
        assertEquals(1, gardenList.size());
        for (Garden garden : gardenList) {
            assertEquals(gardens[0], garden);
        }
    }

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

    @Test
    public void testSearchNoResults() {
        for (Garden garden : gardens) {
            gardenManager.insert(garden);
        }

        List<Garden> gardenList = gardenManager.searchGardens(2, "Carrots");
        assertEquals(0, gardenList.size());
    }

    @Test
    public void testSearchEmptyQuery() {
        for (Garden garden : gardens) {
            gardenManager.insert(garden);
        }

        List<Garden> gardenList = gardenManager.searchGardens(1, "");
        assertEquals(6, gardenList.size());
    }

    @Test
    public void testSearchNullQuery() {
        for (Garden garden : gardens) {
            gardenManager.insert(garden);
        }

        List<Garden> gardenList = gardenManager.searchGardens(1, null);
        assertEquals(6, gardenList.size());
    }

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

    @Test
    public void testSearchEmptyGardens() {
        List<Garden> gardenList = gardenManager.searchGardens(1, "Tomatoes");
        assertEquals(0, gardenList.size());
    }
}
