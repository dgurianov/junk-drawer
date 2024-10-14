package gud.fun.junkdrawer.serialize;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import gud.fun.junkdrawer.persistance.model.transaction.TransactionEntryType;
import gud.fun.junkdrawer.persistance.model.transaction.TransactionType;

import java.io.IOException;
import java.util.Objects;

public class TransactionEntryTypeSerializer extends JsonSerializer<TransactionEntryType> {
    @Override
    public void serialize(TransactionEntryType t, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeString(Objects.isNull(t) ? "MANUAL" : t.name());
    }

    @Override
    public Class<TransactionEntryType> handledType() {
        return TransactionEntryType.class;
    }
}
