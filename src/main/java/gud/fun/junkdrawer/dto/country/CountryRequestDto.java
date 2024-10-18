package gud.fun.junkdrawer.dto.country;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CountryRequestDto {

    @NotBlank
    private String id;

    @NotBlank
    private String name;

    @NotBlank
    private String countryCode;
}
