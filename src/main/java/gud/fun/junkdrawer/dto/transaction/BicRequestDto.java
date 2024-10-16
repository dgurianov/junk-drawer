package gud.fun.junkdrawer.dto.transaction;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BicRequestDto {

    private String id;

    private String value;

    private String institution;
}
