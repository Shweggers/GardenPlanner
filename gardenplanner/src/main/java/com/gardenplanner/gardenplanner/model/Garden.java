package com.gardenplanner.gardenplanner.model;

/**
 * Garden is a class that represents a garden record.
 *
 * @param userID the user ID
 * @param name the garden name
 */
public record Garden(int userID, String name) {
    /**
     * Returns a string representation of the Garden record.
     *
     * @return a string representation of the Garden record
     */
    @Override
    public String toString() {
        return "Garden{" +
                "name=" + name +
                ", userID=" + userID +
                '}';
    }
}
