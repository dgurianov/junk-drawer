package gud.fun.junkdrawer.integration.city;

import gud.fun.junkdrawer.configuration.Endpoints;
import gud.fun.junkdrawer.dto.transaction.TransactionResponseDto;
import gud.fun.junkdrawer.persistance.model.TransactionEntryType;
import gud.fun.junkdrawer.persistance.model.TransactionType;
import org.json.JSONException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.stream.Collectors;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ITTransaction {

    private static final String LOCALHOST = "http://localhost:";
    private static final String NEW_TRANSACTION = "/integration/transaction/NewTransactionOkAuth.json";
    private static final String UPDATE_TRANSACTION = "/integration/transaction/UpdateTransactionOkAuth.json";

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    private HttpHeaders headers;
    private HttpEntity<String> request;
    private ResponseEntity<TransactionResponseDto> response;


    @BeforeEach
    public void setUp() {
        headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");
    }

    @DisplayName("Create new transaction")
    @Test
    public void test_01() throws JSONException {
        //GIVEN
        request = new HttpEntity<String>(getFileAsString(NEW_TRANSACTION), headers);

        //WHEN
        response = restTemplate.exchange(LOCALHOST + port + Endpoints.TRANSACTION, HttpMethod.PUT, request, TransactionResponseDto.class);

        //THEN
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        TransactionResponseDto responseDto = response.getBody();
        Assertions.assertNotNull(responseDto.getId());
        Assertions.assertEquals(TransactionEntryType.POS, responseDto.getEntryType());
        Assertions.assertEquals(848.12, responseDto.getAmount().doubleValue());
        Assertions.assertEquals("USD", responseDto.getCurrency());
        Assertions.assertEquals(TransactionType.SETTLEMENT, responseDto.getType());
    }

    @DisplayName("Update transaction")
    @Test
    public void test_02() throws JSONException {
        //GIVEN
        request = new HttpEntity<String>(getFileAsString(NEW_TRANSACTION), headers);
//        response = restTemplate.postForEntity(LOCALHOST + port + Endpoints.TRANSACTION, request, TransactionResponseDto.class);
        response = restTemplate.exchange(LOCALHOST + port + Endpoints.TRANSACTION, HttpMethod.PUT, request, TransactionResponseDto.class);
        String existingTransactionId = response.getBody().getId();

        request = new HttpEntity<String>(getFileAsString(UPDATE_TRANSACTION).replace("{{id}}", existingTransactionId), headers);

        //WHEN
        response = restTemplate.exchange(LOCALHOST + port + Endpoints.TRANSACTION, HttpMethod.PUT, request, TransactionResponseDto.class);

        //THEN
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        TransactionResponseDto responseDto = response.getBody();
        Assertions.assertEquals(TransactionEntryType.MANUAL, responseDto.getEntryType());
        Assertions.assertEquals(848.12, responseDto.getAmount().doubleValue());
        Assertions.assertEquals("USD", responseDto.getCurrency());
        Assertions.assertEquals(TransactionType.SETTLEMENT, responseDto.getType());

    }

    private String getFileAsString(String name)  {
        try {
            final BufferedReader inputData = new BufferedReader(new InputStreamReader(getClass().getResourceAsStream(name)));
            return inputData.lines().collect(Collectors.joining("\n"));
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }




}


