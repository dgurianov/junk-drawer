package gud.fun.junkdrawer.service.data;

import gud.fun.junkdrawer.dto.city.CityRequestDto;
import gud.fun.junkdrawer.dto.city.CityResponseDto;
import gud.fun.junkdrawer.persistance.model.City;
import gud.fun.junkdrawer.persistance.repository.CityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CityService implements JunkDataService<CityRequestDto,CityResponseDto> {

    @Autowired
    private CityRepository cityRepository;

    CityRequestDto request;

    @Override
    public CityResponseDto create(CityRequestDto dto) {
        request = (CityRequestDto) dto;
        City entity = new City();
        entity.setName(request.getName());
        entity.setCountryCode(request.getCountryCode());
        City savedCity = cityRepository.save(entity);
        return convertToDTO(savedCity);
    }

    @Override
    public CityResponseDto getById(Long id) {
        City city = cityRepository.findById(id).orElseThrow(() -> new RuntimeException("City not found"));
        return convertToDTO(city);
    }

    @Override
    public List<CityResponseDto> getAll() {
        return cityRepository.findAll().stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    @Override
    public CityResponseDto update(Long id, CityRequestDto dto) {
        request = (CityRequestDto) dto;
        City city = cityRepository.findById(id).orElseThrow(() -> new RuntimeException("City not found"));
        city.setName(request.getName());
        city.setCountryCode(request.getCountryCode());
        City updatedCity = cityRepository.save(city);
        return convertToDTO(updatedCity);
    }

    @Override
    public CityResponseDto delete(Long id) {
        cityRepository.deleteById(id);
        CityResponseDto responseDto = new CityResponseDto();
        responseDto.setId(id);
        return responseDto;
    }

    private CityResponseDto convertToDTO(City city) {
        CityResponseDto cityDto = new CityResponseDto();
        cityDto.setId(city.getId());
        cityDto.setName(city.getName());
        cityDto.setCountryCode(city.getCountryCode());
        return cityDto;
    }
}