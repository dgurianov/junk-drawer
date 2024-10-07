package gud.fun.junkdrawer.util.generator;

import com.neovisionaries.i18n.CountryCode;
import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class CountryGenerator implements JunkDataGenerator<CountryCode>{

    private Random random = new Random();

    @Override
    public CountryCode generateRandom() {

        CountryCode country = null;
        while(country == null){
            country = CountryCode.getByCode(random.nextInt(999));
        }
        return country;
    }

    @Override
    public String generateRandomAsString() {
        return generateRandom().getName();
    }


}
