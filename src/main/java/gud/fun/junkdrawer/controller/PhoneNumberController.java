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
public class PhoneNumberController {

    @Autowired
    private PhoneNumberService phoneNumberService;

    @GetMapping(produces = "application/json")
    public ResponseEntity<List<PhoneNumberResponseDto>> getAllPhoneNumbers() {
        List<PhoneNumberResponseDto> phoneNumbers = phoneNumberService.getAll();
        return ResponseEntity.ok(phoneNumbers);
    }

    @GetMapping(value = "/{id}",produces = "application/json")
    public ResponseEntity<PhoneNumberResponseDto> getPhoneNumberById(@PathVariable String id) {
        PhoneNumberResponseDto phoneNumber = phoneNumberService.getById(UUID.fromString(id));
        return ResponseEntity.ok(phoneNumber);
    }

    @PostMapping(produces = "application/json", consumes = "application/json")
    public ResponseEntity<PhoneNumberResponseDto> createPhoneNumber(@RequestBody PhoneNumberRequestDto phoneNumberDto) {
        PhoneNumberResponseDto createdPhoneNumber = phoneNumberService.create(phoneNumberDto);
        return ResponseEntity.ok(createdPhoneNumber);
    }

    @PutMapping(value = "/{id}",produces = "application/json", consumes = "application/json")
    public ResponseEntity<PhoneNumberResponseDto> updatePhoneNumber(@PathVariable String id, @RequestBody PhoneNumberRequestDto phoneNumberDto) {
        PhoneNumberResponseDto updatedPhoneNumber = phoneNumberService.update(UUID.fromString(id), phoneNumberDto);
        return ResponseEntity.ok(updatedPhoneNumber);
    }

    @DeleteMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<PhoneNumberResponseDto> deletePhoneNumber(@PathVariable String id) {

        return ResponseEntity.ok(phoneNumberService.delete(UUID.fromString(id)));
    }
}