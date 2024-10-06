package com.gardenplanner.gardenplanner.model;

import java.sql.SQLException;

public interface IUserDAO {
    void createTable() throws SQLException;

    // Method that inserts a new user into the database
    void insert(User user) throws SQLException;

    // Method that retrieves a user by their username from the database
    User getUser(String username) throws SQLException;

    // Method that updates a users password by username
    void updatePassword(String username, String hashedPassword) throws SQLException;

    int returnID(String username) throws SQLException;
}
