package com.gardenplanner.gardenplanner.model;

/**
 * Garden is a class that represents a garden record.
 */
public record Garden(String name, int userID) {
    /**
     * The ID of the Garden record.
     */
    public static int ID;

    /**
     * Sets the ID of the Garden record.
     *
     * @param id the ID of the Garden record
     */
    public void setID(int id) {
        ID = id;
    }

    /**
     * Returns a string representation of the Garden record.
     *
     * @return a string representation of the Garden record
     */
    @Override
    public String toString() {
        return "Garden{" +
                ", name='" + name + '\'' +
                ", userID=" + userID +
                '}';
    }
}
