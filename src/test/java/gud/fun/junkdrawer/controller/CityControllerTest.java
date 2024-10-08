package gud.fun.junkdrawer.controller;

import gud.fun.junkdrawer.configuration.Endpoints;
import gud.fun.junkdrawer.dto.CityDto;
import gud.fun.junkdrawer.service.CityService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

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
    private CityService cityService;

    private CityDto cityDto;

    @BeforeEach
    public void setUp() {
        cityDto = new CityDto();
        cityDto.setName("Berlin");
        cityDto.setCountryCode("DEU");
    }

    @Test
    public void testCreateCity() throws Exception {
        when(cityService.createCity(any(CityDto.class))).thenReturn(cityDto);

        mockMvc.perform(post(Endpoints.CITY)
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\":\"Berlin\",\"country\":\"DEU\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Berlin"))
                .andExpect(jsonPath("$.countryCode").value("DEU"));
    }

    @Test
    public void testGetCityById() throws Exception {
        when(cityService.getCityById(anyLong())).thenReturn(cityDto);

        mockMvc.perform(get("/city/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Test City"))
                .andExpect(jsonPath("$.countryCode").value("Test Country"));
    }

    @Test
    public void testGetAllCities() throws Exception {
        List<CityDto> cities = Arrays.asList(cityDto);
        when(cityService.getAllCities()).thenReturn(cities);

        mockMvc.perform(get("/city"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("Test City"))
                .andExpect(jsonPath("$[0].country").value("Test Country"));
    }

    @Test
    public void testUpdateCity() throws Exception {
        when(cityService.updateCity(anyLong(), any(CityDto.class))).thenReturn(cityDto);

        mockMvc.perform(put("/city/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\":\"Updated City\",\"country\":\"Updated Country\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Test City"))
                .andExpect(jsonPath("$.country").value("Test Country"));
    }

    @Test
    public void testDeleteCity() throws Exception {
        mockMvc.perform(delete("/city/1"))
                .andExpect(status().isNoContent());
    }
}