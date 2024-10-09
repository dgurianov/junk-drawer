package gud.fun.junkdrawer.controller;

import gud.fun.junkdrawer.configuration.Endpoints;
import gud.fun.junkdrawer.dto.CityDto;
import gud.fun.junkdrawer.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000", maxAge = 3600)
@RestController
@RequestMapping(value=Endpoints.CITY)
public class CityController {

    @Autowired
    private CityService cityService;

    @PostMapping(produces = "application/json", consumes = "application/json")
    public ResponseEntity<CityDto> createCity(@RequestBody CityDto cityDTO) {
        return ResponseEntity.ok(cityService.createCity(cityDTO));
    }

    @GetMapping(value = "/{id}",produces = "application/json", consumes = "application/json")
    public ResponseEntity<CityDto> getCityById(@PathVariable Long id) {
        return ResponseEntity.ok(cityService.getCityById(id));
    }

    @GetMapping(produces = "application/json")
    public ResponseEntity<List<CityDto>> getAllCities() {
        List<CityDto> cities = cityService.getAllCities();
        return ResponseEntity.ok(cities);
    }

    @PutMapping(value = "/{id}",produces = "application/json", consumes = "application/json")
    public ResponseEntity<CityDto> updateCity(@PathVariable Long id, @RequestBody CityDto cityDTO) {
        return ResponseEntity.ok(cityService.updateCity(id, cityDTO));
    }

    @DeleteMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<CityDto> deleteCity(@PathVariable Long id) {
        return ResponseEntity.ok(cityService.deleteCity(id));
    }
}