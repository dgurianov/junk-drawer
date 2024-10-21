package gud.fun.junkdrawer.serialize;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import gud.fun.junkdrawer.persistance.model.TransactionState;

import java.io.IOException;

public class TransactionStateDeserializer extends JsonDeserializer<TransactionState> {

    @Override
    public TransactionState deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JacksonException {
        final JsonNode node = jsonParser.readValueAsTree();
        final String transactionType = node.asText();

        try{
            return TransactionState.valueOf(transactionType);
        }catch (IllegalArgumentException | NullPointerException e){
            return TransactionState.UNKNOWN;
        }
    }
}
