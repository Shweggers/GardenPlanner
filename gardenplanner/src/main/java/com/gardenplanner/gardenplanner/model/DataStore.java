package com.gardenplanner.gardenplanner.model;

/**
 * DataStore is a class that represents a data store.
 */
public class DataStore {
    private User currentUser;

    /**
     * Set the current user
     * 
     * @param currentUser
     */
    public void setCurrentUser(User currentUser) {
        this.currentUser = currentUser;
    }

    /**
     * Get the current user
     * 
     * @return the current user
     */
    public User getCurrentUser() {
        return currentUser;
    }
}
