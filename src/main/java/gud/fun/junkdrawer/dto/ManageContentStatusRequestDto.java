package gud.fun.junkdrawer.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ManageContentStatusRequestDto {

    @NotNull(message = "Job id is required")
    private Long jobId;

}
