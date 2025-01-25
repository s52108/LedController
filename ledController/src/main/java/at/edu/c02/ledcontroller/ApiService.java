package at.edu.c02.ledcontroller;

import org.json.JSONObject;

import java.io.IOException;

/**
 * Interface for API communication.
 */
public interface ApiService {

    /**
     * Fetches the status of all LEDs.
     *
     * @return JSONObject containing the status of all LEDs.
     * @throws IOException if the request fails.
     */
    JSONObject getLights() throws IOException;

    /**
     * Fetches the status of a specific LED.
     *
     * @param id The ID of the LED.
     * @return JSONObject containing the status of the LED.
     * @throws IOException if the request fails.
     */
    JSONObject getLight(String id) throws IOException;

    /**
     * Sets the color and state of a specific LED.
     *
     * @param id The ID of the LED.
     * @param color The color to set.
     * @param state The state to set (true for on, false for off).
     * @throws IOException if the request fails.
     */
    void setLight(int id, String color, boolean state) throws IOException;

    /**
     * Deletes a specific LED.
     *
     * @param id The ID of the LED.
     * @throws IOException if the request fails.
     */
    void deleteLight(int id) throws IOException;
}