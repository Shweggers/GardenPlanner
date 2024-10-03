package com.gardenplanner.gardenplanner;

import com.gardenplanner.gardenplanner.model.User;

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
