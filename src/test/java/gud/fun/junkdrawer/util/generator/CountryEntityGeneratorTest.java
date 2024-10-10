package gud.fun.junkdrawer.util.generator;

import com.neovisionaries.i18n.CountryCode;
import gud.fun.junkdrawer.persistance.model.City;
import gud.fun.junkdrawer.persistance.repository.CityRepository;
import gud.fun.junkdrawer.util.generator.withentity.CountryEntityGenerator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.Arrays;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CountryEntityGeneratorTest {

    private final UUID TEST_UUID = UUID.fromString("e6c96a16-51b4-4ac7-bbe7-86e1a1f4da21");

    private CountryEntityGenerator countryEntityGenerator;


    @BeforeEach
    public void setUp() {
        countryEntityGenerator = new CountryEntityGenerator();
    }

    @Test
    public void testGenerateRandom() {
        CityRepository repositoryMock = Mockito.mock(CityRepository.class);
        Mockito.when(repositoryMock.findAllByCountryCode(ArgumentMatchers.anyString())).thenReturn(Arrays.asList(new City(TEST_UUID,"Berlin","DE")));
        ReflectionTestUtils.setField(countryEntityGenerator, "cityRepository", repositoryMock);

        String countryName = countryEntityGenerator.generateRandomAsString();
        assertNotNull(countryName, "Generated country name should not be null");
        assertTrue(CountryCode.findByName(countryName).size() > 0, "Generated country name should be valid");
    }
}