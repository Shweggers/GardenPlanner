package com.gardenplanner.gardenplanner.model.DAO;

import com.gardenplanner.gardenplanner.model.Friend;

import java.util.ArrayList;
import java.util.List;

/**
 * MockFriendDAO is a mock class for the FriendDAO class. It is used for testing purposes.
 */
public class MockFriendDAO implements IFriendDAO {
    /**
     * The list of friends
     */
    private final ArrayList<Friend> friends = new ArrayList<>();

    /**
     * The auto-incremented ID
     */
    private int autoIncrementedId = 0;

    /**
     * Insert a friend into the database
     *
     * @param friend the friend to insert
     */
    @Override
    public void insert(Friend friend) {
        friend.setID(autoIncrementedId);
        autoIncrementedId++;

        friends.add(friend);
    }

    /**
     * Delete a friend from the database
     *
     * @param friend the friend to delete
     */
    @Override
    public void delete(Friend friend) {
        friends.remove(friend);
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
        for (Friend friend : friends) {
            if (friend.userID() == userID && friend.friendName() == friendName) {
                return true;
            }
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
        ArrayList<Friend> friendList = new ArrayList<>();
        for (Friend friend : friends) {
            if (friend.userID() == userID) {
                friendList.add(friend);
            }
        }
        return friendList;
    }
}
