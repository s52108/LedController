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
    public void demo() throws IOException, ApiServiceImpl {
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

	public JSONObject GetLights() throws ApiServiceImpl, IOException {
		return apiService.getLights();
	}
    public List<JSONObject> getGroupLeds() throws IOException, ApiServiceImpl {
        // Call `getLights` to get the status of all lights
        JSONObject response = apiService.getLights();
        JSONArray lights = response.getJSONArray("lights");

        List<JSONObject> groupLights = new ArrayList<>();
        for (int i = 0; i < lights.length(); i++) {
            groupLights.add(lights.getJSONObject(i));
        }
        return groupLights;
    }

}
