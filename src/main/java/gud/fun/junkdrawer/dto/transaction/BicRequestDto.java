package gud.fun.junkdrawer.dto.transaction;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BicRequestDto {

    private UUID id;

    @NotBlank(message = "Bic value is required")
    private String value;

    @NotBlank(message = "Institution is required")
    private String institution;
}
