 package gud.fun.junkdrawer.service.stream;

import gud.fun.junkdrawer.dto.city.CityResponseDto;
import gud.fun.junkdrawer.persistance.model.City;
import gud.fun.junkdrawer.util.generator.CityGenerator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

class CityStreamServiceTest {

    @Mock
    private CityGenerator cityGenerator;

    @InjectMocks
    private CityStreamService cityStreamService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllStream() {
        City city = new City();
        city.setId(UUID.randomUUID());
        city.setName("Test City");
        city.setCountryCode("TC");

        when(cityGenerator.generateRandom()).thenReturn(city);

        List<CityResponseDto> result = cityStreamService.getAllStream(5);

        assertEquals(5, result.size());
        for (CityResponseDto dto : result) {
            assertEquals(city.getId(), dto.getId());
            assertEquals(city.getName(), dto.getName());
            assertEquals(city.getCountryCode(), dto.getCountryCode());
        }
    }

    @Test
    void testGetAllStreamWithLimitZero() {
        List<CityResponseDto> result = cityStreamService.getAllStream(0);
        assertEquals(0, result.size());
    }
}

