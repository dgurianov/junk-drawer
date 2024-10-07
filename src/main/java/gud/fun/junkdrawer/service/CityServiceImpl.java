package gud.fun.junkdrawer.service;

import gud.fun.junkdrawer.dto.CityDto;
import gud.fun.junkdrawer.persistance.model.City;
import gud.fun.junkdrawer.persistance.repository.CityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CityServiceImpl implements CityService {

    @Autowired
    private CityRepository cityRepository;

    @Override
    public CityDto createCity(CityDto cityDto) {
        City city = new City();
        city.setName(cityDto.getName());
        city.setCountry(cityDto.getCountry());
        City savedCity = cityRepository.save(city);
        return convertToDTO(savedCity);
    }

    @Override
    public CityDto getCityById(Long id) {
        City city = cityRepository.findById(id).orElseThrow(() -> new RuntimeException("City not found"));
        return convertToDTO(city);
    }

    @Override
    public List<CityDto> getAllCities() {
        return cityRepository.findAll().stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    @Override
    public CityDto updateCity(Long id, CityDto cityDto) {
        City city = cityRepository.findById(id).orElseThrow(() -> new RuntimeException("City not found"));
        city.setName(cityDto.getName());
        city.setCountry(cityDto.getCountry());
        City updatedCity = cityRepository.save(city);
        return convertToDTO(updatedCity);
    }

    @Override
    public void deleteCity(Long id) {
        cityRepository.deleteById(id);
    }

    private CityDto convertToDTO(City city) {
        CityDto cityDto = new CityDto();
        cityDto.setId(city.getId());
        cityDto.setName(city.getName());
        cityDto.setCountry(city.getCountry());
        return cityDto;
    }
}