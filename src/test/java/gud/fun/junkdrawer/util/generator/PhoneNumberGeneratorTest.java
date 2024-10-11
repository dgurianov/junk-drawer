package gud.fun.junkdrawer.util.generator;

import com.neovisionaries.i18n.CountryCode;
import gud.fun.junkdrawer.persistance.model.PhoneNumber;
import gud.fun.junkdrawer.util.IsoCountryCodeToCallPrefix;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PhoneNumberGeneratorTest {

    @Test
    void testGenerateRandom() {
        PhoneNumberGenerator generator = new PhoneNumberGenerator();
        PhoneNumber phoneNumber = generator.generateRandom();

        assertNotNull(phoneNumber);
        assertNotNull(phoneNumber.getPhoneNumber());
        assertNotNull(phoneNumber.getCountryCode());
        assertTrue(phoneNumber.getPhoneNumber().startsWith(IsoCountryCodeToCallPrefix.getByCountryCode(
                CountryCode.getByAlpha3Code(phoneNumber.getCountryCode()).getAlpha2())));
    }

    @Test
    void testGenerateRandomAsString() {
        PhoneNumberGenerator generator = new PhoneNumberGenerator();
        String phoneNumberStr = generator.generateRandomAsString();

        assertNotNull(phoneNumberStr);
        assertFalse(phoneNumberStr.isEmpty());
    }

    @Test
    void testGenerateByCountryCode() {
        PhoneNumberGenerator generator = new PhoneNumberGenerator();
        PhoneNumber phoneNumber = generator.generateRandomByCriteria(CountryCode.US);

        assertNotNull(phoneNumber);
        assertEquals("USA", phoneNumber.getCountryCode());
        assertTrue(phoneNumber.getPhoneNumber().startsWith(IsoCountryCodeToCallPrefix.getByCountryCode("US")));
    }

    @Test
    void testGenerateByCountryCodeString() {
        PhoneNumberGenerator generator = new PhoneNumberGenerator();
        String phoneNumberStr = generator.generateRandomAsStringByCriteria(CountryCode.US);

        assertNotNull(phoneNumberStr);
        assertTrue(phoneNumberStr.startsWith(IsoCountryCodeToCallPrefix.getByCountryCode("US")));
    }
}