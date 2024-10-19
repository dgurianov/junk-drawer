package gud.fun.junkdrawer.dto.transaction;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BicResponseDto {

    private UUID id;

    private String value;

    private String institution;
}
