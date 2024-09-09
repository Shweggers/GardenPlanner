package com.gardenplanner.gardenplanner;

public record User(String username, String email, String hashedPassword) {

    @Override
    public String toString() {
        return "User{" +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
