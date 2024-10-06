package com.gardenplanner.gardenplanner.model;

import java.util.List;

public record Plot(String id, List<Plant> plants) {

    /**
     * Returns a string representation of the Plot record.
     *
     * @return a string representation of the Plot record
     */
    @Override
    public String toString() {
        return "Plot{" +
                ", id='" + id + '\'' +
                ", plants=" + plants +
                '}';
    }
}
