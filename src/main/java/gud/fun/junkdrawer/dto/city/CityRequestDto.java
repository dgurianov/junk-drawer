package gud.fun.junkdrawer.dto.city;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.UUID;

@Data
public class CityRequestDto {

    @NotBlank
    private String id;

    @NotBlank
    private String name;

    @NotBlank
    private String countryCode;
}
