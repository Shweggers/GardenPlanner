package com.gardenplanner.gardenplanner.model;

import com.gardenplanner.gardenplanner.model.DAO.IFriendDAO;

import java.util.List;

public class FriendManager {
    private IFriendDAO friendDAO;
    public FriendManager(IFriendDAO friendDao) {
        this.friendDAO = friendDao;
    }

    public void insert(Friend friend) {
        friendDAO.insert(friend);
    }

    public void delete(Friend friend) {
        friendDAO.delete(friend);
    }

    public boolean areFriends(int userID, String friendName) {
        return friendDAO.areFriends(userID, friendName);
    }

    private boolean areFriendsMutual(Friend friend, String friendName) {
        // TODO: Implement this method
        return false;
    }

    public List<Friend> searchFriends(int userID, String query) {
        return friendDAO.getFriends(userID)
                .stream()
                .filter(friend -> isFriendMatched(friend, query))
                .toList();
    }

    private boolean isFriendMatched(Friend friend, String query) {
        query = query == null ? "" : query;
        return friend.friendName().toLowerCase().contains(query.toLowerCase());
    }
}
