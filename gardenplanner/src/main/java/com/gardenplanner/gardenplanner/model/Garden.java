package com.gardenplanner.gardenplanner.model;

/**
 * Garden is a class that represents a garden record.
 *
 * @param ID     the garden ID
 * @param userID the user ID
 * @param name   the garden name
 */
public record Garden(int ID, int userID, String name) {
    /**
     * Create a new Garden object without an id.
     *
     * @param userID the user ID
     * @param name   the garden name
     */
    public Garden(int userID, String name) {
        this(0, userID, name);
    }

    /**
     * return a new Garden object with the given ID.
     *
     * @param i the ID
     * @return a new Garden object with the given ID
     */
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
