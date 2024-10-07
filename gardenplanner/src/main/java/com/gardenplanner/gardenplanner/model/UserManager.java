package com.gardenplanner.gardenplanner.model;

import com.gardenplanner.gardenplanner.model.DAO.IUserDAO;

/**
 * UserManager is a class that manages the User objects.
 */
public class UserManager {
    private final IUserDAO userDAO;
    public UserManager(IUserDAO userDAO) {
        this.userDAO = userDAO;
    }

    /**
     * Insert a user object into the database.
     *
     * @param user the user object to insert
     */
    public void insert(User user) {
        userDAO.insert(user);
    }

    /**
     * Get a user object from the database.
     *
     * @param username the username
     * @return the user object
     */
    public User getUser(String username) {
        return userDAO.getUser(username);
    }

    /**
     * Update a user object in the database.
     *
     * @param username the user's username
     * @param password the user's password to update
     */
    public void updatePassword(String username, String password) {
        if (username == null || password == null || password.isEmpty()) {
            return;
        }
        userDAO.updatePassword(username, password);
    }

    /**
     * Get the userID of a username.
     *
     * @param username the username
     * @return the userID
     */
    public int returnID(String username) {
        return userDAO.returnID(username);
    }
}
