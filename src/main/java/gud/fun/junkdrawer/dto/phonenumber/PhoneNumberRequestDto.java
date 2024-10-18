package gud.fun.junkdrawer.dto.phonenumber;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PhoneNumberRequestDto {

    @NotBlank
    private String id;

    @NotBlank
    private String phoneNumber;

    @NotBlank
    private String countryCode;
}
