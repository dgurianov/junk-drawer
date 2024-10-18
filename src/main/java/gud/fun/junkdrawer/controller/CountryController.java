package gud.fun.junkdrawer.controller;

import gud.fun.junkdrawer.configuration.Endpoints;
import gud.fun.junkdrawer.dto.country.CountryRequestDto;
import gud.fun.junkdrawer.dto.country.CountryResponseDto;
import gud.fun.junkdrawer.service.data.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(Endpoints.COUNTRY)
public class CountryController {

    @Autowired
    private CountryService countryService;

    @GetMapping(value = "/{id}",produces = "application/json")
    public ResponseEntity<CountryResponseDto> getCountryById(@PathVariable String id) {
        return ResponseEntity.ok(countryService.getById(UUID.fromString(id)));
    }

    @GetMapping(produces = "application/json")
    public ResponseEntity<List<CountryResponseDto>> getAllCountries() {
        return ResponseEntity.ok(countryService.getAll());
    }

    @PutMapping(produces = "application/json", consumes = "application/json")
    public ResponseEntity<CountryResponseDto> updateCountry(@RequestBody CountryRequestDto dto) {
        return ResponseEntity.ok(countryService.update(dto));
    }

    @DeleteMapping(value = "/{id}",produces = "application/json")
    public ResponseEntity<CountryResponseDto> deleteCountry(@PathVariable String id) {
        return ResponseEntity.ok(countryService.delete(UUID.fromString(id)));
    }
}