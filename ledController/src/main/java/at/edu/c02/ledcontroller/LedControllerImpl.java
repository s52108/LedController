package at.edu.c02.ledcontroller;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * This class handles the actual logic
 */
public class LedControllerImpl implements LedController {
    private final ApiService apiService;

    public LedControllerImpl(ApiService apiService)
    {
        this.apiService = apiService;
    }

    @Override
    public void demo() throws IOException
    {
        // Call `getLights`, the response is a json object in the form `{ "lights": [ { ... }, { ... } ] }`
        JSONObject response = apiService.getLights();
        // get the "lights" array from the response
        JSONArray lights = response.getJSONArray("lights");
        // read the first json object of the lights array
        JSONObject firstLight = lights.getJSONObject(0);
        // read int and string properties of the light
        System.out.println("First light id is: " + firstLight.getInt("id"));
        System.out.println("First light color is: " + firstLight.getString("color"));
    }

    /**
     * This method queries the status of all lights in the group and returns their details.
     *
     * @return List of JSON objects representing the status of each light.
     * @throws IOException if the request to the API fails.
     */
    public List<JSONObject> getGroupLeds() throws IOException {
        // Call `getLights` to get the status of all lights
        JSONObject response = apiService.getLights();
        JSONArray lights = response.getJSONArray("lights");

        List<JSONObject> groupLights = new ArrayList<>();
        for (int i = 0; i < lights.length(); i++) {
            groupLights.add(lights.getJSONObject(i));
        }
        return groupLights;
    }

    /**
     * Turns off all LEDs in the group using their original colors.
     *
     * @throws IOException if the request to the API fails.
     */
    public void turnOffAllLeds() throws IOException {
        turnOffAllLeds(null);
    }

    /**
     * Turns off all LEDs in the group, optionally using a specific color.
     *
     * @param overrideColor Optional color to use when turning off LEDs (null for original color).
     * @throws IOException if the request to the API fails.
     */
    public void turnOffAllLeds(String overrideColor) throws IOException {
        List<JSONObject> groupLeds = getGroupLeds();
        for (JSONObject led : groupLeds) {
            int id = led.getInt("id");
            String color = (overrideColor != null) ? overrideColor : led.getString("color");
            apiService.setLight(id, color, false);
        }
    }

    /**
     * Implements a spinning LED effect.
     *
     * @param color The color to set the LEDs to during the effect.
     * @param turns The number of complete rotations.
     * @throws IOException if the request to the API fails.
     * @throws InterruptedException if the thread sleep is interrupted.
     */
    public void spinningLed(String color, int turns) throws IOException, InterruptedException {
        List<JSONObject> groupLeds = getGroupLeds();

        for (int i = 0; i < turns * groupLeds.size(); i++) {
            // Turn off all LEDs using the effect color
            turnOffAllLeds(color);

            // Turn on the current LED
            int currentLedIndex = i % groupLeds.size();
            int id = groupLeds.get(currentLedIndex).getInt("id");
            apiService.setLight(id, color, true);

            // Wait 1 second before the next step
            Thread.sleep(1000);
        }

        // Turn off all LEDs at the end
        turnOffAllLeds(color);
    }

    /**
     * Sets the color and state of a specific LED.
     *
     * @param id The ID of the LED.
     * @param color The color to set.
     * @param state Whether the LED should be turned on or off.
     * @throws IOException if the request to the API fails.
     */
    public void setLed(int id, String color, boolean state) throws IOException {
        apiService.setLight(id, color, state);
    }
}
