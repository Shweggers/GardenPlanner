import com.gardenplanner.gardenplanner.model.PerenualService;
import com.google.gson.JsonObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * A class to test the PerenualService class
 */
public class PerenualServiceTest {

    /**
     * The PerenualService object to test
     */
    private PerenualService perenualService;

    /**
     * The HttpClient object to mock
     */
    private HttpClient httpClientMock;

    /**
     * The HttpResponse object to mock
     */
    private HttpResponse<String> httpResponseMock;

    /**
     * Set up the test
     */
    @BeforeEach
    public void setUp() {
        perenualService = new PerenualService();
        httpClientMock = mock(HttpClient.class);
        httpResponseMock = mock(HttpResponse.class);
    }

    /**
     * Test the getPlantData method
     * @throws IOException
     * @throws InterruptedException
     */
    @Test
    public void testGetPlantData() throws IOException, InterruptedException {
        String plantID = "1";
        String jsonResponse = "{\"id\": 1, \"common_name\": \"European Silver Fir\"}";

        when(httpResponseMock.body()).thenReturn(jsonResponse);
        when(httpClientMock.send(any(HttpRequest.class), any(HttpResponse.BodyHandler.class)))
                .thenReturn(httpResponseMock);

        JsonObject result = perenualService.getPlantData(plantID);

        assertEquals(1, result.get("id").getAsInt());
        assertEquals("European Silver Fir", result.get("common_name").getAsString());
    }

    /**
     * Test the getPlantIdFromName method
     * @throws IOException
     * @throws InterruptedException
     */
    @Test
    public void testGetPlantIdFromName() throws IOException, InterruptedException {
        String plantName = "Pyramidalis Silver Fir";
        String jsonResponse = "{\"id\": 2, \"common_name\": \"Pyramidalis Silver Fir\"}";

        when(httpResponseMock.body()).thenReturn(jsonResponse);
        when(httpClientMock.send(any(HttpRequest.class), any(HttpResponse.BodyHandler.class)))
                .thenReturn(httpResponseMock);

        String result = perenualService.getPlantIdFromName(plantName);

        assertEquals("2", result);
    }
}