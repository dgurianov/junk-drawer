package gud.fun.junkdrawer.service.data;

import gud.fun.junkdrawer.dto.phonenumber.PhoneNumberRequestDto;
import gud.fun.junkdrawer.dto.phonenumber.PhoneNumberResponseDto;
import gud.fun.junkdrawer.persistance.model.PhoneNumber;
import gud.fun.junkdrawer.persistance.repository.PhoneNumberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class PhoneNumberService implements JunkDataService<PhoneNumberRequestDto,PhoneNumberResponseDto,PhoneNumber>{

    @Autowired
    private PhoneNumberRepository phoneNumberRepository;

    @Override
    public List<PhoneNumberResponseDto> getAll() {
        List<PhoneNumber> phoneNumbers = phoneNumberRepository.findAll();
        return phoneNumbers.stream()
                .map(this::toResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public PhoneNumberResponseDto getById(UUID id) {
        PhoneNumber phoneNumber = phoneNumberRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Phone number not found for id: " + id));
        return toResponseDTO(phoneNumber);
    }

    @Override
    public PhoneNumberResponseDto create(PhoneNumberRequestDto phoneNumberDto) {
        PhoneNumber phoneNumber = toEntity(phoneNumberDto);
        phoneNumber = phoneNumberRepository.save(phoneNumber);
        return toResponseDTO(phoneNumber);
    }

    @Override
    public PhoneNumberResponseDto update(PhoneNumberRequestDto phoneNumberDto) {
        PhoneNumber phoneNumber = phoneNumberRepository.findById(UUID.fromString(phoneNumberDto.getId()))
                .orElseThrow(() -> new IllegalArgumentException("Phone number not found for id: " + phoneNumberDto.getId()));
        phoneNumber.setPhoneNumber(phoneNumberDto.getPhoneNumber());
        phoneNumber.setCountryCode(phoneNumberDto.getCountryCode());
        phoneNumber = phoneNumberRepository.save(phoneNumber);
        return toResponseDTO(phoneNumber);
    }

    @Override
    public PhoneNumberResponseDto delete(UUID id) {
        phoneNumberRepository.deleteById(id);
        PhoneNumberResponseDto response = new PhoneNumberResponseDto();
        response.setId(id.toString());
        return response;
    }

    @Override
    public PhoneNumberResponseDto toResponseDTO(PhoneNumber phoneNumber) {
        return new PhoneNumberResponseDto(phoneNumber.getId().toString(), phoneNumber.getPhoneNumber(), phoneNumber.getCountryCode());
    }

    @Override
    public PhoneNumber toEntity(PhoneNumberRequestDto dto) {
        return new PhoneNumber(
                dto.getId() != null ? UUID.fromString(dto.getId()):null,
                dto.getPhoneNumber(),
                dto.getCountryCode());
    }
}