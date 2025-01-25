package at.edu.c02.ledcontroller;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * This class should handle all HTTP communication with the server.
 * Each method here should correspond to an API call, accept the correct parameters and return the response.
 * Do not implement any other logic here - the ApiService will be mocked to unit test the logic without needing a server.
 */
public class ApiServiceImpl extends Throwable implements ApiService {
    /**
     * This method calls the `GET /getLights` endpoint and returns the response.
     * TODO: When adding additional API calls, refactor this method. Extract/Create at least one private method that
     * handles the API call + JSON conversion (so that you do not have duplicate code across multiple API calls)
     *
     * @return `getLights` response JSON object
     * @throws IOException Throws if the request could not be completed successfully
     */
    private static final String BASE_URL = "https://balanced-civet-91.hasura.app/api/rest";

    @Override
    public JSONObject getLights() throws IOException, ApiServiceImpl {
        return sendRequest(getHttpURLConnection("/getLights", "GET", null), null);
    }

	public JSONObject getLight(String id) throws IOException, ApiServiceImpl {
		return sendRequest(getHttpURLConnection("/lights", "GET", id), null);
	}

    public JSONObject setLed(String ledId, String color, boolean state) throws IOException, ApiServiceImpl {
        JSONObject requestBody = new JSONObject();
        requestBody.put("ledId", ledId);
        requestBody.put("color", color);
        requestBody.put("state", state);

        return sendRequest(getHttpURLConnection("/setLed", "POST", null), requestBody);
    }

	private static String GetUrl(String endpoint, String method, String postfix) throws ApiServiceImpl {
		if(method.equals("GET")) {
			if(postfix == null)
				return BASE_URL + endpoint;
			else
				return BASE_URL + endpoint + "/" + postfix;

		}
		if(method.equals("POST"))
			return BASE_URL + endpoint;

		System.out.println("Method "+method+" is not supported!");
		throw new ApiServiceImpl();
	}

	private static HttpURLConnection getHttpURLConnection(String endpoint, String method, String postfix) throws IOException, ApiServiceImpl {
		URL url;
		url = new URL(GetUrl(endpoint,method, postfix));
		HttpURLConnection connection = (HttpURLConnection) url.openConnection();
		connection.setRequestMethod(method);
		connection.setRequestProperty("Content-Type", "application/json");
		connection.setRequestProperty("X-Hasura-Group-ID", "rot");
		return connection;
	}

    private JSONObject sendRequest(HttpURLConnection connection, JSONObject requestBody) throws IOException {

		if (requestBody != null) {
            connection.setDoOutput(true);
            try (OutputStream os = connection.getOutputStream()) {
                os.write(requestBody.toString().getBytes());
                os.flush();
            }
        }

        int responseCode = connection.getResponseCode();
        if (responseCode != HttpURLConnection.HTTP_OK) {
            throw new IOException("Error: Request to " + connection.getURL() + " failed with response code " + responseCode);
        }

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
            StringBuilder sb = new StringBuilder();
            int character;
            while ((character = reader.read()) != -1) {
                sb.append((char) character);
            }
            return new JSONObject(sb.toString());
        }
    }


}
