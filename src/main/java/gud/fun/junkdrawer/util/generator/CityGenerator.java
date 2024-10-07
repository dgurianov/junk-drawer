package gud.fun.junkdrawer.util.generator;

import gud.fun.junkdrawer.persistance.model.City;
import gud.fun.junkdrawer.util.reader.CityCsvFileReader;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Random;

@Component
public class CityGenerator implements JunkDataGenerator<City> {

    private List<City> cities;

    @PostConstruct
    public void init() {
        this.cities = new CityCsvFileReader().read();
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
