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
@JsonRootName(value = "credit_card_entries")
@Relation(collectionRelation = "credit_cards", itemRelation = "credit_card")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CreditCardResponseDto extends RepresentationModel<CreditCardResponseDto> {

    private UUID id;

    private String Ccn;

    private String issuer;

    private BicResponseDto bic;
}
