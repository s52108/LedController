package at.edu.c02.ledcontroller;

import org.json.JSONObject;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mockito;

import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;


public class GetLightsTest {

	private ApiService apiServiceMock;
	private LedControllerImpl ledController;

	@BeforeEach
	void setUp() {
		// Mock the ApiService
		apiServiceMock = Mockito.mock(ApiService.class);
		// Create an instance of LedControllerImpl with the mocked ApiService
		ledController = new LedControllerImpl(apiServiceMock);
	}

    /**
     * This test is just here to check if tests get executed. Feel free to delete it when adding your own tests.
     * Take a look at the stack calculator tests again if you are unsure where to start.
     */
    @Test
    public void getLights() throws ApiServiceImpl, IOException {
		ApiService apiService = new ApiServiceImpl();
		JSONObject jsonObject = apiService.getLights();
		assertNotNull(jsonObject, "Das JSON-Objekt ist null!");
    }

	@Test
	public void getLight() throws ApiServiceImpl, IOException {
		ApiService apiService = new ApiServiceImpl();
		JSONObject jsonObject = apiService.getLight("1");
		assertNotNull(jsonObject, "Das JSON-Objekt ist null!");
	}
}
