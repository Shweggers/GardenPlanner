package com.gardenplanner.gardenplanner.model;

import java.sql.Date;

/**
 * A class to represent a plant
 */
public record Plant(String id, String userid, Date datePlanted) {


    /**
     * Returns a string representation of the Plant record.
     *
     * @return a string representation of the Plant record
     */
    @Override
    public String toString() {
        return "Plant{" +
                "id='" + id + '\'' +
                ", userid='" + userid + '\'' +
                ", datePlanted=" + datePlanted +
                '}';
    }
}
