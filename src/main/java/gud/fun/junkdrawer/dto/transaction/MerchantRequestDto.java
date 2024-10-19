package gud.fun.junkdrawer.dto.transaction;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class MerchantRequestDto {

    private UUID id;

    private String name;

    private String countryCode;

    private String address;

}
