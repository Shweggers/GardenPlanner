package com.gardenplanner.gardenplanner.model;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonSyntaxException;

import java.io.IOException;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;

/**
 * PerenualService is a class that interacts with the Perenual API.
 */
public class PerenualService {

    private static final String API_KEY = "sk-ZtM366ec7a8690cc16915";
    private static final String BASE_URL = "https://perenual.com/api/";
    private static PerenualService instance;
    private final int page = 1;

    public static PerenualService getInstance() {
        if (instance == null) {
            instance = new PerenualService();
        }
        return instance;
    }

    /**
     * Get plant data from the Perenual API
     *
     * @param plantID the ID of the plant
     * @return the plant data
     * @throws IOException          if an I/O error occurs
     * @throws InterruptedException if the operation is interrupted
     */
    public JsonObject getPlantDataFromID(String plantID) throws IOException, InterruptedException {
        String url = BASE_URL + "species/details/" + plantID + "?key=" + API_KEY;

        try {
            JsonObject result = getResponse(url);

            return result;
        } catch (NullPointerException e) {
            return null;
        }
    }

    /**
     * Get the ID of a plant from its name
     *
     * @param plantName the name of the plant
     * @return the ID of the plant
     * @throws IOException          if an I/O error occurs
     * @throws InterruptedException if the operation is interrupted
     */
    public String getPlantIdFromName(String plantName) throws IOException, InterruptedException {
        String url = BASE_URL + "species-list" + "?key=" + API_KEY + "&q=" + URLEncoder.encode(plantName, StandardCharsets.UTF_8);

        try {
            JsonObject result = getResponse(url);

            JsonArray plantData = result.getAsJsonArray("data");
            return plantData.get(0).getAsJsonObject().get("id").getAsString();
        } catch (NullPointerException e) {
            return null;
        }
    }

    public PerenualCollection getPlantNames(String query, int page) throws IOException, InterruptedException {
        String url = BASE_URL + "species-list" + "?key=" + API_KEY + "&q=" + URLEncoder.encode(query, StandardCharsets.UTF_8) + "&page=" + page;

        try {
            JsonObject result = getResponse(url);

            JsonArray data = result.getAsJsonArray("data");

            PerenualCollection output = new PerenualCollection();
            output.pages = result.get("last_page").getAsInt();

            data.forEach(plant -> {
                String plantID = plant.getAsJsonObject().get("id").getAsString();
                String plantName = plant.getAsJsonObject().get("common_name").getAsString();

                if (!plant.getAsJsonObject().getAsJsonArray("scientific_name").isEmpty()) {
                    plantName += " (" + plant.getAsJsonObject().getAsJsonArray("scientific_name").get(0).getAsString() + ")";
                }
                PerenualItem perenualItem = new PerenualItem(plantID, plantName);

                output.add(perenualItem);
            });
            return output;

        } catch (NullPointerException e) {
            return null;
        }
    }

    public PerenualCollection debugQuery(String query, int page) throws IOException, InterruptedException {
        String url = BASE_URL + "species-list" + "?key=" + API_KEY + "&q=" + URLEncoder.encode(query, StandardCharsets.UTF_8) + "&page=" + page;

        try {
            JsonObject result = getResponse(url);

            JsonArray data = result.getAsJsonArray("data");

            PerenualCollection output = new PerenualCollection();
            data.forEach(plant -> {
                String plantID = plant.getAsJsonObject().get("id").getAsString();
                String plantName = plant.getAsJsonObject().get("common_name").getAsString();

                if (!plant.getAsJsonObject().getAsJsonArray("scientific_name").isEmpty()) {
                    plantName += "\n(" + plant.getAsJsonObject().getAsJsonArray("scientific_name").get(0).getAsString() + ")";
                }
                PerenualItem perenualItem = new PerenualItem(plantID, plantName);

                output.add(perenualItem);
            });
            return output;

        } catch (NullPointerException e) {
            return null;
        }
    }

    private JsonObject getResponse(String url) throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .GET()
                .build();

        HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());

        try {
            return new Gson().fromJson(response.body(), JsonObject.class);
        } catch (JsonSyntaxException e) {
            throw new NullPointerException();
        }
    }
}
