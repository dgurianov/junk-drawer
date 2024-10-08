package gud.fun.junkdrawer.util.generator.withentity;

import com.neovisionaries.i18n.CountryCode;
import gud.fun.junkdrawer.persistance.model.Country;
import gud.fun.junkdrawer.persistance.repository.CityRepository;
import gud.fun.junkdrawer.util.generator.JunkDataGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class CountryEntityGenerator implements JunkDataGenerator<Country> {

    private Random random = new Random();

    @Autowired
    private CityRepository cityRepository;


    @Override
    public Country generateRandom() {

        CountryCode countryCode = null;
        while(countryCode == null){
            countryCode = CountryCode.getByCode(random.nextInt(999));
        }
        Country countryEntity = new Country(countryCode);
        countryEntity.getCities().addAll(cityRepository.findAllByCountryCode(countryCode.getAlpha3()));

        return countryEntity;
    }

    @Override
    public String generateRandomAsString() {
        return generateRandom().getCountryName();
    }


}
