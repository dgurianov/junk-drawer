package gud.fun.junkdrawer.dto.country;

import com.fasterxml.jackson.annotation.JsonInclude;
import gud.fun.junkdrawer.dto.city.CityResponseDto;
import lombok.Data;

import java.util.List;

@Data
public class CountryResponseDto {
    private String id;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String name;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String countryCode;

    private List<CityResponseDto> cities;
}
