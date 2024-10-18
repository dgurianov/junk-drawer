package gud.fun.junkdrawer.controller;

import gud.fun.junkdrawer.configuration.Endpoints;
import gud.fun.junkdrawer.dto.phonenumber.PhoneNumberRequestDto;
import gud.fun.junkdrawer.dto.phonenumber.PhoneNumberResponseDto;
import gud.fun.junkdrawer.service.data.PhoneNumberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(value = Endpoints.PHONE_NUMBER)
public class PhoneNumberController implements JunkDataController<PhoneNumberRequestDto, PhoneNumberResponseDto> {

    @Autowired
    private PhoneNumberService phoneNumberService;

    @Override
    @GetMapping(produces = "application/json")
    public ResponseEntity<List<PhoneNumberResponseDto>> getAll() {
        return ResponseEntity.ok(phoneNumberService.getAll());
    }

    @Override
    @GetMapping(value = "/{id}",produces = "application/json")
    public ResponseEntity<PhoneNumberResponseDto> getOneById(@PathVariable UUID id) {
        return ResponseEntity.ok(phoneNumberService.getById(id));
    }

    @Override
    @PutMapping(produces = "application/json", consumes = "application/json")
    public ResponseEntity<PhoneNumberResponseDto> createOrUpdate(@RequestBody PhoneNumberRequestDto dto) {
        return ResponseEntity.ok(phoneNumberService.update(dto));
    }

    @Override
    @DeleteMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<PhoneNumberResponseDto> delete(@PathVariable UUID id) {
        return ResponseEntity.ok(phoneNumberService.delete(id));
    }
}