package com.gardenplanner.gardenplanner.model;

import com.gardenplanner.gardenplanner.model.DAO.IPlantDAO;
import com.gardenplanner.gardenplanner.model.DAO.SQLPlantDAO;

import java.util.List;

/**
 * PlantManager is a class that manages the Plant objects.
 */
public class PlantManager {
    private static PlantManager instance;
    private final IPlantDAO plantDAO;

    /**
     * Create a new PlantManager object.
     *
     * @param plantDAO the plant DAO
     */
    public PlantManager(IPlantDAO plantDAO) {
        this.plantDAO = plantDAO;
    }

    /**
     * Get the instance of the PlantManager.
     *
     * @return the instance of the PlantManager
     */
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


    public Plant getPlantFromName(int userID, String plantName) {
        return plantDAO.getPlants(userID)
                .stream()
                .filter(plant -> plant.name().equals(plantName))
                .findFirst()
                .orElse(null);
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
        return query.isEmpty() || plant.name().toLowerCase().contains(query);
    }
}
