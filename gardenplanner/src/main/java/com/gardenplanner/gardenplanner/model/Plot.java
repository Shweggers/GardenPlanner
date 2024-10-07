package com.gardenplanner.gardenplanner.model;

import java.time.LocalDate;

public record Plot(int userID, String plant, LocalDate datePlanted) {
    public static int ID;

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
