package gud.fun.junkdrawer.serialize;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import gud.fun.junkdrawer.persistance.model.TransactionState;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.io.IOException;

import static org.mockito.Mockito.verify;

class TransactionStateSerializerTest {

    private TransactionStateSerializer serializer;

    @Mock
    private JsonGenerator jsonGenerator;

    @Mock
    private SerializerProvider serializerProvider;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        serializer = new TransactionStateSerializer();
    }

    @Test
    void testSerializeDebit() throws IOException {
        serializer.serialize(TransactionState.SETTLEMENT, jsonGenerator, serializerProvider);
        verify(jsonGenerator).writeString("SETTLEMENT");
    }

    @Test
    void testSerializeNull() throws IOException {
        serializer.serialize(null, jsonGenerator, serializerProvider);
        verify(jsonGenerator).writeString("UNKNOWN");
    }
}