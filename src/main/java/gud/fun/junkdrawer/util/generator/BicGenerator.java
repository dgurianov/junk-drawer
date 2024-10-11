package gud.fun.junkdrawer.util.generator;

import com.neovisionaries.i18n.CountryCode;
import gud.fun.junkdrawer.util.generator.withentity.CountryEntityGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class BicGenerator implements JunkDataGenerator<String, CountryCode> {

    @Autowired
    CountryEntityGenerator countryEntityGenerator;

    @Override
    public String generateRandom() {
        //Fabricate Institution code
        Random random = new Random();
        StringBuilder institutionCode = new StringBuilder(4);
        for(int i = 0; i < 4; i++){
            institutionCode.append((char) ('A' + random.nextInt(26)));
        }
        //Select the country
        String countryCode = countryEntityGenerator.generateRandom().getCountryCode();

        /*Fabricate the location code
        ISO 9362:2019
        2 letters or digits: location code
            if the second character is "0", then it is typically a test BIC as opposed to a BIC used on the live network.
            if the second character is "1", then it denotes a passive participant in the SWIFT network
            if the second character is "2", then it typically indicates a reverse billing BIC, where the recipient pays for the message as opposed to the more usual mode whereby the sender pays for the message.
        */
        String locationCode = "A0";

        //Fabricate the branch code
        StringBuilder branchCode = new StringBuilder(3);
        for(int i = 0; i < 3; i++){
            branchCode.append((char) ('A' + random.nextInt(26)));
        }

        return institutionCode.toString() + countryCode + locationCode + branchCode.toString();
    }

    @Override
    public String generateRandomByCriteria(CountryCode criteria) {
        return "";
    }

    @Override
    public String generateRandomAsString() {
        return generateRandom();
    }

    @Override
    public String generateRandomAsStringByCriteria(CountryCode criteria) {
        return "";
    }
}
