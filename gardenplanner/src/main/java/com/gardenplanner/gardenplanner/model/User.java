package com.gardenplanner.gardenplanner.model;

// Record class representing a User in the system with a username, email, and hashed password
public record User(String username, String email, String hashedPassword) {

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
