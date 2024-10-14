import com.gardenplanner.gardenplanner.model.PerenualCollection;
import com.gardenplanner.gardenplanner.model.PerenualService;
import com.google.gson.JsonObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

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
    public void testGetPlantDataFromID() throws IOException, InterruptedException {
        String plantID = "1";
        String jsonResponse = "{\"id\": 1, \"common_name\": \"European Silver Fir\"}";

        when(httpResponseMock.body()).thenReturn(jsonResponse);
        when(httpClientMock.send(any(HttpRequest.class), any(HttpResponse.BodyHandler.class)))
                .thenReturn(httpResponseMock);

        JsonObject result = perenualService.getPlantDataFromID(plantID);

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

    /**
     * Test the getPlantNames method
     * @throws IOException
     * @throws InterruptedException
     */
    @Test
    public void testGetPlantName() throws IOException, InterruptedException {
        String query = "t";
        String jsonResponse = "[White Fir, Candicans White Fir, Fraser Fir, Alpine Fir, Noble Fir, Snakebark Maple, Cutleaf Fullmoon Maple, Attaryi Fullmoon Maple*, Emmett's Pumpkin Fullmoon Maple, Flamingo Boxelder, Kelly's Gold Boxelder, Japanese Maple, Aka Shigitatsu Sawa Japanese Maple, Alpenweiss Variegated Dwarf Japanese Maple*, Ao Shime No Uchi Japanese Maple, Aoyagi Japanese Maple*, Arakawa Cork Bark Japanese Maple, Asahi Zuru Japanese Maple, Ribbon-leaf Japanese Maple*, Purple-Leaf Japanese Maple, Aureum Japanese Maple*, Autumn Fire Japanese Maple, Azuma Murasaki Japanese Maple, Beni Kawa Coral Bark Japanese Maple*, Beni Otake Japanese Maple, Beni Schichihenge Japanese Maple, Beni Tsukasa Japanese Maple*, Bloodgood Japanese Maple, Bonfire Japanese Maple, Brandt's Dwarf Japanese Maple]";

        when(httpResponseMock.body()).thenReturn(jsonResponse);
        when(httpClientMock.send(any(HttpRequest.class), any(HttpResponse.BodyHandler.class)))
                .thenReturn(httpResponseMock);

        List<String> output = perenualService.getPlantNames(query);

        assertEquals(jsonResponse, output.toString());
    }

    @Test
    public void debugTest() throws IOException, InterruptedException {
        String query = "banana";

        PerenualCollection output = perenualService.debugQuery(query);
        System.out.println(output.getItemNames());
    }
}