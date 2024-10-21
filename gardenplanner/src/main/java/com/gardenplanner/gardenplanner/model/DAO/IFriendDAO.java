package com.gardenplanner.gardenplanner.model.DAO;

import com.gardenplanner.gardenplanner.model.Friend;

import java.util.List;

/**
 * Interface for the FriendDAO class
 */
public interface IFriendDAO {

    /**
     * Insert a friend into the database
     *
     * @param friend the friend to insert
     */
    void insert(Friend friend);
    /**
     * Delete a friend from the database
     *
     * @param friend the friend to delete
     */
    void delete(Friend friend);

    /**
     * Get a list of friends for a user
     *
     * @param userID the user ID
     * @return a list of friends
     */
    List<Friend> getFriends(int userID);

    /**
     * Check if a user has friended another user
     *
     * @param userID     the user ID
     * @param friendName the friend name
     * @return true if the user has friended the other user, false otherwise
     */
    boolean areFriends(int userID, String friendName);
}
