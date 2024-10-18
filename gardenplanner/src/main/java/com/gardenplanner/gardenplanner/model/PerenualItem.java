package com.gardenplanner.gardenplanner.model;

import com.google.gson.JsonObject;

import java.io.IOException;
import java.util.Dictionary;
import java.util.Hashtable;

public class PerenualItem {
    private final PerenualService perenualService;
    private final String id;
    private final String name;

    private final Dictionary<String, String> itemData = new Hashtable<>();
    private String waterDepth;
    private String waterVolume;
    private String waterAmount;
    private String sunlight;
    private String careLevel;
    private String harvestSeason;
    private String imageURL;
    private JsonObject itemDataJson;

    /**
     * Create a new PerenualItem object.
     *
     * @param id         the ID of the item
     * @param name the common name of the item
     */
    public PerenualItem(String id, String name) {
        this.perenualService = new PerenualService();
        this.id = id;
        this.name = name;
    }

    /**
     * Get the item data.
     *
     * @return the item data or create it if it does not exist
     */
    public Dictionary<String, String> getItemData() {
        if (itemData.isEmpty()) {
            try {
                if (itemDataJson == null) {
                    itemDataJson = perenualService.getPlantDataFromID(id);
                }
                SetItemData(itemDataJson);
            } catch (IOException | InterruptedException e) {
                e.printStackTrace();
            }
            itemData.put("waterDepth", waterDepth);
            itemData.put("waterVolume", waterVolume);
            itemData.put("waterAmount", waterAmount);
            itemData.put("sunlight", sunlight);
            itemData.put("careLevel", careLevel);
            itemData.put("harvestSeason", harvestSeason);
            itemData.put("imageURL", imageURL);
        }
        return itemData;
    }

    /**
     * Get the ID of the item.
     *
     * @return the ID of the item
     */
    public String getId() {
        return id;
    }

    /**
     * Get the common name of the item.
     *
     * @return the common name of the item
     */
    public String getName() {
        return name;
    }

    /**
     * Get the item data JSON.
     *
     * @return the item data JSON
     */
    public JsonObject getItemDataJson() {
        return itemDataJson;
    }

    /**
     * Get the string representation of the item.
     *
     * @return the string representation of the item
     */
    @Override
    public String toString() {
        return "{id: " + id + ", " +
                "name: " + name + ", " +
                "waterDepth: " + waterDepth + ", " +
                "waterVolume: " + waterVolume + ", " +
                "waterAmount: " + waterAmount + ", " +
                "sunlight: " + sunlight + ", " +
                "careLevel: " + careLevel + ", " +
                "harvestSeason: " + harvestSeason + ", " +
                "imageURL: " + imageURL + "}";
    }

    private String validateField(JsonObject itemData, String field) {
        try {
            return itemData.get(field).getAsString();
        } catch (UnsupportedOperationException uoe) {
            try {
                return itemData.getAsJsonObject(field).get("value").getAsString() + " " + itemData.getAsJsonObject(field).get("unit").getAsString();
            } catch (NullPointerException npe) {
                try {
                    return itemData.getAsJsonObject(field).get("thumbnail").getAsString();
                } catch (NullPointerException npe2) {
                    return "n/a";
                }
            } catch (ClassCastException cce) {
                return "n/a";
            }
        } catch (IllegalStateException ise) {
            try {
                return itemData.getAsJsonArray(field).get(0).getAsString();
            } catch (IndexOutOfBoundsException ioobe) {
                return "n/a";
            }
        }
    }

    /**
     * Set the item data.
     *
     * @param itemData the item data
     */
    private void SetItemData(JsonObject itemData) {
        waterDepth =    validateField(itemData, "depth_water_requirement");
        waterVolume =   validateField(itemData, "volume_water_requirement");
        waterAmount =   validateField(itemData, "watering_general_benchmark");
        sunlight =      validateField(itemData, "sunlight");
        careLevel =     validateField(itemData, "care_level");
        harvestSeason = validateField(itemData, "harvest_season");
        imageURL =      validateField(itemData, "default_image");
    }
}
