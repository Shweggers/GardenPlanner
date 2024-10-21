package com.gardenplanner.gardenplanner.model;

/**
 * User is a public record class that represents a user.
 *
 * @param ID the user ID
 * @param username the username
 * @param email the email
 * @param hashedPassword the hashed password
 */
public record User(int ID, String username, String email, String hashedPassword) {
    /**
     * Create a new User object without an id.
     *
     * @param username the username
     * @param email the email
     * @param hashedPassword the hashed password
     */
    public User(String username, String email, String hashedPassword) {
        this(0, username, email, hashedPassword);
    }

    /**
     * Create a new User object with the given ID.
     *
     * @param id the user ID
     */
    public User(int id) {
        this(id, "", "", "");
    }

    /**
     * Create a new User object with the given ID.
     *
     * @param ID the user ID
     * @return a new User object with the given ID
     */
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
