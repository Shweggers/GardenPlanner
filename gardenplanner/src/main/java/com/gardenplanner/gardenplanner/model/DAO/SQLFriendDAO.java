package com.gardenplanner.gardenplanner.model.DAO;

import com.gardenplanner.gardenplanner.model.DatabaseConnection;
import com.gardenplanner.gardenplanner.model.Friend;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SQLFriendDAO implements IFriendDAO {
    private final Connection connection;

    public SQLFriendDAO() {
        connection = DatabaseConnection.getInstance();
    }

    public void createTable() throws SQLException {
        Statement createTable = connection.createStatement();
        createTable.execute(
                "CREATE TABLE IF NOT EXISTS friends ("
                        + "id           INTEGER PRIMARY KEY AUTOINCREMENT, "
                        + "userID       INTEGER FOREIGN KEY REFERENCES users(id), "
                        + "friendID     INTEGER FOREIGN KEY REFERENCES users(id), "
                        + "friendName   STRING  NOT NULL"
                        + "UNIQUE(userID, friendID)"
                        + ")"
        );
    }

    @Override
    public void insert(Friend friend) {
        try {
            PreparedStatement insertFriend = connection.prepareStatement(
                    "INSERT INTO friends (userID, friendID, friendName) VALUES (?, ?, ?)"
            );
            insertFriend.setInt(1, friend.userID());
            insertFriend.setInt(2, friend.friendID());
            insertFriend.setString(2, friend.friendName());

            insertFriend.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Friend friend) {
        try {
            PreparedStatement deleteFriend = connection.prepareStatement(
                    "DELETE FROM friends WHERE userID = ? AND friendID = ?"
            );
            deleteFriend.setInt(1, friend.userID());
            deleteFriend.setInt(2, friend.friendID());

            deleteFriend.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean areFriends(int userID , String friendName) {
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

    @Override
    public List<Friend> getFriends(int userID) {
        ArrayList<Friend> friends = new ArrayList<>();

        try {
            PreparedStatement getFriends = connection.prepareStatement(
                    "SELECT friendName FROM friends WHERE userID = ?"
            );
            getFriends.setInt(1, userID);

            ResultSet rs = getFriends.executeQuery();
            while (rs.next()) {
                friends.add(new Friend(userID, rs.getInt("friendID"), rs.getString("friendName")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return friends;
    }
}
