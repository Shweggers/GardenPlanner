package com.gardenplanner.gardenplanner.model;

import com.gardenplanner.gardenplanner.model.DAO.IFriendDAO;

import java.util.List;

/**
 * FriendManager is a class that manages the Friend objects.
 */
public class FriendManager {
    /**
     * The friend DAO
     */
    private final IFriendDAO friendDAO;

    /**
     * Create a new FriendManager object.
     *
     * @param friendDao the friend DAO
     */
    public FriendManager(IFriendDAO friendDao) {
        this.friendDAO = friendDao;
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
     * Get a list of friends for a user.
     *
     * @param userID the user ID
     * @param friendName the friend name
     * @return a list of friends
     */
    public boolean areFriends(int userID, String friendName) {
        return friendDAO.areFriends(userID, friendName);
    }

    public boolean areMutual(Friend friend, String friendName) {
        // TODO: Implement this method
        return false;
    }

    /**
     * Search for friends by a query.
     *
     * @param userID the user ID
     * @param query the query
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
     * @param query the query
     * @return true if the friend object matches the query, false otherwise
     */
    private boolean isFriendMatched(Friend friend, String query) {
        query = query == null ? "" : query.toLowerCase();
        return query.isEmpty() || friend.friendName().toLowerCase().contains(query);
    }
}
