package com.gardenplanner.gardenplanner;

import java.util.HashMap;
import java.util.Map;

// Mock implementation of the UserDAO class for testing
public class MockUserDAO extends UserDAO {

    // Map to hold user data for testing purposes
    private final Map<String, User> users = new HashMap<>();

    // Method to manually add users to the MockUserDAO
    public void addUser(User user) {
        users.put(user.username(), user);  // Store the user in the users map
    }

    // Override the getUser method to fetch users from the mock users map
    @Override
    public User getUser(String username) {
        return users.get(username);  // Return the user if found, otherwise null
    }

    // Override the insert method to add a user to the mock users map
    @Override
    public void insert(User user) {
        users.put(user.username(), user);  // Store the user in the map
    }

    // Override the updatePassword method to change the password for an existing user
    @Override
    public void updatePassword(String username, String hashedPassword) {
        User user = users.get(username);  // Fetch the user by username
        if (user != null) {
            // If the user exists, update their password with a new User object
            users.put(username, new User(username, user.email(), hashedPassword));
        }
    }
}
