package com.gardenplanner.gardenplanner.model;

import com.google.gson.JsonObject;

import java.io.IOException;
import java.util.Dictionary;
import java.util.Hashtable;
import java.util.Objects;

public class PerenualItem {
    private PerenualService perenualService;
    private String id;
    private String commonName;

    private Dictionary<String, String> itemData = new Hashtable<>();
    private String depthWaterRequirement;
    private String wateringGeneralBenchmark;
    private String volumeWaterRequirement;
    private String sunRequirement;
    private String harvestSeason;
    private String imageURL;

    public PerenualItem(String id, String commonName) {
        this.perenualService = new PerenualService();
        this.id = id;
        this.commonName = commonName;
    }

    public Dictionary<String, String> getItemData() {
        if (itemData.isEmpty()) {
            try {
                JsonObject itemData = perenualService.getPlantDataFromID(id);
                SetItemData(itemData);
            } catch (IOException | InterruptedException e) {
                e.printStackTrace();
            }

            itemData.put("depthWaterRequirement", depthWaterRequirement);
            itemData.put("wateringGeneralBenchmark", wateringGeneralBenchmark);
            itemData.put("volumeWaterRequirement", volumeWaterRequirement);
            itemData.put("sunRequirement", sunRequirement);
            itemData.put("harvestSeason", harvestSeason);
            itemData.put("imageURL", imageURL);
        }
        return itemData;
    }

    public String getCommonName() {
        return commonName;
    }

    @Override
    public String toString() {
        return "{id: " + id + ", " +
                "commonName: " + commonName + ", " +
                "depthWaterRequirement: " + depthWaterRequirement + ", " +
                "wateringGeneralBenchmark: " + wateringGeneralBenchmark + ", " +
                "volumeWaterRequirement: " + volumeWaterRequirement + ", " +
                "sunRequirement: " + sunRequirement + ", " +
                "harvestSeason: " + harvestSeason + ", " +
                "imageURL: " + imageURL + "}";
    }

    private String validateObject(JsonObject itemData, String field) {
        if (itemData.get(field).isJsonNull() || Objects.equals(itemData.get(field).toString(), "[]")) {
            return "n/a";
        }
        if (itemData.getAsJsonObject(field).get("value").isJsonNull()) {
            return itemData.getAsJsonObject(field).get("unit").getAsString();
        }
        return itemData.getAsJsonObject(field).get("value").getAsString() + " " + itemData.getAsJsonObject(field).get("unit").getAsString();
    }

    private String validateElement(JsonObject itemData, String field) {
        if (itemData.get(field).isJsonNull()) {
            return "n/a";
        }
        if (itemData.get(field).isJsonObject()) {
            return itemData.getAsJsonObject(field).get("thumbnail").getAsString();
        }
        return itemData.get(field).getAsString();
    }

    private void SetItemData(JsonObject itemData) {
        this.depthWaterRequirement = validateObject(itemData, "depth_water_requirement");
        this.wateringGeneralBenchmark = validateObject(itemData, "watering_general_benchmark");
        this.volumeWaterRequirement = validateObject(itemData, "volume_water_requirement");
        this.sunRequirement = itemData.getAsJsonArray("sunlight").get(0).getAsString();
        this.harvestSeason = validateElement(itemData, "harvest_season");
        this.imageURL = validateElement(itemData, "default_image");
    }
}
