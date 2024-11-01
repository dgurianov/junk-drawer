package gud.fun.junkdrawer.service.stream;

import gud.fun.junkdrawer.dto.phonenumber.PhoneNumberResponseDto;
import gud.fun.junkdrawer.persistance.model.PhoneNumber;
import gud.fun.junkdrawer.util.generator.PhoneNumberGenerator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

class PhoneNumberStreamServiceTest {

    @Mock
    private PhoneNumberGenerator phoneNumberGenerator;

    @InjectMocks
    private PhoneNumberStreamService phoneNumberStreamService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllStream() {
        PhoneNumber phoneNumber = new PhoneNumber();
        phoneNumber.setId(UUID.randomUUID());
        phoneNumber.setPhoneNumber("123-456-7890");
        phoneNumber.setCountryCode("TC");

        when(phoneNumberGenerator.generateRandom()).thenReturn(phoneNumber);

        List<PhoneNumberResponseDto> result = phoneNumberStreamService.getAllStream(5);

        assertEquals(5, result.size());
        for (PhoneNumberResponseDto dto : result) {
            assertEquals(phoneNumber.getId(), dto.getId());
            assertEquals(phoneNumber.getPhoneNumber(), dto.getPhoneNumber());
            assertEquals(phoneNumber.getCountryCode(), dto.getCountryCode());
        }
    }

    @Test
    void testGetAllStreamWithLimitZero() {
        List<PhoneNumberResponseDto> result = phoneNumberStreamService.getAllStream(0);
        assertEquals(0, result.size());
    }
}