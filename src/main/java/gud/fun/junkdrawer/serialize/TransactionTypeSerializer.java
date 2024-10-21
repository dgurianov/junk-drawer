package gud.fun.junkdrawer.serialize;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import gud.fun.junkdrawer.statemachine.transaction.TransactionType;

import java.io.IOException;

public class TransactionTypeSerializer extends JsonSerializer<TransactionType> {
    @Override
    public void serialize(TransactionType t, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        if( t != null ) {
            jsonGenerator.writeString(t.name());
        } else {
            jsonGenerator.writeString("UNKNOWN");
        }

    }

    @Override
    public Class<TransactionType> handledType() {
        return TransactionType.class;
    }
}
