package gud.fun.junkdrawer.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class ManageContentStatusRequestDto {

    @NotBlank
    private Long jobId;
}
