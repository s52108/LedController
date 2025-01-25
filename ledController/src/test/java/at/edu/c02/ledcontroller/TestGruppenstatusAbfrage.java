package at.edu.c02.ledcontroller;

import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * Test class for group status query functionality in LedControllerImpl.
 */
public class TestGruppenstatusAbfrage {

    private ApiService apiService;
    private LedController ledController;

    @BeforeEach
    public void setUp() {
        // Mock the ApiService
        apiService = mock(ApiService.class);
        // Create the LedControllerImpl with the mocked ApiService
        ledController = new LedControllerImpl(apiService);
    }

    @Test
    public void testGetGroupLeds() throws IOException, ApiServiceImpl {
        // Prepare a mocked response for `getLights`
        JSONObject mockResponse = new JSONObject();
        JSONArray lightsArray = new JSONArray();

        JSONObject light1 = new JSONObject();
        light1.put("id", 1);
        light1.put("color", "red");
        light1.put("on", true);
        light1.put("groupByGroup", new JSONObject().put("name", "A"));

        JSONObject light2 = new JSONObject();
        light2.put("id", 2);
        light2.put("color", "green");
        light2.put("on", false);
        light2.put("groupByGroup", new JSONObject().put("name", "A"));

        lightsArray.put(light1);
        lightsArray.put(light2);

        mockResponse.put("lights", lightsArray);

        // Configure the mock to return the prepared response
        when(apiService.getLights()).thenReturn(mockResponse);

        // Call the getGroupLeds method
        List<JSONObject> groupLeds = ((LedControllerImpl) ledController).getGroupLeds();

        // Assert the response
        assertEquals(2, groupLeds.size());
        assertEquals(1, groupLeds.get(0).getInt("id"));
        assertEquals("red", groupLeds.get(0).getString("color"));
        assertEquals(true, groupLeds.get(0).getBoolean("on"));
        assertEquals("A", groupLeds.get(0).getJSONObject("groupByGroup").getString("name"));

        assertEquals(2, groupLeds.get(1).getInt("id"));
        assertEquals("green", groupLeds.get(1).getString("color"));
        assertEquals(false, groupLeds.get(1).getBoolean("on"));
        assertEquals("A", groupLeds.get(1).getJSONObject("groupByGroup").getString("name"));

        // Verify the interaction with the mock
        verify(apiService, times(1)).getLights();
    }
}
