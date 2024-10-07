package com.gardenplanner.gardenplanner.model;

import com.gardenplanner.gardenplanner.model.DAO.IGardenDAO;

import java.util.List;

/**
 * GardenManager is a class that manages the Garden objects.
 */
public class GardenManager {
    /**
     * The garden DAO
     */
    private final IGardenDAO gardenDAO;

    /**
     * Create a new GardenManager object.
     *
     * @param gardenDao the garden DAO
     */
    public GardenManager(IGardenDAO gardenDao) {
        this.gardenDAO = gardenDao;
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
     * @param query the query
     * @return a list of gardens
     */
    public List<Garden> searchGardens(int userID, String query) {
        return gardenDAO.getGardens(userID)
                .stream()
                .filter(garden -> isGardenMatched(garden, query))
                .toList();
    }

    /**
     * Search for gardens by a query.
     *
     * @param garden the garden object
     * @param query the query
     * @return true if the garden matches the query, false otherwise
     */
    private boolean isGardenMatched(Garden garden, String query) {
        query = query == null ? "" : query.toLowerCase();
        return query.isEmpty() || garden.name().toLowerCase().contains(query);
    }
}
