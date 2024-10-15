package gud.fun.junkdrawer.util.generator;

import com.neovisionaries.i18n.CountryCode;
import gud.fun.junkdrawer.persistance.model.Merchant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class MerchantGenerator implements JunkDataGenerator<Merchant, CountryCode> {

    @Autowired
    private AddressGenerator addressGenerator;

    @Autowired
    private NameGenerator nameGenerator;

    private Random random = new Random();

    @Override
    public Merchant generateRandom() {
        Merchant merchant = new Merchant();
        merchant.setName(nameGenerator.generateRandom());
        merchant.setCountry(CountryCode.values()[random.nextInt(CountryCode.values().length)]);
        merchant.setAddress(addressGenerator.generateRandom());
        return merchant;
    }

    @Override
    public Merchant generateRandomByCriteria(CountryCode criteria) {
        Merchant merchant = new Merchant();
        merchant.setName(nameGenerator.generateRandom());
        merchant.setCountry(criteria);
        merchant.setAddress(addressGenerator.generateRandomByCriteria(criteria));
        return merchant;
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
