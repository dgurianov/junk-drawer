package gud.fun.junkdrawer.serialize;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import gud.fun.junkdrawer.statemachine.transaction.TransactionType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

class TransactionTypeDeserializerTest {

    private TransactionTypeDeserializer deserializer;

    @Mock
    private JsonParser jsonParser;

    @Mock
    private JsonNode jsonNode;

    @Mock
    private DeserializationContext deserializationContext;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        deserializer = new TransactionTypeDeserializer();
    }

    @Test
    void testDeserializeCredit() throws IOException {
        when(jsonParser.readValueAsTree()).thenReturn(new ObjectMapper().readTree("\"SETTLEMENT\""));
        TransactionType result = deserializer.deserialize(jsonParser, deserializationContext);
        assertEquals(TransactionType.SETTLEMENT, result);
    }

    @Test
    void testDeserializeNull() throws IOException {
        when(jsonNode.asText()).thenReturn(null);
        when(jsonParser.readValueAsTree()).thenReturn(jsonNode);
        TransactionType result = deserializer.deserialize(jsonParser, deserializationContext);
        assertEquals(TransactionType.UNKNOWN, result);
    }

    @Test
    void testDeserializeUnknownValue() throws IOException {
        when(jsonNode.asText()).thenReturn("unknown value");
        when(jsonParser.readValueAsTree()).thenReturn(jsonNode);
        TransactionType result = deserializer.deserialize(jsonParser, deserializationContext);
        assertEquals(TransactionType.UNKNOWN, result);
    }
}
