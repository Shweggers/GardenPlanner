package com.gardenplanner.gardenplanner.model;

public record Friend(String friend1, String friend2) {

    /**
     * Returns a string representation of the Friend record.
     * 
     * @return a string representation of the Friend record
     */
    @Override
    public String toString() {
        return "Friend{" +
                "friend1='" + friend1 + '\'' +
                ", friend2='" + friend2 + '\'' +
                '}';
    }
}
