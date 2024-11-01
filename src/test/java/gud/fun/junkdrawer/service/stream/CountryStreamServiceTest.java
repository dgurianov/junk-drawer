package gud.fun.junkdrawer.service.stream;

import gud.fun.junkdrawer.dto.country.CountryResponseDto;
import gud.fun.junkdrawer.persistance.model.Country;
import gud.fun.junkdrawer.util.generator.CountryGenerator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

class CountryStreamServiceTest {

    @Mock
    private CountryGenerator countryGenerator;

    @InjectMocks
    private CountryStreamService countryStreamService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllStream() {
        Country country = new Country();
        country.setId(UUID.randomUUID());
        country.setName("Test Country");
        country.setCountryCode("TC");

        when(countryGenerator.generateRandom()).thenReturn(country);

        List<CountryResponseDto> result = countryStreamService.getAllStream(5);

        assertEquals(5, result.size());
        for (CountryResponseDto dto : result) {
            assertEquals(country.getId(), dto.getId());
            assertEquals(country.getName(), dto.getName());
            assertEquals(country.getCountryCode(), dto.getCountryCode());
        }
    }

    @Test
    void testGetAllStreamWithLimitZero() {
        List<CountryResponseDto> result = countryStreamService.getAllStream(0);
        assertEquals(0, result.size());
    }
}