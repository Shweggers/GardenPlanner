package com.gardenplanner.gardenplanner.model;

import com.gardenplanner.gardenplanner.model.DAO.IUserDAO;
import com.gardenplanner.gardenplanner.model.DAO.SQLUserDAO;

/**
 * UserManager is a class that manages the User objects.
 */
public class UserManager {
    private static UserManager instance;
    private final IUserDAO userDAO;

    /**
     * Create a new UserManager object.
     *
     * @param userDAO the user DAO
     */
    public UserManager(IUserDAO userDAO) {
        this.userDAO = userDAO;
    }

    /**
     * Get the instance of the UserManager.
     *
     * @return the instance of the UserManager
     */
    public static UserManager getInstance() {
        if (instance == null) {
            instance = new UserManager(new SQLUserDAO());
        }
        return instance;
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
}
