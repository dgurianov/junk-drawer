package gud.fun.junkdrawer.serialize;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import gud.fun.junkdrawer.persistance.model.TransactionType;

import java.io.IOException;
import java.util.Objects;

public class TransactionTypeDeserializer extends JsonDeserializer<TransactionType> {

    @Override
    public TransactionType deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JacksonException {
        final JsonNode node = jsonParser.readValueAsTree();
        final String transactionType = node.asText();

        try{
            return TransactionType.valueOf(transactionType);
        }catch (IllegalArgumentException | NullPointerException e){
            return TransactionType.UNKNOWN;
        }
    }
}
