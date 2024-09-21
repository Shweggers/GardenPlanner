package com.gardenplanner.gardenplanner;

import java.sql.*;

public class FriendDAO {
    private final Connection connection;

    public FriendDAO() {
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
                        + "id INTEGER PRIMARY KEY AUTOINCREMENT, "
                        + "friend1 string NOT NULL, "
                        + "friend2 string NOT NULL"
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
    public void insert(String friend1, String friend2) throws SQLException {
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
    }

    /**
     * Deletes a pair of friends from the 'friends' table.
     * This method deletes the pair in both orders (friend1, friend2) and (friend2, friend1).
     *
     * @param friend1 the first friend
     * @param friend2 the second friend
     * @throws SQLException if a database access error occurs
     */
    public void delete(String friend1, String friend2) throws SQLException {
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
    }

    /**
     * Checks if two people are friends.
     *
     * @param friend1 the first friend
     * @param friend2 the second friend
     * @return true if the pair (friend1, friend2) exists in the 'friends' table, false otherwise
     * @throws SQLException if a database access error occurs
     */
    public boolean areFriends(String friend1, String friend2) throws SQLException {
        PreparedStatement areFriends = connection.prepareStatement(
                "SELECT * FROM friends WHERE friend1 = ? AND friend2 = ?"
        );
        areFriends.setString(1, friend1);
        areFriends.setString(2, friend2);
        ResultSet rs = areFriends.executeQuery();
        return rs.next();
    }

    /**
     * Retrieves a list of friends for a given person.
     *
     * @param friend1 the person whose friends are to be retrieved
     * @return an array of friends' names
     * @throws SQLException if a database access error occurs
     */
    public String[] getFriends(String friend1) throws SQLException {
        PreparedStatement getFriends = connection.prepareStatement(
                "SELECT friend2 FROM friends WHERE friend1 = ?"
        );
        getFriends.setString(1, friend1);
        ResultSet rs = getFriends.executeQuery();
        String[] friends = new String[100];
        int i = 0;
        while (rs.next()) {
            friends[i] = rs.getString("friend2");
            i++;
        }
        return friends;
    }

}
