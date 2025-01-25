package at.edu.c02.ledcontroller;

import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * Test class for LedControllerImpl.
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
    public void testDemoMethod() throws IOException {
        // Prepare a mocked response for `getLights`
        JSONObject mockResponse = new JSONObject();
        JSONArray lightsArray = new JSONArray();

        JSONObject light1 = new JSONObject();
        light1.put("id", 1);
        light1.put("color", "red");
        lightsArray.put(light1);

        mockResponse.put("lights", lightsArray);

        // Configure the mock to return the prepared response
        when(apiService.getLights()).thenReturn(mockResponse);

        // Call the demo method
        ledController.demo();

        // Verify that the correct methods were called
        verify(apiService, times(1)).getLights();

        // Optionally, assert system outputs (if required)
        // For simplicity, these outputs are printed. Capturing them requires additional setup.
    }

    @Test
    public void testGetLightStatus() throws IOException {
        // Prepare a mocked response for `getLight`
        JSONObject mockLightResponse = new JSONObject();
        mockLightResponse.put("id", 1);
        mockLightResponse.put("color", "blue");
        mockLightResponse.put("status", "ON");

        // Configure the mock to return the prepared response when called with ID 1
        when(apiService.getLight("1")).thenReturn(mockLightResponse);

        // Call the getLight method
        JSONObject lightStatus = apiService.getLight("1");

        // Assert the response
        assertEquals(1, lightStatus.getInt("id"));
        assertEquals("blue", lightStatus.getString("color"));
        assertEquals("ON", lightStatus.getString("status"));

        // Verify the interaction with the mock
        verify(apiService, times(1)).getLight("1");
    }
}