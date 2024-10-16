package gud.fun.junkdrawer.serialize;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import gud.fun.junkdrawer.persistance.model.TransactionEntryType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.io.IOException;

import static org.mockito.Mockito.verify;

class TransactionEntryTypeSerializerTest {

    private TransactionEntryTypeSerializer serializer;

    @Mock
    private JsonGenerator jsonGenerator;

    @Mock
    private SerializerProvider serializerProvider;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        serializer = new TransactionEntryTypeSerializer();
    }

    @Test
    void testSerializeManual() throws IOException {
        serializer.serialize(TransactionEntryType.MANUAL, jsonGenerator, serializerProvider);
        verify(jsonGenerator).writeString("MANUAL");
    }

    @Test
    void testSerializeContactless() throws IOException {
        serializer.serialize(TransactionEntryType.CONTACTLESS, jsonGenerator, serializerProvider);
        verify(jsonGenerator).writeString("CONTACTLESS");
    }

    @Test
    void testSerializePos() throws IOException {
        serializer.serialize(TransactionEntryType.POS, jsonGenerator, serializerProvider);
        verify(jsonGenerator).writeString("POS");
    }

    @Test
    void testSerializeNull() throws IOException {
        serializer.serialize(null, jsonGenerator, serializerProvider);
        verify(jsonGenerator).writeString("MANUAL");
    }
}
