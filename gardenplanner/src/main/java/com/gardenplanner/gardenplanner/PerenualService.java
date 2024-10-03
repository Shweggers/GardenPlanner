package com.gardenplanner.gardenplanner;

import java.io.IOException;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

public class PerenualService {
    private static final String API_KEY = "sk-ZtM366ec7a8690cc16915";
    private static final String BASE_URL = "https://perenual.com/api/";

    /**
     * Get plant data from the Perenual API
     *
     * @param plantID the ID of the plant
     * @return the plant data
     * @throws IOException
     * @throws InterruptedException
     */
    public JsonObject getPlantData(String plantID) throws IOException, InterruptedException {
        String url = BASE_URL + "species/details/" + plantID + "?key=" + API_KEY;
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .GET()
                .build();

        HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
        return new Gson().fromJson(response.body(), JsonObject.class);
    }

    /**
     * Get the ID of a plant from its name
     *
     * @param plantName the name of the plant
     * @return the ID of the plant
     * @throws IOException
     * @throws InterruptedException
     */
    public String getPlantIdFromName(String plantName) throws IOException, InterruptedException {
        String url = BASE_URL + "species-list" + "?key=" + API_KEY + "&q=" + URLEncoder.encode(plantName, "UTF-8");

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .GET()
                .build();

        HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
        JsonObject result = new Gson().fromJson(response.body(), JsonObject.class);
        System.out.println(result.get("data").toString());
        return result.getAsJsonArray("data").get(0).getAsJsonObject().get("id").getAsString();
    }
}
