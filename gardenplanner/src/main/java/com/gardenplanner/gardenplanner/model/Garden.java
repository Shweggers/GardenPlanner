package com.gardenplanner.gardenplanner.model;

public record Garden(String name, int userID) {
    public static int ID;

    public void setID(int ID) {
        Garden.ID = ID;
    }
    /**
     * Returns a string representation of the Garden record.
     *
     * @return a string representation of the Garden record
     */
    @Override
    public String toString() {
        return "Garden{" +
                ", name='" + name + '\'' +
                ", userID=" + userID +
                '}';
    }
}
