package at.edu.c02.ledcontroller;

import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.mockito.Mockito.*;

/**
 * Test class for LedControllerImpl.
 */
public class TestLedControllerImpl {

    private ApiService apiService;
    private LedControllerImpl ledController;

    @BeforeEach
    public void setUp() {
        // Mock the ApiService
        apiService = mock(ApiService.class);
        // Create the LedControllerImpl with the mocked ApiService
        ledController = new LedControllerImpl(apiService);
    }

    @Test
    public void testTurnOffAllLeds() throws IOException {
        // Prepare a mocked response for `getLights`
        JSONObject mockResponse = new JSONObject();
        mockResponse.put("lights", new org.json.JSONArray()
                .put(new JSONObject().put("id", 1).put("color", "red").put("on", true))
                .put(new JSONObject().put("id", 2).put("color", "blue").put("on", true)));

        // Configure the mock to return the prepared response
        when(apiService.getLights()).thenReturn(mockResponse);

        // Call the turnOffAllLeds method
        ledController.turnOffAllLeds();

        // Verify that setLight was called to turn off each LED
        verify(apiService).setLight(1, "red", false);
        verify(apiService).setLight(2, "blue", false);
        verify(apiService, times(2)).setLight(anyInt(), anyString(), eq(false));
    }

    @Test
    public void testSpinningLed() throws IOException, InterruptedException {
        // Prepare a mocked response for `getLights`
        JSONObject mockResponse = new JSONObject();
        mockResponse.put("lights", new org.json.JSONArray()
                .put(new JSONObject().put("id", 1).put("color", "red").put("on", false))
                .put(new JSONObject().put("id", 2).put("color", "blue").put("on", false)));

        // Configure the mock to return the prepared response
        when(apiService.getLights()).thenReturn(mockResponse);

        // Call the spinningLed method
        ledController.spinningLed("green", 1);

        // Verify the sequence of calls to setLight
        verify(apiService).setLight(1, "green", true);
        verify(apiService).setLight(1, "green", false);
        verify(apiService).setLight(2, "green", true);
        verify(apiService).setLight(2, "green", false);
        verify(apiService, times(2)).setLight(anyInt(), eq("green"), eq(true));
        verify(apiService, times(2)).setLight(anyInt(), eq("green"), eq(false));
    }

    @Test
    public void testSetLed() throws IOException {
        // Call the setLed method
        ledController.setLed(1, "yellow", true);

        // Verify that setLight was called with the correct parameters
        verify(apiService).setLight(1, "yellow", true);
    }
}
