package com.gardenplanner.gardenplanner.model;

public class FriendManager {
    private IFriendDAO friendDAO;
    public FriendManager(IFriendDAO friendDao) {
        this.friendDAO = friendDao;
    }

    public void insert(String s, String s1) {
        friendDAO.insert(s, s1);
    }

    public void delete(String s, String s1) {
        friendDAO.delete(s, s1);
    }

    public boolean areFriends(String s, String s1) {
        return friendDAO.areFriends(s, s1);
    }

    public String[] getFriends(String s) {
        if (s == null) {
            s = "";
        }
        return friendDAO.getFriends(s);
    }
}
