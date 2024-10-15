package gud.fun.junkdrawer.dto.transaction;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import gud.fun.junkdrawer.persistance.model.TransactionType;
import gud.fun.junkdrawer.persistance.model.TransactionEntryType;
import gud.fun.junkdrawer.serialize.TransactionEntryTypeDeserializer;
import gud.fun.junkdrawer.serialize.TransactionTypeDeserializer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransactionRequestDto {

    private String id;

    private Date dateTime;

    @JsonDeserialize(using = TransactionEntryTypeDeserializer.class)
    private TransactionEntryType entryType;

    @JsonDeserialize(using = TransactionTypeDeserializer.class)
    private TransactionType type;

    private MerchantRequestDto merchant;

    private Long amount;

    private String currency;

    private CreditCardRequestDto creditCard;





}
