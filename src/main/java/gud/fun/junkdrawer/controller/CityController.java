package gud.fun.junkdrawer.controller;

import gud.fun.junkdrawer.configuration.Endpoints;
import gud.fun.junkdrawer.dto.city.CityRequestDto;
import gud.fun.junkdrawer.dto.city.CityResponseDto;
import gud.fun.junkdrawer.persistance.model.City;
import gud.fun.junkdrawer.service.data.JunkDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@CrossOrigin(origins = "http://localhost:3000", maxAge = 3600)
@RestController
@RequestMapping(value=Endpoints.CITY)
public class CityController implements JunkDataController<CityRequestDto, CityResponseDto> {

    @Autowired
    private JunkDataService<CityRequestDto,CityResponseDto, City> cityService;

    @Override
    @GetMapping(value = "/{id}",produces = "application/json", consumes = "application/json")
    public ResponseEntity<CityResponseDto> getOneById(@PathVariable UUID id) {
        return ResponseEntity.ok(cityService.getById(id));
    }

    @Override
    @GetMapping(produces = "application/json")
    public ResponseEntity<List<CityResponseDto>> getAll() {
        return ResponseEntity.ok(cityService.getAll());
    }

    @Override
    @PutMapping(produces = "application/json", consumes = "application/json")
    public ResponseEntity<CityResponseDto> createOrUpdate(@RequestBody CityRequestDto dto) {
        return ResponseEntity.ok(cityService.update(dto));
    }

    @Override
    @DeleteMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<CityResponseDto> delete(@PathVariable UUID id) {
        return ResponseEntity.ok(cityService.delete(id));
    }
}