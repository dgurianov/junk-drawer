package gud.fun.junkdrawer.dto.city;

import lombok.Data;

import java.util.UUID;

@Data
public class CityRequestDto {
    private String id;
    private String name;
    private String countryCode;
}
