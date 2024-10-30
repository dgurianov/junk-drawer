package gud.fun.junkdrawer.dto.assembler;

import gud.fun.junkdrawer.controller.data.CreditCardController;
import gud.fun.junkdrawer.dto.transaction.CreditCardResponseDto;
import gud.fun.junkdrawer.persistance.model.CreditCard;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

@Component
public class CreditCardResponseDtoAssembler extends RepresentationModelAssemblerSupport<CreditCard, CreditCardResponseDto> {
    public CreditCardResponseDtoAssembler() {
        super(CreditCardController.class, CreditCardResponseDto.class);
    }

    @Autowired
    private BicResponseDtoAssembler bicAssembler;

    @Override
    public CreditCardResponseDto toModel(CreditCard entity) {
        CreditCardResponseDto dto = instantiateModel(entity);
        dto.setId(entity.getId());
        dto.setCcn(entity.getCcn());
        dto.setIssuer(entity.getIssuer());
        dto.setBic(bicAssembler.toModel(entity.getBic()));

        return dto;
    }

    @Override
    public CollectionModel<CreditCardResponseDto> toCollectionModel(Iterable<? extends CreditCard> entities) {
        return super.toCollectionModel(entities);
    }
}
