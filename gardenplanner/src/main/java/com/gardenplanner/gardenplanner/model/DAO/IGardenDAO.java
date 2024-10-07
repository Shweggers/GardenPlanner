package com.gardenplanner.gardenplanner.model.DAO;

import com.gardenplanner.gardenplanner.model.Garden;

import java.util.List;

/**
 * Interface for the GardenDAO class
 */
public interface IGardenDAO {

    /**
     * Insert a garden into the database
     *
     * @param garden the garden to insert
     */
    void insert(Garden garden);

    /**
     * Delete a garden in the database
     *
     * @param garden the garden to update
     */
    void delete(Garden garden);

    /**
     * Get a list of gardens for a user
     *
     * @param userid the user ID
     * @return a list of gardens
     */
    List<Garden> getGardens(int userid);
}
