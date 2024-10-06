package com.gardenplanner.gardenplanner.model;

public record Garden(String name, int userid) {

    /**
     * Returns a string representation of the Garden record.
     *
     * @return a string representation of the Garden record
     */
    @Override
    public String toString() {
        return "Garden{" +
                ", name='" + name + '\'' +
                '}';
    }
}
