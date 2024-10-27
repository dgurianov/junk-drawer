package gud.fun.junkdrawer.dto.transaction;

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
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonRootName(value = "merchants_entries")
@Relation(collectionRelation = "merchants", itemRelation = "merchant")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class MerchantResponseDto extends RepresentationModel<MerchantResponseDto> {

    private UUID id;

    private String name;

    private String countryCode;

    private String address;

}
