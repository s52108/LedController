package at.edu.c02.ledcontroller;

import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.IOException;

import static org.mockito.Mockito.*;

    class LedControllerImplTest {

        private ApiService apiServiceMock;
        private LedControllerImpl ledController;

        @BeforeEach
        void setUp() {
            // Mock the ApiService
            apiServiceMock = Mockito.mock(ApiService.class);
            // Create an instance of LedControllerImpl with the mocked ApiService
            ledController = new LedControllerImpl(apiServiceMock);
        }

        @Test
        void testDemo() throws IOException, ApiServiceImpl {
            // Arrange: Set up the mocked API response
            JSONObject mockResponse = new JSONObject();
            JSONArray lightsArray = new JSONArray();
            JSONObject firstLight = new JSONObject();
            firstLight.put("id", 1);
            firstLight.put("color", "red");
            lightsArray.put(firstLight);
            mockResponse.put("lights", lightsArray);

            // Define the behavior of the mocked ApiService
            when(apiServiceMock.getLights()).thenReturn(mockResponse);

            // Act: Call the demo() method
            ledController.demo();

            // Assert: Verify the mocked ApiService was called
            verify(apiServiceMock, times(1)).getLights();

            // Note: We use System.out.println in the demo() method, so there is no return value to assert.
            // However, we verify that the mocked ApiService behaves as expected.
        }
    }


