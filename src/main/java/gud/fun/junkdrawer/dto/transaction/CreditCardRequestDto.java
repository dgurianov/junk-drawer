package gud.fun.junkdrawer.dto.transaction;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreditCardRequestDto {

    private UUID id;

    private String Ccn;

    private String issuer;

    private BicRequestDto bic;

}
