package gud.fun.junkdrawer.controller;

import gud.fun.junkdrawer.configuration.Endpoints;
import gud.fun.junkdrawer.dto.phonenumber.PhoneNumberDto;
import gud.fun.junkdrawer.service.PhoneNumberService;
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

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class PhoneNumberControllerTest {

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
        List<PhoneNumberDto> phoneNumbers = Arrays.asList(
                new PhoneNumberDto(1L, "1234567890", "US"),
                new PhoneNumberDto(2L, "0987654321", "CA")
        );

        when(phoneNumberService.getAllPhoneNumbers()).thenReturn(phoneNumbers);

        mockMvc.perform(get(Endpoints.PHONE_NUMBER))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1L))
                .andExpect(jsonPath("$[0].phoneNumber").value("1234567890"))
                .andExpect(jsonPath("$[0].countryCode").value("US"))
                .andExpect(jsonPath("$[1].id").value(2L))
                .andExpect(jsonPath("$[1].phoneNumber").value("0987654321"))
                .andExpect(jsonPath("$[1].countryCode").value("CA"));

        verify(phoneNumberService, times(1)).getAllPhoneNumbers();
    }

    @Test
    void testGetPhoneNumberById() throws Exception {
        PhoneNumberDto phoneNumber = new PhoneNumberDto(1L, "1234567890", "US");

        when(phoneNumberService.getPhoneNumberById(anyLong())).thenReturn(phoneNumber);

        mockMvc.perform(get(Endpoints.PHONE_NUMBER + "/{id}", 1L))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.phoneNumber").value("1234567890"))
                .andExpect(jsonPath("$.countryCode").value("US"));

        verify(phoneNumberService, times(1)).getPhoneNumberById(1L);
    }

    @Test
    void testCreatePhoneNumber() throws Exception {
        PhoneNumberDto phoneNumber = new PhoneNumberDto(1L, "1234567890", "US");

        when(phoneNumberService.createPhoneNumber(any(PhoneNumberDto.class))).thenReturn(phoneNumber);

        mockMvc.perform(post(Endpoints.PHONE_NUMBER)
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"phoneNumber\":\"1234567890\",\"countryCode\":\"US\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.phoneNumber").value("1234567890"))
                .andExpect(jsonPath("$.countryCode").value("US"));

        verify(phoneNumberService, times(1)).createPhoneNumber(any(PhoneNumberDto.class));
    }

    @Test
    void testUpdatePhoneNumber() throws Exception {
        PhoneNumberDto phoneNumber = new PhoneNumberDto(1L, "1234567890", "DEU");

        when(phoneNumberService.updatePhoneNumber(anyLong(), any(PhoneNumberDto.class))).thenReturn(phoneNumber);

        mockMvc.perform(put(Endpoints.PHONE_NUMBER + "/{id}", 1L)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"phoneNumber\":\"1234567890\",\"countryCode\":\"DEU\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.countryCode").value("DEU"));;
    }

    @Test
    void  testDeletePhoneNumber() throws Exception{
        PhoneNumberDto phoneNumber = new PhoneNumberDto(1L, "1234567890", "US");

        when(phoneNumberService.deletePhoneNumber(anyLong())).thenReturn(phoneNumber);
         mockMvc.perform(delete(Endpoints.PHONE_NUMBER + "/{id}", 1L))
                .andExpect(status().isOk())
                 .andExpect(jsonPath("$.id").value("1"));

        verify(phoneNumberService, times(1)).deletePhoneNumber(1L);
    }
}