package gud.fun.junkdrawer.dto.transaction;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class MerchantNewRequestDto {

    @NotBlank
    private String name;

    @NotBlank
    private String countryCode;

    @NotBlank
    private String address;

}
