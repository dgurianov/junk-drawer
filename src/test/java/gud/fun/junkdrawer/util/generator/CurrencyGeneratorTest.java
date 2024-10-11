package gud.fun.junkdrawer.util.generator;

import com.neovisionaries.i18n.CountryCode;
import com.neovisionaries.i18n.CurrencyCode;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CurrencyGeneratorTest {

    private CurrencyGenerator currencyGenerator;

    @BeforeEach
    void setUp() {
        currencyGenerator = new CurrencyGenerator();
    }

    @Test
    void testGenerateRandom() {
        CurrencyCode currencyCode = currencyGenerator.generateRandom();
        assertNotNull(currencyCode);
        assertTrue(currencyCode.getNumeric() > 0);
    }

    @Test
    void testGenerateRandomByCriteria() {
        CountryCode countryCode = CountryCode.getByCode("DE"); // Germany
        CurrencyCode currencyCode = currencyGenerator.generateRandomByCriteria(countryCode);
        assertNotNull(currencyCode);
        assertEquals("EUR", currencyCode.getCurrency().getCurrencyCode());
    }

    @Test
    void testGenerateRandomAsString() {
        String currencyName = currencyGenerator.generateRandomAsString();
        assertNotNull(currencyName);
        assertTrue(currencyName.length() > 0);
    }

    @Test
    void testGenerateRandomAsStringByCriteria() {
        CountryCode countryCode = CountryCode.getByCode("DE"); // Germany
        String currencyName = currencyGenerator.generateRandomAsStringByCriteria(countryCode);
        assertNotNull(currencyName);
        assertEquals("Euro", currencyName);
    }
}

