package com.gardenplanner.gardenplanner.model;

import java.util.ArrayList;

/**
 * MockFriendDAO is a mock class for the FriendDAO class. It is used for testing purposes.
 */
public class MockFriendDAO implements IFriendDAO {
    private ArrayList<Friend> friends = new ArrayList<>();
    private int autoIncrementedId = 0;

    @Override
    public void insert(String friend1, String friend2) {
        Friend friends1 = new Friend(friend1, friend2);
        Friend friends2 = new Friend(friend2, friend1);
        friends1.setID(autoIncrementedId);
        autoIncrementedId++;
        friends2.setID(autoIncrementedId);
        autoIncrementedId++;
        friends.add(friends1);
        friends.add(friends2);
    }

    @Override
    public void delete(String friend1, String friend2) {
        for (int i = 0; i < friends.size(); i++) {
            if (friends.get(i).friend1().equals(friend1) && friends.get(i).friend2().equals(friend2)) {
                friends.remove(i);
                break;
            }
        }
    }

    @Override
    public boolean areFriends(String friend1, String friend2) {
        for (Friend friend : friends) {
            if (friend.friend1().equals(friend1) && friend.friend2().equals(friend2)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public String[] getFriends(String s) {
        ArrayList<String> friendList = new ArrayList<>();
        for (Friend friend : friends) {
            String friend1fromfriend = friend.friend1().toLowerCase();
            if (friend1fromfriend.contains(s.toLowerCase())) {
                friendList.add(friend.friend2());
            }
        }
        return friendList.toArray(new String[0]);
    }
}
