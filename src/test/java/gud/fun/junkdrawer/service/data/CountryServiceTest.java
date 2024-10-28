package gud.fun.junkdrawer.service.data;

import gud.fun.junkdrawer.dto.assembler.CountryResponseDtoAssembler;
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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedModel;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

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

    @Mock
    private CountryResponseDtoAssembler countryDtoAssembler;

    @Mock
    private PagedResourcesAssembler<Country> pagedResourcesAssembler;

    private Country country;
    private CountryRequestDto countryRequestDto;
    private CountryResponseDto countryResponsetDto;
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
        countryRequestDto.setId(TEST_UUID_1);
        countryRequestDto.setName("Germany");
        countryRequestDto.setCountryCode("DEU");

        countryResponsetDto = new CountryResponseDto();
        countryResponsetDto.setId(TEST_UUID_1);
        countryResponsetDto.setName("Germany");
        countryResponsetDto.setCountryCode("DEU");

        cityResponseDto = new CityResponseDto();
        cityResponseDto.setId(TEST_UUID_1);
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
        assertEquals(country.getId(), responseDto.getId());
        assertEquals(country.getName(), responseDto.getName());
        assertEquals(country.getCountryCode(), responseDto.getCountryCode());

        verify(countryRepository, times(1)).save(any(Country.class));
    }

    @Test
    void testGetById() {
        when(countryRepository.findById(any(UUID.class))).thenReturn(Optional.of(country));

        CountryResponseDto responseDto = countryService.getById(TEST_UUID_1);

        assertNotNull(responseDto);
        assertEquals(country.getId(), responseDto.getId());
        assertEquals(country.getName(), responseDto.getName());
        assertEquals(country.getCountryCode(), responseDto.getCountryCode());

        verify(countryRepository, times(1)).findById(any(UUID.class));
    }

    @Test
    void testGetAll() {
        when(countryRepository.findAll(any(Pageable.class))).thenReturn(new PageImpl<>(Arrays.asList(country)));
        when(pagedResourcesAssembler.toModel(any(Page.class), any(CountryResponseDtoAssembler.class))).thenReturn(PagedModel.of(List.of(countryResponsetDto), new PagedModel.PageMetadata(1, 1, 1, 1)));
        PagedModel<CountryResponseDto> responseDtos = countryService.getAll(PageRequest.of(0,1));
        CountryResponseDto responseDto = responseDtos.getContent().iterator().next();
        assertEquals(country.getId(), responseDto.getId());
        assertEquals(country.getName(), responseDto.getName());
        assertEquals(country.getCountryCode(), responseDto.getCountryCode());

        verify(countryRepository, times(1)).findAll(any(Pageable.class));
    }

    @Test
    void testUpdate() {
        when(countryRepository.findById(any(UUID.class))).thenReturn(Optional.of(country));
        when(countryRepository.save(any(Country.class))).thenReturn(country);

        CountryResponseDto responseDto = countryService.update(countryRequestDto);

        assertNotNull(responseDto);
        assertEquals(country.getId(), responseDto.getId());
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
        assertEquals(TEST_UUID_1, responseDto.getId());

        verify(countryRepository, times(1)).deleteById(any(UUID.class));
    }
}
