package gud.fun.junkdrawer.dto.city;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CityNewRequestDto {

    @NotBlank
    private String name;

    @NotBlank
    private String countryCode;
}
