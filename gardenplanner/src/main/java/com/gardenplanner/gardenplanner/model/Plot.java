package com.gardenplanner.gardenplanner.model;

import java.time.LocalDate;

/**
 * Plot is a class that represents a plot record.
 *
 * @param userID the user ID
 * @param plant the plant
 * @param datePlanted the date planted
 */
public record Plot(int userID, String plant, LocalDate datePlanted) {
    /**
     * Returns a string representation of the Plot record.
     *
     * @return a string representation of the Plot record
     */
    @Override
    public String toString() {
        return "Plot{" +
                "userID=" + userID +
                ", plants=" + plant +
                ", datePlanted=" + datePlanted +
                '}';
    }
}
