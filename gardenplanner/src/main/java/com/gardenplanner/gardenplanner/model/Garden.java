package com.gardenplanner.gardenplanner.model;

/**
 * Garden is a class that represents a garden record.
 *
 * @param userID the user ID
 * @param name   the garden name
 */
public record Garden(int ID, int userID, String name) {
    public Garden(int userID, String name) {
        this(0, userID, name);
    }
    public Garden withID(int i) {
        return new Garden(i, userID, name);
    }


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
