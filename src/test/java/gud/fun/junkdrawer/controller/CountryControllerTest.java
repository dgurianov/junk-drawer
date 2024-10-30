package gud.fun.junkdrawer.controller;

import gud.fun.junkdrawer.configuration.Endpoints;
import gud.fun.junkdrawer.controller.data.CountryController;
import gud.fun.junkdrawer.dto.country.CountryRequestDto;
import gud.fun.junkdrawer.dto.country.CountryResponseDto;
import gud.fun.junkdrawer.service.data.CountryService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CountryController.class)
class CountryControllerTest {

    private final UUID TEST_UUID = UUID.fromString("e6c96a16-51b4-4ac7-bbe7-86e1a1f4da21");

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CountryService countryService;

    @Test
    void testGetCountryById() throws Exception {
        CountryResponseDto responseDto = new CountryResponseDto();
        responseDto.setId(TEST_UUID);

        when(countryService.getById(any(UUID.class))).thenReturn(responseDto);

        mockMvc.perform(get(Endpoints.COUNTRY + "/{id}", TEST_UUID)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").exists());
    }

    @Test
    void testGetAllCountries() throws Exception {
        CountryResponseDto responseDto = new CountryResponseDto();
        responseDto.setId(TEST_UUID);
        PagedModel<CountryResponseDto> responseDtos = PagedModel.of(Collections.singletonList(responseDto), new PagedModel.PageMetadata(1,1,1));

        when(countryService.getAll(any(Pageable.class))).thenReturn(responseDtos);

        mockMvc.perform(get(Endpoints.COUNTRY)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("._embedded.countries[0].id").exists());
    }

    @Test
    void testUpdateCountry() throws Exception {
        CountryResponseDto responseDto = new CountryResponseDto();
        responseDto.setId(TEST_UUID);

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
        responseDto.setId(TEST_UUID);

        when(countryService.delete(any(UUID.class))).thenReturn(responseDto);

        mockMvc.perform(delete(Endpoints.COUNTRY+ "/{id}", TEST_UUID)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").exists());
    }
}