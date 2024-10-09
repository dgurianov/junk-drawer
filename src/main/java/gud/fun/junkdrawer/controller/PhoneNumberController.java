package gud.fun.junkdrawer.controller;

import gud.fun.junkdrawer.configuration.Endpoints;
import gud.fun.junkdrawer.dto.phonenumber.PhoneNumberDto;
import gud.fun.junkdrawer.service.PhoneNumberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = Endpoints.PHONE_NUMBER)
public class PhoneNumberController {

    @Autowired
    private PhoneNumberService phoneNumberService;

    @GetMapping(produces = "application/json")
    public ResponseEntity<List<PhoneNumberDto>> getAllPhoneNumbers() {
        List<PhoneNumberDto> phoneNumbers = phoneNumberService.getAllPhoneNumbers();
        return ResponseEntity.ok(phoneNumbers);
    }

    @GetMapping(value = "/{id}",produces = "application/json")
    public ResponseEntity<PhoneNumberDto> getPhoneNumberById(@PathVariable Long id) {
        PhoneNumberDto phoneNumber = phoneNumberService.getPhoneNumberById(id);
        return ResponseEntity.ok(phoneNumber);
    }

    @PostMapping(produces = "application/json", consumes = "application/json")
    public ResponseEntity<PhoneNumberDto> createPhoneNumber(@RequestBody PhoneNumberDto phoneNumberDto) {
        PhoneNumberDto createdPhoneNumber = phoneNumberService.createPhoneNumber(phoneNumberDto);
        return ResponseEntity.ok(createdPhoneNumber);
    }

    @PutMapping(value = "/{id}",produces = "application/json", consumes = "application/json")
    public ResponseEntity<PhoneNumberDto> updatePhoneNumber(@PathVariable Long id, @RequestBody PhoneNumberDto phoneNumberDto) {
        PhoneNumberDto updatedPhoneNumber = phoneNumberService.updatePhoneNumber(id, phoneNumberDto);
        return ResponseEntity.ok(updatedPhoneNumber);
    }

    @DeleteMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<PhoneNumberDto> deletePhoneNumber(@PathVariable Long id) {

        return ResponseEntity.ok(phoneNumberService.deletePhoneNumber(id));
    }
}