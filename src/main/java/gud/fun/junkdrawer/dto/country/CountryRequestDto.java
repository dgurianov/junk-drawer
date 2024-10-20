package gud.fun.junkdrawer.dto.country;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.util.UUID;

@Data
public class CountryRequestDto {

    private UUID id;

    @NotBlank(message = "Name is required")
    private String name;

    @NotBlank(message = "Country code is required")
    private String countryCode;
}
