package gud.fun.junkdrawer.dto.transaction;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreditCardRequestDto {

    private UUID id;

    @NotBlank
    private String Ccn;

    @NotBlank
    private String issuer;

    @Valid
    private BicRequestDto bic;

}
