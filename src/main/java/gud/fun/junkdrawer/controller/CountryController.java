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
public class CountryController implements JunkDataController<CountryRequestDto, CountryResponseDto> {

    @Autowired
    private CountryService countryService;

    @Override
    @GetMapping(value = "/{id}",produces = "application/json")
    public ResponseEntity<CountryResponseDto> getOneById(@PathVariable UUID id) {
        return ResponseEntity.ok(countryService.getById(id));
    }

    @Override
    @GetMapping(produces = "application/json")
    public ResponseEntity<List<CountryResponseDto>> getAll() {
        return ResponseEntity.ok(countryService.getAll());
    }

    @Override
    @PutMapping(produces = "application/json", consumes = "application/json")
    public ResponseEntity<CountryResponseDto> createOrUpdate(@RequestBody CountryRequestDto dto) {
        return ResponseEntity.ok(countryService.update(dto));
    }

    @Override
    @DeleteMapping(value = "/{id}",produces = "application/json")
    public ResponseEntity<CountryResponseDto> delete(@PathVariable UUID id) {
        return ResponseEntity.ok(countryService.delete(id));
    }
}