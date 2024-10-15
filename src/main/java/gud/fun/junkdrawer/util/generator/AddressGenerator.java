package gud.fun.junkdrawer.util.generator;

import com.neovisionaries.i18n.CountryCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class AddressGenerator implements JunkDataGenerator<String, CountryCode> {

    private Random random = new Random();

    @Autowired
    private CityGenerator cityGenerator;

    @Autowired
    private StreetGenerator streetGenerator;

    @Override
    public String generateRandom() {
        StringBuilder address = new StringBuilder();
        address.append(random.nextInt(1000,9999));
        address.append(" ");
        address.append(cityGenerator.generateRandom());
        address.append(" ");
        address.append(streetGenerator.generateRandom());
        address.append(" ");
        address.append(random.nextInt(1,100));
        address.append("abcdef".charAt(random.nextInt(6)));


        return address.toString();
    }

    @Override
    public String generateRandomByCriteria(CountryCode criteria) {
        StringBuilder address = new StringBuilder();
        address.append(random.nextInt(1000,9999));
        address.append(" ");
        address.append(cityGenerator.generateRandomAsStringByCriteria(criteria));
        address.append(" ");
        address.append(streetGenerator.generateRandomAsString());
        address.append(" ");
        address.append(random.nextInt(1,100));
        address.append("abcdef".charAt(random.nextInt(6)));

        return address.toString();
    }

    @Override
    public String generateRandomAsString() {
        return generateRandom();
    }

    @Override
    public String generateRandomAsStringByCriteria(CountryCode criteria) {
        return generateRandomByCriteria(criteria);
    }
}
