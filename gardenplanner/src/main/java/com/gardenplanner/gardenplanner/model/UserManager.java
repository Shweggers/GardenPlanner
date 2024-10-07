package com.gardenplanner.gardenplanner.model;

import com.gardenplanner.gardenplanner.model.DAO.IUserDAO;

public class UserManager {
    private final IUserDAO userDAO;
    public UserManager(IUserDAO userDAO) {
        this.userDAO = userDAO;
    }

    public void insert(User user) {
        userDAO.insert(user);
    }

    public User getUser(String username) {
        return userDAO.getUser(username);
    }

    public void updatePassword(String username, String password) {
        if (username == null || password == null || password.isEmpty()) {
            return;
        }
        userDAO.updatePassword(username, password);
    }

    public int returnID(String username) {
        return userDAO.returnID(username);
    }
}
