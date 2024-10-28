package gud.fun.junkdrawer.service.data;

import gud.fun.junkdrawer.dto.assembler.PhoneNumberResponseDtoAssembler;
import gud.fun.junkdrawer.dto.phonenumber.PhoneNumberRequestDto;
import gud.fun.junkdrawer.dto.phonenumber.PhoneNumberResponseDto;
import gud.fun.junkdrawer.persistance.model.PhoneNumber;
import gud.fun.junkdrawer.persistance.repository.PhoneNumberRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedModel;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class PhoneNumberServiceTest {

    private final UUID TEST_UUID = UUID.fromString("e6c96a16-51b4-4ac7-bbe7-86e1a1f4da21");

    @Mock
    private PhoneNumberResponseDtoAssembler phoneNumberDtoAssembler;

    @Mock
    private PagedResourcesAssembler<PhoneNumber> pagedResourcesAssembler;

    @Mock
    private PhoneNumberRepository phoneNumberRepository;

    @InjectMocks
    private PhoneNumberService phoneNumberService;

    private PhoneNumber phoneNumber;
    private PhoneNumberRequestDto phoneNumberRequestDto;
    private PhoneNumberResponseDto phoneNumberResponseDto;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        phoneNumber = new PhoneNumber();
        phoneNumber.setId(TEST_UUID);
        phoneNumber.setPhoneNumber("1234567890");

        phoneNumberRequestDto = new PhoneNumberRequestDto();
        phoneNumberRequestDto.setId(TEST_UUID);
        phoneNumberRequestDto.setPhoneNumber("1234567890");

        phoneNumberResponseDto = new PhoneNumberResponseDto();
        phoneNumberResponseDto.setId(TEST_UUID);
        phoneNumberResponseDto.setPhoneNumber("1234567890");

    }

    @Test
    void testCreate() {
        when(phoneNumberRepository.save(any(PhoneNumber.class))).thenReturn(phoneNumber);

        PhoneNumberResponseDto responseDto = phoneNumberService.create(phoneNumberRequestDto);

        assertNotNull(responseDto);
        assertEquals(phoneNumber.getId(), responseDto.getId());
        assertEquals(phoneNumber.getPhoneNumber(), responseDto.getPhoneNumber());

        verify(phoneNumberRepository, times(1)).save(any(PhoneNumber.class));
    }

    @Test
    void testGetById() {
        when(phoneNumberRepository.findById(any(UUID.class))).thenReturn(Optional.of(phoneNumber));

        PhoneNumberResponseDto responseDto = phoneNumberService.getById(TEST_UUID);

        assertNotNull(responseDto);
        assertEquals(phoneNumber.getId(), responseDto.getId());
        assertEquals(phoneNumber.getPhoneNumber(), responseDto.getPhoneNumber());

        verify(phoneNumberRepository, times(1)).findById(any(UUID.class));
    }

    @Test
    void testGetAll() {
        when(phoneNumberRepository.findAll(any(Pageable.class))).thenReturn(new PageImpl<>(Arrays.asList(phoneNumber)));
        when(pagedResourcesAssembler.toModel(any(Page.class), any(PhoneNumberResponseDtoAssembler.class))).thenReturn(PagedModel.of(List.of(phoneNumberResponseDto), new PagedModel.PageMetadata(1, 1, 1, 1)));
        PagedModel<PhoneNumberResponseDto> responseDtos = phoneNumberService.getAll(PageRequest.of(0,1));
        PhoneNumberResponseDto responseDto = responseDtos.getContent().iterator().next();
        assertEquals(phoneNumber.getId(), responseDto.getId());
        assertEquals(phoneNumber.getPhoneNumber(), responseDto.getPhoneNumber());

        verify(phoneNumberRepository, times(1)).findAll(any(Pageable.class));
    }

    @Test
    void testUpdate() {
        when(phoneNumberRepository.findById(any(UUID.class))).thenReturn(Optional.of(phoneNumber));
        when(phoneNumberRepository.save(any(PhoneNumber.class))).thenReturn(phoneNumber);

        PhoneNumberResponseDto responseDto = phoneNumberService.update(phoneNumberRequestDto);

        assertNotNull(responseDto);
        assertEquals(phoneNumber.getId(), responseDto.getId());
        assertEquals(phoneNumber.getPhoneNumber(), responseDto.getPhoneNumber());

        verify(phoneNumberRepository, times(1)).findById(any(UUID.class));
        verify(phoneNumberRepository, times(1)).save(any(PhoneNumber.class));
    }

    @Test
    void testDelete() {
        doNothing().when(phoneNumberRepository).deleteById(any(UUID.class));

        PhoneNumberResponseDto responseDto = phoneNumberService.delete(TEST_UUID);

        assertNotNull(responseDto);
        assertEquals(TEST_UUID, responseDto.getId());

        verify(phoneNumberRepository, times(1)).deleteById(any(UUID.class));
    }
}