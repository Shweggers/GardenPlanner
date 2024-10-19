// Java
package com.gardenplanner.gardenplanner.model;

public class Friend {
    private final int userID;
    private final String friendName;

    public Friend(int userID, String friendName) {
        this.userID = userID;
        this.friendName = friendName;
    }

    public int getUserID() {
        return userID;
    }

    public String getFriendName() {
        return friendName;
    }

    public int userID() {
        return userID;
    }

    public String friendName() {
        return friendName;
    }
}