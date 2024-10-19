package gud.fun.junkdrawer.dto.city;

import lombok.Data;

import java.util.UUID;

@Data
public class CityRequestDto {
    private UUID id;
    private String name;
    private String countryCode;
}
