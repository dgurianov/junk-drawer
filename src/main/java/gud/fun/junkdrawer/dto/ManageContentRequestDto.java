package gud.fun.junkdrawer.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ManageContentRequestDto {

    @NotBlank(message = "Content name is required")
    private String contentName;

    @NotNull(message = "Content amount is required")
    private Long contentAmount;
}