package gud.fun.junkdrawer.controller;

import gud.fun.junkdrawer.configuration.Endpoints;
import gud.fun.junkdrawer.dto.phonenumber.PhoneNumberRequestDto;
import gud.fun.junkdrawer.dto.phonenumber.PhoneNumberResponseDto;
import gud.fun.junkdrawer.service.data.PhoneNumberService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class PhoneNumberControllerTest {

    private final UUID TEST_UUID_1 = UUID.fromString("e6c96a16-51b4-4ac7-bbe7-86e1a1f4da21");
    private final UUID TEST_UUID_2 = UUID.fromString("e6c96a16-51b4-4ac7-bbe7-86e1a1f4da22");

    private MockMvc mockMvc;

    @Mock
    private PhoneNumberService phoneNumberService;

    @InjectMocks
    private PhoneNumberController phoneNumberController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(phoneNumberController).build();
    }

    @Test
    void testGetAllPhoneNumbers() throws Exception {
        List<PhoneNumberResponseDto> phoneNumbers = Arrays.asList(
                new PhoneNumberResponseDto(TEST_UUID_1, "1234567890", "USA"),
                new PhoneNumberResponseDto(TEST_UUID_2, "0987654321", "CA")
        );

        when(phoneNumberService.getAll()).thenReturn(phoneNumbers);

        mockMvc.perform(get(Endpoints.PHONE_NUMBER))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(TEST_UUID_1.toString()))
                .andExpect(jsonPath("$[0].phoneNumber").value("1234567890"))
                .andExpect(jsonPath("$[0].countryCode").value("USA"))
                .andExpect(jsonPath("$[1].id").value(TEST_UUID_2.toString()))
                .andExpect(jsonPath("$[1].phoneNumber").value("0987654321"))
                .andExpect(jsonPath("$[1].countryCode").value("CA"));

        verify(phoneNumberService, times(1)).getAll();
    }

    @Test
    void testGetPhoneNumberById() throws Exception {
        PhoneNumberResponseDto phoneNumber = new PhoneNumberResponseDto(TEST_UUID_1, "1234567890", "USA");

        when(phoneNumberService.getById(any(UUID.class))).thenReturn(phoneNumber);

        mockMvc.perform(get(Endpoints.PHONE_NUMBER + "/{id}", TEST_UUID_1))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(TEST_UUID_1.toString()))
                .andExpect(jsonPath("$.phoneNumber").value("1234567890"))
                .andExpect(jsonPath("$.countryCode").value("USA"));

        verify(phoneNumberService, times(1)).getById(any(UUID.class));
    }

    @Test
    void testUpdatePhoneNumber() throws Exception {
        PhoneNumberResponseDto phoneNumber = new PhoneNumberResponseDto(TEST_UUID_1, "1234567890", "DEU");

        when(phoneNumberService.update(any(PhoneNumberRequestDto.class))).thenReturn(phoneNumber);

        mockMvc.perform(put(Endpoints.PHONE_NUMBER)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"phoneNumber\":\"1234567890\",\"countryCode\":\"DEU\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.countryCode").value("DEU"));;
    }

    @Test
    void  testDeletePhoneNumber() throws Exception{
        PhoneNumberResponseDto phoneNumber = new PhoneNumberResponseDto(TEST_UUID_1, "1234567890", "US");

        when(phoneNumberService.delete(any(UUID.class))).thenReturn(phoneNumber);
         mockMvc.perform(delete(Endpoints.PHONE_NUMBER + "/{id}", TEST_UUID_1))
                .andExpect(status().isOk())
                 .andExpect(jsonPath("$.id").value(TEST_UUID_1.toString()));

        verify(phoneNumberService, times(1)).delete(TEST_UUID_1);
    }
}