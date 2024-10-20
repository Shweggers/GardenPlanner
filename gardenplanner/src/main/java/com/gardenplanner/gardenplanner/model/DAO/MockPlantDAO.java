package com.gardenplanner.gardenplanner.model.DAO;

import com.gardenplanner.gardenplanner.model.Plant;

import java.util.ArrayList;
import java.util.List;

/**
 * Mock implementation of the PlantDAO class
 */
public class MockPlantDAO implements IPlantDAO {
    private final ArrayList<Plant> plants = new ArrayList<Plant>();

    /**
     * Insert a plant into the database
     *
     * @param plant the plant to insert
     */
    @Override
    public void insert(Plant plant) {
        plants.add(plant);
    }

    /**
     * Delete a plant from the database
     *
     * @param plant the plant to delete
     */
    @Override
    public void delete(Plant plant) {
        plants.remove(plant);
    }

    /**
     * Get a list of plants for a user
     *
     * @param userID the user ID
     * @return a list of plants
     */
    @Override
    public List<Plant> getPlants(int userID) {
        ArrayList<Plant> plantsList = new ArrayList<Plant>();
        for (Plant plant : plants) {
            if (plant.userID() == userID) {
                plantsList.add(plant);
            }
        }
        return plantsList;
    }
}
