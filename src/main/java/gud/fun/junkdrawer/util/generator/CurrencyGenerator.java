package gud.fun.junkdrawer.util.generator;

import com.neovisionaries.i18n.CountryCode;
import com.neovisionaries.i18n.CurrencyCode;
import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class CurrencyGenerator implements JunkDataGenerator<CurrencyCode, CountryCode>{
    @Override
    public CurrencyCode generateRandom() {
        Random  random = new Random();
        CurrencyCode cc = null;
        while(cc == null){
            cc = CurrencyCode.getByCode(random.nextInt(999));
        }
        return cc;
    }

    @Override
    public CurrencyCode generateRandomByCriteria(CountryCode criteria) {
        return null;
    }

    @Override
    public String generateRandomAsString() {
        return generateRandom().getName();
    }

    @Override
    public String generateRandomAsStringByCriteria(CountryCode criteria) {
        return "";
    }
}
