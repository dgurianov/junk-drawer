package gud.fun.junkdrawer.integration.city;


import gud.fun.junkdrawer.configuration.Endpoints;
import gud.fun.junkdrawer.persistance.model.City;
import gud.fun.junkdrawer.persistance.repository.CityRepository;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ITCity {
    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private CityRepository cRepo;

    @DisplayName("Get all cities")
    @Test
    public void test_01() throws JSONException {
        City c = new City();
        c.setName("Paris");
        c.setCountryCode("FRA");
        cRepo.save(c);
        ResponseEntity<String> response = restTemplate.getForEntity("http://localhost:" + port + Endpoints.CITY, String.class);
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        JSONObject jo = (JSONObject) (new JSONArray(response.getBody())).get(0);
        Assertions.assertEquals("Paris", jo.get("name"));
    }
}

