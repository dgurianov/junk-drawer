package gud.fun.junkdrawer.util.generator.withentity;

import gud.fun.junkdrawer.persistance.model.City;
import gud.fun.junkdrawer.util.generator.JunkDataGenerator;
import gud.fun.junkdrawer.util.reader.CityCsvToEntityFileReader;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Random;

@Component
public class CityEntityGenerator implements JunkDataGenerator<City> {

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
    public String generateRandomAsString() {
        return generateRandom().getName();
    }
}
