package gud.fun.junkdrawer.dto.transaction;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import gud.fun.junkdrawer.dto.city.CityResponseDto;
import gud.fun.junkdrawer.persistance.model.TransactionEntryType;
import gud.fun.junkdrawer.persistance.model.TransactionState;
import gud.fun.junkdrawer.persistance.model.TransactionType;
import gud.fun.junkdrawer.serialize.TransactionEntryTypeSerializer;
import gud.fun.junkdrawer.serialize.TransactionStateSerializer;
import gud.fun.junkdrawer.serialize.TransactionTypeSerializer;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import java.math.BigDecimal;
import java.util.Date;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonRootName(value = "data")
@Relation(collectionRelation = "transactions", itemRelation = "transaction")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TransactionResponseDto extends RepresentationModel<TransactionResponseDto> {

    private UUID id;

    private UUID correlationId;

    private Date dateTime;

    @JsonSerialize(using = TransactionEntryTypeSerializer.class)
    private TransactionEntryType entryType;

    @JsonSerialize(using = TransactionStateSerializer.class)
    private TransactionState state;

    @JsonSerialize(using = TransactionTypeSerializer.class)
    private TransactionType type;

    private MerchantResponseDto merchant;

    private BigDecimal amount;

    private String currency;

    private CreditCardResponseDto creditCard;

}
