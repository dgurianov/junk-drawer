package gud.fun.junkdrawer.util.generator;

import com.neovisionaries.i18n.CountryCode;
import gud.fun.junkdrawer.util.generator.withentity.CountryEntityGenerator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CountryEntityGeneratorTest {

    @Autowired
    private CountryEntityGenerator countryEntityGenerator;

    @BeforeEach
    public void setUp() {
        countryEntityGenerator = new CountryEntityGenerator();
    }

    @Test
    public void testGenerateRandom() {
        String countryName = countryEntityGenerator.generateRandomAsString();
        assertNotNull(countryName, "Generated country name should not be null");
        assertTrue(CountryCode.findByName(countryName).size() > 0, "Generated country name should be valid");
    }
}