package com.gardenplanner.gardenplanner.model;

public record Friend(int userID, int friendID, String friendName) {
    public static int ID;

    public void setID(int ID) {
        Friend.ID = ID;
    }

    /**
     * Returns a string representation of the Friend record.
     * 
     * @return a string representation of the Friend record
     */
    @Override
    public String toString() {
        return "Friend{" +
                ", userID='" + userID + '\'' +
                ", friendID='" + friendID + '\'' +
                ", friendName='" + friendName + '\'' +
                '}';
    }
}
