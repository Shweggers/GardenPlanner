package com.gardenplanner.gardenplanner.model.DAO;

import com.gardenplanner.gardenplanner.model.DatabaseConnection;
import com.gardenplanner.gardenplanner.model.Friend;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * SQL implementation of the FriendDAO class
 */
public class SQLFriendDAO implements IFriendDAO {
    private final Connection connection;

    /**
     * Constructor for the SQLFriendDAO class
     */
    public SQLFriendDAO() {
        connection = DatabaseConnection.getInstance();
        createTable();
    }

    /**
     * Create the friends table
     */
    public void createTable() {
        try {
            Statement createTable = connection.createStatement();
            createTable.execute(
                    "CREATE TABLE IF NOT EXISTS friends ("
                            //+ "id           INTEGER PRIMARY KEY AUTOINCREMENT, "
                            + "userID       INTEGER NOT NULL, "
                            + "friendID     INTEGER NOT NULL, "
                            + "friendName   STRING  NOT NULL, "
                            + "FOREIGN KEY(userID) REFERENCES users(id), "
                            + "FOREIGN KEY(friendID) REFERENCES users(id), "
                            + "UNIQUE(userID, friendName)"
                            + ")"
            );
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Insert a friend into the database
     *
     * @param friend the friend to insert
     */
    @Override
    public void insert(Friend friend) {
        try {
            PreparedStatement insertFriend = connection.prepareStatement(
                    "INSERT INTO friends (userID, friendID, friendName) VALUES (?, ?, ?)"
            );
            insertFriend.setInt(1, friend.userID());
            insertFriend.setInt(2, friend.friendID());
            insertFriend.setString(3, friend.friendName());

            insertFriend.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Delete a friend from the database
     *
     * @param friend the friend to delete
     */
    @Override
    public void delete(Friend friend) {
        try {
            PreparedStatement deleteFriend = connection.prepareStatement(
                    "DELETE FROM friends WHERE userID = ? AND friendName = ?"
            );
            deleteFriend.setInt(1, friend.userID());
            deleteFriend.setInt(2, friend.friendID());
            deleteFriend.setString(2, friend.friendName());

            deleteFriend.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Check if a user has friended another user
     *
     * @param userID     the user ID
     * @param friendName the friend name
     * @return true if the user has friended the other user, false otherwise
     */
    @Override
    public boolean areFriends(int userID, String friendName) {
        try {
            PreparedStatement areFriends = connection.prepareStatement(
                    "SELECT * FROM friends WHERE userID = ? AND friendName = ?"
            );
            areFriends.setInt(1, userID);
            areFriends.setString(2, friendName);

            ResultSet rs = areFriends.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * Get a list of friends for a user
     *
     * @param userID the user ID
     * @return a list of friends
     */
    @Override
    public List<Friend> getFriends(int userID) {
        List<Friend> friends = new ArrayList<>();
        try {
            PreparedStatement getFriends = connection.prepareStatement(
                    "SELECT * FROM friends WHERE userID = ?"
            );
            getFriends.setInt(1, userID);

            ResultSet rs = getFriends.executeQuery();
            while (rs.next()) {
                friends.add(new Friend(rs.getInt("userID"), rs.getInt("friendID"), rs.getString("friendName")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
    }
        return friends;
    }
}
