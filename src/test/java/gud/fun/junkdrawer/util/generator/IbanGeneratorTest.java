package gud.fun.junkdrawer.util.generator;

import com.neovisionaries.i18n.CountryCode;
import org.iban4j.Iban;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class IbanGeneratorTest {

    private IbanGenerator ibanGenerator;

    @BeforeEach
    void setUp() {
        ibanGenerator = new IbanGenerator();
    }

    @Test
    void testGenerateRandom() {
        Iban iban = ibanGenerator.generateRandom();
        assertNotNull(iban);
        assertTrue(iban.toString().length() > 0);
    }

    @Test
    void testGenerateRandomByCriteria() {
        CountryCode countryCode = CountryCode.DE; // Germany
        Iban iban = ibanGenerator.generateRandomByCriteria(countryCode);
        assertNotNull(iban);
        assertEquals("DE", iban.getCountryCode().name());
    }

    @Test
    void testGenerateRandomAsString() {
        String ibanString = ibanGenerator.generateRandomAsString();
        assertNotNull(ibanString);
        assertTrue(ibanString.length() > 0);
    }

    @Test
    void testGenerateRandomAsStringByCriteria() {
        CountryCode countryCode = CountryCode.DE; // Germany
        String ibanString = ibanGenerator.generateRandomAsStringByCriteria(countryCode);
        assertNotNull(ibanString);
        assertTrue(ibanString.startsWith("DE"));
    }

}
