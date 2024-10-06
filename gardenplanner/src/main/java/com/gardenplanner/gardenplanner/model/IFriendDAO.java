package com.gardenplanner.gardenplanner.model;

import java.sql.SQLException;

public interface IFriendDAO {
    void createTable() throws SQLException;

    void insert(String friend1, String friend2) throws SQLException;

    void delete(String friend1, String friend2) throws SQLException;

    boolean areFriends(String friend1, String friend2) throws SQLException;

    String[] getFriends(String friend1) throws SQLException;
}
