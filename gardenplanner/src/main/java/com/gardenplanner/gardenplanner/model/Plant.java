package com.gardenplanner.gardenplanner.model;

import java.sql.Date;

/**
 * A class to represent a plant
 */
public record Plant(String userid, String id, Date datePlanted) {

    /**
     * Get the field
     *
     * @return the field
     */
    public String getField() {
        return null;
    }
}
