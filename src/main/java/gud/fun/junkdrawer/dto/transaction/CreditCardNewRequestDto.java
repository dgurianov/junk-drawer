package gud.fun.junkdrawer.dto.transaction;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreditCardNewRequestDto {

    @NotBlank
    private String Ccn;

    @NotBlank
    private String issuer;

    @NotNull
    private BicNewRequestDto bic;

}
