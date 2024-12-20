// Java
package com.gardenplanner.gardenplanner.model;

import com.gardenplanner.gardenplanner.model.DAO.IFriendDAO;
import com.gardenplanner.gardenplanner.model.DAO.SQLFriendDAO;

import java.util.List;

/**
 * FriendManager is a class that manages the Friend objects.
 */
public class FriendManager {
    private static FriendManager instance;
    private final IFriendDAO friendDAO;

    /**
     * Create a new FriendManager object.
     *
     * @param friendDAO the friend DAO
     */
    public FriendManager(IFriendDAO friendDAO) {
        this.friendDAO = friendDAO;
    }

    /**
     * Get the instance of the FriendManager.
     *
     * @return the instance of the FriendManager
     */
    public static FriendManager getInstance() {
        if (instance == null) {
            instance = new FriendManager(new SQLFriendDAO());
        }
        return instance;
    }

    /**
     * Insert a friend object into the database.
     *
     * @param friend the friend object to insert
     */
    public void insert(Friend friend) {
        friendDAO.insert(friend);
    }

    /**
     * Delete a friend object from the database.
     *
     * @param friend the friend object to delete
     */
    public void delete(Friend friend) {
        friendDAO.delete(friend);
    }

    /**
     * Check if two users are friends.
     *
     * @param userID     the user ID
     * @param friendName the friend name
     * @return true if they are friends, false otherwise
     */
    public boolean areFriends(int userID, String friendName) {
        return friendDAO.areFriends(userID, friendName);
    }

    /**
     * Search for friends by a query.
     *
     * @param userID the user ID
     * @param query  the query
     * @return a list of friends
     */
    public List<Friend> searchFriends(int userID, String query) {
        return friendDAO.getFriends(userID)
                .stream()
                .filter(friend -> isFriendMatched(friend, query))
                .toList();


    }

    /**
     * Check if a friend object matches a query.
     *
     * @param friend the friend object
     * @param query  the query
     * @return true if the friend object matches the query, false otherwise
     */
    private boolean isFriendMatched(Friend friend, String query) {
        if (query == null || query.isEmpty()) {
            return true;
        }
        return friend.friendName().toLowerCase().contains(query.toLowerCase());
    }
}