package gud.fun.junkdrawer.controller.data;

import gud.fun.junkdrawer.configuration.Endpoints;
import gud.fun.junkdrawer.dto.phonenumber.PhoneNumberRequestDto;
import gud.fun.junkdrawer.dto.phonenumber.PhoneNumberResponseDto;
import gud.fun.junkdrawer.service.data.PhoneNumberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@CrossOrigin(origins = "http://localhost:3000", maxAge = 3600)
@RestController
@RequestMapping(value = Endpoints.PHONE_NUMBER)
public class PhoneNumberController implements JunkDataController<PhoneNumberRequestDto, PhoneNumberResponseDto> {

    @Autowired
    private PhoneNumberService phoneNumberService;

    @Override
    @GetMapping(produces = "application/json")
    public ResponseEntity<PagedModel<PhoneNumberResponseDto>> getAll(Pageable pageable) {
        return ResponseEntity.ok(phoneNumberService.getAll(pageable));
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