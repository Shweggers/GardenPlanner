package com.gardenplanner.gardenplanner;

// Record class representing a User in the system with a username, email, and hashed password
public record User(String username, String email, String hashedPassword) {

    @Override
    public String toString() {
        return "User{" +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
