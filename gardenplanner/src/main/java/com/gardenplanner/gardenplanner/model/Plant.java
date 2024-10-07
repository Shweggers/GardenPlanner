package com.gardenplanner.gardenplanner.model;

import java.time.*;

/**
 * A class to represent a plant
 */
public record Plant(int userID, String plantID, LocalDate datePlanted) {
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
                ", datePlanted=" + datePlanted +
                ", plantID='" + plantID + '\'' +
                '}';
    }
}
