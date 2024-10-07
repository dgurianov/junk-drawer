package gud.fun.junkdrawer.controller;

import gud.fun.junkdrawer.configuration.Endpoints;
import gud.fun.junkdrawer.dto.PhoneNumberDto;
import gud.fun.junkdrawer.service.PhoneNumberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(Endpoints.PHONE_NUMBER)
public class PhoneNumberController {

    @Autowired
    private PhoneNumberService phoneNumberService;

    @GetMapping
    public ResponseEntity<List<PhoneNumberDto>> getAllPhoneNumbers() {
        List<PhoneNumberDto> phoneNumbers = phoneNumberService.getAllPhoneNumbers();
        return ResponseEntity.ok(phoneNumbers);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PhoneNumberDto> getPhoneNumberById(@PathVariable Long id) {
        PhoneNumberDto phoneNumber = phoneNumberService.getPhoneNumberById(id);
        return ResponseEntity.ok(phoneNumber);
    }

    @PostMapping
    public ResponseEntity<PhoneNumberDto> createPhoneNumber(@RequestBody PhoneNumberDto phoneNumberDto) {
        PhoneNumberDto createdPhoneNumber = phoneNumberService.createPhoneNumber(phoneNumberDto);
        return ResponseEntity.ok(createdPhoneNumber);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PhoneNumberDto> updatePhoneNumber(@PathVariable Long id, @RequestBody PhoneNumberDto phoneNumberDto) {
        PhoneNumberDto updatedPhoneNumber = phoneNumberService.updatePhoneNumber(id, phoneNumberDto);
        return ResponseEntity.ok(updatedPhoneNumber);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Long> deletePhoneNumber(@PathVariable Long id) {

        return ResponseEntity.ok(phoneNumberService.deletePhoneNumber(id));
    }
}