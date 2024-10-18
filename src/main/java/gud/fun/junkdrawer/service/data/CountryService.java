package gud.fun.junkdrawer.service.data;

import gud.fun.junkdrawer.dto.city.CityNewRequestDto;
import gud.fun.junkdrawer.dto.country.CountryNewRequestDto;
import gud.fun.junkdrawer.dto.country.CountryRequestDto;
import gud.fun.junkdrawer.dto.country.CountryResponseDto;
import gud.fun.junkdrawer.persistance.model.Country;
import gud.fun.junkdrawer.persistance.repository.CityRepository;
import gud.fun.junkdrawer.persistance.repository.CountryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class CountryService implements JunkDataService<CountryRequestDto, CountryNewRequestDto, CountryResponseDto, Country> {

    @Autowired
    private CountryRepository countryRepository;

    @Autowired
    private CityRepository cityRepository;

    @Autowired
    private CityService cityService;

    @Override
    public CountryResponseDto create(CountryNewRequestDto dto) {
        Country newCountry = new Country();
        newCountry.setName(dto.getName());
        newCountry.setCountryCode(dto.getCountryCode());
        newCountry.setCities(cityRepository.findAllByCountryCode(newCountry.getCountryCode()));
        return toResponseDTO(countryRepository.save(newCountry));
    }

    @Override
    public CountryResponseDto getById(UUID id) {
        Country country = countryRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Country not found for id: " + id));
        return toResponseDTO(country);
    }

    @Override
    public List<CountryResponseDto> getAll() {
        List<Country> countries = countryRepository.findAll();
        return countries.stream()
                .map(this::toResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public CountryResponseDto update(CountryRequestDto dto) {
        Country country = countryRepository.findById(UUID.fromString(dto.getId()))
                .orElseThrow(() -> new IllegalArgumentException("Country not found for id: " + dto.getId()));
        country.setName(dto.getName());
        country.setCountryCode(dto.getCountryCode());
        country = countryRepository.save(country);
        return toResponseDTO(country);
    }

    @Override
    public CountryResponseDto delete(UUID id) {
        countryRepository.deleteById(id);
        CountryResponseDto response = new CountryResponseDto();
        response.setId(id.toString());
        return response;
    }

    @Override
    public CountryResponseDto toResponseDTO(Country country) {
        CountryResponseDto responseDto = new CountryResponseDto();
        responseDto.setId(country.getId().toString());
        responseDto.setName(country.getName());
        responseDto.setCountryCode(country.getCountryCode());
        responseDto.setCities(country.getCities().stream().map(cityService::toResponseDTO).collect(Collectors.toList()));
        return responseDto;
    }

    @Override
    public Country toEntity(CountryRequestDto dto) {
        Country country = new Country();
        country.setId(dto.getId() != null ? UUID.fromString(dto.getId()):null);
        country.setName(dto.getName());
        country.setCountryCode(dto.getCountryCode());
        return country;
    }
}