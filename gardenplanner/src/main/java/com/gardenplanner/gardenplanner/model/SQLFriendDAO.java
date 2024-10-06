package com.gardenplanner.gardenplanner.model;

import java.sql.*;
import java.util.ArrayList;

public class SQLFriendDAO implements IFriendDAO {
    private final Connection connection;

    public SQLFriendDAO() {
        connection = DatabaseConnection.getInstance();
    }

    /**
     * Creates the 'friends' table in the database if it does not already exist.
     * 
     * @throws SQLException if a database access error occurs
     */
    public void createTable() throws SQLException {
        Statement createTable = connection.createStatement();
        createTable.execute(
                "CREATE TABLE IF NOT EXISTS friends ("
                        + "id       INTEGER PRIMARY KEY AUTOINCREMENT, "
                        + "friend1  STRING NOT NULL, "
                        + "friend2  STRING NOT NULL"
                        + ")"
        );
    }

    /**
     * Inserts a pair of friends into the 'friends' table.
     * This method inserts the pair in both orders (friend1, friend2) and (friend2, friend1).
     * 
     * @param friend1 the first friend
     * @param friend2 the second friend
     * @throws SQLException if a database access error occurs
     */
    @Override
    public void insert(String friend1, String friend2) {
        try {
            friend1 = friend1.toLowerCase();
            friend2 = friend2.toLowerCase();

            PreparedStatement insertFriend = connection.prepareStatement(
                    "INSERT INTO friends (friend1, friend2) VALUES (?, ?)"
            );

            PreparedStatement insertFriend2 = connection.prepareStatement(
                    "INSERT INTO friends (friend1, friend2) VALUES (?, ?)"
            );

            insertFriend2.setString(1, friend2);
            insertFriend2.setString(2, friend1);

            insertFriend2.execute();

            insertFriend.setString(1, friend1);
            insertFriend.setString(2, friend2);

            insertFriend.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Deletes a pair of friends from the 'friends' table.
     * This method deletes the pair in both orders (friend1, friend2) and (friend2, friend1).
     *
     * @param friend1 the first friend
     * @param friend2 the second friend
     * @throws SQLException if a database access error occurs
     */
    @Override
    public void delete(String friend1, String friend2) {
        try {
            PreparedStatement deleteFriend = connection.prepareStatement(
                    "DELETE FROM friends WHERE friend1 = ? AND friend2 = ?"
            );

            PreparedStatement deleteFriend2 = connection.prepareStatement(
                    "DELETE FROM friends WHERE friend1 = ? AND friend2 = ?"
            );

            deleteFriend2.setString(1, friend2);
            deleteFriend2.setString(2, friend1);

            deleteFriend2.execute();

            deleteFriend.setString(1, friend1);
            deleteFriend.setString(2, friend2);

            deleteFriend.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Checks if two people are friends.
     *
     * @param friend1 the first friend
     * @param friend2 the second friend
     * @return true if the pair (friend1, friend2) exists in the 'friends' table, false otherwise
     * @throws SQLException if a database access error occurs
     */
    @Override
    public boolean areFriends(String friend1, String friend2) {
        try {
            PreparedStatement areFriends = connection.prepareStatement(
                    "SELECT * FROM friends WHERE friend1 = ? AND friend2 = ?"
            );
            areFriends.setString(1, friend1);
            areFriends.setString(2, friend2);

            ResultSet rs = areFriends.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * Retrieves a list of friends for a given person.
     *
     * @param friend1 the person whose friends are to be retrieved
     * @return a list of friends' names
     * @throws SQLException if a database access error occurs
     */
    @Override
    public String[] getFriends(String friend1) {
        ArrayList<String> friends = new ArrayList<>();

        try {
            PreparedStatement getFriends = connection.prepareStatement(
                    "SELECT friend2 FROM friends WHERE friend1 = ?"
            );
            getFriends.setString(1, friend1);

            ResultSet rs = getFriends.executeQuery();

            while (rs.next()) {
                friends.add(rs.getString("friend2"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return friends.toArray(new String[0]);

    }
}
