package gud.fun.junkdrawer.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PhoneNumberDto {
    private Long id;
    private String phoneNumber;
    private String countryCode;
}
