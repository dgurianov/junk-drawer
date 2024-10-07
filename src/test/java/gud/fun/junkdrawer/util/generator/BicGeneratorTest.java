package gud.fun.junkdrawer.util.generator;

import com.neovisionaries.i18n.CountryCode;
import gud.fun.junkdrawer.persistance.model.Country;
import gud.fun.junkdrawer.util.generator.withentity.CountryEntityGenerator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

public class BicGeneratorTest {

    @Mock
    private CountryEntityGenerator countryEntityGenerator;

    @InjectMocks
    private BicGenerator bicGenerator;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        when(countryEntityGenerator.generateRandom()).thenReturn(new Country(CountryCode.getByCode("US")));
    }

    @Test
    public void testGenerateRandom() {
        String bic = bicGenerator.generateRandom();
        assertNotNull(bic, "Generated BIC should not be null");
        assertTrue(bic.matches("[A-Z]{4}[A-Z]{2}[A-Z0-9]{2}[A-Z0-9]{3}"), "Generated BIC should match the expected pattern");
    }
}