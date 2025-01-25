package at.edu.c02.ledcontroller;

import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.*;

public class ApiServiceImplTest {

	//  @Test
	//  public void testSetAndGetLedStatus() throws IOException, ApiServiceImpl {
	//      ApiServiceImpl apiService = new ApiServiceImpl();

	//      // Set LED color and state
	//      String ledId = "LED1";
	//      String color = "red";
	//      boolean state = true;
	//JSONObject setResponse = apiService.setLed(ledId, color, state);

	//assertNotNull(setResponse);
	//assertTrue(setResponse.has("success"));
	//assertTrue(setResponse.getBoolean("success"));

	// Get LED status
	//JSONObject getLightsResponse = apiService.getLights();
	//assertNotNull(getLightsResponse);

	//JSONArray leds = getLightsResponse.getJSONArray("leds");
//       boolean found = false;

//       for (int i = 0; i < leds.length(); i++) {
//           JSONObject led = leds.getJSONObject(i);
//           if (led.getString("id").equals(ledId)) {
//               assertEquals(color, led.getString("color"));
//               assertEquals(state, led.getBoolean("state"));
//               found = true;
//               break;
//           }
}

//assertTrue(found, "LED with ID " + ledId + " was not found in the response.");
//    }
//		}
