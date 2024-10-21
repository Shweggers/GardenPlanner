// Java
package com.gardenplanner.gardenplanner.model;

/**
 * Friend is a class that represents a friend record.
 *
 * @param userID the user ID
 * @param friendID the friend ID
 * @param friendName the friend name
 */
public record Friend(int userID, int friendID, String friendName) {
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
                ", friendName=" + friendName +
                '}';
    }

    /**
     * Returns the user ID.
     *
     * @return the user ID
     */
    public int userID() {
        return userID;
    }

    /**
     * Returns the friend ID.
     *
     * @return the friend ID
     */
    public int friendID() {
        return friendID;
    }

    /**
     * Returns the friend name.
     *
     * @return the friend name
     */
    public String friendName() {
        return friendName;
    }
}