package com.gardenplanner.gardenplanner.model;

// Record class representing a User in the system with a username, email, and hashed password
public record User(String username, String email, String hashedPassword) {
    private static int ID;
    public void setID(int id) {
        ID = id;
    }
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
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
