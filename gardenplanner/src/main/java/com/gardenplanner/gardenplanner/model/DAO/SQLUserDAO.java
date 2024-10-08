package com.gardenplanner.gardenplanner.model.DAO;

import com.gardenplanner.gardenplanner.model.DatabaseConnection;
import com.gardenplanner.gardenplanner.model.User;

import java.sql.*;

/**
 * SQL implementation of the UserDAO class
 */
public class SQLUserDAO implements IUserDAO {
    /**
     * The database connection
     */
    private final Connection connection;

    /**
     * Create a new SQLUserDAO object.
     */
    public SQLUserDAO() {
        connection = DatabaseConnection.getInstance();
    }

    /**
     * creates the 'users' table in the database if it does not already exist.
     * 
     * @throws SQLException if an error occurs
     */
    public void createTable() throws SQLException {
        Statement createTable = connection.createStatement();
        createTable.execute(
                "CREATE TABLE IF NOT EXISTS users ("
                        + "id       INTEGER PRIMARY KEY AUTOINCREMENT, "
                        + "username TEXT NOT NULL UNIQUE, "
                        + "email    TEXT NOT NULL, "
                        + "password TEXT NOT NULL"
                        + ")"
        );
    }

    /**
     * Method that inserts a user into the database
     *
     * @param user the user to insert
     */
    @Override
    public void insert(User user) {
        try {
            PreparedStatement insertUser = connection.prepareStatement(
                    "INSERT INTO users (username, email, password) VALUES (?, ?, ?)"
            );
            insertUser.setString(1, user.username());
            insertUser.setString(2, user.email());
            insertUser.setString(3, user.hashedPassword());

            insertUser.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Method that deletes a user from the database
     *
     * @param username the username to search for
     * @return the user
     */
    @Override
    public User getUser(String username) {
        try {
            PreparedStatement getUser = connection.prepareStatement("SELECT * FROM users WHERE username = ?");
            getUser.setString(1, username);

            ResultSet rs = getUser.executeQuery();
            if (rs.next()) {
                return new User(
                        rs.getString("username"),
                        rs.getString("email"),
                        rs.getString("password")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Method that updates a user's password in the database
     *
     * @param username the username to search for
     * @param hashedPassword the new hashed password
     */
    @Override
    public void updatePassword(String username, String hashedPassword) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE users SET password = ? WHERE username = ?");
            preparedStatement.setString(1, hashedPassword);
            preparedStatement.setString(2, username);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Method that returns the ID of a user
     *
     * @param username the username to search for
     * @return the ID of the user
     */
    @Override
    public int returnID(String username) {
        try {
            PreparedStatement returnID = connection.prepareStatement("SELECT id FROM users WHERE username = ?");
            returnID.setString(1, username);

            ResultSet rs = returnID.executeQuery();
            if (rs.next()) {
                return rs.getInt("id");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }
}
