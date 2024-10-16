package com.gardenplanner.gardenplanner.model;

/**
 * Friend is a class that represents a friend record.
 */
public record Friend(int userID, int friendID, String friendName) {
    /**
     * The ID of the Friend record.
     */
    public static int ID;

    /**
     * Sets the ID of the Friend record.
     *
     * @param id the ID of the Friend record
     */
    public void setID(int id) {
        ID = id;
    }

    /**
     * Returns a string representation of the Friend record.
     *
     * @return a string representation of the Friend record
     */
    @Override
    public String toString() {
        return "Friend{" +
                "userID=" + userID +
                ", friendID=" + friendID +
                ", friendName='" + friendName + '\'' +
                '}';
    }
}
