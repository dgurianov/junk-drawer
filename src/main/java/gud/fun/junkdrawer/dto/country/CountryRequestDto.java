package gud.fun.junkdrawer.dto.country;

import lombok.Data;

import java.util.UUID;

@Data
public class CountryRequestDto {
    private UUID id;
    private String name;
    private String countryCode;
}
