package com.gardenplanner.gardenplanner;

import com.google.gson.JsonObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.IOException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class PerenualServiceTest {

    private PerenualService perenualService;
    private HttpClient httpClientMock;
    private HttpResponse<String> httpResponseMock;

    @BeforeEach
    public void setUp() {
        perenualService = new PerenualService();
        httpClientMock = mock(HttpClient.class);
        httpResponseMock = mock(HttpResponse.class);
    }

    @Test
    public void testGetPlantData() throws IOException, InterruptedException {
        String plantId = "123";
        String jsonResponse = "{\"id\": 123, \"name\": \"Rose\"}";

        when(httpResponseMock.body()).thenReturn(jsonResponse);
        when(httpClientMock.send(any(HttpRequest.class), any(HttpResponse.BodyHandler.class)))
                .thenReturn(httpResponseMock);

        JsonObject result = perenualService.getPlantData(plantId);

        assertEquals(123, result.get("id").getAsInt());
        assertEquals("Rose", result.get("name").getAsString());
    }
}