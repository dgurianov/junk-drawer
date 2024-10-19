package gud.fun.junkdrawer.dto.country;

import com.fasterxml.jackson.annotation.JsonInclude;
import gud.fun.junkdrawer.dto.city.CityResponseDto;
import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
public class CountryResponseDto {
    private UUID id;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String name;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String countryCode;

    private List<CityResponseDto> cities;
}
