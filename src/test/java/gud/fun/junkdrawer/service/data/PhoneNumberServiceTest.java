package gud.fun.junkdrawer.service.data;

import gud.fun.junkdrawer.dto.phonenumber.PhoneNumberRequestDto;
import gud.fun.junkdrawer.dto.phonenumber.PhoneNumberResponseDto;
import gud.fun.junkdrawer.persistance.model.PhoneNumber;
import gud.fun.junkdrawer.persistance.repository.PhoneNumberRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class PhoneNumberServiceTest {

    private final UUID TEST_UUID = UUID.fromString("e6c96a16-51b4-4ac7-bbe7-86e1a1f4da21");

    @Mock
    private PhoneNumberRepository phoneNumberRepository;

    @InjectMocks
    private PhoneNumberService phoneNumberService;

    private PhoneNumber phoneNumber;
    private PhoneNumberRequestDto phoneNumberRequestDto;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        phoneNumber = new PhoneNumber();
        phoneNumber.setId(TEST_UUID);
        phoneNumber.setPhoneNumber("1234567890");

        phoneNumberRequestDto = new PhoneNumberRequestDto();
        phoneNumberRequestDto.setId(TEST_UUID.toString());
        phoneNumberRequestDto.setPhoneNumber("1234567890");
    }

    @Test
    void testCreate() {
        when(phoneNumberRepository.save(any(PhoneNumber.class))).thenReturn(phoneNumber);

        PhoneNumberResponseDto responseDto = phoneNumberService.create(phoneNumberRequestDto);

        assertNotNull(responseDto);
        assertEquals(phoneNumber.getId().toString(), responseDto.getId());
        assertEquals(phoneNumber.getPhoneNumber(), responseDto.getPhoneNumber());

        verify(phoneNumberRepository, times(1)).save(any(PhoneNumber.class));
    }

    @Test
    void testGetById() {
        when(phoneNumberRepository.findById(any(UUID.class))).thenReturn(Optional.of(phoneNumber));

        PhoneNumberResponseDto responseDto = phoneNumberService.getById(TEST_UUID);

        assertNotNull(responseDto);
        assertEquals(phoneNumber.getId().toString(), responseDto.getId());
        assertEquals(phoneNumber.getPhoneNumber(), responseDto.getPhoneNumber());

        verify(phoneNumberRepository, times(1)).findById(any(UUID.class));
    }

    @Test
    void testGetAll() {
        when(phoneNumberRepository.findAll()).thenReturn(Arrays.asList(phoneNumber));
        List<PhoneNumberResponseDto> responseDtos = phoneNumberService.getAll();

        assertEquals(phoneNumber.getId().toString(), responseDtos.get(0).getId());
        assertEquals(phoneNumber.getPhoneNumber(), responseDtos.get(0).getPhoneNumber());

        verify(phoneNumberRepository, times(1)).findAll();
    }

    @Test
    void testUpdate() {
        when(phoneNumberRepository.findById(any(UUID.class))).thenReturn(Optional.of(phoneNumber));
        when(phoneNumberRepository.save(any(PhoneNumber.class))).thenReturn(phoneNumber);

        PhoneNumberResponseDto responseDto = phoneNumberService.update(phoneNumberRequestDto);

        assertNotNull(responseDto);
        assertEquals(phoneNumber.getId().toString(), responseDto.getId());
        assertEquals(phoneNumber.getPhoneNumber(), responseDto.getPhoneNumber());

        verify(phoneNumberRepository, times(1)).findById(any(UUID.class));
        verify(phoneNumberRepository, times(1)).save(any(PhoneNumber.class));
    }

    @Test
    void testDelete() {
        doNothing().when(phoneNumberRepository).deleteById(any(UUID.class));

        PhoneNumberResponseDto responseDto = phoneNumberService.delete(TEST_UUID);

        assertNotNull(responseDto);
        assertEquals(TEST_UUID.toString(), responseDto.getId());

        verify(phoneNumberRepository, times(1)).deleteById(any(UUID.class));
    }
}