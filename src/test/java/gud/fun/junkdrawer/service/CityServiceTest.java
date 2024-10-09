package gud.fun.junkdrawer.service;

import gud.fun.junkdrawer.dto.city.CityRequestDto;
import gud.fun.junkdrawer.dto.city.CityResponseDto;
import gud.fun.junkdrawer.persistance.model.City;
import gud.fun.junkdrawer.persistance.repository.CityRepository;
import gud.fun.junkdrawer.service.data.CityService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

class CityServiceTest {

    @Mock
    private CityRepository cityRepository;

    @InjectMocks
    private CityService cityService;

    private City city;
    private CityRequestDto cityRequestDto;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        city = new City();
        city.setId(1L);
        city.setName("Berlin");
        city.setCountryCode("DEU");

        cityRequestDto = new CityRequestDto();
        cityRequestDto.setName("Berlin");
        cityRequestDto.setCountryCode("DEU");
    }

    @Test
    void testCreate() {
        when(cityRepository.save(any(City.class))).thenReturn(city);

        CityResponseDto responseDto = (CityResponseDto) cityService.create(cityRequestDto);

        assertNotNull(responseDto);
        assertEquals(city.getId(), responseDto.getId());
        assertEquals(city.getName(), responseDto.getName());
        assertEquals(city.getCountryCode(), responseDto.getCountryCode());

        verify(cityRepository, times(1)).save(any(City.class));
    }

    @Test
    void testGetById() {
        when(cityRepository.findById(anyLong())).thenReturn(Optional.of(city));

        CityResponseDto responseDto = (CityResponseDto) cityService.getById(1L);

        assertNotNull(responseDto);
        assertEquals(city.getId(), responseDto.getId());
        assertEquals(city.getName(), responseDto.getName());
        assertEquals(city.getCountryCode(), responseDto.getCountryCode());

        verify(cityRepository, times(1)).findById(anyLong());
    }

    @Test
    void testGetAll() {
        when(cityRepository.findAll()).thenReturn(Arrays.asList(city));
        List<CityResponseDto> responseDtos = (List<CityResponseDto>) cityService.getAll();

        assertEquals(city.getId(), responseDtos.get(0).getId());
        assertEquals(city.getName(), responseDtos.get(0).getName());
        assertEquals(city.getCountryCode(), responseDtos.get(0).getCountryCode());

        verify(cityRepository, times(1)).findAll();
    }

    @Test
    void testUpdate() {
        when(cityRepository.findById(anyLong())).thenReturn(Optional.of(city));
        when(cityRepository.save(any(City.class))).thenReturn(city);

        CityResponseDto responseDto = (CityResponseDto) cityService.update(1L, cityRequestDto);

        assertNotNull(responseDto);
        assertEquals(city.getId(), responseDto.getId());
        assertEquals(city.getName(), responseDto.getName());
        assertEquals(city.getCountryCode(), responseDto.getCountryCode());

        verify(cityRepository, times(1)).findById(anyLong());
        verify(cityRepository, times(1)).save(any(City.class));
    }

    @Test
    void testDelete() {
        doNothing().when(cityRepository).deleteById(anyLong());

        CityResponseDto responseDto = (CityResponseDto) cityService.delete(1L);

        assertNotNull(responseDto);
        assertEquals(1L, responseDto.getId());

        verify(cityRepository, times(1)).deleteById(anyLong());
    }
}
