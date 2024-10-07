package com.gardenplanner.gardenplanner;

import com.gardenplanner.gardenplanner.model.*;
import com.gardenplanner.gardenplanner.model.DAO.MockGardenDAO;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class GardenManagerTest {
    private GardenManager gardenManager;
    private Garden[] gardens = {
            new Garden("Tomatoes 1", 1),
            new Garden("Tomatoes 2", 1),
            new Garden("Potatoes 1", 2),
            new Garden("New Potatoes 1", 1),
            new Garden("Garden 1", 1),
            new Garden("Garden 2", 1),
            new Garden("Garden 1", 2),
            new Garden("Garden 2", 2),
            new Garden("Carrots", 1),
            new Garden("Tomatoes", 2),
            new Garden("Potatoes", 2),
            new Garden("Garden 3", 2)
    };

    @BeforeEach
    public void setUp() {
        gardenManager = new GardenManager(new MockGardenDAO());
    }
}
