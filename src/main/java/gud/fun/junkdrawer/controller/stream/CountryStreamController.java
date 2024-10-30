package gud.fun.junkdrawer.controller.stream;

import gud.fun.junkdrawer.configuration.Endpoints;
import gud.fun.junkdrawer.dto.country.CountryResponseDto;
import gud.fun.junkdrawer.service.stream.CountryStreamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000", maxAge = 3600)
@RestController
@RequestMapping(value = Endpoints.STREAM_COUNTRY)
public class CountryStreamController {

    @Autowired
    private CountryStreamService countryStreamService;

    @GetMapping(produces = "application/json")
    public ResponseEntity<List<CountryResponseDto>> getStream(@RequestParam int limit) {
        return ResponseEntity.ok(
                countryStreamService.getAllStream(limit)
        );
    }
}
