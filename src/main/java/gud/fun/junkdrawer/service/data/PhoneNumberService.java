package gud.fun.junkdrawer.service.data;

import gud.fun.junkdrawer.dto.assembler.PhoneNumberResponseDtoAssembler;
import gud.fun.junkdrawer.dto.phonenumber.PhoneNumberRequestDto;
import gud.fun.junkdrawer.dto.phonenumber.PhoneNumberResponseDto;
import gud.fun.junkdrawer.persistance.model.PhoneNumber;
import gud.fun.junkdrawer.persistance.repository.PhoneNumberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedModel;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class PhoneNumberService implements JunkDataService<PhoneNumberRequestDto,PhoneNumberResponseDto,PhoneNumber>{

    @Autowired
    private PhoneNumberRepository phoneNumberRepository;

    @Autowired
    private PhoneNumberResponseDtoAssembler phoneNumberResponseDtoAssembler;

    @Autowired
    private PagedResourcesAssembler<PhoneNumber> pagedResourcesAssembler;

    @Override
    public PagedModel<PhoneNumberResponseDto> getAll(Pageable pageable) {
        return pagedResourcesAssembler.toModel(phoneNumberRepository.findAll(pageable), phoneNumberResponseDtoAssembler);
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
        PhoneNumber phoneNumber = phoneNumberRepository.findById(phoneNumberDto.getId())
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
        response.setId(id);
        return response;
    }

    @Override
    public PhoneNumberResponseDto toResponseDTO(PhoneNumber phoneNumber) {
        return new PhoneNumberResponseDto(phoneNumber.getId(), phoneNumber.getPhoneNumber(), phoneNumber.getCountryCode());
    }

    @Override
    public PhoneNumber toEntity(PhoneNumberRequestDto dto) {
        return new PhoneNumber(
                dto.getId() != null ? dto.getId():null,
                dto.getPhoneNumber(),
                dto.getCountryCode());
    }
}