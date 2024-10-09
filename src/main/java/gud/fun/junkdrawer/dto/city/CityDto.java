package gud.fun.junkdrawer.dto.city;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
public class CityDto {
    private Long id;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String name;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String countryCode;

}