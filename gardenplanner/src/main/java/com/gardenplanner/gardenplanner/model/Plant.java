package com.gardenplanner.gardenplanner.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Plant is a record that represents a plant object.
 *
 * @param userID the user ID
 * @param plantID the plant ID
 * @param name the name
 * @param waterDepth the water depth
 * @param waterVolume the water volume
 * @param waterAmount the water amount
 * @param sunlight the sunlight
 * @param careLevel the care level
 * @param harvestSeason the harvest season
 */
public record Plant(int userID, String plantID, String name, String waterDepth, String waterVolume, String waterAmount, String sunlight, String careLevel, String harvestSeason, String imageURL) {

    /**
     * Create a new Plant object with only the userID and name.
     *
     * @param userID the user ID
     * @param name the name
     */
    public Plant(int userID, String name) {
        this(userID, "", name, "", "", "", "", "", "", "");
    }


    public List<PlantProperty> getProperties() {
        List<PlantProperty> properties = new ArrayList<>();
        properties.add(new PlantProperty("Water Depth", waterDepth));
        properties.add(new PlantProperty("Water Volume", waterVolume));
        properties.add(new PlantProperty("Water Amount", waterAmount));
        properties.add(new PlantProperty("Sunlight", sunlight));
        properties.add(new PlantProperty("Care Level", careLevel));
        properties.add(new PlantProperty("Harvest Season", harvestSeason));
        return properties;
    }

    /**
     * Returns a string representation of the Plant record.
     *
     * @return a string representation of the Plant record
     */
    @Override
    public String toString() {
        return "Plant{" +
                "userid=" + userID +
                ", plantID=" + plantID +
                '}';
    }
}
