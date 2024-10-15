package gud.fun.junkdrawer.util.generator;

import com.neovisionaries.i18n.CountryCode;
import gud.fun.junkdrawer.persistance.model.CreditCard;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Random;

/*
Card issuer	       | First 6 digits	| Next 5 digits	     | Last 6 digits
Mastercard	       | 5-	            |2, 3, 2 – 4, 5, 6	| Customer account number
Visa	          |4-	            |2 – 6	            |Customer account number
American Express  |3-	            |3, 4	            |Customer account number


That is why, first number for this generator is 9 to not to intersect with any of the above.
 */
@Component
public class CreditCardGenerator implements JunkDataGenerator<CreditCard, CountryCode> {

    private Random random = new Random();

    @Autowired
    private BicGenerator bicGenerator;

    @Autowired
    private NameGenerator nameGenerator;

    @Override
    public CreditCard generateRandom() {
        CreditCard creditCard = new CreditCard();
        creditCard.setCcn("9" + random.nextInt(10000,99999) + random.nextInt(10000,99999) + random.nextInt(100000,999999));
        creditCard.setIssuer(nameGenerator.generateRandom());
        creditCard.setBic(bicGenerator.generateRandom());
        return creditCard;
    }

    @Override
    public CreditCard generateRandomByCriteria(CountryCode criteria){
        CreditCard creditCard = new CreditCard();
        creditCard.setCcn("9" + random.nextInt(10000,99999) + random.nextInt(10000,99999) + random.nextInt(100000,999999));
        creditCard.setIssuer(nameGenerator.generateRandom());
        creditCard.setBic(bicGenerator.generateRandomByCriteria(criteria));
        return creditCard;
    }

    @Override
    public String generateRandomAsString() {
        return generateRandomAsString();
    }

    @Override
    public String generateRandomAsStringByCriteria(CountryCode criteria) {
        return generateRandomAsStringByCriteria(criteria);
    }
}
