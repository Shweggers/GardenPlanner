package com.gardenplanner.gardenplanner.model.DAO;

import com.gardenplanner.gardenplanner.model.DatabaseConnection;
import com.gardenplanner.gardenplanner.model.User;

import java.sql.*;

public class SQLUserDAO implements IUserDAO {
    // Connection to the database
    private final Connection connection;

    // Constructor that initialises the database connection
    public SQLUserDAO() {
        connection = DatabaseConnection.getInstance();
    }

    /**
     * creates the 'users' table in the database if it does not already exist.
     * 
     * @throws SQLException
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
     * @throws SQLException if a database access error occurs
     */
    @Override
    public void insert(User user) throws SQLException {
        PreparedStatement insertUser = connection.prepareStatement(
                "INSERT INTO users (username, email, password) VALUES (?, ?, ?)"
        );
        insertUser.setString(1, user.username());
        insertUser.setString(2, user.email());
        insertUser.setString(3, user.hashedPassword());

        insertUser.execute();
    }

    /**
     * Method that deletes a user from the database
     *
     * @param username the username to search for
     * @return the user
     * @throws SQLException if a database access error occurs
     */
    @Override
    public User getUser(String username) throws SQLException {
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
        return null;
    }

    /**
     * Method that updates a user's password in the database
     *
     * @param username the username to search for
     * @param hashedPassword the new hashed password
     * @throws SQLException if a database access error occurs
     */
    @Override
    public void updatePassword(String username, String hashedPassword) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("UPDATE users SET password = ? WHERE username = ?");
        preparedStatement.setString(1, hashedPassword);
        preparedStatement.setString(2, username);

        preparedStatement.executeUpdate();
    }

    /**
     * Method that returns the ID of a user
     *
     * @param username the username to search for
     * @return the ID of the user
     * @throws SQLException if a database access error occurs
     */
    @Override
    public int returnID(String username) throws SQLException {
        PreparedStatement returnID = connection.prepareStatement("SELECT id FROM users WHERE username = ?");
        returnID.setString(1, username);

        ResultSet rs = returnID.executeQuery();
        if (rs.next()) {
            return rs.getInt("id");
        }
        return -1;
    }
}
