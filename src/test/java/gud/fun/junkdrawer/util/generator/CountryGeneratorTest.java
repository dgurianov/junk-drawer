package gud.fun.junkdrawer.util.generator;

import com.neovisionaries.i18n.CountryCode;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CountryGeneratorTest {

    @Autowired
    private CountryGenerator countryGenerator;

    @BeforeEach
    public void setUp() {
        countryGenerator = new CountryGenerator();
    }

    @Test
    public void testGenerateRandom() {
        String countryName = countryGenerator.generateRandomAsString();
        assertNotNull(countryName, "Generated country name should not be null");
        assertTrue(CountryCode.findByName(countryName).size() > 0, "Generated country name should be valid");
    }
}