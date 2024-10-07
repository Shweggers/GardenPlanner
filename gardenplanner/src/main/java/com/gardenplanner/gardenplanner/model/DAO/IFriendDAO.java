package com.gardenplanner.gardenplanner.model.DAO;

import com.gardenplanner.gardenplanner.model.Friend;

import java.util.List;

public interface IFriendDAO {

    void insert(Friend friend);

    void delete(Friend friend);

    List<Friend> getFriends(int userID);

    boolean areFriends(int userID, String friendName);


}
