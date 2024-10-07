package com.gardenplanner.gardenplanner.model.DAO;

import com.gardenplanner.gardenplanner.model.User;

import java.util.ArrayList;

public class MockUserDAO implements IUserDAO {
    private final ArrayList<User> users = new ArrayList<User>();
    private int autoIncrementedId = 0;

    @Override
    public void insert(User user) {
        user.setID(autoIncrementedId);
        autoIncrementedId++;

        users.add(user);
    }

    @Override
    public User getUser(String username) {
        for (User user : users) {
            if (user.username().equals(username)) {
                return user;
            }
        }
        return null;
    }

    @Override
    public void updatePassword(String username, String password) {
        User user = getUser(username);
        if (user == null) {
            return;
        }

        User newUser = new User(username, user.email(), password);
        newUser.setID(user.getID());

        users.remove(user);
        users.add(newUser);
    }

    @Override
    public int returnID(String username) {
        return getUser(username).getID();
    }
}
