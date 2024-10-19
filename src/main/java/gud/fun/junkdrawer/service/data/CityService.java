package gud.fun.junkdrawer.service.data;

import gud.fun.junkdrawer.dto.city.CityRequestDto;
import gud.fun.junkdrawer.dto.city.CityResponseDto;
import gud.fun.junkdrawer.persistance.model.City;
import gud.fun.junkdrawer.persistance.repository.CityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class CityService implements JunkDataService<CityRequestDto,CityResponseDto,City> {

    @Autowired
    private CityRepository cityRepository;

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
    public List<CityResponseDto> getAll() {
        return cityRepository.findAll().stream().map(this::toResponseDTO).collect(Collectors.toList());
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