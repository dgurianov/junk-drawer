package gud.fun.junkdrawer.serialize;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import gud.fun.junkdrawer.persistance.model.TransactionState;

import java.io.IOException;

public class TransactionStateSerializer extends JsonSerializer<TransactionState> {
    @Override
    public void serialize(TransactionState t, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        if( t != null ) {
            jsonGenerator.writeString(t.name());
        } else {
            jsonGenerator.writeString("UNKNOWN");
        }

    }

    @Override
    public Class<TransactionState> handledType() {
        return TransactionState.class;
    }
}
