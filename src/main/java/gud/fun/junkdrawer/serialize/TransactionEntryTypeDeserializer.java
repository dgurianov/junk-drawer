package gud.fun.junkdrawer.serialize;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import gud.fun.junkdrawer.persistance.model.TransactionEntryType;

import java.io.IOException;
import java.util.Objects;

public class TransactionEntryTypeDeserializer extends JsonDeserializer<TransactionEntryType> {

    @Override
    public TransactionEntryType deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JacksonException {
        final JsonNode node = jsonParser.readValueAsTree();
        final String transactionEntryType = node.asText();

        try{
            return TransactionEntryType.valueOf(transactionEntryType);  
        }catch (NullPointerException | IllegalArgumentException e){
            return TransactionEntryType.MANUAL;
        }
    }
}
