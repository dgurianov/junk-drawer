package gud.fun.junkdrawer.service;

import gud.fun.junkdrawer.dto.city.CityDto;

import java.util.List;

public interface CityService {
    CityDto createCity(CityDto cityDTO);
    CityDto getCityById(Long id);
    List<CityDto> getAllCities();
    CityDto updateCity(Long id, CityDto cityDTO);
    CityDto deleteCity(Long id);
}