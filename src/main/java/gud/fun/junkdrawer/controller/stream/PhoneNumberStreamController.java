package gud.fun.junkdrawer.controller.stream;

import gud.fun.junkdrawer.configuration.Endpoints;
import gud.fun.junkdrawer.dto.phonenumber.PhoneNumberResponseDto;
import gud.fun.junkdrawer.service.stream.PhoneNumberStreamService;
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
@RequestMapping(value = Endpoints.STREAM_PHONE_NUMBER)
public class PhoneNumberStreamController {

    @Autowired
    private PhoneNumberStreamService streamService;

    @GetMapping(produces = "application/json")
    public ResponseEntity<List<PhoneNumberResponseDto>> getStream(@RequestParam int limit) {
        return ResponseEntity.ok(
                streamService.getAllStream(limit)
        );
    }
}