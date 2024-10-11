package gud.fun.junkdrawer.util.generator;

import com.neovisionaries.i18n.CountryCode;
import gud.fun.junkdrawer.persistance.model.PhoneNumber;
import gud.fun.junkdrawer.util.IsoCountryCodeToCallPrefix;
import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class PhoneNumberGenerator implements JunkDataGenerator<PhoneNumber,CountryCode> {
    Random random = new Random();

    @Override
    public PhoneNumber generateRandom() {

        //Random Country code
        String randomCountryCode = IsoCountryCodeToCallPrefix.getAll().keySet().toArray()[random.nextInt(IsoCountryCodeToCallPrefix.getSize())].toString();
        return generateRandomByCriteria(CountryCode.getByCode(randomCountryCode));

    }

    @Override
    public PhoneNumber generateRandomByCriteria(CountryCode criteria) {
        StringBuilder phoneNumber = new StringBuilder();

        //Country code
        phoneNumber.append(IsoCountryCodeToCallPrefix.getByCountryCode(criteria.getAlpha2()));
        phoneNumber.append("-");

        //Identification code
        int identCodeLength = random.nextInt(3) + 1;
        for(int i = 0; i < identCodeLength; i++) phoneNumber.append(random.nextInt(10));
        phoneNumber.append("-");

        //Subscriber number
        for (int i = 0; i < 8; i++) phoneNumber.append(random.nextInt(10));

        return new PhoneNumber(phoneNumber.toString(), criteria.getAlpha3());
    }

    @Override
    public String generateRandomAsString() {
        return generateRandom().getPhoneNumber();
    }

    @Override
    public String generateRandomAsStringByCriteria(CountryCode criteria) {
        return generateRandomByCriteria(criteria).getPhoneNumber();
    }
}
