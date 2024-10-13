package com.gardenplanner.gardenplanner.model;

/**
 * User is a public record class that represents a user.
 */
public record User(String username, String email, String hashedPassword) {
    /**
     * The ID of the User record.
     */
    private static int ID;

    /**
     * Sets the ID of the User record.
     *
     * @param id the ID of the User record
     */
    public void setID(int id) {
        ID = id;
    }

    /**
     * Returns the ID of the User record.
     *
     * @return the ID of the User record
     */
    public int getID() {
        return ID;
    }

    /**
     * Returns a string representation of the User record.
     *
     * @return a string representation of the User record
     */
    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
