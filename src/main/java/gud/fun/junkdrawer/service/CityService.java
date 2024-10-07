package gud.fun.junkdrawer.service;

import gud.fun.junkdrawer.dto.CityDto;

import java.util.List;

public interface CityService {
    CityDto createCity(CityDto cityDTO);
    CityDto getCityById(Long id);
    List<CityDto> getAllCities();
    CityDto updateCity(Long id, CityDto cityDTO);
    Long deleteCity(Long id);
}