package gud.fun.junkdrawer.service.data;

import gud.fun.junkdrawer.dto.assembler.CityResponseDtoAssembler;
import gud.fun.junkdrawer.dto.city.CityRequestDto;
import gud.fun.junkdrawer.dto.city.CityResponseDto;
import gud.fun.junkdrawer.persistance.model.City;
import gud.fun.junkdrawer.persistance.repository.CityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedModel;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class CityService  implements JunkDataService<CityRequestDto,CityResponseDto,City>{

    @Autowired
    private CityRepository cityRepository;

    @Autowired
    private CityResponseDtoAssembler cityDtoAssembler;

    @Autowired
    private PagedResourcesAssembler<City> pagedResourcesAssembler;

    @Override
    public CityResponseDto create(CityRequestDto dto) {
        return toResponseDTO(
                cityRepository.save(
                        toEntity(dto)
                )
        );
    }

    @Override
    public CityResponseDto getById(UUID id) {
        City city = cityRepository.findById(id).orElseThrow(() -> new RuntimeException("City not found"));
        return toResponseDTO(city);
    }

    @Override
    public PagedModel<CityResponseDto> getAll(Pageable pageable) {
        return pagedResourcesAssembler.toModel(cityRepository.findAll(pageable), cityDtoAssembler);
    }

    @Override
    public CityResponseDto update(CityRequestDto dto) {
        City city = cityRepository.findById(dto.getId()).orElseThrow(() -> new RuntimeException("City not found"));
        city.setName(dto.getName());
        city.setCountryCode(dto.getCountryCode());
        City updatedCity = cityRepository.save(city);
        return toResponseDTO(updatedCity);
    }

    @Override
    public CityResponseDto delete(UUID id) {
        cityRepository.deleteById(id);
        CityResponseDto responseDto = new CityResponseDto();
        responseDto.setId(id);
        return responseDto;
    }

    @Override
    public CityResponseDto toResponseDTO(City entity) {
        CityResponseDto cityDto = new CityResponseDto();
        cityDto.setId(entity.getId());
        cityDto.setName(entity.getName());
        cityDto.setCountryCode(entity.getCountryCode());
        return cityDto;
    }

    @Override
    public City toEntity(CityRequestDto dto) {
        City city = new City();
        city.setId(dto.getId() != null ? dto.getId() : null);
        city.setName(dto.getName());
        city.setCountryCode(dto.getCountryCode());
        return city;
    }
}