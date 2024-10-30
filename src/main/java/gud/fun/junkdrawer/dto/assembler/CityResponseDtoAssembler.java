package gud.fun.junkdrawer.dto.assembler;

import gud.fun.junkdrawer.controller.data.CityController;
import gud.fun.junkdrawer.dto.city.CityResponseDto;
import gud.fun.junkdrawer.persistance.model.City;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

@Component
public class CityResponseDtoAssembler extends RepresentationModelAssemblerSupport<City, CityResponseDto> {
    public CityResponseDtoAssembler() {
        super(CityController.class, CityResponseDto.class);
    }

    @Override
    public CityResponseDto toModel(City entity) {
        CityResponseDto dto = instantiateModel(entity);
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setCountryCode(entity.getCountryCode());
        /*
        import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;
        Link selfLink = linkTo(methodOn(CityController.class).getOneById(entity.getId())).withSelfRel();
        dto.add(selfLink); */
        return dto;
    }

    @Override
    public CollectionModel<CityResponseDto> toCollectionModel(Iterable<? extends City> entities) {
        return super.toCollectionModel(entities);
    }
}
