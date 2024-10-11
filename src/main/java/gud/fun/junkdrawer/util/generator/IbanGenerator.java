package gud.fun.junkdrawer.util.generator;

import com.neovisionaries.i18n.CountryCode;
import org.iban4j.Iban;
import org.springframework.stereotype.Component;

@Component
public class IbanGenerator implements JunkDataGenerator<Iban, CountryCode> {
    @Override
    public Iban generateRandom() {
        return Iban.random();
    }

    @Override
    public Iban generateRandomByCriteria(CountryCode criteria) {
        return Iban.random(org.iban4j.CountryCode.getByCode(criteria.getAlpha3()));
    }

    @Override
    public String generateRandomAsString() {
        return generateRandom().toFormattedString();
    }

    @Override
    public String generateRandomAsStringByCriteria(CountryCode criteria) {
        return generateRandomByCriteria(criteria).toFormattedString();
    }
}
