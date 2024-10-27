package gud.fun.junkdrawer.dto.country;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonRootName;
import gud.fun.junkdrawer.dto.city.CityResponseDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import java.util.List;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonRootName(value = "data")
@Relation(collectionRelation = "countries", itemRelation = "country")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CountryResponseDto extends RepresentationModel<CountryResponseDto> {
    private UUID id;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String name;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String countryCode;

    private List<CityResponseDto> cities;
}
