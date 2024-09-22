package com.gardenplanner.gardenplanner;

import java.util.HashMap;
import java.util.Map;

public class MockUserDAO extends UserDAO {
    private final Map<String, User> users = new HashMap<>();

    public void addUser(User user) {
        users.put(user.username(), user);
    }

    @Override
    public User getUser(String username) {
        return users.get(username);
    }

    @Override
    public void insert(User user) {
        users.put(user.username(), user);
    }

    @Override
    public void updatePassword(String username, String hashedPassword) {
        User user = users.get(username);
        if (user != null) {
            users.put(username, new User(username, user.email(), hashedPassword));
        }
    }
}
