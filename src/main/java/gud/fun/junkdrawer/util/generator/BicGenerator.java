package gud.fun.junkdrawer.util.generator;

import com.neovisionaries.i18n.CountryCode;
import gud.fun.junkdrawer.persistance.model.Bic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class BicGenerator implements JunkDataGenerator<Bic, CountryCode> {

    @Autowired
    CountryGenerator countryGenerator;

    @Autowired
    NameGenerator nameGenerator;

    @Override
    public Bic generateRandom() {
        //Fabricate Institution code
        Random random = new Random();
        StringBuilder institutionCode = new StringBuilder(4);
        for(int i = 0; i < 4; i++){
            institutionCode.append((char) ('A' + random.nextInt(26)));
        }
        //Select the country
        String countryCode = countryGenerator.generateRandom().getCountryCode();

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

        Bic bic = new Bic();
        bic.setInstitution(nameGenerator.generateRandom());
        bic.setIdentifier(institutionCode.toString() + countryCode + locationCode + branchCode.toString());
        return bic;
    }

    @Override
    public Bic generateRandomByCriteria(CountryCode criteria) {
        //Fabricate Institution code
        Random random = new Random();
        StringBuilder institutionCode = new StringBuilder(4);
        for(int i = 0; i < 4; i++){
            institutionCode.append((char) ('A' + random.nextInt(26)));
        }

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

        Bic bic = new Bic();
        bic.setInstitution(nameGenerator.generateRandom());
        bic.setIdentifier(institutionCode.toString() + criteria + locationCode + branchCode.toString());
        return bic;
    }

    @Override
    public String generateRandomAsString() {
        return generateRandom().toString();
    }

    @Override
    public String generateRandomAsStringByCriteria(CountryCode criteria) {
        return generateRandomByCriteria(criteria).toString();
    }
}
