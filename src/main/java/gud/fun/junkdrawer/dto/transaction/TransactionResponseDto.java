package gud.fun.junkdrawer.dto.transaction;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import gud.fun.junkdrawer.persistance.model.TransactionEntryType;
import gud.fun.junkdrawer.persistance.model.TransactionType;
import gud.fun.junkdrawer.serialize.TransactionEntryTypeSerializer;
import gud.fun.junkdrawer.serialize.TransactionTypeSerializer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransactionResponseDto {

    private String id;

    private Date dateTime;

    @JsonSerialize(using = TransactionEntryTypeSerializer.class)
    private TransactionEntryType entryType;

    @JsonSerialize(using = TransactionTypeSerializer.class)
    private TransactionType type;

    private MerchantResponseDto merchant;

    private BigDecimal amount;

    private String currency;

    private CreditCardResponseDto creditCard;

}
