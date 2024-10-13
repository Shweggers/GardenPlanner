package com.gardenplanner.gardenplanner.controller;

import com.gardenplanner.gardenplanner.model.DataStore;

public class GardenbookAddplantController {
    /**
     * The data store
     */
    private final DataStore dataStore;

    /**
     * Constructs a new GardenbookAddplantController with the specified data store.
     *
     * @param dataStore the data store
     */
    public GardenbookAddplantController(DataStore dataStore) {
        this.dataStore = dataStore;
    }
}
