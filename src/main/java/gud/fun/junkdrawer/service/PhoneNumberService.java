package gud.fun.junkdrawer.service;

import gud.fun.junkdrawer.dto.PhoneNumberDto;
import gud.fun.junkdrawer.persistance.model.PhoneNumber;
import gud.fun.junkdrawer.persistance.repository.PhoneNumberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PhoneNumberService {

    @Autowired
    private PhoneNumberRepository phoneNumberRepository;

    public List<PhoneNumberDto> getAllPhoneNumbers() {
        List<PhoneNumber> phoneNumbers = phoneNumberRepository.findAll();
        return phoneNumbers.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    public PhoneNumberDto getPhoneNumberById(Long id) {
        PhoneNumber phoneNumber = phoneNumberRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Phone number not found for id: " + id));
        return convertToDto(phoneNumber);
    }

    public PhoneNumberDto createPhoneNumber(PhoneNumberDto phoneNumberDto) {
        PhoneNumber phoneNumber = convertToEntity(phoneNumberDto);
        phoneNumber = phoneNumberRepository.save(phoneNumber);
        return convertToDto(phoneNumber);
    }

    public PhoneNumberDto updatePhoneNumber(Long id, PhoneNumberDto phoneNumberDto) {
        PhoneNumber phoneNumber = phoneNumberRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Phone number not found for id: " + id));
        phoneNumber.setPhoneNumber(phoneNumberDto.getPhoneNumber());
        phoneNumber.setCountryCode(phoneNumberDto.getCountryCode());
        phoneNumber = phoneNumberRepository.save(phoneNumber);
        return convertToDto(phoneNumber);
    }

    public Long deletePhoneNumber(Long id) {
        phoneNumberRepository.deleteById(id);
        return id;
    }

    private PhoneNumberDto convertToDto(PhoneNumber phoneNumber) {
        return new PhoneNumberDto(phoneNumber.getId(), phoneNumber.getPhoneNumber(), phoneNumber.getCountryCode());
    }

    private PhoneNumber convertToEntity(PhoneNumberDto phoneNumberDto) {
        return new PhoneNumber(phoneNumberDto.getId(), phoneNumberDto.getPhoneNumber(), phoneNumberDto.getCountryCode());
    }
}