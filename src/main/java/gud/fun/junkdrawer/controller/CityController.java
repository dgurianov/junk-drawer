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
@RequestMapping(value=Endpoints.CITY ,produces = "application/json", consumes = "application/json")
public class CityController {

    @Autowired
    private CityService cityService;

    @PostMapping
    public ResponseEntity<CityDto> createCity(@RequestBody CityDto cityDTO) {
        CityDto createdCity = cityService.createCity(cityDTO);
        return ResponseEntity.ok(createdCity);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CityDto> getCityById(@PathVariable Long id) {
        CityDto city = cityService.getCityById(id);
        return ResponseEntity.ok(city);
    }

    @GetMapping
    public ResponseEntity<List<CityDto>> getAllCities() {
        List<CityDto> cities = cityService.getAllCities();
        return ResponseEntity.ok(cities);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CityDto> updateCity(@PathVariable Long id, @RequestBody CityDto cityDTO) {
        CityDto updatedCity = cityService.updateCity(id, cityDTO);
        return ResponseEntity.ok(updatedCity);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Long> deleteCity(@PathVariable Long id) {
       ;
        return ResponseEntity.ok(cityService.deleteCity(id));
    }
}