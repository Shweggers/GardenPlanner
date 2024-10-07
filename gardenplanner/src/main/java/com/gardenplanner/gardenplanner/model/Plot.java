package com.gardenplanner.gardenplanner.model;

import java.time.LocalDate;

/**
 * Plot is a class that represents a plot record.
 */
public record Plot(int userID, String plant, LocalDate datePlanted) {
    /**
     * The ID of the Plot record.
     */
    public static int ID;

    /**
     * Sets the ID of the Plot record.
     *
     * @param id the ID of the Plot record
     */
    public void setID(int id) {
        ID = id;
    }

    /**
     * Returns a string representation of the Plot record.
     *
     * @return a string representation of the Plot record
     */
    @Override
    public String toString() {
        return "Plot{" +
                ", userID='" + userID + '\'' +
                ", plants=" + plant + '\'' +
                ", datePlanted=" + datePlanted +
                '}';
    }
}
