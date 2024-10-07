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
        assertTrue(phoneNumber.getPhoneNumber().startsWith(IsoCountryCodeToCallPrefix.getByCountryCode(phoneNumber.getCountryCode())));
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
        PhoneNumber phoneNumber = generator.generateByCountryCode(CountryCode.US);

        assertNotNull(phoneNumber);
        assertEquals("US", phoneNumber.getCountryCode());
        assertTrue(phoneNumber.getPhoneNumber().startsWith(IsoCountryCodeToCallPrefix.getByCountryCode("US")));
    }

    @Test
    void testGenerateByCountryCodeString() {
        PhoneNumberGenerator generator = new PhoneNumberGenerator();
        String phoneNumberStr = generator.generateByCountryCodeString(CountryCode.US);

        assertNotNull(phoneNumberStr);
        assertTrue(phoneNumberStr.startsWith(IsoCountryCodeToCallPrefix.getByCountryCode("US")));
    }
}