package gud.fun.junkdrawer.dto.transaction;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BicResponseDto {

    private String id;

    private String value;

    private String institution;
}
