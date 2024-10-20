package gud.fun.junkdrawer.dto.transaction;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import gud.fun.junkdrawer.persistance.model.TransactionType;
import gud.fun.junkdrawer.persistance.model.TransactionEntryType;
import gud.fun.junkdrawer.serialize.TransactionEntryTypeDeserializer;
import gud.fun.junkdrawer.serialize.TransactionTypeDeserializer;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransactionRequestDto {

    private UUID id;

    private Date dateTime;

    @JsonDeserialize(using = TransactionEntryTypeDeserializer.class)
    private TransactionEntryType entryType;

    @JsonDeserialize(using = TransactionTypeDeserializer.class)
    private TransactionType type;

    @NotNull(message = "Merchant is required")
    private MerchantRequestDto merchant;

    @NotNull(message = "Amount is required")
    private BigDecimal amount;

    @NotBlank(message = "Curency is required")
    private String currency;

    @NotNull(message = "Credit card is required")
    private CreditCardRequestDto creditCard;





}
