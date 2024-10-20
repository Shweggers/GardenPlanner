package com.gardenplanner.gardenplanner.model;

/**
 * Plot is a class that represents a plot record.
 *
 * @param userID the user ID
 * @param plant the plant
 */
public record Plot(int ID, int userID, int gardenID, String name, String plant) {
    public Plot(int userID, int gardenID, String plant) {
        this(0, userID, gardenID, "", plant);
    }
    public Plot withID(int id) {
        return new Plot(id, userID, gardenID, name, plant);
    }
    /**
     * Returns a string representation of the Plot record.
     *
     * @return a string representation of the Plot record
     */
    @Override
    public String toString() {
        return "Plot{" +
                "userID=" + userID +
                ", gardenID=" + gardenID +
                ", plant=" + plant +
                '}';
    }
}
