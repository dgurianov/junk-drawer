package gud.fun.junkdrawer.controller;

import gud.fun.junkdrawer.configuration.Endpoints;
import gud.fun.junkdrawer.dto.city.CityRequestDto;
import gud.fun.junkdrawer.dto.city.CityResponseDto;
import gud.fun.junkdrawer.service.data.JunkDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000", maxAge = 3600)
@RestController
@RequestMapping(value=Endpoints.CITY)
public class CityController {

    @Autowired
    private JunkDataService dataService;

    @PostMapping(produces = "application/json", consumes = "application/json")
    public ResponseEntity<CityResponseDto> createCity(@RequestBody CityRequestDto cityDTO) {
        return ResponseEntity.ok((CityResponseDto) dataService.create(cityDTO));
    }

    @GetMapping(value = "/{id}",produces = "application/json", consumes = "application/json")
    public ResponseEntity<CityResponseDto> getCityById(@PathVariable Long id) {
        return ResponseEntity.ok((CityResponseDto) dataService.getById(id));
    }

    @GetMapping(produces = "application/json")
    public ResponseEntity<List<CityResponseDto>> getAllCities() {
        List<CityResponseDto> cities = dataService.getAll();
        return ResponseEntity.ok(cities);
    }

    @PutMapping(value = "/{id}",produces = "application/json", consumes = "application/json")
    public ResponseEntity<CityResponseDto> updateCity(@PathVariable Long id, @RequestBody CityRequestDto dto) {
        return ResponseEntity.ok((CityResponseDto)dataService.update(id, dto));
    }

    @DeleteMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<CityResponseDto> deleteCity(@PathVariable Long id) {
        return ResponseEntity.ok((CityResponseDto) dataService.delete(id));
    }
}