package gud.fun.junkdrawer.util.generator;

import com.neovisionaries.i18n.CountryCode;
import gud.fun.junkdrawer.persistance.model.PhoneNumber;
import gud.fun.junkdrawer.util.IsoCountryCodeToCallPrefix;
import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class PhoneNumberGenerator implements JunkDataGenerator<PhoneNumber> {
    Random random = new Random();

    @Override
    public PhoneNumber generateRandom() {

        //Random Country code
        String randomCountryCode = IsoCountryCodeToCallPrefix.getAll().keySet().toArray()[random.nextInt(IsoCountryCodeToCallPrefix.getSize())].toString();
        return generateByCountryCode(CountryCode.getByCode(randomCountryCode));

    }

    @Override
    public String generateRandomAsString() {
        return generateRandom().getPhoneNumber();
    }

    public PhoneNumber generateByCountryCode(CountryCode code){

        StringBuilder phoneNumber = new StringBuilder();

        //Country code
        phoneNumber.append(IsoCountryCodeToCallPrefix.getByCountryCode(code.getAlpha2()));
        phoneNumber.append("-");

        //Identification code
        int identCodeLength = random.nextInt(3) + 1;
        for(int i = 0; i < identCodeLength; i++) phoneNumber.append(random.nextInt(10));
        phoneNumber.append("-");

        //Subscriber number
        for (int i = 0; i < 8; i++) phoneNumber.append(random.nextInt(10));

        return new PhoneNumber(phoneNumber.toString(), code.getAlpha3());
    }

    public String generateByCountryCodeString(CountryCode code) {
        return generateByCountryCode(code).getPhoneNumber();
    }
}
