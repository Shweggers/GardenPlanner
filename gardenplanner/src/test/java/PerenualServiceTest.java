import com.gardenplanner.gardenplanner.model.PerenualCollection;
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
        String jsonResponse = "[White Fir (Abies concolor), Candicans White Fir (Abies concolor 'Candicans'), Fraser Fir (Abies fraseri), Alpine Fir (Abies lasiocarpa), Noble Fir (Abies procera), Snakebark Maple (Acer davidii), Cutleaf Fullmoon Maple (Acer japonicum 'Aconitifolium'), Attaryi Fullmoon Maple* (Acer japonicum 'Attaryi'), Emmett's Pumpkin Fullmoon Maple (Acer japonicum 'Emmett's Pumpkin'), Flamingo Boxelder (Acer negundo 'Flamingo'), Kelly's Gold Boxelder (Acer negundo 'Kelly's Gold'), Japanese Maple (Acer palmatum), Aka Shigitatsu Sawa Japanese Maple (Acer palmatum 'Aka Shigitatsu Sawa'), Alpenweiss Variegated Dwarf Japanese Maple* (Acer palmatum 'Alpenweiss'), Ao Shime No Uchi Japanese Maple (Acer palmatum 'Ao Shime No Uchi'), Aoyagi Japanese Maple* (Acer palmatum 'Aoyagi'), Arakawa Cork Bark Japanese Maple (Acer palmatum 'Arakawa'), Asahi Zuru Japanese Maple (Acer palmatum 'Asahi Zuru'), Ribbon-leaf Japanese Maple* (Acer palmatum 'Atrolineare'), Purple-Leaf Japanese Maple (Acer palmatum 'Atropurpureum'), Aureum Japanese Maple* (Acer palmatum 'Aureum'), Autumn Fire Japanese Maple (Acer palmatum 'Autumn Fire'), Azuma Murasaki Japanese Maple (Acer palmatum 'Azuma Murasaki'), Beni Kawa Coral Bark Japanese Maple* (Acer palmatum 'Beni Kawa'), Beni Otake Japanese Maple (Acer palmatum 'Beni Otake'), Beni Schichihenge Japanese Maple (Acer palmatum 'Beni Schichihenge'), Beni Tsukasa Japanese Maple* (Acer palmatum 'Beni Tsukasa'), Bloodgood Japanese Maple (Acer palmatum 'Bloodgood'), Bonfire Japanese Maple (Acer palmatum 'Bonfire'), Brandt's Dwarf Japanese Maple (Acer palmatum 'Brandt's Dwarf')]";

        when(httpResponseMock.body()).thenReturn(jsonResponse);
        when(httpClientMock.send(any(HttpRequest.class), any(HttpResponse.BodyHandler.class)))
                .thenReturn(httpResponseMock);

        PerenualCollection output = perenualService.getPlantNames(query, 1);

        assertEquals(jsonResponse, output.getItemNames().toString());
    }

    @Test
    public void debugTest() throws IOException, InterruptedException {
        String query = "banana";

        PerenualCollection output = perenualService.debugQuery(query, 1);
        System.out.println(output.getItemNames());
    }
}