package gud.fun.junkdrawer.controller;

import gud.fun.junkdrawer.configuration.Endpoints;
import gud.fun.junkdrawer.dto.city.CityRequestDto;
import gud.fun.junkdrawer.dto.city.CityResponseDto;
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

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(CityController.class)
public class CityControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private JunkDataService dataService;

    private CityResponseDto cityDto;

    @BeforeEach
    public void setUp() {
        cityDto = new CityResponseDto();
        cityDto.setId(1L);
        cityDto.setName("Berlin");
        cityDto.setCountryCode("DEU");
    }

    @Test
    public void testCreateCity() throws Exception {
        when(dataService.create(any(CityRequestDto.class))).thenReturn(cityDto);

        mockMvc.perform(post(Endpoints.CITY)
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\":\"Berlin\",\"country\":\"DEU\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Berlin"))
                .andExpect(jsonPath("$.countryCode").value("DEU"));
    }

    @Test
    public void testGetCityById() throws Exception {
        when(dataService.getById(anyLong())).thenReturn(cityDto);

        mockMvc.perform(get(Endpoints.CITY + "/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Berlin"))
                .andExpect(jsonPath("$.countryCode").value("DEU"));
    }

    @Test
    public void testGetAllCities() throws Exception {
        List<CityResponseDto> cities = Arrays.asList(cityDto);
        when(dataService.getAll()).thenReturn(cities);

        mockMvc.perform(get(Endpoints.CITY))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("Berlin"))
                .andExpect(jsonPath("$[0].countryCode").value("DEU"));
    }

    @Test
    public void testUpdateCity() throws Exception {
        when(dataService.update(anyLong(), any(CityRequestDto.class))).thenReturn(cityDto);

        mockMvc.perform(put(Endpoints.CITY + "/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\":\"Berlin\",\"countryCode\":\"DEU\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Berlin"))
                .andExpect(jsonPath("$.countryCode").value("DEU"));
    }

    @Test
    public void testDeleteCity() throws Exception {
        when(dataService.delete(anyLong())).thenReturn(cityDto);
        mockMvc.perform(delete(Endpoints.CITY + "/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value("1"));
    }
}