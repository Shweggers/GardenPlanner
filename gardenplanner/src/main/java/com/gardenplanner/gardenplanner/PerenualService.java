package com.gardenplanner.gardenplanner;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

public class PerenualService {
    // sk-ZtM366ec7a8690cc16915
    private static final String API_KEY = "sk-ZtM366ec7a8690cc16915";
    private static final String BASE_URL = "https://perenual.com/api/v2/plants";

    public JsonObject getPlantData(String plantId) throws IOException, InterruptedException {
        String url = BASE_URL + "/" + plantId + "?key=" + API_KEY;
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .GET()
                .build();

        HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
        return new Gson().fromJson(response.body(), JsonObject.class);
    }
}
