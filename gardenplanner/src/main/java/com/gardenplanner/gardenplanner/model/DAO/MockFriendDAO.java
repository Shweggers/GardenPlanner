package com.gardenplanner.gardenplanner.model.DAO;

import com.gardenplanner.gardenplanner.model.Friend;

import java.util.ArrayList;
import java.util.List;

/**
 * MockFriendDAO is a mock class for the FriendDAO class. It is used for testing purposes.
 */
public class MockFriendDAO implements IFriendDAO {
    private final ArrayList<Friend> friends = new ArrayList<>();
    private int autoIncrementedId = 0;

    @Override
    public void insert(Friend friend) {
        friend.setID(autoIncrementedId);
        autoIncrementedId++;

        friends.add(friend);
    }

    @Override
    public void delete(Friend friend) {
        friends.remove(friend);
    }

    @Override
    public boolean areFriends(int userID, String friendName) {
        for (Friend friend : friends) {
            if (friend.userID() == userID && friend.friendName() == friendName) {
                return true;
            }
        }
        return false;
    }

    @Override
    public List<Friend> getFriends(int userID) {
        ArrayList<Friend> friendList = new ArrayList<>();
        for (Friend friend : friends) {
            if (friend.userID() == userID) {
                friendList.add(friend);
            }
        }
        return friendList;
    }
}
