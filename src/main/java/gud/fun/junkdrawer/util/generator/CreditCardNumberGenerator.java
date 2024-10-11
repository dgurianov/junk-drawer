package gud.fun.junkdrawer.util.generator;

import com.neovisionaries.i18n.CountryCode;
import org.springframework.stereotype.Component;

@Component
public class CreditCardNumberGenerator implements JunkDataGenerator<String, CountryCode> {
    @Override
    public String generateRandom() {
        return "";
    }

    @Override
    public String generateRandomByCriteria(CountryCode criteria) {
        return "";
    }

    @Override
    public String generateRandomAsString() {
        return "";
    }

    @Override
    public String generateRandomAsStringByCriteria(CountryCode criteria) {
        return "";
    }
}
