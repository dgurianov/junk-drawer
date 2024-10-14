package gud.fun.junkdrawer.serialize;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import gud.fun.junkdrawer.persistance.model.transaction.TransactionType;

import java.io.IOException;
import java.util.Objects;

public class TransactionTypeDeserializer extends JsonDeserializer<TransactionType> {

    @Override
    public TransactionType deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JacksonException {
        final JsonNode node = jsonParser.readValueAsTree();
        final String transactionType = node.asText();

        return Objects.isNull(transactionType)? TransactionType.UNKNOWN : TransactionType.valueOf(transactionType);
    }
}
