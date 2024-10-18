package com.gardenplanner.gardenplanner.model;

import com.google.gson.JsonObject;

import javax.naming.LimitExceededException;
import java.io.IOException;
import java.util.Dictionary;
import java.util.Hashtable;

public class PerenualItem {
    private final String id;
    private final String name;
    private final Dictionary<String, String> itemData = new Hashtable<>();

    /**
     * Create a new PerenualItem object.
     *
     * @param id         the ID of the item
     * @param name the common name of the item
     */
    public PerenualItem(String id, String name) {
        this.id = id;
        this.name = name;
    }

    /**
     * Get the item data.
     *
     * @return the item data or create it if it does not exist
     */
    public Dictionary<String, String> getItemData() throws LimitExceededException {
        if (itemData.isEmpty()) {
            try {
                JsonObject itemDataJson = PerenualService.getInstance().getPlantDataFromID(id);
                SetItemData(itemDataJson);
            }
            catch (IOException | InterruptedException e) {
                e.printStackTrace();
            }
        }
        return itemData;
    }

    /**
     * Get the ID of the item.
     *
     * @return the ID of the item
     */
    public String getID() {
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
        this.itemData.put("waterDepth", validateField(itemData, "depth_water_requirement"));
        this.itemData.put("waterVolume", validateField(itemData, "volume_water_requirement"));
        this.itemData.put("waterAmount", validateField(itemData, "watering_general_benchmark"));
        this.itemData.put("sunlight", validateField(itemData, "sunlight"));
        this.itemData.put("careLevel",  validateField(itemData, "care_level"));
        this.itemData.put("harvestSeason", validateField(itemData, "harvest_season"));
        this.itemData.put("imageURL", validateField(itemData, "default_image"));
    }
}
