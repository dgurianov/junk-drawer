package gud.fun.junkdrawer.dto.transaction;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreditCardRequestDto {

    private UUID id;

    @NotBlank(message = "Credit card number is required")
    private String Ccn;

    @NotBlank(message = "Issuer date is required")
    private String issuer;

    @NotNull(message = "Bic is required")
    private BicRequestDto bic;

}
