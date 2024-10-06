package com.gardenplanner.gardenplanner.model;

import java.sql.SQLException;
import java.util.ArrayList;

public interface IFriendDAO {

    void insert(String friend1, String friend2);

    void delete(String friend1, String friend2);

    boolean areFriends(String friend1, String friend2);

    String[] getFriends(String friend1);
}
