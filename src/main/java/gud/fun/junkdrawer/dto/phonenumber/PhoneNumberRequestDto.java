package gud.fun.junkdrawer.dto.phonenumber;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PhoneNumberRequestDto {
    private UUID id;
    private String phoneNumber;
    private String countryCode;
}
