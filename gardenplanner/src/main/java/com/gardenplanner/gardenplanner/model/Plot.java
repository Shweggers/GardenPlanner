package com.gardenplanner.gardenplanner.model;

/**
 * Plot is a class that represents a plot record.
 *
 * @param ID the plot ID
 * @param userID the user ID
 * @param gardenID the garden ID
 * @param name the plot name
 * @param plant the plant
 */
public record Plot(int ID, int userID, int gardenID, String name, String plant) {
    /**
     * Create a new Plot object without an id or name.
     *
     * @param userID the user ID
     * @param gardenID the garden ID
     * @param plant the plant
     */
    public Plot(int userID, int gardenID, String plant) {
        this(0, userID, gardenID, "", plant);
    }

    /**
     * get a new Plot object with the given ID.
     *
     * @param id the plot ID
     * @return a new Plot object with the given ID
     */
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
