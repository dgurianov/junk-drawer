package gud.fun.junkdrawer.dto.assembler;

import gud.fun.junkdrawer.controller.data.PhoneNumberController;
import gud.fun.junkdrawer.dto.phonenumber.PhoneNumberResponseDto;
import gud.fun.junkdrawer.persistance.model.PhoneNumber;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

@Component
public class PhoneNumberResponseDtoAssembler extends RepresentationModelAssemblerSupport<PhoneNumber, PhoneNumberResponseDto> {
    public PhoneNumberResponseDtoAssembler() {
        super(PhoneNumberController.class, PhoneNumberResponseDto.class);
    }

    @Override
    public PhoneNumberResponseDto toModel(PhoneNumber entity) {
        PhoneNumberResponseDto dto = instantiateModel(entity);
        dto.setId(entity.getId());
        dto.setPhoneNumber(entity.getPhoneNumber());
        dto.setCountryCode(entity.getCountryCode());
        return dto;
    }

    @Override
    public CollectionModel<PhoneNumberResponseDto> toCollectionModel(Iterable<? extends PhoneNumber> entities) {
        return super.toCollectionModel(entities);
    }
}
