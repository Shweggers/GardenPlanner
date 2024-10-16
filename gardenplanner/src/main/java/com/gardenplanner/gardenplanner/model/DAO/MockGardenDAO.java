package com.gardenplanner.gardenplanner.model.DAO;

import com.gardenplanner.gardenplanner.model.Garden;

import java.util.ArrayList;
import java.util.List;

/**
 * Mock class for the GardenDAO class
 */
public class MockGardenDAO implements IGardenDAO {
    private final ArrayList<Garden> gardens = new ArrayList<>();
    private int autoIncrementedId = 0;

    /**
     * Insert a garden into the database
     *
     * @param garden the garden to insert
     */
    @Override
    public void insert(Garden garden) {
        garden.setID(autoIncrementedId);
        autoIncrementedId++;

        gardens.add(garden);
    }

    /**
     * Delete a garden from the database
     *
     * @param garden the garden to delete
     */
    @Override
    public void delete(Garden garden) {
        gardens.remove(garden);
    }

    /**
     * Get a list of gardens for a user
     *
     * @param userID the user ID
     * @return a list of gardens
     */
    @Override
    public List<Garden> getGardens(int userID) {
        ArrayList<Garden> gardenList = new ArrayList<>();
        for (Garden garden : gardens) {
            if (garden.userID() == userID) {
                gardenList.add(garden);
            }
        }
        return gardenList;
    }
}
