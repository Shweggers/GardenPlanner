package com.gardenplanner.gardenplanner.model;

import com.gardenplanner.gardenplanner.model.DAO.IPlantDAO;
import com.gardenplanner.gardenplanner.model.DAO.SQLPlantDAO;

import java.util.List;

/**
 * PlantManager is a class that manages the Plant objects.
 */
public class PlantManager {

    private static PlantManager instance;
    /**
     * The plant DAO
     */
    private final IPlantDAO plantDAO;

    /**
     * Create a new PlantManager object.
     *
     * @param plantDAO the plant DAO
     */
    public PlantManager(IPlantDAO plantDAO) {
        this.plantDAO = plantDAO;
    }

    public static PlantManager getInstance() {
        if (instance == null) {
            instance = new PlantManager(new SQLPlantDAO());
        }
        return instance;
    }

    /**
     * Insert a plant object into the database.
     *
     * @param plant the plant object to insert
     */
    public void insert(Plant plant) {
        plantDAO.insert(plant);
    }

    /**
     * Delete a plant object from the database.
     *
     * @param plant the plant object to delete
     */
    public void delete(Plant plant) {
        plantDAO.delete(plant);
    }

    /**
     * Get a list of plants for a user.
     *
     * @param userID the user ID
     * @param query  the query
     * @return a list of plants
     */
    public List<Plant> searchPlants(int userID, String query) {
        return plantDAO.getPlants(userID)
                .stream()
                .filter(plant -> isPlantMatched(plant, query))
                .toList();
    }

    /**
     * Search for plants by a query.
     *
     * @param plant the plant object
     * @param query the query
     * @return true if the plant matches the query, false otherwise
     */
    private boolean isPlantMatched(Plant plant, String query) {
        query = query == null ? "" : query.toLowerCase();
        return query.isEmpty() || plant.plantID().toLowerCase().contains(query);
    }
}
