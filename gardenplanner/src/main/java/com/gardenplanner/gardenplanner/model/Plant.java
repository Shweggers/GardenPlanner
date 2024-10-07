package com.gardenplanner.gardenplanner.model;

/**
 * A class to represent a plant
 */
public record Plant(int userID, String plantID) {
    public static int ID;

    public void setID(int id) {
        ID = id;
    }

    /**
     * Returns a string representation of the Plant record.
     *
     * @return a string representation of the Plant record
     */
    @Override
    public String toString() {
        return "Plant{" +
                ", userid='" + userID + '\'' +
                ", plantID='" + plantID +
                '}';
    }
}
