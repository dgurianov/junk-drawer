package gud.fun.junkdrawer.service.data;

import gud.fun.junkdrawer.dto.city.CityRequestDto;
import gud.fun.junkdrawer.dto.city.CityResponseDto;
import gud.fun.junkdrawer.persistance.model.City;
import gud.fun.junkdrawer.persistance.repository.CityRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class CityServiceTest {

    private final UUID TEST_UUID = UUID.fromString("e6c96a16-51b4-4ac7-bbe7-86e1a1f4da21");

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
        city.setId(TEST_UUID);
        city.setName("Berlin");
        city.setCountryCode("DEU");

        cityRequestDto = new CityRequestDto();
        cityRequestDto.setId(TEST_UUID);
        cityRequestDto.setName("Berlin");
        cityRequestDto.setCountryCode("DEU");
    }

    @Test
    void testCreate() {
        when(cityRepository.save(any(City.class))).thenReturn(city);

        CityResponseDto responseDto =  cityService.create(cityRequestDto);

        assertNotNull(responseDto);
        assertEquals(city.getId(), responseDto.getId());
        assertEquals(city.getName(), responseDto.getName());
        assertEquals(city.getCountryCode(), responseDto.getCountryCode());

        verify(cityRepository, times(1)).save(any(City.class));
    }

    @Test
    void testGetById() {
        when(cityRepository.findById(any(UUID.class))).thenReturn(Optional.of(city));

        CityResponseDto responseDto =  cityService.getById(TEST_UUID);

        assertNotNull(responseDto);
        assertEquals(city.getId(), responseDto.getId());
        assertEquals(city.getName(), responseDto.getName());
        assertEquals(city.getCountryCode(), responseDto.getCountryCode());

        verify(cityRepository, times(1)).findById(any(UUID.class));
    }

    @Test
    void testGetAll() {
        when(cityRepository.findAll()).thenReturn(Arrays.asList(city));
        List<CityResponseDto> responseDtos = cityService.getAll();

        assertEquals(city.getId(), responseDtos.get(0).getId());
        assertEquals(city.getName(), responseDtos.get(0).getName());
        assertEquals(city.getCountryCode(), responseDtos.get(0).getCountryCode());

        verify(cityRepository, times(1)).findAll();
    }

    @Test
    void testUpdate() {
        when(cityRepository.findById(any(UUID.class))).thenReturn(Optional.of(city));
        when(cityRepository.save(any(City.class))).thenReturn(city);

        CityResponseDto responseDto =  cityService.update(cityRequestDto);

        assertNotNull(responseDto);
        assertEquals(city.getId(), responseDto.getId());
        assertEquals(city.getName(), responseDto.getName());
        assertEquals(city.getCountryCode(), responseDto.getCountryCode());

        verify(cityRepository, times(1)).findById(any(UUID.class));
        verify(cityRepository, times(1)).save(any(City.class));
    }

    @Test
    void testDelete() {
        doNothing().when(cityRepository).deleteById(any(UUID.class));

        CityResponseDto responseDto =  cityService.delete(TEST_UUID);

        assertNotNull(responseDto);
        assertEquals(TEST_UUID, responseDto.getId());

        verify(cityRepository, times(1)).deleteById(any(UUID.class));
    }
}
