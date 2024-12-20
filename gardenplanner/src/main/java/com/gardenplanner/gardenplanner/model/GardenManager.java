package com.gardenplanner.gardenplanner.model;

import com.gardenplanner.gardenplanner.model.DAO.IGardenDAO;
import com.gardenplanner.gardenplanner.model.DAO.SQLGardenDAO;

import java.util.List;

/**
 * GardenManager is a class that manages the Garden objects.
 */
public class GardenManager {
    private static GardenManager instance;
    private final IGardenDAO gardenDAO;

    /**
     * Create a new GardenManager object.
     *
     * @param gardenDAO the garden DAO
     */
    public GardenManager(IGardenDAO gardenDAO) {
        this.gardenDAO = gardenDAO;
    }

    /**
     * Get the instance of the GardenManager.
     *
     * @return the instance of the GardenManager
     */
    public static GardenManager getInstance() {
        if (instance == null) {
            instance = new GardenManager(new SQLGardenDAO());
        }
        return instance;
    }

    /**
     * Insert a garden object into the database.
     *
     * @param garden the garden object to insert
     */
    public void insert(Garden garden) {
        gardenDAO.insert(garden);
    }

    /**
     * Delete a garden object from the database.
     *
     * @param garden the garden object to delete
     */
    public void delete(Garden garden) {
        gardenDAO.delete(garden);
    }

    /**
     * Get a list of gardens for a user.
     *
     * @param userID the user ID
     * @param query  the query
     * @return a list of gardens
     */
    public List<Garden> searchGardens(int userID, String query) {
        return gardenDAO.getGardens(userID)
                .stream()
                .filter(garden -> isGardenMatched(garden, query))
                .toList();
    }

    /**
     * Get a list of users for a garden.
     *
     * @param gardenID the garden ID
     * @return a list of users
     */
    public List<User> getUsers(int gardenID) {
        return gardenDAO.getUsers(gardenID);
    }

    /**
     * Search for gardens by a query.
     *
     * @param garden the garden object
     * @param query  the query
     * @return true if the garden matches the query, false otherwise
     */
    private boolean isGardenMatched(Garden garden, String query) {
        query = query == null ? "" : query.toLowerCase();
        return query.isEmpty() || garden.name().toLowerCase().contains(query);
    }
}
