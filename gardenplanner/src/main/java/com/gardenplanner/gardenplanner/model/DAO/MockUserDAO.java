package com.gardenplanner.gardenplanner.model.DAO;

import com.gardenplanner.gardenplanner.model.User;

import java.util.ArrayList;

/**
 * Mock implementation of the UserDAO class
 */
public class MockUserDAO implements IUserDAO {
    private final ArrayList<User> users = new ArrayList<User>();
    private int autoIncrementID = 1;

    /**
     * Insert a user into the database
     *
     * @param user the user to insert
     */
    @Override
    public void insert(User user) {
        User newUser = user.withID(autoIncrementID++);
        users.add(newUser);
    }

    /**
     * Get a user from the database
     *
     * @param username the username of the user
     * @return the user
     */
    @Override
    public User getUser(String username) {
        for (User user : users) {
            if (user.username().equals(username)) {
                return user;
            }
        }
        return null;
    }

    /**
     * Update a user's password
     *
     * @param username the username
     * @param password the password
     */
    @Override
    public void updatePassword(String username, String password) {
        User user = getUser(username);
        if (user == null) {
            return;
        }

        User newUser = new User(user.ID(), username, user.email(), password);

        users.remove(user);
        users.add(newUser);
    }
}
