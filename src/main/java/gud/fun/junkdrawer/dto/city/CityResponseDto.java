package gud.fun.junkdrawer.dto.city;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonRootName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonRootName(value = "data")
@Relation(collectionRelation = "cities", itemRelation = "city")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CityResponseDto extends RepresentationModel<CityResponseDto> {
    private UUID id;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String name;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String countryCode;
}
