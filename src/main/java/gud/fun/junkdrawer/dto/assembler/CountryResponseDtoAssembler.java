package gud.fun.junkdrawer.dto.assembler;

import gud.fun.junkdrawer.controller.data.CountryController;
import gud.fun.junkdrawer.dto.city.CityResponseDto;
import gud.fun.junkdrawer.dto.country.CountryResponseDto;
import gud.fun.junkdrawer.persistance.model.Country;
import gud.fun.junkdrawer.persistance.repository.CityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class CountryResponseDtoAssembler extends RepresentationModelAssemblerSupport<Country, CountryResponseDto> {
    public CountryResponseDtoAssembler() {
        super(CountryController.class, CountryResponseDto.class);
    }

    @Autowired
    private CityRepository cityRepository;

    @Override
    public CountryResponseDto toModel(Country entity) {
        CountryResponseDto dto = instantiateModel(entity);
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setCountryCode(entity.getCountryCode());
        dto.setCities(cityRepository.findAllByCountryCode(entity.getCountryCode()).stream().map(city -> {
             return CityResponseDto.builder()
            .id(city.getId())
            .name(city.getName())
            .countryCode(city.getCountryCode()).build();
        }).collect(Collectors.toList()));

        /*
        import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;
        Link selfLink = linkTo(methodOn(CityController.class).getOneById(entity.getId())).withSelfRel();
        dto.add(selfLink);*/

        return dto;
    }

    @Override
    public CollectionModel<CountryResponseDto> toCollectionModel(Iterable<? extends Country> entities) {
        return super.toCollectionModel(entities);
    }
}
