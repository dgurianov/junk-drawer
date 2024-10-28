package gud.fun.junkdrawer.dto.transaction;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MerchantRequestDto {

    private UUID id;

    @NotBlank
    private String name;

    @NotBlank
    private String countryCode;

    @NotBlank
    private String address;

}
