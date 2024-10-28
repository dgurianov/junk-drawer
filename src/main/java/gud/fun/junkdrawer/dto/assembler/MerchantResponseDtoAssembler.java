package gud.fun.junkdrawer.dto.assembler;

import gud.fun.junkdrawer.controller.MerchantController;
import gud.fun.junkdrawer.dto.transaction.MerchantResponseDto;
import gud.fun.junkdrawer.persistance.model.Merchant;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

@Component
public class MerchantResponseDtoAssembler extends RepresentationModelAssemblerSupport<Merchant, MerchantResponseDto> {
    public MerchantResponseDtoAssembler() {
        super(MerchantController.class, MerchantResponseDto.class);
    }

    @Override
    public MerchantResponseDto toModel(Merchant entity) {
        MerchantResponseDto dto = instantiateModel(entity);
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setCountryCode(entity.getCountry().getAlpha3());
        dto.setAddress(entity.getAddress());
        return dto;
    }

    @Override
    public CollectionModel<MerchantResponseDto> toCollectionModel(Iterable<? extends Merchant> entities) {
        return super.toCollectionModel(entities);
    }
}
