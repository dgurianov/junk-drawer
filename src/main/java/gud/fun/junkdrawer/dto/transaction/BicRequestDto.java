package gud.fun.junkdrawer.dto.transaction;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BicRequestDto {

    @NotBlank
    private String id;

    @NotBlank
    private String identifier;

    @NotBlank
    private String institution;
}
