package com.gardenplanner.gardenplanner.model;

/**
 * User is a public record class that represents a user.
 */
public record User(int ID, String username, String email, String hashedPassword) {
    public User(String username, String email, String hashedPassword) {
        this(0, username, email, hashedPassword);
    }
    public User withID(int ID) {
        return new User(ID, username, email, hashedPassword);
    }

    /**
     * Returns a string representation of the User record.
     *
     * @return a string representation of the User record
     */
    @Override
    public String toString() {
        return "User{" +
                "username=" + username +
                ", email=" + email +
                '}';
    }
}
