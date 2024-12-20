package com.gardenplanner.gardenplanner.model;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonSyntaxException;

import javax.naming.LimitExceededException;
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

    /**
     * Create a new PerenualService instance.
     *
     * @return the PerenualService instance
     */
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
     * @throws LimitExceededException if the limit is exceeded
     */
    public JsonObject getPlantDataFromID(String plantID) throws IOException, InterruptedException, LimitExceededException {
        String url = BASE_URL + "species/details/" + plantID + "?key=" + API_KEY;
        try {
            JsonObject result = getResponse(url);
            try {
                return result;
            } catch (NullPointerException e) {
                throw new LimitExceededException("API Error: API rate limit exceeded");
            }
        } catch (NullPointerException e) {
            throw new NullPointerException("Response Error: Bad response");
        }
    }

    /**
     * Get the ID of a plant from its name
     *
     * @param plantName the name of the plant
     * @return the ID of the plant
     * @throws IOException          if an I/O error occurs
     * @throws InterruptedException if the operation is interrupted
     * @throws LimitExceededException if the limit is exceeded
     */
    public String getPlantIdFromName(String plantName) throws IOException, InterruptedException, LimitExceededException {
        String url = BASE_URL + "species-list" + "?key=" + API_KEY + "&q=" + URLEncoder.encode(plantName, StandardCharsets.UTF_8);
        try {
            JsonObject result = getResponse(url);
            try {
                JsonArray plantData = result.getAsJsonArray("data");
                return plantData.get(0).getAsJsonObject().get("id").getAsString();
            } catch (NullPointerException e) {
                throw new LimitExceededException("API Error: API rate limit exceeded");
            }
        } catch (NullPointerException e) {
            throw new NullPointerException("Response Error: Bad response");
        }
    }

    /**
     * Get a list of plant names from the Perenual API
     *
     * @param query the query
     * @param page  the page
     * @return a list of plant names
     * @throws IOException          if an I/O error occurs
     * @throws InterruptedException if the operation is interrupted
     * @throws LimitExceededException if the limit is exceeded
     */
    public PerenualCollection getPlantNames(String query, int page) throws IOException, InterruptedException, LimitExceededException {
        String url = BASE_URL + "species-list" + "?key=" + API_KEY + "&q=" + URLEncoder.encode(query, StandardCharsets.UTF_8) + "&page=" + page;
        try {
            JsonObject result = getResponse(url);
            try {
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
                throw new LimitExceededException("API Error: API rate limit exceeded");
            }
        } catch (NullPointerException e) {
            throw new NullPointerException("Response Error: Bad response");
        }
    }

    /**
     * Get the response from a URL
     *
     * @param url the URL
     * @return the response
     * @throws IOException          if an I/O error occurs
     * @throws InterruptedException if the operation is interrupted
     */
    private JsonObject getResponse(String url) throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .GET()
                .build();
        HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());

        try {
            return new Gson().fromJson(response.body(), JsonObject.class);
        } catch (JsonSyntaxException e) {
            throw new NullPointerException("Bad response");
        }
    }
}
