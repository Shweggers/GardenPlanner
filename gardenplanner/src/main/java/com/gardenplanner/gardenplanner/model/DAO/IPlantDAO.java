package com.gardenplanner.gardenplanner.model.DAO;

import com.gardenplanner.gardenplanner.model.Plant;

import java.util.List;

/**
 * Interface for the PlantDAO class
 */
public interface IPlantDAO {

    /**
     * Insert a plant into the database
     *
     * @param plant the plant to insert
     */
    void insert(Plant plant);

    /**
     * Delete a plant from the database
     *
     * @param plant the plant to delete
     */
    void delete(Plant plant);

    /**
     * Get a list of plants for a user
     *
     * @param userID the user ID
     * @return a list of plants
     */
    List<Plant> getPlants(int userID);
}
