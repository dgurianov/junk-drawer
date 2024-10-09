package gud.fun.junkdrawer.dto.phonenumber;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PhoneNumberDto {
    private Long id;
    private String phoneNumber;
    private String countryCode;
}
