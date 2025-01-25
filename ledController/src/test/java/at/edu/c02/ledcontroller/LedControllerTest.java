package at.edu.c02.ledcontroller;

import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.experimental.runners.Enclosed;
import org.junit.runner.RunWith;

import java.io.IOException;

import static org.mockito.Mockito.*;


@RunWith(Enclosed.class)
public class LedControllerTest {
    /**
     * This test is just here to check if tests get executed. Feel free to delete it when adding your own tests.
     * Take a look at the stack calculator tests again if you are unsure where to start.
     */
    @Test
    public void dummyTest() {
        assertEquals(1, 1);
    }



        @Test
        public void testRunEffect() throws IOException, InterruptedException {
            ApiService apiService = mock(ApiService.class);
            LedControllerImpl controller = new LedControllerImpl(apiService);

            // Mock response
            JSONArray lights = new JSONArray();
            lights.put(new JSONObject().put("id", 1).put("color", "off"));
            lights.put(new JSONObject().put("id", 2).put("color", "off"));
            when(apiService.getLights()).thenReturn(new JSONObject().put("lights", lights));

            // Run the method
            controller.runEffect("red", 2);

            // Verify LEDs are turned off initially
            verify(apiService).setLightColor(1, "off");
            verify(apiService).setLightColor(2, "off");

            // Verify LEDs are cycled through
            verify(apiService, times(2)).setLightColor(1, "red");
            verify(apiService, times(2)).setLightColor(2, "red");
            verify(apiService, times(4)).setLightColor(anyInt(), eq("off"));
        }
    }