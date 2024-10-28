package gud.fun.junkdrawer.dto.assembler;

import gud.fun.junkdrawer.controller.BicController;
import gud.fun.junkdrawer.dto.transaction.BicResponseDto;
import gud.fun.junkdrawer.persistance.model.Bic;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

@Component
public class BicResponseDtoAssembler extends RepresentationModelAssemblerSupport<Bic, BicResponseDto> {
    public BicResponseDtoAssembler() {
        super(BicController.class, BicResponseDto.class);
    }

    @Override
    public BicResponseDto toModel(Bic entity) {
        BicResponseDto dto = instantiateModel(entity);
        dto.setId(entity.getId());
        dto.setValue(entity.getIdentifier());
        dto.setInstitution(entity.getInstitution());
        return dto;
    }

    @Override
    public CollectionModel<BicResponseDto> toCollectionModel(Iterable<? extends Bic> entities) {
        return super.toCollectionModel(entities);
    }
}
