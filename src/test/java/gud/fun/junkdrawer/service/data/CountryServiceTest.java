package gud.fun.junkdrawer.service.data;

import gud.fun.junkdrawer.dto.city.CityResponseDto;
import gud.fun.junkdrawer.dto.country.CountryRequestDto;
import gud.fun.junkdrawer.dto.country.CountryResponseDto;
import gud.fun.junkdrawer.persistance.model.City;
import gud.fun.junkdrawer.persistance.model.Country;
import gud.fun.junkdrawer.persistance.repository.CityRepository;
import gud.fun.junkdrawer.persistance.repository.CountryRepository;
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

class CountryServiceTest {

    private final UUID TEST_UUID_1 = UUID.fromString("e6c96a16-51b4-4ac7-bbe7-86e1a1f4da21");
    private final UUID TEST_UUID_2 = UUID.fromString("e6c96a16-51b4-4ac7-bbe7-86e1a1f4da22");

    @Mock
    private CountryRepository countryRepository;

    @Mock
    private CityRepository cityRepository;

    @Mock
    private CityService cityService;

    @InjectMocks
    private CountryService countryService;

    private Country country;
    private CountryRequestDto countryRequestDto;
    private List<City> testCities;
    private CityResponseDto cityResponseDto;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        country = new Country();
        country.setId(TEST_UUID_1);
        country.setName("Germany");
        country.setCountryCode("DEU");

        countryRequestDto = new CountryRequestDto();
        countryRequestDto.setName("Germany");
        countryRequestDto.setCountryCode("DEU");

        cityResponseDto = new CityResponseDto();
        cityResponseDto.setId(TEST_UUID_1.toString());
        cityResponseDto.setName("Berlin");
        cityResponseDto.setCountryCode("DEU");

    }

    @Test
    void testCreate() {
        testCities = Arrays.asList(new City(TEST_UUID_2,"Berlin","DEU"));
        when(cityRepository.findAllByCountryCode(anyString())).thenReturn(testCities);
        when(countryRepository.save(any(Country.class))).thenReturn(country);
        when(cityService.toResponseDTO(any(City.class))).thenReturn(cityResponseDto);


        CountryResponseDto responseDto = countryService.create(countryRequestDto);

        assertNotNull(responseDto);
        assertEquals(country.getId().toString(), responseDto.getId());
        assertEquals(country.getName(), responseDto.getName());
        assertEquals(country.getCountryCode(), responseDto.getCountryCode());

        verify(countryRepository, times(1)).save(any(Country.class));
    }

    @Test
    void testGetById() {
        when(countryRepository.findById(any(UUID.class))).thenReturn(Optional.of(country));

        CountryResponseDto responseDto = countryService.getById(TEST_UUID_1);

        assertNotNull(responseDto);
        assertEquals(country.getId().toString(), responseDto.getId());
        assertEquals(country.getName(), responseDto.getName());
        assertEquals(country.getCountryCode(), responseDto.getCountryCode());

        verify(countryRepository, times(1)).findById(any(UUID.class));
    }

    @Test
    void testGetAll() {
        when(countryRepository.findAll()).thenReturn(Arrays.asList(country));
        List<CountryResponseDto> responseDtos = countryService.getAll();

        assertEquals(country.getId().toString(), responseDtos.get(0).getId());
        assertEquals(country.getName(), responseDtos.get(0).getName());
        assertEquals(country.getCountryCode(), responseDtos.get(0).getCountryCode());

        verify(countryRepository, times(1)).findAll();
    }

    @Test
    void testUpdate() {
        when(countryRepository.findById(any(UUID.class))).thenReturn(Optional.of(country));
        when(countryRepository.save(any(Country.class))).thenReturn(country);

        CountryResponseDto responseDto = countryService.update(TEST_UUID_1, countryRequestDto);

        assertNotNull(responseDto);
        assertEquals(country.getId().toString(), responseDto.getId());
        assertEquals(country.getName(), responseDto.getName());
        assertEquals(country.getCountryCode(), responseDto.getCountryCode());

        verify(countryRepository, times(1)).findById(any(UUID.class));
        verify(countryRepository, times(1)).save(any(Country.class));
    }

    @Test
    void testDelete() {
        doNothing().when(countryRepository).deleteById(any(UUID.class));

        CountryResponseDto responseDto = countryService.delete(TEST_UUID_1);

        assertNotNull(responseDto);
        assertEquals(TEST_UUID_1.toString(), responseDto.getId());

        verify(countryRepository, times(1)).deleteById(any(UUID.class));
    }
}
