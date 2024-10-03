package com.gardenplanner.gardenplanner;

import java.sql.Date;

public record Plant(String userid, String id, Date datePlanted) {
    public String getField() {
        return null;
    }
}
