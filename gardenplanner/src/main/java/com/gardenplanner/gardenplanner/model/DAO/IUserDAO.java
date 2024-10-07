package com.gardenplanner.gardenplanner.model.DAO;

import com.gardenplanner.gardenplanner.model.User;

public interface IUserDAO {

    // Method that inserts a new user into the database
    void insert(User user);

    // Method that retrieves a user by their username from the database
    User getUser(String username);

    // Method that updates a users password by username
    void updatePassword(String username, String hashedPassword);

    int returnID(String username);
}
