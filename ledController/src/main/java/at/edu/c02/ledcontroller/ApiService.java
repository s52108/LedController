package at.edu.c02.ledcontroller;

import org.json.JSONObject;

import java.io.IOException;

public interface ApiService {
    JSONObject getLights() throws IOException, ApiServiceImpl;
    JSONObject getLight(String id) throws IOException, ApiServiceImpl;
}
