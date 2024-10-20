package com.gardenplanner.gardenplanner.model.DAO;

import com.gardenplanner.gardenplanner.model.User;

/**
 * Interface for the UserDAO class
 */
public interface IUserDAO {

    /**
     * Insert a user into the database
     *
     * @param user the user to insert
     */
    void insert(User user);

    /**
     * Get a user by username
     *
     * @param username the username
     * @return the user
     */
    User getUser(String username);

    /**
     * Update a user's password
     *
     * @param username       the username
     * @param hashedPassword the hashed password
     */
    void updatePassword(String username, String hashedPassword);
}
