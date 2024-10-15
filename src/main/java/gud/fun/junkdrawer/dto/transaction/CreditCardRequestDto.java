package gud.fun.junkdrawer.dto.transaction;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreditCardRequestDto {

    private String id;

    private String Ccn;

    private String issuer;

    private BicRequestDto bic;

}
