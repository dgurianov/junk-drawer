package gud.fun.junkdrawer.controller;

import gud.fun.junkdrawer.configuration.Endpoints;
import gud.fun.junkdrawer.dto.country.CountryRequestDto;
import gud.fun.junkdrawer.dto.country.CountryResponseDto;
import gud.fun.junkdrawer.service.data.CountryService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Collections;
import java.util.List;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class CountryControllerTest {

    private final UUID TEST_UUID = UUID.fromString("e6c96a16-51b4-4ac7-bbe7-86e1a1f4da21");

    private MockMvc mockMvc;

    @Mock
    private CountryService countryService;

    @InjectMocks
    private CountryController countryController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(countryController).build();
    }

    @Test
    void testGetCountryById() throws Exception {
        CountryResponseDto responseDto = new CountryResponseDto();
        responseDto.setId(TEST_UUID.toString());

        when(countryService.getById(any(UUID.class))).thenReturn(responseDto);

        mockMvc.perform(get(Endpoints.COUNTRY + "/{id}", TEST_UUID.toString())
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").exists());
    }

    @Test
    void testGetAllCountries() throws Exception {
        CountryResponseDto responseDto = new CountryResponseDto();
        responseDto.setId(TEST_UUID.toString());
        List<CountryResponseDto> responseDtos = Collections.singletonList(responseDto);

        when(countryService.getAll()).thenReturn(responseDtos);

        mockMvc.perform(get(Endpoints.COUNTRY)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").exists());
    }

    @Test
    void testUpdateCountry() throws Exception {
        CountryRequestDto requestDto = new CountryRequestDto();
        CountryResponseDto responseDto = new CountryResponseDto();
        responseDto.setId(TEST_UUID.toString());

        when(countryService.update(any(CountryRequestDto.class))).thenReturn(responseDto);

        mockMvc.perform(put(Endpoints.COUNTRY)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\":\"Updated Country\",\"countryCode\":\"UC\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").exists());
    }

    @Test
    void testDeleteCountry() throws Exception {
        CountryResponseDto responseDto = new CountryResponseDto();
        responseDto.setId(TEST_UUID.toString());

        when(countryService.delete(any(UUID.class))).thenReturn(responseDto);

        mockMvc.perform(delete(Endpoints.COUNTRY+ "/{id}", TEST_UUID.toString())
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").exists());
    }
}