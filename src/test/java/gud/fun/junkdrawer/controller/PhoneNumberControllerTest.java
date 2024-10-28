package gud.fun.junkdrawer.controller;

import gud.fun.junkdrawer.configuration.Endpoints;
import gud.fun.junkdrawer.dto.phonenumber.PhoneNumberRequestDto;
import gud.fun.junkdrawer.dto.phonenumber.PhoneNumberResponseDto;
import gud.fun.junkdrawer.service.data.PhoneNumberService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(PhoneNumberController.class)
class PhoneNumberControllerTest {

    private final UUID TEST_UUID_1 = UUID.fromString("e6c96a16-51b4-4ac7-bbe7-86e1a1f4da21");
    private final UUID TEST_UUID_2 = UUID.fromString("e6c96a16-51b4-4ac7-bbe7-86e1a1f4da22");

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PhoneNumberService phoneNumberService;

    @Test
    void testGetAllPhoneNumbers() throws Exception {
        PagedModel<PhoneNumberResponseDto> phoneNumbers = PagedModel.of(Arrays.asList(
                new PhoneNumberResponseDto(TEST_UUID_1, "1234567890", "USA"),
                new PhoneNumberResponseDto(TEST_UUID_2, "0987654321", "CA")
        ), new PagedModel.PageMetadata(2, 1, 2));

        when(phoneNumberService.getAll(any(Pageable.class))).thenReturn(phoneNumbers);

        mockMvc.perform(get(Endpoints.PHONE_NUMBER))
                .andExpect(status().isOk())
                .andExpect(jsonPath("._embedded.phone_numbers[0].id").value(TEST_UUID_1.toString()))
                .andExpect(jsonPath("._embedded.phone_numbers[0].phoneNumber").value("1234567890"))
                .andExpect(jsonPath("._embedded.phone_numbers[0].countryCode").value("USA"))
                .andExpect(jsonPath("._embedded.phone_numbers[1].id").value(TEST_UUID_2.toString()))
                .andExpect(jsonPath("._embedded.phone_numbers[1].phoneNumber").value("0987654321"))
                .andExpect(jsonPath("._embedded.phone_numbers[1].countryCode").value("CA"));

        verify(phoneNumberService, times(1)).getAll(any(Pageable.class));
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