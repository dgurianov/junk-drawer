package gud.fun.junkdrawer.dto.phonenumber;

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
@JsonRootName(value = "data")
@Relation(collectionRelation = "phone_numbers", itemRelation = "phone_number")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PhoneNumberResponseDto  extends RepresentationModel<PhoneNumberResponseDto> {
    private UUID id;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String phoneNumber;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String countryCode;
}
