package gud.fun.junkdrawer.util.generator.withentity;

import com.neovisionaries.i18n.CountryCode;
import gud.fun.junkdrawer.persistance.model.City;
import gud.fun.junkdrawer.util.generator.JunkDataGenerator;
import gud.fun.junkdrawer.util.generator.JunkGeneratorException;
import gud.fun.junkdrawer.util.reader.CityCsvToEntityFileReader;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Random;
import java.util.function.Supplier;

@Component
public class CityEntityGenerator implements JunkDataGenerator<City, CountryCode> {

    private List<City> cities;

    @PostConstruct
    public void init() {
        this.cities = new CityCsvToEntityFileReader().read();
    }

    @Override
    public City generateRandom() {
        Random random = new Random();
        return cities.get(random.nextInt(cities.size()));
    }

    @Override
    public City generateRandomByCriteria(CountryCode criteria) {
        return cities.stream().filter(
                e -> e.getCountryCode().equalsIgnoreCase(criteria.getAlpha3()))
                .findAny()
                .orElseThrow(() -> new JunkGeneratorException(String.format("There is no city for provided CountryCode: {}",criteria)));
    }

    @Override
    public String generateRandomAsString() {
        return generateRandom().getName();
    }

    @Override
    public String generateRandomAsStringByCriteria(CountryCode criteria) {
        return generateRandomByCriteria(criteria).getName();
    }
}
