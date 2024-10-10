package gud.fun.junkdrawer.controller;

import gud.fun.junkdrawer.configuration.Endpoints;
import gud.fun.junkdrawer.dto.city.CityRequestDto;
import gud.fun.junkdrawer.dto.city.CityResponseDto;
import gud.fun.junkdrawer.persistance.model.City;
import gud.fun.junkdrawer.service.data.JunkDataService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(CityController.class)
public class CityControllerTest {

    private final UUID TEST_UUID = UUID.fromString("e6c96a16-51b4-4ac7-bbe7-86e1a1f4da21");

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private JunkDataService<CityRequestDto,CityResponseDto, City> cityService;

    private CityResponseDto cityDto;

    @BeforeEach
    public void setUp() {
        cityDto = new CityResponseDto();
        cityDto.setId(TEST_UUID.toString());
        cityDto.setName("Berlin");
        cityDto.setCountryCode("DEU");
    }

    @Test
    public void testCreateCity() throws Exception {
        when(cityService.create(any(CityRequestDto.class))).thenReturn(cityDto);

        mockMvc.perform(post(Endpoints.CITY)
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\":\"Berlin\",\"country\":\"DEU\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Berlin"))
                .andExpect(jsonPath("$.countryCode").value("DEU"));
    }

    @Test
    public void testGetCityById() throws Exception {
        when(cityService.getById(any(UUID.class))).thenReturn(cityDto);

        mockMvc.perform(get(Endpoints.CITY + "/" + TEST_UUID.toString())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Berlin"))
                .andExpect(jsonPath("$.countryCode").value("DEU"));
    }

    @Test
    public void testGetAllCities() throws Exception {
        List<CityResponseDto> cities = Arrays.asList(cityDto);
        when(cityService.getAll()).thenReturn(cities);

        mockMvc.perform(get(Endpoints.CITY))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("Berlin"))
                .andExpect(jsonPath("$[0].countryCode").value("DEU"));
    }

    @Test
    public void testUpdateCity() throws Exception {
        when(cityService.update(any(UUID.class), any(CityRequestDto.class))).thenReturn(cityDto);

        mockMvc.perform(put(Endpoints.CITY + "/" + TEST_UUID.toString())
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\":\"Berlin\",\"countryCode\":\"DEU\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Berlin"))
                .andExpect(jsonPath("$.countryCode").value("DEU"));
    }

    @Test
    public void testDeleteCity() throws Exception {
        when(cityService.delete(any(UUID.class))).thenReturn(cityDto);
        mockMvc.perform(delete(Endpoints.CITY + "/" + TEST_UUID.toString()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(TEST_UUID.toString()));
    }
}