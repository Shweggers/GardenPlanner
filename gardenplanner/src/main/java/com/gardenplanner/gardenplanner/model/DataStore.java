package com.gardenplanner.gardenplanner.model;

/**
 * DataStore is a class that represents a data store.
 */
public class DataStore {
    private static DataStore instance = null;
    private User currentUser;

    /**
     * Create a new DataStore instance
     */
    public static DataStore getInstance() {
        if (instance == null) {
            instance = new DataStore();
        }
        return instance;
    }

    /**
     * Get the current user
     *
     * @return the current user
     */
    public User getCurrentUser() {
        return currentUser;
    }

    /**
     * Set the current user
     *
     * @param currentUser the current user
     */
    public void setCurrentUser(User currentUser) {
        this.currentUser = currentUser;
    }
}
